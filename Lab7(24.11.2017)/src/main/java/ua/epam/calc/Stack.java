package ua.epam.calc;

public class Stack {
    private int maxSize;
    private String[] stackArray;
    private int top;

    //--------------------------------------------------------------
    public Stack(int size)      // constructor
    {
        maxSize = size;
        stackArray = new String[maxSize];
        top = -1;
    }

    //--------------------------------------------------------------
    public void push(String j)     // put item on top of stack is stack is not full
    {
        if(top<maxSize)
            stackArray[++top] = j;
    }

    //--------------------------------------------------------------
    public String pop()            // take item from top of stack is stack is not empty
    {
        if(top>=0)
            return stackArray[top--];
        else
            return null;
    }

    //--------------------------------------------------------------
    public String peek()           // peek at top of stack
    {
        return stackArray[top];
    }

    //--------------------------------------------------------------
    public boolean isEmpty()    // true if stack is empty
    {
        return (top == -1);
    }

    //--------------------------------------------------------------
    public boolean isFull()     // true if stack is full
    {
        return (top == maxSize - 1);
    }

    //--------------------------------------------------------------
    public int size()           // return size
    {
        return top + 1;
    }

    //--------------------------------------------------------------
    public String peekN(int n)     // peek at index n
    {
        return stackArray[n];
    }

    //--------------------------------------------------------------
    public void displayStack(String s) {
        System.out.print(s);
        System.out.print("Stack (bottom-->top): ");
        for (int j = 0; j < size(); j++) {
            System.out.print(peekN(j));
            System.out.print(' ');
        }
        System.out.println("");
    }
//--------------------------------------------------------------
}  // end class StackX

