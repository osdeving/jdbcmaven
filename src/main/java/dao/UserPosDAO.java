package dao;

import conexaojdbc.SingleConnection;
import model.Userposjava;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UserPosDAO {
    private Connection connection;

    public UserPosDAO() {
        connection = SingleConnection.getConnection();
    }

    public void salvar(Userposjava userposjava) {
        String sql = "insert into userposjava(id, nome, email) values (?, ?, ?)";

        try {
            PreparedStatement insert = connection.prepareStatement(sql);
            insert.setLong(1, 4);
            insert.setString(2, "willams2");
            insert.setString(3, "email2@email2.com");
            insert.execute();
            // dando erro, não pode dar commit quando 'autocommit' está ativo
            // TODO:
            // pesquisar sobre autocommit
           // connection.commit(); // salva no banco
        } catch(Exception e) {
            e.printStackTrace();
        }

    }
}
