package ua.epam.string.textprocessor.entity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParagraphTest {
    TextElement element;

    @Before
    public void init(){
        String line = "     Hello, World..Hello World.Hello World. Hello World! Java is the best. \n Java EE JavaEE";
        System.out.println(line);
        element = new Paragraph(line);
    }

    @Test
    public void _toString() {
        System.out.println(element.toString());
    }
    @Test
    public void getUniqueList() throws Exception {
        System.out.println(element.getUniqueList());
    }

    @Test
    public void getList() throws Exception {
        System.out.println(element.getList());
    }

}