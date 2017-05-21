package collectors;

import DTOs.scrape_dtos.WSCCEventDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.ws.rs.WebApplicationException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WsccCollector {

    public Set<WSCCEventDTO> getWsccEvents() {
        try {
            Document document = Jsoup.connect("http://www.wscc.com/upcoming-events").get();
            Elements listOfEvents = document.select("div.view-content").select("h3");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("M/dd");
            final String date = simpleDateFormat.format(new Date());

            Optional<Element> first = listOfEvents.stream()
//                .filter(s -> s.childNode(0).toString().equals(simpleDateFormat.format(new Date()).toString()))
                    .filter(s -> s.childNode(0).toString().equals(date))
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
        } catch (Exception e) {
            throw new WebApplicationException(e);
        }
    }

    private LocalDate dateFormatter(String rawInput) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String trim = rawInput.replaceAll("(?<=\\d)(st|nd|rd|th)", "").trim() + " " + Calendar.getInstance().get(Calendar.YEAR);
        return LocalDate.parse(trim, formatter);
    }

    private Function<Element, WSCCEventDTO> elementWSCCEventDTOFunction = element -> {
        LocalDate startDate;
        LocalDate endDate = null;
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

}
