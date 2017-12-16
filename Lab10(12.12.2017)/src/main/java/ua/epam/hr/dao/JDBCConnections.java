package ua.epam.hr.dao;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCConnections {

    public static Logger logger = Logger.getLogger(JDBCConnections.class.getSimpleName());

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public JDBCConnections() {
        try {
            Properties dbProperties = new Properties();
            InputStream inStream = this.getClass().getClassLoader().getResourceAsStream("db.properties");
            dbProperties.load(inStream);
            inStream.close();

            String driver = dbProperties.getProperty("db_driver");
            String url = "jdbc:mysql://"+dbProperties.getProperty("db_host")
                    +":"+dbProperties.getProperty("db_port")
                    +"/"+dbProperties.getProperty("db_name")
                    +"?useSSL=false";
            String login = dbProperties.getProperty("db_login");
            String password = dbProperties.getProperty("db_password");

            Class.forName(driver);

            Connection connection = DriverManager.getConnection(url,login,password);
            this.connection = connection;
        } catch (ClassNotFoundException e) {
            logger.error(e);
        } catch (SQLException e) {
            logger.error(e);
        } catch (IOException e) {
            logger.error(e);
        }
    }
}
