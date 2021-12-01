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
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy-HH:mm:ss");
        Date day = format.parse("12.08.2022-12:43:01");
        Main obj = new Main();
        obj.add(day, "asd");
        assertEquals(day, obj.a_Date[obj.n - 1]);
        assertEquals("asd", obj.a_Task[obj.n - 1]);
        obj.delete(day);
    }

    @org.junit.jupiter.api.Test
    void show() throws ParseException {
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy-HH:mm:ss");
        Date day = format.parse("12.08.2022-12:43:01");
        Main obj = new Main();
        obj.add(day, "asd");
        obj.show(day);
        assertEquals(day, obj.a_Date[0]);
        assertEquals("asd",obj.a_Task[0] );
        obj.delete(day);
        int k=obj.delete(day);
        assertEquals(k,2);
    }

    @org.junit.jupiter.api.Test
    void delete() throws ParseException {
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy-HH:mm:ss");
        Date day = format.parse("12.08.2022-12:43:01");
        Main obj = new Main();
        obj.add(day, "asd");
        int t=obj.n;
        int k=obj.delete(day);
        assertEquals(k,1);
        assertEquals(t-1,obj.n);
        k=obj.delete(day);
        assertEquals(k,2);

    }

    @org.junit.jupiter.api.Test
    void convert() {
    }

    @org.junit.jupiter.api.Test
    void read() {
        Main obj = new Main();
        obj.read();
    }

    @org.junit.jupiter.api.Test
    void save() {
        Main obj = new Main();
        obj.save();
    }

    @org.junit.jupiter.api.Test
    void readFromWeb() {

        String url = "https://myfin.by/currency/minsk";
        Main obj = new Main();

        try{obj.readFromWeb(url);
            restore();}
        catch(Exception B){}
    }

    @org.junit.jupiter.api.Test
    void restore() {
        Main obj = new Main();
        obj.restore();
    }

    @org.junit.jupiter.api.Test
    void main() {
    }
}