import com.company.Main;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class MainTest {

    @Test
    public void add() throws ParseException {
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy-HH:mm");
        Date day = format.parse("12.08.2022-12:43");
        Main obj = new Main();
        obj.add(day, "asd");
        assertEquals(day, obj.a_Date[obj.n-1]);
        assertEquals("asd", obj.a_Task[obj.n-1]);
    }
}