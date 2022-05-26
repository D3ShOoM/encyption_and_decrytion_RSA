import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Scanner;

public class Q2 {

    public static void main(String[] args) {
        String fileName = "t22"; //Enter file name here without extension (.rsa)

        Scanner kb = new Scanner(System.in);
        System.out.print("Enter d:");
        long d = kb.nextLong();
        System.out.print("Enter n:");
        long n = kb.nextLong();
        kb.close();
        try {
            Scanner input = new Scanner(new File(fileName+".rsa"));
            String eData = input.nextLine(), dData = new String(), data = new String();
            //eData = encrypted data
            //dData = decrypted data
            //data = normal text
            input.close();

            //find block size
            //b = block size
            int b, nSize = (String.valueOf(n)).length();
            long N2 = 0;
            for (b=0; N2 < n ;b = b+2) {
                N2 = N2*100 +78;
            }
            b=b-2;


            for (int i=0; i < eData.length() ;i=i+nSize) {
                String current = eData.substring(i, i + nSize);
                long num = Long.valueOf(current);
                String s = String.format("%0"+b+"d",mod(num, d, n));
                dData = dData + s;
            }
            //to text
            for (int i = 0; i < dData.length()-1; i=i+2) {
                String current = dData.substring(i, i+2);
                int temp = Integer.parseInt(current);
                char c = 126;
                if (temp>=0 && temp <=25) //if capital lette
                    c= (char)(temp + 65);
                else if(temp >=26 && temp <=51) //if small letter
                    c = (char)(temp + 71);
                else if(temp >=52 && temp <=61) //if digit
                    c= (char)(temp - 4);
                else if(temp == 62) {
                    c = '.';
                } else if(temp == 63) {
                    c = '?';
                } else if(temp == 64) {
                    c = '!';
                } else if(temp == 65)
                    c = ',';
                else if(temp == 66)
                    c = ';';
                else if(temp == 67)
                    c = ':';
                else if(temp == 68)
                    c = '-';
                else if(temp == 69)
                    c = '(';
                else if(temp == 70)
                    c = ')';
                else if(temp == 71)
                    c = '[';
                else if(temp == 72)
                    c = ']';
                else if(temp == 73)
                    c = '{';
                else if(temp == 74)
                    c = '}';
                else if(temp == 75)
                    c = '\'';
                else if(temp == 76)
                    c = '\"';
                else if(temp == 77)
                    c = ' ';
                else if(temp == 78)
                    c = '\n';

                data = data + c;
            }

            PrintWriter output = new PrintWriter(fileName+".dec");
            output.print(data);
            output.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static BigInteger mod(long x, long e,long n) {
        BigInteger r = BigInteger.valueOf(x);
        r = r.modPow(BigInteger.valueOf(e), BigInteger.valueOf(n));
        return r;
    }
}

