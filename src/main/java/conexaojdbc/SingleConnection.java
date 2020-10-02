package conexaojdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {
    private static String url = "jdbc:postgresql://localhost:5432/crmdb?autoReconnect=true";
    private static String user = "postgres";
    private static String password = "admin";
    private static Connection connection = null;

    static {
        connectar();
    }

    public SingleConnection() {
        connectar();
    }

    private static void connectar() {
        try {
            if(connection == null) {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(url, user, password);
                System.out.println("Conectou com sucesso!");
            }
        }
        catch (Exception e) {
            e.printStackTrace();

        }

    }

    public static Connection getConnection() {
        return connection;
    }

}
