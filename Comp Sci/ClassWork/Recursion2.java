
/**
 * Write a description of class Recursion2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Recursion2
{
    // instance variables

    public static void nToOne(int n)
    {
        System.out.println(n);
        if(n>1)
            nToOne(n-1);
    }

    public static void oneToN(int n)
    {
        if(n>1)
            oneToN(n-1);
        System.out.println(n);
    }

    public static int sigmaN(int n)
    {
        if(n>=1)
            return n+sigmaN(n-1);
        return 0;
    }

    public static int sequenceA(int n)
    {
        if(n>1)
            return 2+sequenceA(n-1);
        return 3;
    }

    public static int sequenceB(int n)
    {
        if(n>1)
            return 2*sequenceB(n-1);
        return 1;
    }

    public static int sequenceC(int n)
    {
        if(n>2)
            return (sequenceC(n-1))*(sequenceC(n-2));
        else if(n>1)
            return 2;
        return 1;
    }
    
    public static int sequenceD(int n)
    {
        if(n>2)
            return (sequenceD(n-1))+(sequenceD(n-2));
        else if(n>1)
            return 5;
        return 2;
    }
}
