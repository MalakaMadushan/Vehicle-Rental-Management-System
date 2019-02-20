import lk.ijse.dep.fx.db.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class TestDB {
    public static void main(String[] args) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        System.out.println(connection);

        Connection connection1 = DBConnection.getInstance().getConnection();
        System.out.println(connection1);

    }
}

