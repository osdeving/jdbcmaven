package dao;

import conexaojdbc.SingleConnection;

import java.sql.Connection;

public class UserPosDAO {
    private Connection connection;

    public UserPosDAO() {
        connection = SingleConnection.getConnection();
    }
}
