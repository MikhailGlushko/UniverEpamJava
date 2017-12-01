package ua.epam.calc;

import org.junit.Test;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.RunListener;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class InToPostTest {

    InToPost inToPost;
    String   value;
    String   expected;
    String   result;

    public InToPostTest(String value, String expected) {
        this.expected = expected;
        this.value = value;
    }

    @Parameterized.Parameters
    public static Collection getValues(){
        return Arrays.asList(new Object[][]{
                {"1 + 2", "1 2 +"},
                {"1 * 2","1 2 *"},
                {"1 + 2 * 3","1 2 3 * +"},
                {"1 * 2 + 3","1 2 * 3 +"},
                {"1 + 2 * 3 + 4","1 2 3 * + 4 +"},
                {"1 * 2 + 3 * 4","1 2 * 3 4 * +"},
                {"1 * 2 * 3", "1 2 * 3 *"},
                {"(1 * 2) * 3", "1 2 * 3 *"},
                {"1 * (2 * 3)", "1 2 3 * *"},
                {"1","1"},
                {"-1","1 -"},
                {"1 +","1 +"},
                {"1 * 2 / 3","1 2 * 3 /"},
                {"1 + cos(0)","1 0 cos +"},
                {"",""},
                {"cos(0)","0 cos"},
                {"cos(0) * sin(0)","0 cos 0 sin *"}
        });
    }

    @Test
    public void doTrans() throws Exception {
        inToPost = new InToPost(value);
        result = this.inToPost.doTrans().trim();
        System.out.println(value + " --> " +result+" == "+expected);
        assertEquals(value + " --> " +result+" != "+expected,expected,result);
    }
}