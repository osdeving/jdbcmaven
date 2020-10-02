package conexaojdbc;

import dao.UserPosDAO;
import model.Userposjava;
import org.junit.Test;

public class TestaBancoJDBC {

    @Test
    public void initBanco() {
        UserPosDAO userPosDAO = new UserPosDAO();
        Userposjava userposjava = new Userposjava();

        userposjava.setId(6L);
        userposjava.setNome("Will numero seis");
        userposjava.setEmail("email-will@uol.com.br");
        userPosDAO.salvar(userposjava);

    }
}
