import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

public class sort_old
{
    static long steps=0;
    static int nums, temp;
    static int[] numbers, sorted, usedNum;
    static String path="20_source.txt";
    static Scanner scan;
    static boolean used;

    public static void main()
    {
        try
        {
            scan = new Scanner(new File(path));
            while(scan.hasNextInt())
            {
                nums++;
                scan.nextInt();
            }
            numbers= new int[nums];
            sorted = new int[nums];
            usedNum = new int[nums];
            scan.close();

            scan = new Scanner (new File(path));
            try
            {
                for(int x = 0; x < nums; x++)
                {
                    numbers[x]=scan.nextInt();
                }
            }
            catch(NoSuchElementException e)
            {
            }
            scan.close();

            sortLargestToSmallest();
            System.out.println(checkResults(sorted,true));
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Sucks to suck");
        }
    }

    private static void sortLargestToSmallest()
    {
        for( int y = 0; y < nums; y++)
        {
            temp = numbers[0];
            used = false;
            for(int x = 0; x < nums; x++)
            {
                if(y==0)
                {
                    if(temp<=numbers[x])
                    {
                        temp=numbers[x];
                        usedNum[y]=x;
                    }
                }
                else
                {
                    if(temp<=numbers[x])
                    {
                        for(int j = 0; j < y; j++)
                        {
                            if(usedNum[j]==x)
                            {
                                used=true;
                                break;
                            }
                            else
                                used = false;
                        }
                        if(!used)
                        {
                            temp=numbers[x];
                            usedNum[y]=x;
                        }
                    }
                }
            }
            sorted[y]=temp;
        }
    }

    /**
     * Sort result checker.
     * 
     * This modular method will check any given array of integers and 
     * return true if the values are correctly sorted or false if there
     * are any errors. Setting the parameter boolean reporting to true
     * will also output the results to the screen.
     * 
     * @author Jordan Cohen
     * @version April 2014
     */
    public static boolean checkResults (int[] theArray, boolean report)
    {
        System.out.println("Checking Validity");
        boolean stillValid = true;
        int counter = 0;
        while (stillValid && counter < theArray.length - 1)
        {
            if (theArray[counter] > theArray[counter + 1])
            {
                stillValid = false;
            }
            counter++;
        }
        if (report)
        {
            if (stillValid)
            {
                System.out.println("Checked " + theArray.length + " values. Sort is valid");
            }
            else
            {
                System.out.println("Checked " + theArray.length + " values. Found error at " + counter);
            }
        }

        return stillValid;
    }
}