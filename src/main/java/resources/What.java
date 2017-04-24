package resources;

import DTOs.ShowBoxEventDTO;
import DTOs.WSCCEventDTO;
import org.joda.time.DateTime;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Path("/what")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class What {

    public What() {
    }

    @GET
    @Path("/wscc")
    public Set<WSCCEventDTO> getWsccEvents() throws Exception {
//        http://www.wscc.com/upcoming-events
//        http://www.wsdot.com/traffic/trafficalerts/
//        http://www.seattle.gov/event-calendar
//        http://www.showboxpresents.com/events/all
        Document document = Jsoup.connect("http://www.wscc.com/upcoming-events").get();
        Elements listOfEvents = document.select("div.view-content").select("h3");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("M/dd");

        Optional<Element> first = listOfEvents.stream()
//                .filter(s -> s.childNode(0).toString().equals(simpleDateFormat.format(new Date()).toString()))
                .filter(s -> s.childNode(0).toString().equals("4/25"))
                .map(element -> element.nextElementSibling())
                .findFirst();
        if (first.isPresent()) {
            Elements li = first.get().select("li");
            Set<WSCCEventDTO> wsccEvents = li.stream()
                    .map(elementWSCCEventDTOFunction)
                    .collect(Collectors.toSet());
            return wsccEvents;
        } else {
            return Collections.emptySet();
        }
    }

    private Date dateFormatter(String rawInput) throws ParseException {
        SimpleDateFormat rangeFormatter = new SimpleDateFormat("MMM d");
        return rangeFormatter.parse(rawInput.replaceAll("(?<=\\d)(st|nd|rd|th)", "").trim());
    }

    private Function<Element, WSCCEventDTO> elementWSCCEventDTOFunction = element -> {
        Date startDate;
        Date endDate = null;
        try {
            String dateRangeRaw = element.select("div.views-field-field-event-start-date-2").select("div.field-content").text();
            if (dateRangeRaw.contains("-")) {
                String[] split = dateRangeRaw.split("-");
                startDate = dateFormatter(split[0]);
                endDate = dateFormatter(split[0]);
            } else {
                startDate = dateFormatter(dateRangeRaw);
            }
        } catch (ParseException e) {
            throw new WebApplicationException(e.getCause());
        }
        String est = element.select("div.views-field-field-event-attendance").select("span.field-content").text().replaceAll(",", "");
        return new WSCCEventDTO(
                element.select("div.views-field-field-event-description").select("div.field-content").text(),
                startDate,
                endDate,
                est.isEmpty() ? 0 : Integer.parseInt(est));
    };

    @GET
    @Path("/showbox")
    public Set<ShowBoxEventDTO> getShowBoxEvents() throws Exception {
        Document document = Jsoup.connect("http://www.showboxpresents.com/events/all").get();
        Elements eventsList = document.getElementById("eventsList").getElementsByClass("entry");
        Set<ShowBoxEventDTO> showboxEvents = eventsList.stream()
                .filter(element -> element.attr("data-state").equals("WA"))
                .map(element -> {
                    Element infoElement = element.getElementsByClass("info").first();
                    String bandName = infoElement.getElementsByClass("title").first().text();
                    Element dateTime = infoElement.getElementsByClass("date-time-container").first();
                    String date = dateTime.select("span.date").first().text();
                    String time = dateTime.select("span.time").first().text().replaceAll("Show", "").trim();
                    String venue = dateTime.select("span.venue").first().text();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, MMM d, y h:m a");
                    LocalDateTime formattedDate = LocalDateTime.parse(date + " " + time, formatter);
                    return new ShowBoxEventDTO(formattedDate, venue, bandName);
                })
                .collect(Collectors.toSet());
        return showboxEvents;
    }

}
