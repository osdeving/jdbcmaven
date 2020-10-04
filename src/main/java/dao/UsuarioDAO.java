package dao;

import conexaojdbc.SingleConnection;
import model.Telefone;
import model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private final Connection connection;

    public UsuarioDAO() {
        connection = SingleConnection.getConnection();
    }

    public void salvar(Usuario usuario) {
        String sql = "insert into userposjava(nome, email) values (?, ?)";

        try {
            PreparedStatement insert = connection.prepareStatement(sql);
            insert.setString(1, usuario.getNome());
            insert.setString(2, usuario.getEmail());
            insert.execute();
            connection.commit(); // salva no banco
        } catch(Exception e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        }

    }

    public void salvarTelefone(Telefone telefone) {
        String sql = "insert into telefone(numero,tipo, usuario) values (?, ?, ?)";

        try {
            PreparedStatement insert = connection.prepareStatement(sql);
            insert.setString(1, telefone.getNumero());
            insert.setString(2, telefone.getTipo());
            insert.setLong(3, telefone.getUsuario());
            insert.execute();
            connection.commit(); // salva no banco
        } catch(Exception e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        }

    }

    public List<Usuario> listar() throws  Exception {
        List<Usuario> list = new ArrayList<>();

        String sql = "select * from userposjava";

        PreparedStatement select = connection.prepareStatement(sql);
        ResultSet result = select.executeQuery();

        while(result.next()) {
            Usuario usuario = new Usuario();
            usuario.setId(result.getLong("id"));
            usuario.setNome(result.getString("nome"));
            usuario.setEmail(result.getString("email"));

            list.add(usuario);
        }

        return list;

    }

    public Usuario buscar(Long id) throws  Exception {
        Usuario retorno = new Usuario();

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

    public void atualizar(Usuario usuario) {
        try {
            String sql = "update userposjava set nome = ?  where id = " + usuario.getId();
            PreparedStatement update = connection.prepareStatement(sql);

            update.setString(1, usuario.getNome());
            update.execute();

            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }


        }
    }

    public void deletar(Long id) {
        try {
            String sql = "delete from userposjava where id = " + id;
            PreparedStatement delete = connection.prepareStatement(sql);
            delete.execute();
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        }
    }
}
