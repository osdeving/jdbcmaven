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
            insert.setLong(1, userposjava.getId());
            insert.setString(2, userposjava.getNome());
            insert.setString(3, userposjava.getEmail());
            insert.execute();
            // dando erro, não pode dar commit quando 'autocommit' está ativo
            // TODO:
            // pesquisar sobre autocommit
            connection.commit(); // salva no banco
        } catch(Exception e) {
            try {
                connection.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }

    }
}
