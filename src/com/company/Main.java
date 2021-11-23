package com.company;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;

public class Main {

    static int n = 0;
    static Date[] a_Date = new Date[101];
    static String[] a_Task = new String[101];

    static void help()
    {
        System.out.println("Все команды вводить с маленькой буквы.\n" +
                "\"просмотреть\" - показывает ве записи на указанный день. Вводить в виде: \"просмотреть день.месяц.год\" (пример: просмотреть 12.12.2020).\n" +
                "\"конвертировать\" - позволяет производить перевод валют по заданному вами курсу (пример: конвертировать 12,63 BYN в RUB).\n" +
                "\"добавить\" - позволяет сделать запись на указанный момент времени. Вводить в виде: \"добавить день.месяц.год-часы:минуты задача\" (пример: добавить 12.12.2021-15:15 пойти в гости).\n" +
                "\"удалить\" - удаляет выбранную запись. Вводить в виде: \"удалить день.месяц.год-часы:минуты\" (пример: удалить 12.12.2021-15:15).\n" +
                "\"выход\" - сохраняет все изменения и завершает работу.");
    }

    static int add (Date day, String task)
    {
        for(int i = 0; i < n; i++)
            if( (a_Date[i].equals(day)) && (a_Task[i].equals(task)) )
                return 2;
        a_Date[n] = day;
        a_Task[n] = task;

        n++;
        return 1;
    }

    public static int show (Date day) {
        Date[] find_Date = new Date[101];
        String[] find_Task = new String[101];
        int k = 0;

        for (int i = 0; i < n; i++) {
            if(a_Date[i].getTime()-day.getTime()<24*60*60*1000 && a_Date[i].getTime()-day.getTime()>=0){
                find_Date[k] = a_Date[i];
                find_Task[k] = a_Task[i];
                k++;
            }
        }

        for (int j = 1; j < k; j++){
            for (int i = 0; i < k-1; i++){
                if (find_Date[i].getTime() > find_Date[i+1].getTime()){
                    Date x = find_Date[i];
                    find_Date[i] = find_Date[i+1];
                    find_Date[i+1] = x;
                }
            }
        }
        

        if(k==0)
            return 2;


        DateFormat df = new SimpleDateFormat("HH:mm");
        for (int i = 0;i < k; i++ )
            System.out.println(df.format(find_Date[i])+" - "+find_Task[i]);

        return 1;
    }

    static int delete(Date day)
    {
        int i;

        if(n==0)
            return 2;

        for(i=0;(i<n-1)&&(!a_Date[i].equals(day));i++);

        if( a_Date[i].equals(day) )
        {a_Date[i]=a_Date[n-1];n--;return 1;}

        return 2;
    }

    static int convert(String s) {

        return 1;}

    static void read(){
        String s;
        Date day;
        String[] podstr;

        try {
            File file = new File("D:\\JavaProject\\untitled\\src\\com\\company\\save.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            s = reader.readLine();

            int i;
            n=Integer.parseInt(s);

            DateFormat format = new SimpleDateFormat("dd.MM.yyyy-HH:mm");

            for(i=0;i<n;i++)
            {   s=reader.readLine();
                podstr = s.split(" +",3);
                try{day = format.parse(podstr[0]);a_Date[i]=day;}catch (Exception v ){System.out.println(podstr[0]);}

                a_Task[i]=podstr[2];
            }

            fr.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    static void save(){
        try {
            File file = new File("D:\\JavaProject\\untitled\\src\\com\\company\\save.txt");
            FileWriter fw = new FileWriter(file);

            fw.write(Integer.toString(n)+'\n');

            DateFormat df = new SimpleDateFormat("dd.MM.yyyy-HH:mm");

            for (int i = 0;i < n; i++ )
                fw.write(df.format(a_Date[i])+" - "+a_Task[i]+'\n');

            fw.close();
        } catch (IOException e) {

        }
    }

    public static void main(String[] args) {
        System.out.println("Чтобы ознакомиться с инструкциями введите \"помощь\".");

        int p = 0,check;
        Date day ;
        String s;
        String[] podstr;

        Scanner in = new Scanner(System.in);

        read();

        while(p == 0) {

            s = in.nextLine();
            s = ' '+s;
            podstr = s.split(" +",4);

            switch (podstr[1]) {
                case "добавить":
                    try{
                        DateFormat format = new SimpleDateFormat("dd.MM.yyyy-HH:mm");
                        day = format.parse(podstr[2]);

                        check = add(day, podstr[3]);

                        if(check == 2)
                            System.out.println("Такая запись уже есть!");
                        else System.out.println("Успешно добавлено!");

                    } catch(Exception x){
                        System.out.println("Не удалось распознать дату либо отсуствует задача!");
                    }

                    save();

                    break;

                case "просмотреть":
                    try {
                        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
                        day = format.parse(podstr[2]);
                        check = show(day);
                        if(check == 2)
                            System.out.println("Ничего не найдено!");

                    } catch (Exception e){System.out.println("Не удалось распознать дату!");}
                    break;

                case "помощь": help();
                    break;

                case "удалить":
                    try{
                        DateFormat format = new SimpleDateFormat("dd.MM.yyyy-HH:mm");
                        day = format.parse(podstr[2]);

                        check = delete(day);
                        if(check == 2)
                            System.out.println("То,что можно было удалить, не найдено!");
                        else System.out.println("Удалено успешно!");
                    }catch(Exception x){
                        System.out.println("Не удалось распознать дату!");
                    }

                    save();

                    break;

                case "конвертировать":
                    check = convert(s);
                    if(check == -1)
                        System.out.println("Формат не совпадает!");
                    else System.out.println(check);
                    break;

                case "выход": p = 1;

                    save();

                    break;

                default:System.out.println("Команда не распознана!");
                    break;
            }


        }


    }

}
