package conexaojdbc;

import dao.UsuarioDAO;
import model.BeanUserFone;
import model.Telefone;
import model.Usuario;
import org.junit.Test;

import java.util.List;

public class TestaBancoJDBC {

    @Test
    public void initBanco() {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = new Usuario();

        usuario.setNome("Will numero sete");
        usuario.setEmail("email-will@uol.com.br");

        usuarioDAO.salvar(usuario);

    }

    @Test
    public void initListar() {
        UsuarioDAO dao = new UsuarioDAO();

        try {
            List<Usuario> list = dao.listar();

            for(Usuario usuario : list) {
                System.out.println(usuario);
                System.out.println("---------------------------------------------------------------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void initBuscar() {
        UsuarioDAO dao = new UsuarioDAO();

        try {
           Usuario usuario = dao.buscar(7L);

            System.out.println(usuario);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void initAtualizar() {
        try {
            UsuarioDAO dao = new UsuarioDAO();

            Usuario objBanco = dao.buscar(5L);

            objBanco.setNome("Mudado com método atualizar");

            dao.atualizar(objBanco);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void initDeletar() {
        try {
            UsuarioDAO dao = new UsuarioDAO();
            dao.deletar(7L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testeInsertTelefone() {
        Telefone telefone = new Telefone();
        telefone.setNumero("(18) 4445-4545");
        telefone.setTipo("casa");
        telefone.setUsuario(4L);

        UsuarioDAO dao = new UsuarioDAO();

        dao.salvarTelefone(telefone);

    }

    @Test
    public void testeCarregaFoneUser() {
        UsuarioDAO dao = new UsuarioDAO();

        List<BeanUserFone> beanUserFones = dao.listarUserFone(9L);

        for(BeanUserFone beanUserFone : beanUserFones) {
            System.out.println(beanUserFone);
        }
    }
}
