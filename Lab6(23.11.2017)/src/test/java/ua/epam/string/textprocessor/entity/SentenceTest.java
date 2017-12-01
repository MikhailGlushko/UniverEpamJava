package ua.epam.string.textprocessor.entity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SentenceTest {
    TextElement element;

    @Before
    public void init(){
        element = new Sentence("Hello Word!");
        System.out.println(element);
    }

    @Test
    public void getList() throws Exception {
        System.out.println(element.getList());
    }

    @Test
    public void _toString() throws Exception {
        System.out.println(element.toString());
    }

}