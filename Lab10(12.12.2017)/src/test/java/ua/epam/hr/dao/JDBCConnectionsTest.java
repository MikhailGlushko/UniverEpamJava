package ua.epam.hr.dao;

import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

public class JDBCConnectionsTest {

    JDBCConnections jdbcConnections;

    @Before
    public void getConnection() {
        jdbcConnections = new JDBCConnections();
    }

    @Test
    public void setConnection() {
        Connection connection = jdbcConnections.getConnection();
        assertNotNull(connection);
    }
}