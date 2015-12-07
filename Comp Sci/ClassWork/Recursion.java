import java.util.Scanner;
import java.util.InputMismatchException;
/**
 * Write a description of class StringRevRec here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Recursion
{
    // instance variables 
    private static String input, result;
    private static Scanner scan;
    private static int inputNum;

    static public void main()
    {
        try
        {
            Scanner scan = new Scanner(System.in);
            System.out.println("Input string to reverse");
            input=scan.nextLine();

            result=reverse(input);

            System.out.println(result);
            scan.close();

            scan = new Scanner (System.in);
            System.out.println("Input a number");
            inputNum=scan.nextInt();
            System.out.println(checkPrime(inputNum));
            scan.close();
        }
        catch(InputMismatchException e)
        {
            System.out.println("Not a string");
        }
    }

    private static String reverse( String in)
    {
        if(in.length()==1)
            return in;
        return reverse(in.substring(1))+in.charAt(0);
    }

    public static boolean checkPrime(int in)
    {
        if(in<=1)
            return false;
        return checkPrime(in,in/2);
    }

    public static boolean checkPrime(int in, int x )
    {
        if(x<=1)
            return true;
        else if (in%x==0)
            return false;
        else
        {
            return checkPrime(in, x-1);
        }
    }
}
