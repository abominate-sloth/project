package com.company;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.Date;

public class Main {

    static int n=0;
    static Date[] a_Date=new Date[101];
    static String[] a_Task=new String[101];
    static float[][] cor=new float[4][4];

    static void help()
    {
        System.out.println("Все команды вводить с маленькой буквы\n" +
                "просмотреть день.месяц.год (пример просмотреть 12.12.2020), показывает ве записи на указанный день\n" +
                "конвертировать сколько из какой валюты в какую(пример конвертировать 12,63 BYN в RUB)\n" +
                "добавить день.месяц.год-часы:минуты что я должен сделать (пример добавить 12.12.2021-15:15 пойти в гости)\n" +
                "удалить день.месяц.год-часы:минуты (удалить 12.12.2021-15:15)\n" +
                "выход сохраняет все изменения и завершает работу");
    }

    static int add(Date day,String s)
    {
       for(int i=0;i<n;i++)
           if((a_Date[i].equals(day))&&(a_Task[i].equals(s)))
            return(2);

       a_Date[n]=day;
       a_Task[n]=s;
       n++;
       return 1;
    }
    static int show(Date day) {
        Date[] find_Date=new Date[101];
        String[] find_Task=new String[101];
        int k=0;
        for (int i = 0;i < n; i++ )
            if(a_Date[i].getTime()-day.getTime()<24*60*60*1000 && a_Date[i].getTime()-day.getTime()>=0)
            {   find_Date[k]=a_Date[i];
                find_Task[k]=a_Task[i];
                k++;
            }

        for (int i = 0;i < k; i++ )
             System.out.println(find_Date[i]+"-"+find_Task[i]);

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

        Scanner in = new Scanner(System.in);
        int p=0,chek;

        Date day;
        String s;
        String[] podstr;
        SimpleDateFormat FormNo = new SimpleDateFormat("dd.MM.yyyy");

        while(p==0)
            {
                s=in.nextLine();
                s=' '+s;
                podstr = s.split(" +",4);

                switch (podstr[1]) {
                    case "добавить":
                        try{
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy-HH:mm");
                            day = Date.from(LocalDate.parse(podstr[2], formatter).atStartOfDay(ZoneId.systemDefault()).toInstant());;

                            chek=add(day,podstr[3]);

                            if(chek==2)
                                System.out.println("Такая запись уже есть");
                        }catch(Exception e){
                            try{
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy-HH");
                                day = Date.from(LocalDate.parse(podstr[2], formatter).atStartOfDay(ZoneId.systemDefault()).toInstant());;

                                chek=add(day,podstr[3]);

                                if(chek==2)
                                    System.out.println("Такая запись уже есть");
                            }catch(Exception x){
                                System.out.println("Не удалось распознать дату либо отсуствует задача");
                            }
                        }
                        break;

                    case "просмотреть":
                        try{
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                            day = Date.from(LocalDate.parse(podstr[2], formatter).atStartOfDay(ZoneId.systemDefault()).toInstant());;

                            chek=show(day);
                            if(chek==2)
                                System.out.println("Ничего не найдено!");

                        }catch(Exception e){
                            System.out.println("Не удалось распознать дату");
                        }
                        break;

                    case "помощь": help();
                        break;

                    case "удалить":
                        try{
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy-HH:mm");
                            day = Date.from(LocalDate.parse(podstr[2], formatter).atStartOfDay(ZoneId.systemDefault()).toInstant());;

                            chek=delete(day);
                        if(chek==2)
                            System.out.println("То,что можно было удалить, не найдено!");

                    }catch(Exception e){
                            try{
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy-HH");
                                day = Date.from(LocalDate.parse(podstr[2], formatter).atStartOfDay(ZoneId.systemDefault()).toInstant());;

                                chek=delete(day);
                                if(chek==2)
                                    System.out.println("То,что можно было удалить, не найдено!");

                            }catch(Exception x){

                                System.out.println("Не удалось распознать дату");
                            }
                    }
                        break;

                    case "конвертировать":
                        chek=convert(s);
                        if(chek==-1)
                            System.out.println("Формат не совпадает");
                        else System.out.println(chek);
                        break;

                    case "выход": p=1;
                        break;

                    default:System.out.println("Команда не распознана");
                        break;
                }


            }


    }

}
