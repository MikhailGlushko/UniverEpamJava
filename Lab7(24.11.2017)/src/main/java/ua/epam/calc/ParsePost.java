package ua.epam.calc;

public class ParsePost
{
    private Stack theStack;
    private String input;
    private boolean debugMode = false;
    //--------------------------------------------------------------
    public ParsePost(String s){
        input = s; }
    //--------------------------------------------------------------
    public double doParse()
    {
        theStack = new Stack(20);             // make new stack
        String ch;
        int j;
        double num1, num2;
        double interAns;

        for(j=0; j<input.length(); j++)       // for each char,
        {
            int k=j+1;
            while (k<input.length()){
                if (input.charAt(k)!=' ')k++;
                else break;
            }
            ch = input.substring(j,k);              // read from input
            j=k;
            if(debugMode)
                theStack.displayStack(""+ch+" ");  // *diagnostic*
            if(ch.charAt(0) >= '0' && ch.charAt(0) <= '9')         // if it's a number
                theStack.push( (ch) ); //   push it
            else                               // it's an operator
            {
                num2 = Double.parseDouble(theStack.pop());          // pop operands
                switch(ch)                      // do arithmetic
                {
                    case "+":
                        String pop = theStack.pop();
                        if (pop==null) pop = "0";
                        num1 = Double.parseDouble(pop);
                        interAns = num1 + num2;
                        break;
                    case "-":
                        pop = theStack.pop();
                        if (pop==null) pop = "0";
                        num1 = Double.parseDouble(pop);
                        interAns = num1 - num2;
                        break;
                    case "*":
                        pop = theStack.pop();
                        if (pop==null) pop = "1";
                        num1 = Double.parseDouble(pop);
                        interAns = num1 * num2;
                        break;
                    case "/":
                        pop = theStack.pop();
                        if (pop==null) pop = "1";
                        num1 = Double.parseDouble(pop);
                        interAns = num1 / num2;
                        break;
                    case "cos":
                        interAns = Math.cos(num2);
                        break;
                    case "sin":
                        interAns = Math.sin(num2);
                        break;
                    default:
                        interAns = 0;
                }  // end switch
                theStack.push(interAns+"");        // push result
            }  // end else
        }  // end for
        if(!theStack.isEmpty())
            interAns = Double.parseDouble(theStack.pop());            // get answer
        else
            interAns = 0;
        return interAns;
    }  // end doParse()
}  // end class ParsePost
