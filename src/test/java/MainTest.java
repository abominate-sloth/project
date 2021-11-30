import com.company.Main;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class MainTest {

    @org.junit.Test
    public void help() {

    }

    @org.junit.Test
    public void add() throws ParseException {
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy-HH:mm");
        Date day = format.parse("12.08.2022-12:43");
        Main obj = new Main();
        obj.add(day, "asd");
        assertEquals(day, obj.a_Date[obj.n-1]);
        assertEquals("asd", obj.a_Task[obj.n-1]);
    }

    @org.junit.Test
    public void show() {
    }

    @org.junit.Test
    public void delete() {
    }

    @org.junit.Test
    public void convert() {



    }

    @org.junit.Test
    public void read() {
    }

    @org.junit.Test
    public void save() {
    }

    @org.junit.Test
    public void readFromWeb() {
    }

    @org.junit.Test
    public void restore() {
    }

    @org.junit.Test
    public void main() {
    }
}