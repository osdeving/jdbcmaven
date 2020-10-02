package conexaojdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {
    private static String url = "jdbc:mysql://localhost:3306/posjava?useTimezone=true&serverTimezone=UTC";
    private static String user = "root";
    private static String password = "ewq1234r5";
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
                Class.forName("com.mysql.cj.jdbc.Driver");
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
