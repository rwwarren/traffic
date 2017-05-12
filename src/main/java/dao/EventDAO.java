package dao;

import javax.ws.rs.WebApplicationException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class EventDAO {
    private final Connection connection;

    public EventDAO(Connection connection) {
        this.connection = connection;
    }

    public String getEvent() throws Exception {
        try {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM EVENT;");
            String name = resultSet.getString("name");
            return name;

        } catch(Exception e) {
            throw new WebApplicationException(e);
        }
    }
}
