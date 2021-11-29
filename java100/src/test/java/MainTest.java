import company.Main;

import static org.junit.Assert.*;

public class MainTest {

    @org.junit.Test
    public void add() {
        int result = Main.add(3, 2);
        int correct = 5;
        assertEquals(5, result);
    }
}