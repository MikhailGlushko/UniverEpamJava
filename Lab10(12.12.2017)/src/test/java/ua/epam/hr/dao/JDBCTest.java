package ua.epam.hr.dao;

import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

public class JDBCTest {

    JDBC jdbc;

    @Before
    public void getConnection() {
        jdbc = new JDBC();
    }

    @Test
    public void setConnection() {
        Connection connection = jdbc.getConnection();
        assertNotNull(connection);
    }
}