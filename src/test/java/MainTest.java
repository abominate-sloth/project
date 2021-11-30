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
    void show() {
    }

    @org.junit.jupiter.api.Test
    void delete() {
    }

    @org.junit.jupiter.api.Test
    void convert() {
    }

    @org.junit.jupiter.api.Test
    void read() {
    }

    @org.junit.jupiter.api.Test
    void save() {
    }

    @org.junit.jupiter.api.Test
    void readFromWeb() {
    }

    @org.junit.jupiter.api.Test
    void restore() {
    }

    @org.junit.jupiter.api.Test
    void main() {
    }
}