package com.wrixton.manager;

import com.wrixton.collectors.GameTonightCollector;
import com.wrixton.collectors.ShowboxCollector;
import com.wrixton.collectors.WsccCollector;
import com.wrixton.collectors.WsdotCollector;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.wrixton.model.Event;

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
        final List<Event> events = new ArrayList<>();
        events.addAll(gameTonightCollector.getFromGameTonight(city));
        events.addAll(showboxCollector.getShowBoxEvents(true).stream().map(current -> new Event(current.getBand(), current.getVenue(), current.getEventTime().toLocalDate(), current.getEventTime().toLocalTime(), null)).collect(Collectors.toList()));
        events.addAll(wsdotCollector.getWsdotEvents().stream().map(current -> new Event("", current.getDescription(), current.getLastUpdated().toLocalDate(), current.getLastUpdated().toLocalTime(), null)).collect(Collectors.toList()));
        events.addAll(wsccCollector.getWsccEvents().stream().map(current -> new Event(current.getEventName(), "" + current.getEstAttendees(), current.getStartDate(), null, null)).collect(Collectors.toList()));
        return events.stream().filter(current -> current.getTime().isAfter(LocalTime.now())).collect(Collectors.toList());
    }

}
