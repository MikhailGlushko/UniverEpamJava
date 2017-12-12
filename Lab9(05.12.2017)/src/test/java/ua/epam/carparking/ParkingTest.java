package ua.epam.carparking;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class ParkingTest {

    private Parking parking;

    @Before
    public void setUp() throws Exception {
        parking = new Parking("парковка 1",5);
    }

    @Test
    public void takaPlace() {
        int result = parking.takaPlace();
        assertEquals(0,result);
    }

    @Test
    public void takePlaceWhenNoPlace(){
        int result=-1;
        for (int i=0; i<=5; i++)
            result = parking.takaPlace();
        assertEquals(-1,result);
    }

    @Test
    public void releasePlace(){
        parking.releasePlace(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void releasePlaceOut(){
        parking.releasePlace(5);
    }

    @Test(timeout = 1)
    public void accept(){
            parking.accept();
    }

    @Test
    public void acceptTimeout(){
        parking.accept(1000, TimeUnit.MILLISECONDS);
    }

    @Test
    public void release(){
        parking.release();
    }
}