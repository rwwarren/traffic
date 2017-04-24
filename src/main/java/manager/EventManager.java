package manager;

import DTOs.GameTonightDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.ResponseProcessingException;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import model.Event;
import model.Location;

public class EventManager {

    private final Client client;

    private static final GenericType<GameTonightDTO> GAME_TONIGHT = new GenericType<GameTonightDTO>() {
    };

    public EventManager(Client client) {
        this.client = client;
    }

    public List<Event> getEvents(String city) {

        final ArrayList<Event> events = new ArrayList<>();
        final WebTarget target = client.target(String.format("http://gametonight.in/%s/json", city));
        final Invocation.Builder request = target.request();
        try{
            final GameTonightDTO gameTonightDTO = request.get(GAME_TONIGHT);
            if (gameTonightDTO.isGame_tonight()) {
                final List<Event> gameTonightEvents = gameTonightDTO.getToday().values().stream().map(current -> new Event(current.getKey(), current.getTitle(), current.getDate().toLocalDate(), current.getDate().toLocalTime(), new Location(1.1, 1.1))).collect(Collectors.toList());
                events.addAll(gameTonightEvents);
            }
        } catch (ResponseProcessingException e){
            //No game tonight
            System.out.println(e);
        }
        return events;
    }

}
