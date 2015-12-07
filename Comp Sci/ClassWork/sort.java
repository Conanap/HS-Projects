import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class sort
{
    static int [] num, sorted, usedNum;
    static int nums,temp,type;
    static String path;
    static Scanner scan;
    static long step=0;
    static Timer time = new Timer();
    public static void main ()
    {
        nums = 0;

        System.out.println("Please type in the name of the source document: (without extension)");
        scan = new Scanner(System.in);
        path = scan.nextLine()+".txt";
        scan.close();

        try
        {
            scan = new Scanner (new File(path));

            while(scan.hasNextInt())// getting amount of numbers in the file
            {
                nums++;
                scan.nextInt();
            }
            scan.close();

            num = new int [nums]; //initializing variables
            sorted = new int [nums];
            usedNum = new int [nums];

            scan = new Scanner (new File(path));
            for(int x = 0; x<nums;x++)
            {
                num[x]=scan.nextInt();//putting values from the file into the arrays
            }
            scan.close();

            scan = new Scanner(System.in);
            System.out.println("Please choose a type of sort:");
            System.out.println("1. The Albion Special");
            System.out.println("2. Bubble Sort");
            System.out.println("3. Merge Sort");
            type=(scan.nextInt());

            time.startTimer();
            if(type==1)
            {
                mySort();
                System.out.println(step+" Steps were taken");
            }
            else if(type==2)
            {
                bubbleSort();
                System.out.println(step+" Steps were taken");
            }
            else if(type==3)
            {
                sorted=Mergesort.mergesort(num);
                System.out.println(step+" Steps were taken");
            }
            time.endTimer();
            
            System.out.println(time.getTimeString());
            checkResults(sorted, true);
            System.out.println(sorted[0]+" "+sorted[1]);
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Sucks to suck");
        }
    }
    
    /**
     * Add the amount of steps to the int step of the class
     * 
     * @param change  Amount of steps
     */
    public static void changeStep(int change)
    {
        step+=change;
    }

    private static void mySort ()
    {
        int switching=0;//initializing and declaration of local variable
        for (int y = 0; y < nums; y++)
        {
            temp=num[nums-1];//set temp to the last number on the array
            step++;
            for(int x = nums-2; x >= 0+y; x--)//comparing backwards because 
            {
                if(temp>num[x])//Finding the smallest unsorted
                {
                    temp=num[x];//set temp to the smaller of the compared
                    step++;
                    switching = x;//get the index for the number to be moved
                }
                step++;
            }
            num[switching]=num[y];//switching the number that was smallest of the unsorted with the first slot after the sorted values
            num[y]=temp;
            step+=2;
        }
        sorted=num;
    }

    private static void bubbleSort ()
    {
        for (int i =0; i <nums; i++)
        {
            for(int x = 1; x <nums - i; x++)
            {
                step++;
                if(num[x-1]>num[x])//comparing the last 2
                {
                    int temp = num[x-1];//mking the larger value move to closer to the end of the array
                    num[x-1]=num[x];//switching the numbers
                    num[x]=temp;
                    step+=4;
                }
            }
        }
        sorted=num;
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