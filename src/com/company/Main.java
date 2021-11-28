package com.company;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class Main {

    static int n = 0;
    static Date[] a_Date = new Date[101];
    static String[] a_Task = new String[101];
    static float[][] kurs = new float[4][4];

    static void help() {
        System.out.println("Все команды вводить с маленькой буквы.\n" +
                "\"просмотреть\" - показывает ве записи на указанный день. Вводить в виде: \"просмотреть день.месяц.год\" (пример: просмотреть 12.12.2021).\n" +
                "\"конвертировать\" - позволяет производить перевод валют по заданному вами курсу (пример: конвертировать 12.63 BYN в RUB).\n" +
                "\"добавить\" - позволяет сделать запись на указанный момент времени. Вводить в виде: \"добавить день.месяц.год-часы:минуты задача\" (пример: добавить 12.12.2021-15:15 пойти в гости).\n" +
                "\"удалить\" - удаляет выбранную запись. Вводить в виде: \"удалить день.месяц.год-часы:минуты\" (пример: удалить 12.12.2021-15:15).\n" +
                "\"выход\" - сохраняет все изменения и завершает работу.");
    }

    static int add(Date day, String task) {
        for (int i = 0; i < n; i++)
            if ((a_Date[i].equals(day)) && (a_Task[i].equals(task)))
                return 2;
        a_Date[n] = day;
        a_Task[n] = task;

        n++;
        return 1;
    }

    public static int show(Date day) {
        Date[] find_Date = new Date[101];
        String[] find_Task = new String[101];
        int k = 0, t;

        for (int i = 0; i < n; i++) {
            if (a_Date[i].getTime() - day.getTime() < 24 * 60 * 60 * 1000 && a_Date[i].getTime() - day.getTime() >= 0) {
                find_Date[k] = a_Date[i];
                find_Task[k] = a_Task[i];
                k++;
            }
        }

        for (int i = 0; i < k - 1; i++) {
            t = i;
            for (int j = i + 1; j < k; j++)
                if (find_Date[t].getTime() > find_Date[j].getTime())
                    t = j;

            Date od = find_Date[i];
            find_Date[i] = find_Date[t];
            find_Date[t] = od;

            String o = find_Task[i];
            find_Task[i] = find_Task[t];
            find_Task[t] = o;
        }


        if (k == 0)
            return 2;


        DateFormat df = new SimpleDateFormat("HH:mm");
        for (int i = 0; i < k; i++)
            System.out.println(df.format(find_Date[i]) + " - " + find_Task[i]);

        return 1;
    }

    static int delete(Date day) {
        int i;

        if (n == 0)
            return 2;

        for (i = 0; (i < n - 1) && (!a_Date[i].equals(day)); i++) ;

        if (a_Date[i].equals(day)) {
            a_Date[i] = a_Date[n - 1];
            n--;
            return 1;
        }

        return 2;
    }

    static float convert(String s) {
        String[] podstr;
        String[] val = {"BYN", "RUB", "EUR", "USD"};
        int from, to;
        float mon;

        podstr = s.split(" +", 6);

        for (from = 0; (from < 4) && !(val[from].equals(podstr[3])); from++) ;

        if (from == 4)
            return -1;

        for (to = 0; (to < 4) && !(val[to].equals(podstr[5])); to++) ;

        if (to == 4)
            return -2;

        try {
            mon = Float.parseFloat(podstr[2]);
        } catch (Exception x) {
            return (-3);
        }

        return mon * kurs[from][to];
    }

    static void read() {
        String s;
        Date day = new Date(), today = new Date();
        String[] podstr;

        try {
            File file = new File("src\\com\\company\\save.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            s = reader.readLine();

            int i, j, k;
            n = 0;
            k = Integer.parseInt(s);

            DateFormat format = new SimpleDateFormat("dd.MM.yyyy-HH:mm");

            for (i = 0; i < k; i++) {
                s = reader.readLine();
                podstr = s.split(" +", 3);
                try {
                    day = format.parse(podstr[0]);
                } catch (Exception v) {
                    System.out.println(podstr[0]);
                }

                if (today.getTime() - day.getTime() < 24 * 60 * 60 * 1000 * 2) {
                    a_Date[n] = day;
                    a_Task[n] = podstr[2];
                    n++;
                }
            }


            for (i = 0; i < 4; i++) {
                s = reader.readLine();
                podstr = s.split(" +", 4);

                for (j = 0; j < 4; j++)
                    kurs[i][j] = Float.parseFloat(podstr[j]);
            }


            fr.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    static void save() {
        try {
            int i, j;

            File file = new File("src\\com\\company\\save.txt");
            FileWriter fw = new FileWriter(file);

            fw.write(Integer.toString(n) + '\n');

            DateFormat df = new SimpleDateFormat("dd.MM.yyyy-HH:mm");

            for (i = 0; i < n; i++)
                fw.write(df.format(a_Date[i]) + " - " + a_Task[i] + '\n');

            for (i = 0; i < 4; i++) {
                for (j = 0; j < 4; j++)
                    fw.write(Float.toString(kurs[i][j]) + ' ');
                fw.write('\n');
            }

            fw.close();
        } catch (IOException e) {

        }
    }

    static void readFromWeb(String webURL) throws IOException {
        URL url = new URL(webURL);
        InputStream is = url.openStream();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;

            while ((line = br.readLine()) != null) {
                int indexMU = line.indexOf("\" selected=\"selected\">USD</option>"); // Нахождение символа в строке
                int indexME = line.indexOf("\">EUR</option>");
                int indexMR = line.indexOf("\">RUB</option>");

                {
                    if (indexMU == -1) {
                    } else {
                        int start = 15;
                        int end = indexMU;
                        char[] dst = new char[end - start];
                        line.getChars(start, end, dst, 0);
                        String str1 = String.valueOf(dst);
                        // System.out.println(str1);

                        try {
                            kurs[3][0] = Float.parseFloat(str1);

                        } catch (NumberFormatException e) {
                            //     System.err.println("Неверный формат строки!");
                        }


                    }

                }


                {
                    if (indexME == -1) {
                    } else {
                        int start2 = 15;
                        int end2 = indexME;
                        char[] dst2 = new char[end2 - start2];
                        line.getChars(start2, end2, dst2, 0);
                        String str2 = String.valueOf(dst2);

                        try {
                            kurs[2][0] = Float.parseFloat(str2);
                            //   System.out.println(f2);

                        } catch (NumberFormatException e) {
                            //   System.err.println("Неверный формат строки!");
                        }
                    }


                }
                {
                    if (indexMR == -1) {
                    } else {
                        int start3 = 15;
                        int end3 = indexMR;
                        char[] dst3 = new char[end3 - start3];
                        line.getChars(start3, end3, dst3, 0);
                        String str3 = String.valueOf(dst3);

                        try {
                            kurs[1][0] = Float.parseFloat(str3);

                        } catch (NumberFormatException e) {
                       //     
                        }
                    }


                }
            }

        }
    }

    static void restore()
    {
     int i,j;

     for(j=1;j<4;j++)
         kurs[0][j]=1/kurs[j][0];

     for(i=1;i<4;i++)
         for(j=1;j<4;j++)
             kurs[i][j]=1*kurs[i][0]*kurs[0][j];

     }


    public static void main(String[] args) throws IOException{
        System.out.println("Чтобы ознакомиться с инструкциями введите \"помощь\".");

        int p = 0, check;
        Date day;
        String s;
        String[] podstr;

        Scanner in = new Scanner(System.in);

        read();

        String url = "https://myfin.by/currency/minsk";

        try{readFromWeb(url);
        restore();}
        catch(Exception B){};

        while (p == 0) {

            s = in.nextLine();
            s = ' ' + s;
            podstr = s.split(" +", 4);
            podstr[1] = podstr[1].toLowerCase();

            switch (podstr[1]) {
                case "lj,fdbnm":
                case "добавить":
                    try {
                        DateFormat format = new SimpleDateFormat("dd.MM.yyyy-HH:mm");
                        day = format.parse(podstr[2]);

                        check = add(day, podstr[3]);

                        if (check == 2)
                            System.out.println("Такая запись уже есть!");
                        else System.out.println("Успешно добавлено!");

                    } catch (Exception x) {
                        System.out.println("Не удалось распознать дату либо отсуствует задача!");
                    }

                    save();

                    break;

                case "ghjcvjnhtnm":
                case "просмотреть":
                    try {
                        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
                        day = format.parse(podstr[2]);
                        check = show(day);
                        if (check == 2)
                            System.out.println("Ничего не найдено!");

                    } catch (Exception e) {
                        System.out.println("Не удалось распознать дату!");
                    }
                    break;

                case "gjvjom":
                case "помощь":
                    help();
                    break;

                case "elfkbnm":
                case "удалить":
                    try {
                        DateFormat format = new SimpleDateFormat("dd.MM.yyyy-HH:mm");
                        day = format.parse(podstr[2]);

                        check = delete(day);
                        if (check == 2)
                            System.out.println("То,что можно было удалить, не найдено!");
                        else System.out.println("Удалено успешно!");
                    } catch (Exception x) {
                        System.out.println("Не удалось распознать дату!");
                    }

                    save();

                    break;

                case "rjydthnbhjdfnm":
                case "конвертировать":
                    float ch = convert(s);
                    int chek = (int) ch;
                    switch (chek) {
                        case -1:
                            System.out.println("Исходная валюта не опознана!");
                            break;
                        case -2:
                            System.out.println("Итоговая валюта не опознана!");
                            break;
                        case -3:
                            System.out.println("Не удалось определить сумму, возможно она написана через ','!");
                            break;
                        default:
                            System.out.println(ch);
                            break;
                    }
                    break;

                case "ds[jl":
                case "выход":
                    p = 1;

                    save();

                    break;

                default:
                    System.out.println("Команда не распознана!");
                    break;
            }


        }


    }
}

