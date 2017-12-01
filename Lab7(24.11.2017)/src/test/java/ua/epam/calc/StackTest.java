package ua.epam.calc;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StackTest {

    Stack stack;
    @Before
    public void init(){
        stack = new Stack(10);
    }

    @Test
    public void push() throws Exception {
        stack.push("1");
        stack.push("+");
        int size = stack.size();
        int required = 2;
        assertEquals(required,size);
    }

    @Test
    public void pop() throws Exception {
        stack.push("1");
        String result = stack.pop();
        String required = "1";
        assertEquals(required,result);
        int size = stack.size();
        int required2 = 0;
        assertEquals(required2,size);
    }

    @Test
    public void peek() throws Exception {
        stack.push("1");
        String result = stack.peek();
        String required = "1";
        assertEquals(required,result);
        int size = stack.size();
        int required2 = 1;
        assertEquals(required2,size);
    }

    @Test
    public void isEmpty() throws Exception {
        boolean empty = stack.isEmpty();
        boolean required = true;
        assertEquals(required,empty);
    }

    @Test
    public void isFull() throws Exception {
        boolean full = stack.isFull();
        boolean required = false;
        assertEquals(required,full);
    }

    @Test
    public void size() throws Exception {
        int size = stack.size();
        int required2 = 0;
        assertEquals(required2,size);
    }

    @Test
    public void peekN() throws Exception {
        stack.push("1");
        stack.push("2");
        String result = stack.peekN(0);
        String expected = "1";
        assertEquals(expected,result);
    }

    @Test
    public void displayStack() throws Exception {
        stack.push("1");
        stack.push("2");
        stack.push("+");
        stack.displayStack("");;
    }

}