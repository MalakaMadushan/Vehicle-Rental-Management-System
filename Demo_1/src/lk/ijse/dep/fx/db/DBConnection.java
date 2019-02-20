package lk.ijse.dep.fx.db;

import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnection {
    private static DBConnection dbConnection;
    private Connection connection;

    private  DBConnection() throws Exception {
        Properties dbproperty = new Properties();
        File file = new File("Demo_1/resourse/application.properties");
        FileReader fileReader = new FileReader(file);
        dbproperty.load(fileReader);
        String ip = dbproperty.getProperty("ip");
        String port = dbproperty.getProperty("port");
        String db = dbproperty.getProperty("db");
        String username = dbproperty.getProperty("username");
        String password = dbproperty.getProperty("password");

        String url = "jdbc:mysql://"+ip+":"+port+"/"+db;

        connection = DriverManager.getConnection(url,username,password);


    }
    public static DBConnection getInstance() throws Exception {
        if(dbConnection == null){
            dbConnection = new DBConnection();

        }return dbConnection;
    }

    public  Connection getConnection(){
        return connection;
    }


}
