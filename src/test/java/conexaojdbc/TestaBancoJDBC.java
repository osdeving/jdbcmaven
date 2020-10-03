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

        userposjava.setId(7L);
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
}
