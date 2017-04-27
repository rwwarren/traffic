package resources;

import DTOs.ShowBoxEventDTO;
import DTOs.WSCCEventDTO;
import DTOs.WsdotDTO;
import collectors.ShowboxCollector;
import collectors.WsccCollector;
import collectors.WsdotCollector;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.FileReader;
import java.util.Set;

@Path("/what")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class What {
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

    @GET
    @Path("/porch-latlong")
    public LatLng getPorchLatLong() throws Exception {
        String apiKey = getApiKey();
        GeoApiContext context = new GeoApiContext().setApiKey(apiKey);
        GeocodingResult[] await = GeocodingApi.geocode(context, "2200 1st Ave South Seattle WA 98134").await();
        return await[0].geometry.location;
    }

    @Deprecated
//    TODO: This should probably be in the YAML not a json file
    private String getApiKey() throws Exception {
        JSONParser parser = new JSONParser();
        try {
            Object parse = parser.parse(new FileReader("src/main/resources/config/creds.json"));
            JSONObject jsonObject = (JSONObject) parse;
            String passkey = (String) jsonObject.get("google_key");
            return passkey;
        } catch (Exception e) {
            throw new Exception("Where's your API Key broh");
        }
    }
}
