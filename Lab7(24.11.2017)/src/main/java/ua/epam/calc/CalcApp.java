package ua.epam.calc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * стаковый калькулятор - реализация в 4 главе Роберт Лафоре - Структуры данных и алгоритмы в Java. 2-е издание
 * добавить sin cos
 */
public class CalcApp {
    public static void main(String[] args) throws IOException {
            String input;
            String postfix;
            double result;

            while(true)
            {
                System.out.print("Enter infix: ");
                //System.out.flush();
                input = getString();         // read a string from kbd
                if( input.equals("") )       // quit if [Enter]
                    break;

                // make a translator
                InToPost theTrans = new InToPost(input);
                postfix = theTrans.doTrans().trim(); // do the translation
                System.out.print(postfix);

                // make a parser
                ParsePost aParser = new ParsePost(postfix);
                result = aParser.doParse();  // do the evaluation
                if(result==Math.round(result))
                    System.out.println("["+postfix+"] Evaluates to " + Math.round(result));
                else
                    System.out.println("["+postfix+"] Evaluates to " + result);
            }  // end while
    }

    public static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        return s;
    }
}
