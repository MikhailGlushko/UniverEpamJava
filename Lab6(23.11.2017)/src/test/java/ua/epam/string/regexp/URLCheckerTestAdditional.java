package ua.epam.string.regexp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.Parameterized.Parameter;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class URLCheckerTestAdditional{
    @Parameter(value=0)
    public String param;
    @Parameter(value=1)
    public boolean result;

    private URLChecker urlChecker;

    @Before
    public void init(){
        urlChecker = new URLChecker();
    }

    @Parameters
    public static Collection getParameters() {
        return Arrays.asList(new Object[][] {
                {"oracle.com", true },
                {"www.oracle.com", true },
                {"http://oracle.com", true },
                {"http://www.oracle.com", true },
                {"httpss//www.oracle.com", false },
        });
    }

    @Test
    public void additional(){
        boolean r = urlChecker.isValidUrl(param);
        assertEquals(result,r);
        System.out.println(param + " is " +result);
    }
}
