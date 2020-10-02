package conexaojdbc;

import org.junit.Test;

public class TestaBancoJDBC {

    @Test
    public void initBanco() {
        SingleConnection.getConnection();
    }
}
