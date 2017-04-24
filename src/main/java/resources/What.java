package resources;

import org.joda.time.DateTime;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/what")
@Produces(MediaType.APPLICATION_JSON)
public class What {

    public What() {
    }

    @GET
    @Path("/events")
    public String whatEvent() throws Exception {
//        http://www.wscc.com/upcoming-events
//        http://www.wsdot.com/traffic/trafficalerts/
//        http://www.seattle.gov/event-calendar
//        http://www.showboxpresents.com/events/all
        Document document = Jsoup.connect("http://www.wscc.com/upcoming-events").get();
        DateTime now = DateTime.now();
        Elements h3 = document.select("h3");
        for (Element element: h3) {
            if (element.childNode(0).toString() == "4/19") {
                return h3.html();
            }
        }
        return h3.html();
    }
}
