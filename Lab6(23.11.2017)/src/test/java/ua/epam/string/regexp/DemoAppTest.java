package ua.epam.string.regexp;

import org.junit.Test;
import sun.misc.IOUtils;
import sun.nio.ch.IOUtil;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.*;

public class DemoAppTest {
    @Test
    public void readData() throws Exception {
        String source = "coracle.com\nhttp://www.oracle.com\nmail.ru\nhttps://www.oracle.com:8080/download/index.html?id=12&p=3#begin";
        InputStream stream = new ByteArrayInputStream(source.getBytes());
        URLChecker urlChecker = new URLChecker();
        DemoApp.readDataFromStream(urlChecker,stream);
    }

}