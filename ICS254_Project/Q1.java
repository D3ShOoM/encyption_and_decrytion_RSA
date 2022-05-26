import java.io.*;
import java.math.BigInteger;
import java.util.Scanner;

public class Q1 {

    public static void main(String[] args) {
        try {
            String fileName = "t22"; //Enter file name here without extension (.txt)
            Scanner input = new Scanner(new File(fileName+".txt"));

            long e = input.nextLong();
            long n = input.nextLong();
            input.nextLine();
            String data = new String(), nData = new String(), eData = new String();
            //data = normal text
            //nData = text to numbers
            //eData = encrypted text
            do {
                    data = data + input.nextLine()+ (input.hasNext()?"\n":"") ;

                }while(input.hasNext());
            input.close();
            for (int i = 0; i < data.length(); i++) {
                    Character c = data.charAt(i);
                    int temp = c;//Ascii value
                    if (temp>=65 && temp <=90) //if capital letter
                        temp = temp - 65;
                else if(temp >=97 && temp <=122) //if small letter
                    temp = temp - 71;
                else if(temp >=48 && temp <=57) //if digit
                    temp = temp + 4;
                else if(c.equals('.'))
                    temp = 62;
                else if(c.equals('?'))
                    temp = 63;
                else if(c.equals('!'))
                    temp = 64;
                else if(c.equals(','))
                    temp = 65;
                else if(c.equals(';'))
                    temp = 66;
                else if(c.equals(':'))
                    temp = 67;
                else if(c.equals('-'))
                    temp = 68;
                else if(c.equals('('))
                    temp = 69;
                else if(c.equals(')'))
                    temp = 70;
                else if(c.equals('['))
                    temp = 71;
                else if(c.equals(']'))
                    temp = 72;
                else if(c.equals('{'))
                    temp = 73;
                else if(c.equals('}'))
                    temp = 74;
                else if(c.equals('\''))
                    temp = 75;
                else if(c.equals('\"'))
                    temp = 76;
                else if(c.equals(' '))
                    temp = 77;
                else if(c.equals('\n'))
                    temp = 78;
                String t = Integer.toString(temp);
                if (temp < 10)
                    t = "0"+t;
                nData = nData + t;
            }

            //find block size
            //b = block size


            int b, nSize = (String.valueOf(n)).length();
            long N2 = 0;
            for (b=0; N2 < n ;b = b+2) {
                N2 = N2*100 +78;
            }

            b=b-2;

            int m = nData.length()%b;

            if(m!=0)
                for (int i = 0; i < (b-m)/2; i++) {
                nData = nData + "23";
                 }

            for (int i=0; i < nData.length() ;i=i+b) {
                String current = nData.substring(i, i + b);
                long num = Long.valueOf(current);
                String s = String.format("%0"+nSize+"d", mod(num, e, n));
                //System.out.println(s);
                eData = eData + s;
            }

            PrintWriter output = new PrintWriter(fileName+".rsa");
            output.print(eData);
            output.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static BigInteger mod(long x, long e,long n) {
//        long r = 1;
//        for (int i = 0; i < e; i++) {
//            r = (r * x) % n;
//        }
//        return r;
        BigInteger r = BigInteger.valueOf(x);
        r = r.modPow(BigInteger.valueOf(e), BigInteger.valueOf(n));
        return r;
    }

}
