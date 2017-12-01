package ua.epam.calc;

public class InToPost                  // infix to postfix conversion
{
    private Stack theStack;
    private String input;
    private String output = "";
    private boolean debugMode = false;

    //--------------------------------------------------------------
    public InToPost(String in)   // constructor
    {
        input = in.replaceAll("\\ +","");
        int stackSize = input.length();
        theStack = new Stack(stackSize);
    }

    //--------------------------------------------------------------
    public String doTrans(){      // do translation to postfix

        for (int j = 0; j < input.length(); j++){      // for each char
            int k = j+1;
            while (k<input.length()){
                if (Character.isAlphabetic(input.charAt(j)) && Character.isAlphabetic(input.charAt(k))) k++;
                else if (Character.isDigit(input.charAt(j)) && Character.isDigit(input.charAt(k))) k++;
                else break;
            }
            String ch = input.substring(j,k);            // get it
            j=k-1;
            if(debugMode)
                theStack.displayStack("For " + ch + " "); // *diagnostic*
            switch (ch) {
                case "+":               // it's + or -
                case "-":
                    gotOper(ch, 1);      // go pop operators
                    break;               //   (precedence 1)
                case "*":               // it's * or /
                case "/":
                    gotOper(ch, 2);      // go pop operators
                    break;               //   (precedence 2)
                case "(":               // it's a left paren
                case "cos":
                case "sin":
                    theStack.push(ch + "");   // push it
                    break;
                case ")":               // it's a right paren
                    gotParen(ch);        // go pop operators
                    break;
                default:                // must be an operand
                    output = output + " "+ch; // write it to output
                    if(!Character.isDigit(ch.charAt(0)) || ch.charAt(0)=='.' || ch.charAt(0)==','){
                        output="";
                        theStack = new Stack(0);
                        j=input.length();
                    }
                    break;
            }  // end switch
        }  // end for
        while (!theStack.isEmpty())     // pop remaining opers
        {
            if(debugMode)
                theStack.displayStack("While ");  // *diagnostic*
            output = output + " "+theStack.pop(); // write to output
        }
        if(debugMode)
            theStack.displayStack("End   ");     // *diagnostic*
        return output;                   // return postfix
    }  // end doTrans()

    //--------------------------------------------------------------
    public void gotOper(String opThis, int prec1) {                                // got operator from input
        while (!theStack.isEmpty()) {
            String opTop = theStack.pop();
            if (opTop.equals("("))            // if it's a '('
            {
                theStack.push(opTop);      // restore '('
                break;
            } else                          // it's an operator
            {
                int prec2;                 // precedence of new op

                if (opTop.equals("+") || opTop.equals("-"))  // find new op prec
                    prec2 = 1;
                else
                    prec2 = 2;
                if (prec2 < prec1)          // if prec of new op less
                {                       //    than prec of old
                    theStack.push(opTop);   // save newly-popped op
                    break;
                } else                       // prec of new not less
                    output = output +" "+ opTop;  // than prec of old
            }  // end else (it's an operator)
        }  // end while
        theStack.push(opThis + "");           // push new operator
    }  // end gotOp()

    //--------------------------------------------------------------
    public void gotParen(String ch) {                             // got right paren from input
        while (!theStack.isEmpty()) {
            String chx = theStack.pop();
            if (chx.equals("("))           // if popped '('
                break;                  // we're done
            else                       // if popped operator
                output = output +" "+ chx;  // output it
        }  // end while
    }  // end popOps()
//--------------------------------------------------------------
}  // end class InToPost
