package manager;

import collectors.GameTonightCollector;
import collectors.ShowboxCollector;
import collectors.WsccCollector;
import collectors.WsdotCollector;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import model.Event;

public class EventManager {

    private final GameTonightCollector gameTonightCollector;
    private final ShowboxCollector showboxCollector;
    private final WsdotCollector wsdotCollector;
    private final WsccCollector wsccCollector;

    public EventManager(GameTonightCollector gameTonightCollector, ShowboxCollector showboxCollector, WsdotCollector wsdotCollector, WsccCollector wsccCollector) {
        this.gameTonightCollector = gameTonightCollector;
        this.showboxCollector = showboxCollector;
        this.wsdotCollector = wsdotCollector;
        this.wsccCollector = wsccCollector;
    }

    public List<Event> getEvents(String city) {
        final ArrayList<Event> events = new ArrayList<>();
        events.addAll(gameTonightCollector.getFromGameTonight(city));
        events.addAll(showboxCollector.getShowBoxEvents().stream().map(current -> new Event(current.getBand(), current.getVenue(), current.getEventTime().toLocalDate(), current.getEventTime().toLocalTime(), null)).collect(Collectors.toList()));
        events.addAll(wsdotCollector.getWsdotEvents().stream().map(current -> new Event("", current.getDescription(), current.getLastUpdated().toLocalDate(), current.getLastUpdated().toLocalTime(), null)).collect(Collectors.toList()));
        events.addAll(wsccCollector.getWsccEvents().stream().map(current -> new Event(current.getEventName(), "" + current.getEstAttendees(), current.getStartDate(), null, null)).collect(Collectors.toList()));
        return events;
    }

}
