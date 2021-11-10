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

    public static void convert(String s)
    {
        System.out.println("Выберете исходную валюту:\n-BYN\n-USD\n-EUR\n-RUB\n ");
        int i;
        float k, s1, s2;
        s2 =0f;
        String j,b, a;

        Scanner once = new Scanner(System.in);

       
        {
            j=once.nextLine();
            j=j+" ";
            b="";
            i=-1;

            while(j.charAt(++i)!=' ')
                b=b+j.charAt(i);}
        a = b;
        
        System.out.println("Выберете валюту, в которую нужно конвертировать:\n-BYN\n-USD\n-EUR\n-RUB\n ");
        Scanner two = new Scanner(System.in);

        
        {
            j=two.nextLine();
            j=j+" ";
            b="";
            i=-1;

            while(j.charAt(++i)!=' ')
                b=b+j.charAt(i);}

        Scanner in = new Scanner(System.in);
        System.out.println("Введите сумму c точностью до сотых и запятой между числами  : s= ");
        s1 = in.nextFloat();
        System.out.println("Введите курc c точностью до сотых и запятой между числами валют "+a+" и  "+b+": k= ");
        k = in.nextFloat();
        if(a==b)
        {	
        System.out.println(a+" "+a);
        System.out.println(s1+" "+s1);} 
        else {
        	s2 = s1 * k;
        	System.out.println(a+" "+b);
            System.out.println(s1+" "+s2);} 
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
