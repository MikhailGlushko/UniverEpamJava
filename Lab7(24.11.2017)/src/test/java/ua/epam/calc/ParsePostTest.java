package ua.epam.calc;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class ParsePostTest {

    ParsePost parsePost;

    double result;
    double expected;
    String value;

    public ParsePostTest(String value, double expected) {
        this.expected = expected;
        this.value = value;
    }

    @Parameterized.Parameters
    public static Collection getValues(){
        return Arrays.asList(new Object[][]{
                {"1 2 +", 3},
                {"1 2 *", 2},
                {"1 2 3 * +", 7},
                {"1 2 * 3 +", 5},
                {"1 2 3 * + 4 +", 11},
                {"1 2 * 3 4 * +", 14},
                {"1 2 * 3 *", 6},
                {"1",1},
                {"1 -", -1},
                {"1 +", 1},
                {"1 2 * 3 /", 0.6666666666666666},
                {"1 0 cos +", 2},
                {"", 0},
                {"0 cos", 1},
                {"0 cos 0 sin *", 0}
        });
    }

    @Test
    public void doParse() {
        parsePost = new ParsePost(value);
        double result = parsePost.doParse();
        System.out.println(value + " --> " +result+" == "+expected);
        assertEquals(expected,result,0);
    }

}