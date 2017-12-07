package ua.epam.string.textprocessor.entity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WordTest {

    TextElement element;

    @Before
    public void init(){
        element = Word.getWord("Hello");
    }

    @Test
    public void _toString() {
        System.out.println(element);
    }

}