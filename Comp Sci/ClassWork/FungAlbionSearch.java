import java.util.Scanner;
import java.util.InputMismatchException;

import java.io.FileNotFoundException;
import java.io.File;

/**
 * Write a description of class searchPrime here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FungAlbionSearch
{
    //declaration
    private static Scanner scan;
    private static String path="100000primes.txt";
    private static int[] elements;
    private static int nums, request,result;
    private static Timer time = new Timer();

    public static void main()
    {
        //initialization
        nums = 0;

        getFile();//get input source file of prmes

        request=getInput();//get input from user

        time.startTimer();
        result=linearSearch(request,elements);//search with linear search
        time.endTimer();
        System.out.println("Linear search result:");//display result
        if(result!=-1)
            System.out.println("The number is a prime number.");
        else if(result==-1)//if result is -1, the initial value, it is not a prime number
            System.out.println("The number is not a prime number.");

        System.out.println(time.getTimeString());//show time

        time.resetTimer();//reset timer
        time.startTimer();
        result=binarySearch(request,elements);//serach again but with binary
        time.endTimer();
        System.out.println("Binary search result:");//show result
        if(result!=-1)
            System.out.println("The number is a prime number.");
        else if(result==-1)//same as above
            System.out.println("The number is not a prime number.");
        System.out.println(time.getTimeString());//show time
    }

    //gets the prime numbers from the source file
    private static void getFile()
    {
        try
        {
            scan = new Scanner (new File(path));//creating scanner pathed to the file
            while(scan.hasNextInt())//if there is another integer, add 1 to the amount of numbers
            {
                nums++;
                scan.nextInt();
            }
            scan.close();

            elements=new int[nums];//initialize the saving array for the prime numbers

            scan = new Scanner (new File(path));
            for(int x=0; x<nums; x++)//assigning prime numbers to the array
            {
                elements[x]=scan.nextInt();
            }
            scan.close();
        }
        catch(FileNotFoundException e)//in case the file isn't found / permission error
        {}
    }

    //conducts linear search, item is the item to be searched for, list is the source to search in
    private static int linearSearch(int item, int[] list)
    {
        int index = -1;//initializing index

        for(int x = 0; x < list.length; x++)//finding element
        {
            if(list[x] == item)
            {
                index = x;
                break;//break when element is found
            }
        }
        return index;
    }

    //conducts linear search, item is the item to be searched for, list is the source to search in
    private static int binarySearch(int item, int[] list)
    {
        int low = 0, high = list.length-1, mid, index = -1;//creating indexes

        while(high>=low)// when the higher index is still higher than the lower index
        {
            mid = (high+low)/2;//find midpoint 
            if(item<list[mid])//when item is smaller than mid point
                high=mid-1;//cut top half off
            else if(item>list[mid])//when item is larger than mid point
                low=mid+1;//cut off bottom
            else
            {
                index=mid;//when index is the same as mid point
                break;
            }
        }
        return index;
    }

    //get input prime number from user
    private static int getInput()
    {
        int userNum=0;//initializing number
        boolean usable=false;//initializing for the loop
        do
        {
            Scanner getNum = new Scanner (System.in);
            System.out.println("Please enter a positive integer to check if it is a prime number:");//prompt input
            try
            {
                userNum=getNum.nextInt();//getting the number
            }
            catch(InputMismatchException e)//when the user did not enter an int
            {
                System.out.println("Please enter a integer(Do not put .0 at the end)");
            }
            finally
            {
                getNum.close();//closing scanner regardless
            }
            if(userNum>0)//breaks loop if the number is positive integer
                usable=true;
            else if(userNum<0)//ask for another value if number is negative
                System.out.println("Value is not positive.");
        }while(!usable);
        return userNum;
    }
}
