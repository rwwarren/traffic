package collectors;

import DTOs.scrape_dtos.StrangerEventDTO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.ws.rs.WebApplicationException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.*;
import java.util.stream.Collectors;

public class StrangerCollector {
    public Set<StrangerEventDTO> getStrangerEvents() {
        try {
            Document document = Jsoup.connect("http://www.thestranger.com/events/").get();
            Elements eventsList = document.getElementsByClass("calendar-post row");
            Set<StrangerEventDTO> strangerEvents = eventsList.stream().map(s -> mapper(s)).collect(Collectors.toSet());
            return strangerEvents;
        } catch (Exception e) {
            throw new WebApplicationException(e);
        }
    }

    private StrangerEventDTO mapper(Element entry) {
        String title = entry.getElementsByClass("calendar-post-title").text();
        String category = entry.getElementsByClass("label calendar-category").text();
        String postDate = entry.getElementsByClass("calendar-post-date").text();
        String venue = entry.getElementsByClass("calendar-post-venue").text();
        LocalDate eventDate = getLocalDate(postDate);
        Map<String, LocalTime> startAndEnd = getStartAndEnd(postDate);
        return new StrangerEventDTO(title, category, venue, eventDate, startAndEnd.get("start"), startAndEnd.get("end"));
    }

    private Map<String, LocalTime> getStartAndEnd(String postDate) {
        String timeRange = postDate.replaceAll("(.*)(at|,)", "").trim().replaceAll("noon", "12 pm");
        Map<String, LocalTime> toReturn = new HashMap<>();
        LocalTime startTime;
        LocalTime endTime = null;
        if (timeRange.contains("-")) {
            String[] split = timeRange.split("-");
            DateTimeFormatter rangeFormatter = new DateTimeFormatterBuilder()
                    .parseCaseInsensitive()
                    .appendPattern("h[:m][ a]")
                    .toFormatter(Locale.US);

            String[] split1 = split[1].split(" ");
            String revised;
            if (split[0].contains("am") || split[0].contains("pm")) {
                revised = split[0];
            } else {
                revised = split[0] + " " + split1[1];
            }
            startTime = LocalTime.parse(revised, rangeFormatter);
            endTime = LocalTime.parse(split[1], rangeFormatter);
        } else {
            DateTimeFormatter rangeFormatter = new DateTimeFormatterBuilder()
                    .parseCaseInsensitive()
                    .appendPattern("h[:m] a")
                    .toFormatter(Locale.US);
            startTime = LocalTime.parse(timeRange, rangeFormatter);
        }

        toReturn.put("start", startTime);
        toReturn.put("end", endTime);

        return toReturn;
    }

    private LocalDate getLocalDate(String postDate) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendPattern("E MMMM d yyyy")
                .toFormatter(Locale.US);
        String formatted = postDate.replaceAll("(at|,).*", "").trim() + " " + Calendar.getInstance().get(Calendar.YEAR);
        return LocalDate.parse(formatted, formatter);
    }
}
