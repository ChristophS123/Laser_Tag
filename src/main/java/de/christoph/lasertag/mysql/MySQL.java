package de.christoph.lasertag.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {

    private Connection connection;

    private String host;
    private int port;
    private String database;
    private String user;
    private String password;

    public MySQL(String host, int port, String database, String user, String password) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.user = user;
        this.password = password;
        connect();
    }

    private void connect() {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://"
                            + host
                            + ":"
                            + port
                            + "/"
                            + database
                            + "?autoReconnect=true",
                    user,
                    password
            );
        } catch (SQLException throwables) {
            System.out.println("[LaserTag] connection failed.");
        }
    }

    public boolean hasConnection() {
        if(connection != null)
            return true;
        return false;
    }

    public void disconnect() {
        try {
            this.connection.close();
        } catch (SQLException throwables) {
            System.out.println("[LaserTag] failed disconnect");
        }
    }

    public Connection getConnection() {
        return connection;
    }

}
