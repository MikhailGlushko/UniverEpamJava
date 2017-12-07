package ua.epam.turnament;

import org.junit.Before;
import org.junit.Test;

public class TurnamentTest {

    Turnament turnament;

    @Before
    public void setUp() throws Exception {
        turnament = new Turnament();
    }

    @Test
    public void makePlayers() {
        turnament.makePlayers();
    }

    @Test
    public void battle(){
        turnament.battle(1,2);
    }

    @Test
    public void start(){
        turnament.start();
    }
}