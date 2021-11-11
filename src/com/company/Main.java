package com.company;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.Date;

public class Main {

    static int n=0;
    static Date[] a_Date=new Date[101];
    static String[] a_Task=new String[101];

    static void help()
    {
        System.out.println("-help\n-show\n-convertor\n-add\n-delete\n");
    }

    static int add(Date day,String s)
    {
        return 1;
    }

    static int show(Date day)
    {
        return 1;
    }

    static int delete(Date day)
    {
        return 1;
    }

    static int convert(String s)
    {
        return 1;
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
            int p=0,i,chek;
            long x;
            Date day= new Date();
            String s,v;
            /*strDate="12.10.2020";
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        GregorianCalendar calendar = new GregorianCalendar();

        Date day= ;new Date();

        try {
            day = formatter.parse(strDate);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }*/

        while(p==0)
            {
                s=in.nextLine();
                s=s+" ";
                v="";
                i=-1;

                while(s.charAt(++i)!=' ')
                    v=v+s.charAt(i);

                switch (v) {
                    case "добавить":
                        chek=add(day,s);
                        if(chek==2)
                            System.out.println("Такая запись уже есть");
                        break;
                    case "просмотреть":
                        chek=show(day);
                        if(chek==2)
                            System.out.println("Ничего не найдено");
                        break;
                    case "помощь": help();
                        break;
                    case "удалить": chek=delete(day);
                        if(chek==2)
                            System.out.println("Таких записей не найдено");

                        break;
                    case "конвертировать":
                        chek=convert(s);
                        if(chek==-1)
                            System.out.println("Формат не совпадает");
                        break;
                    case "выход": p=1;
                        break;

                    default:System.out.println("Команда не распознана");
                        break;
                }


            }


    }

}