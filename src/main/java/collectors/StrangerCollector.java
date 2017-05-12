package collectors;

import DTOs.StrangerEventDTO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Collections;
import java.util.Set;

public class StrangerCollector {
    public Set<StrangerEventDTO> getStrangerEvents() {
        try {
            Document document = Jsoup.connect("http://www.showboxpresents.com/events/all").get();
            Elements eventsList = document.getElementsByClass("calendar-post-row");

            return Collections.EMPTY_SET;
        } catch (IOException e) {
            return Collections.EMPTY_SET;
        }
    }
}
