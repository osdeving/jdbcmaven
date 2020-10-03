package conexaojdbc;

import dao.UserPosDAO;
import model.Userposjava;
import org.junit.Test;

import java.util.List;

public class TestaBancoJDBC {

    @Test
    public void initBanco() {
        UserPosDAO userPosDAO = new UserPosDAO();
        Userposjava userposjava = new Userposjava();

        userposjava.setNome("Will numero sete");
        userposjava.setEmail("email-will@uol.com.br");

        userPosDAO.salvar(userposjava);

    }

    @Test
    public void initListar() {
        UserPosDAO dao = new UserPosDAO();

        try {
            List<Userposjava> list = dao.listar();

            for(Userposjava userposjava : list) {
                System.out.println(userposjava);
                System.out.println("---------------------------------------------------------------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void initBuscar() {
        UserPosDAO dao = new UserPosDAO();

        try {
           Userposjava userposjava = dao.buscar(7L);

            System.out.println(userposjava);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void initAtualizar() {
        try {
            UserPosDAO dao = new UserPosDAO();

            Userposjava objBanco = dao.buscar(5L);

            objBanco.setNome("Mudado com método atualizar");

            dao.atualizar(objBanco);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void initDeletar() {
        try {
            UserPosDAO dao = new UserPosDAO();
            dao.deletar(7L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
