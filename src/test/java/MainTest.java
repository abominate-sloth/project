import static org.junit.jupiter.api.Assertions.*;
import com.company.Main;
import org.junit.jupiter.api.Test;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
class MainTest {

    @org.junit.jupiter.api.Test
    void help() {
        Main obj1 = new Main();
        obj1.help();
    }

    @org.junit.jupiter.api.Test
    public void add() throws ParseException {
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy-HH:mm");
        Date day = format.parse("12.08.2022-12:43");
        Main obj = new Main();
        obj.add(day, "asd");
        assertEquals(day, obj.a_Date[obj.n - 1]);
        assertEquals("asd", obj.a_Task[obj.n - 1]);
    }

    @org.junit.jupiter.api.Test
    void show() throws ParseException {
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy-HH:mm");
        Date day = format.parse("12.08.2022-12:43");
        Main obj2 = new Main();
        obj2.show(day);
        assertEquals(day, obj2.a_Date[0]);
        assertEquals("asd",obj2.a_Task[0] );

    }

    @org.junit.jupiter.api.Test
    void delete() throws ParseException {

    }

    @org.junit.jupiter.api.Test
    void convert() {
    }

    @org.junit.jupiter.api.Test
    void read() {
        Main obj5 = new Main();
        obj5.save();
    }

    @org.junit.jupiter.api.Test
    void save() {
        Main obj4 = new Main();
        obj4.save();
    }

    @org.junit.jupiter.api.Test
    void readFromWeb() {
    }

    @org.junit.jupiter.api.Test
    void restore() {
        Main obj3 = new Main();
        obj3.restore();
    }

    @org.junit.jupiter.api.Test
    void main() {
    }
}