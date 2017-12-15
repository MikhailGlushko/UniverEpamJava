package ua.epam.hr.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {
    private Connection connection;
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/hr?useSSL=false";
    private String login = "root";
    private String password = "compaq";

    public Connection getConnection() {
        return connection;
    }


    public JDBC() {
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url,login,password);
            this.connection = connection;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
