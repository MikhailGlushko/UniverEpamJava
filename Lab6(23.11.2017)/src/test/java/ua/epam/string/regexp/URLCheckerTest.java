package ua.epam.string.regexp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Suite;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;
@RunWith(Suite.class)
public class URLCheckerTest{
    private URLChecker urlChecker;

    @Before
    public void init(){
        urlChecker = new URLChecker();
    }

    @Test
    public void check() {
        String url = "https://www.oracle.com:8080/download/index.html?id=12&p=3#begin";
        boolean result = urlChecker.isValidUrl(url);
        boolean expected = true;
        assertEquals(expected,result);

        url = "oracle.com";
        result = urlChecker.isValidUrl(url);
    }

    @Test
    public void getUrl() {
        String url = "Проверка поиска https://www.oracle.com:8080/download/index.html?id=12&p=3#begin адресса www.google.com";
        urlChecker.extractUrls(url);
    }
}

