package dao;

import conexaojdbc.SingleConnection;
import model.BeanUserFone;
import model.Telefone;
import model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private final Connection connection;

    public UsuarioDAO() {
        connection = SingleConnection.getConnection();
    }

    public void salvar(Usuario usuario) {
        String sql = "insert into usuario(nome, email) values (?, ?)";

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

        String sql = "select * from usuario";

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

    public List<BeanUserFone> listarUserFone(Long idUser)  {
        List<BeanUserFone> beanUserFones = new ArrayList<>();

        String sql = "select nome, numero, email from telefone as fone ";
        sql += " inner join usuario as usr ";
        sql += "on fone.usuario = usr.id";
        sql += " where usr.id = " + idUser;


        try {
            PreparedStatement select = connection.prepareStatement(sql);

            ResultSet result = select.executeQuery();

            while(result.next()) {
                BeanUserFone beanUserFone = new BeanUserFone();
                beanUserFone.setNome(result.getString("nome"));
                beanUserFone.setNumero(result.getString("numero"));
                beanUserFone.setEmail(result.getString("email"));

                beanUserFones.add(beanUserFone);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return beanUserFones;
    }

    public Usuario buscar(Long id) throws  Exception {
        Usuario retorno = new Usuario();

        String sql = "select * from usuario where id = " + id;

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
            String sql = "update usuario set nome = ?  where id = " + usuario.getId();
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
            String sql = "delete from usuario where id = " + id;
            PreparedStatement delete = connection.prepareStatement(sql);
            delete.executeUpdate();
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

    public void deletarTelefonesPorUsuario(Long idUser) {
        try {
            String sqlFone = "delete from telefone where usuario = " + idUser;
            String sqlUser = "delete from usuario where id = " + idUser;

            PreparedStatement delete = connection.prepareStatement(sqlFone);
            delete.executeUpdate();
            connection.commit();

            delete = connection.prepareStatement(sqlUser);
            delete.executeUpdate();
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
