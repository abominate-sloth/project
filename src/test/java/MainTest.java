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
        Date day;
        Main obj = new Main();

        day = format.parse("12.08.2022-12:43:03");
        obj.add(day,"asd");
        day = format.parse("12.08.2022-12:43:02");
        obj.add(day,"asd");
        day = format.parse("12.08.2022-12:43:01");
        obj.add(day,"asd");

        obj.show(day);
        assertEquals(day, obj.a_Date[obj.n-1]);
        assertEquals("asd",obj.a_Task[obj.n-1] );

        day = format.parse("12.08.2022-12:43:03");
        obj.delete(day);
        day = format.parse("12.08.2022-12:43:02");
        obj.delete(day);
        day = format.parse("12.08.2022-12:43:01");
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

        Main obj = new Main();
        obj.convert("конвертировать 15 BYN в USD");
        obj.convert("конвертировать 15 BYN в US");
        obj.convert("конвертировать 15 BY в USD");
        obj.convert("конвертировать 1j BYN в USD");

    }

    @org.junit.jupiter.api.Test
    void read() throws ParseException{
        Main obj = new Main();
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy-HH:mm:ss");
        Date day = format.parse("12.08.2022-12:43:03");
        obj.add(day,"asd");
        day = format.parse("12.08.2022-12:43:02");
        obj.add(day,"asd");
        day = format.parse("12.08.2022-12:43:01");
        obj.add(day,"asd");

        obj.read();
        obj.save();
        obj.read();

        DateFormat format1 = new SimpleDateFormat("dd.MM.yyyy-HH:mm");

        day = format1.parse("12.08.2022-12:43");
        obj.delete(day);
        obj.delete(day);
        obj.delete(day);

        obj.save();

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
    void work() {
        Main obj = new Main();
        obj.work("конвертировать 12 BYN d EUR");
        obj.work("добавить 12.12.2019-15:15 пойти в гости");
        obj.work("просмотреть 12.12.2019-15:15");
        obj.work("удалить 12.12.2019-15:15");

        obj.work("просмотреть 12.12.2019-15:15");
        obj.work("удалить 12.12.2019-15:15");

        obj.work("добавить 12.12.201915:15 пойти в гости");
        obj.work("просмотреть 12.12.201915:15");
        obj.work("удалить 12.12.201915:15");

        obj.work("удалаить 12.12.2019-15:15");

    }

    @org.junit.jupiter.api.Test
    void main() {

    }
}