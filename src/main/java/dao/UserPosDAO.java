package dao;

import conexaojdbc.SingleConnection;
import model.Userposjava;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserPosDAO {
    private Connection connection;

    public UserPosDAO() {
        connection = SingleConnection.getConnection();
    }

    public void salvar(Userposjava userposjava) {
        String sql = "insert into userposjava(nome, email) values (?, ?)";

        try {
            PreparedStatement insert = connection.prepareStatement(sql);
            insert.setString(1, userposjava.getNome());
            insert.setString(2, userposjava.getEmail());
            insert.execute();
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

    public List<Userposjava> listar() throws  Exception {
        List<Userposjava> list = new ArrayList<Userposjava>();

        String sql = "select * from userposjava";

        PreparedStatement select = connection.prepareStatement(sql);
        ResultSet result = select.executeQuery();

        while(result.next()) {
            Userposjava userposjava = new Userposjava();
            userposjava.setId(result.getLong("id"));
            userposjava.setNome(result.getString("nome"));
            userposjava.setEmail(result.getString("email"));

            list.add(userposjava);
        }

        return list;

    }

    public Userposjava buscar(Long id) throws  Exception {
        Userposjava retorno = new Userposjava();

        String sql = "select * from userposjava where id = " + id;

        PreparedStatement select = connection.prepareStatement(sql);
        ResultSet result = select.executeQuery();

        while(result.next()) {
            retorno.setId(result.getLong("id"));
            retorno.setNome(result.getString("nome"));
            retorno.setEmail(result.getString("email"));

         }

        return retorno;

    }

    public void atualizar(Userposjava userposjava) {
        try {
            String sql = "update userposjava set nome = ?  where id = " + userposjava.getId();
            PreparedStatement update = connection.prepareStatement(sql);

            update.setString(1, userposjava.getNome());
            update.execute();

            connection.commit();
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }

            e.printStackTrace();
        }
    }
}
