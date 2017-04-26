package resources;

import DTOs.ShowBoxEventDTO;
import DTOs.WSCCEventDTO;
import DTOs.WsdotDTO;
import collectors.ShowboxCollector;
import collectors.WsccCollector;
import collectors.WsdotCollector;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Path("/what")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class What {

//        http://www.wscc.com/upcoming-events
//        http://www.wsdot.com/traffic/trafficalerts/PugetSound.aspx
//        http://www.seattle.gov/event-calendar
//        http://www.showboxpresents.com/events/all

    private final ShowboxCollector showboxCollector;
    private final WsdotCollector wsdotCollector;
    private final WsccCollector wsccCollector;

    public What(ShowboxCollector showboxCollector, WsdotCollector wsdotCollector, WsccCollector wsccCollector) {
        this.showboxCollector = showboxCollector;
        this.wsdotCollector = wsdotCollector;
        this.wsccCollector = wsccCollector;
    }

    @GET
    @Path("/wscc")
    public Set<WSCCEventDTO> getWsccEvents() throws Exception {
        return wsccCollector.getWsccEvents();
    }

    @GET
    @Path("/showbox")
    public Set<ShowBoxEventDTO> getShowBoxEvents() throws Exception {
        return showboxCollector.getShowBoxEvents();
    }

    @GET
    @Path("/wsdot")
    public Set<WsdotDTO> getWsdotAlerts() throws Exception {
       return wsdotCollector.getWsdotEvents();
    }
}
