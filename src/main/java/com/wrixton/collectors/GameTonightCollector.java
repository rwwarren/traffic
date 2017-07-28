package com.wrixton.collectors;

import com.wrixton.dtos.GameTonightDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.ResponseProcessingException;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import com.wrixton.model.Event;
import com.wrixton.model.Location;

public class GameTonightCollector {

    private static final GenericType<GameTonightDTO> GAME_TONIGHT = new GenericType<GameTonightDTO>() {
    };

    private final Client client;

    public GameTonightCollector(Client client) {
        this.client = client;
    }

    public List<Event> getFromGameTonight(String city) {
        final WebTarget target = client.target(String.format("http://gametonight.in/%s/json", city));
        final Invocation.Builder request = target.request();
        try {
            final GameTonightDTO gameTonightDTO = request.get(GAME_TONIGHT);
            if (gameTonightDTO.isGame_tonight()) {
                return gameTonightDTO.getToday().values().stream().map(current -> new Event(current.getKey(), current.getTitle(), current.getDate().toLocalDate(), current.getDate().toLocalTime(), new Location("",1.1, 1.1))).collect(Collectors.toList());
            }
        } catch (ResponseProcessingException e) {
            //No game tonight
            System.out.println(e);
        }
        return new ArrayList<>();
    }

}
