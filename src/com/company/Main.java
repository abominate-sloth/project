package com.company;
import java.util.Scanner;


public class Main {

    static int n=0;
    static String[] a=new String[101];

    static void help()
    {
        System.out.println("-help\n-show\n-convertor\n-add\n-delete\n");
    }

    static void add(String s)
    {
        int i;
        String g;
        g="";

        for (i=5;i<s.length();i++)
            g=g+s.charAt(i);

        a[n]=g;
        n=n+1;
    }

    static void show()
    {
        int i=0;
        for(i=0;i<n;i++)
            System.out.println(a[i]);
    }

    static void delete(String s)
    {
        int i;
        String g;
        a[n]="";
        g="";

        for (i=8;i<s.length();i++)
            g=g+s.charAt(i);

        i=0;

        while(!(a[i].equals(g))&&(i<n))
            i++;

        if(i<n)
        {n--;a[i]=a[n];}
        else System.out.println("Такой записи не найдено");

    }

    static void convert(String s)
    {
        System.out.println("В разработке ");
    }

    public static void main(String[] args) {

            int p=0,i;
            String s,v;

            Scanner in = new Scanner(System.in);

            while(p==0)
            {
                s=in.nextLine();
                s=s+" ";
                v="";
                i=-1;

                while(s.charAt(++i)!=' ')
                    v=v+s.charAt(i);

                if(v.equals("-add"))
                    add(s);

                if(v.equals("-show"))
                    show();

                if(v.equals("-help"))
                    help();

                if(v.equals("-delete"))
                    delete(s);

                if(v.equals("-convert"))
                    convert(s);

                if(v.equals("-exit"))
                    p=1;

            }


    }

}
