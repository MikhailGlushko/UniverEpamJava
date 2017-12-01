package ua.epam.string.textprocessor.entity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SignTest {
    TextElement element;
    @Before
    public void init(){
        element = new Sign('.');
    }

    @Test
    public void _toString() throws Exception {
        System.out.println(element);
    }

}