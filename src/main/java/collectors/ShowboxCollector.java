package collectors;

import DTOs.scrape_dtos.ShowBoxEventDTO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.ws.rs.WebApplicationException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.stream.Collectors;

public class ShowboxCollector {

    public Set<ShowBoxEventDTO> getShowBoxEvents(Boolean justToday) {
        try {
            Document document = Jsoup.connect("http://www.showboxpresents.com/events/all").get();
            Elements eventsList = document.getElementById("eventsList").getElementsByClass("entry");
            if (justToday != null && justToday) {
                final LocalDate today = LocalDate.now();
                return getEvents(eventsList).parallelStream().filter(current -> current.getEventTime().toLocalDate().equals(today)).collect(Collectors.toSet());
            } else {
                return getEvents(eventsList);
            }
        } catch (Exception e) {
            throw new WebApplicationException(e);
        }
    }

    private Set<ShowBoxEventDTO> getEvents(Elements eventsList) {
        final String state = "WA";
        return eventsList.stream()
                .filter(element -> element.attr("data-state").equals(state))
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
    }
}
