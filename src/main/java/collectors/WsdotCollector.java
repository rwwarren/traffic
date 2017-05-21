package collectors;

import DTOs.scrape_dtos.WsdotDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.ws.rs.WebApplicationException;

public class WsdotCollector {

    public Set<WsdotDTO> getWsdotEvents() {
        final LocalDate today = LocalDate.now();
        try {
            Document document = Jsoup.connect("http://www.wsdot.com/traffic/trafficalerts/pugetsound.aspx").get();
            Element situationDiv = document.getElementsByClass("situationDiv").first();
            Elements traveAlerts = situationDiv.getElementsByClass("sitImpact");
            return traveAlerts.stream()
                    .map(travelAlert -> {
                        Element description = travelAlert.nextElementSibling().getElementsByClass("sitText").first();
                        String text = description.select("span.bold").remove().first().text();
                        description.select("a[href!=(http|https)://pugetsound.aspx?]").remove();
                        String[] split = description.html().replaceAll("\n", "").split("<br>");
                        String lastUpdatedRaw = Arrays.stream(split).filter(s -> s.contains("Last Updated: ")).map(s -> s.replaceAll("Last Updated: ", "").replaceAll("\n", "").trim()).findFirst().get();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/y h:m a");
                        LocalDateTime lastUpdatedFormatted = LocalDateTime.parse(lastUpdatedRaw, formatter);
                        String formattedLocation = split[split.length - 1].replaceAll("&nbsp;", " ").trim();
                        String formattedDescription = text + " " + Arrays.stream(split).limit(split.length - 2).collect(Collectors.joining(" ")).trim();
                        return new WsdotDTO(travelAlert.text(), formattedDescription, lastUpdatedFormatted, formattedLocation);
                    })
                    .filter(current -> current.getLastUpdated().toLocalDate().equals(today))
                    .collect(Collectors.toSet());
        } catch (Exception e) {
            throw new WebApplicationException(e);
        }
    }
}
