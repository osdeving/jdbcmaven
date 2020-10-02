package conexaojdbc;

import dao.UserPosDAO;
import model.Userposjava;
import org.junit.Test;

public class TestaBancoJDBC {

    @Test
    public void initBanco() {
        UserPosDAO userPosDAO = new UserPosDAO();
        Userposjava userposjava = new Userposjava();

        userPosDAO.salvar(userposjava);

    }
}
