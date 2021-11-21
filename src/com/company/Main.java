package company;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Scanner;
import java.util.Date;

public class Main {

    static int n = 0;
    static Date[] a_Date = new Date[101];
    static String[] a_Task = new String[101];
    static String[] a_Time = new String[101];


    static void help()
    {
        System.out.println("Все команды вводить с маленькой буквы.\n" +
                "\"просмотреть\" - показывает ве записи на указанный день. Вводить в виде: \"просмотреть день.месяц.год\" (пример: просмотреть 12.12.2020).\n" +
                "\"конвертировать\" - позволяет производить перевод валют по заданному вами курсу (пример: конвертировать 12,63 BYN в RUB).\n" +
                "\"добавить\" - позволяет сделать запись на указанный момент времени. Вводить в виде: \"добавить день.месяц.год-часы:минуты задача\" (пример: добавить 12.12.2021-15:15 пойти в гости).\n" +
                "\"удалить\" - удаляет выбранную запись. Вводить в виде: \"удалить день.месяц.год-часы:минуты\" (пример: удалить 12.12.2021-15:15).\n" +
                "\"выход\" - сохраняет все изменения и завершает работу.");
    }

    static int add (Date day, String task, String time)
    {
        for(int i = 0; i < n; i++)
            if( (a_Date[i].equals(day)) && (a_Task[i].equals(task)) )
                return 2;
        a_Date[n] = day;
        a_Task[n] = task;
        a_Time[n] = time;
        n++;
        return 1;
    }

    public static int show (Date day) {
        Date[] find_Date = new Date[101];
        String[] find_Task = new String[101];
        String[] find_Time = new String[101];
        int k = 0;

        for (int i = 0; i < n; i++) {
            if(a_Date[i].getTime()-day.getTime()<24*60*60*1000 && a_Date[i].getTime()-day.getTime()>=0){
                find_Date[k] = a_Date[i];
                find_Task[k] = a_Task[i];
                find_Time[k] = a_Time[i];
                k++;
            }
        }
        for (int i = 0;i < k; i++ )
            System.out.println(find_Time[i]+" - "+find_Task[i]);

        return 1;
    }

    static int delete(Date day)
    {
        return 1;
    }

    static int convert(String s) {
        String[] podstr;
        podstr = s.split(" +");
        return 1;}

    public static void main(String[] args) {
        System.out.println("Чтобы ознакомиться с инструкциями введите \"помощь\".");

        Scanner in = new Scanner(System.in);
        int p = 0,check;
        Date day;
        String s, time;
        String[] podstr;

        while(p == 0) {

            s = in.nextLine();
            s = ' '+s;
            podstr = s.split(" ",4);

            switch (podstr[1]) {
                case "добавить":
                    try{
                        DateFormat format = new SimpleDateFormat("dd.MM.yyyy-HH:mm");
                        day = format.parse(podstr[2]);
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(day);
                        DateFormat dateFormat = DateFormat.getTimeInstance(DateFormat.SHORT);
                        time = dateFormat.format(calendar.getTime());

                        check = add(day, podstr[3], time);

                        if(check == 2)
                            System.out.println("Такая запись уже есть");

                    }catch(Exception e){
                        try{
                            DateFormat format = new SimpleDateFormat("dd.MM.yyyy-HH:mm");
                            day = format.parse(podstr[2]);
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(day);
                            DateFormat dateFormat = DateFormat.getTimeInstance(DateFormat.SHORT);
                            time = dateFormat.format(calendar.getTime());

                            check = add(day, podstr[3], time);

                            if(check == 2) System.out.println("Такая запись уже есть");
                        }catch(Exception x){
                            System.out.println("Не удалось распознать дату либо отсуствует задача");
                        }
                    }
                    break;

                case "просмотреть":
                    try {
                        DateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");
                        day = format1.parse(podstr[2]);
                        check = show(day);
                        if(check == 2)
                            System.out.println("Ничего не найдено!");

                    } catch (Exception e){System.out.println("Не удалось распознать дату");}
                    break;

                case "помощь": help();
                    break;

                case "удалить":
                    try{
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy-HH:mm");
                        day = Date.from(LocalDate.parse(podstr[2], formatter).atStartOfDay(ZoneId.systemDefault()).toInstant());;

                        check = delete(day);
                        if(check == 2)
                            System.out.println("То,что можно было удалить, не найдено!");

                    }catch(Exception e){
                        try{
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy-HH");
                            day = Date.from(LocalDate.parse(podstr[2], formatter).atStartOfDay(ZoneId.systemDefault()).toInstant());;

                            check = delete(day);
                            if(check == 2)
                                System.out.println("То,что можно было удалить, не найдено!");

                        }catch(Exception x){

                            System.out.println("Не удалось распознать дату");
                        }
                    }
                    break;

                case "конвертировать":
                    check = convert(s);
                    if(check == -1)
                        System.out.println("Формат не совпадает");
                    else System.out.println(check);
                    break;

                case "выход": p = 1;
                    break;

                default:System.out.println("Команда не распознана");
                    break;
            }


        }


    }

}
