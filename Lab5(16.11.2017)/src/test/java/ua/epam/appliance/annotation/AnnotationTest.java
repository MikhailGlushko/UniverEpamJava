package ua.epam.appliance.annotation;

import org.junit.Before;
import org.junit.Test;
import ua.epam.appliance.entity.Appliance;
import ua.epam.appliance.entity.Kettle;
import ua.epam.appliance.factory.KettleFactory;

import static org.junit.Assert.*;

public class AnnotationTest {

    private Annotation annotation;

    @Test
    @Before
    public void main(){
        annotation = new Annotation("ua.epam.appliance.factory", new Class[]{ApplianceAnnotation.class});
    }

    @Test
    public void getCountClassAnnotated() throws Exception {
        int result = annotation.getCountClassAnnotated();
        int expected = 4;
        assertEquals(result,expected);
    }

    @Test
    public void isClassAnnotated() throws Exception {
        boolean result = annotation.isClassAnnotated(this.getClass());
        boolean expected = false;
        assertEquals(result,expected);

        result = annotation.isClassAnnotated(KettleFactory.class);
        expected = true;
        assertEquals(result,expected);

        result = annotation.isClassAnnotated(Appliance.class);
        expected = false;
        assertEquals(result,expected);
    }

}