import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.lang.IndexOutOfBoundsException;
public class ArrayListWordExample
{
    private static Scanner scan;
    // Declare class variables
    private static ArrayList<String> wordList;
    private static boolean exitRequested;
    private static String input;

    public static void main (String[] args)
    {
        // Initialize variables and objects
        wordList = new ArrayList<String>();      
        scan = new Scanner (System.in);
        exitRequested = false;

        while (!exitRequested)
        {
            outputList();
            input = displayMenuAndGetInput();
            if (input.equals("1"))
            {
                System.out.println("Please type out the word");
                wordList.add(scan.nextLine());
                // - adds word at the end of the ArrayList
            }
            else if (input.equals("2"))
            {
                // CODE TO REMOVE A WORD
                // - allows user to choose which word they want to delete
                System.out.println("Type in number of the word to delete");
                try
                {
                    wordList.remove((scan.nextInt()-1));
                }
                catch(InputMismatchException e)
                {
                    System.out.println("Not a valid entry");
                }
                catch(IndexOutOfBoundsException a)
                {
                    System.out.println("The word you selected does not exist");
                }
            }
            else if (input.equals("3"))
            {
                exitRequested = true;
            }
            else
            {
                System.out.println("Invalid Entry");
            }
        }
    }

    private static void outputList ()
    {
        System.out.println("List of words:");
        if (wordList.size() > 0)
        {
            // list words currently in the List..
            // Should be output along with indexes. 
            // You should use the new for iteration loop we learned today
            // For example:
            // 1. Shark
            // 2. Track
            // 3. Scuba
            int x=1;
            for(String words : wordList)
            {
                System.out.println(x+". "+words);
                x++;
            }
            System.out.println();
        }
        else
        {
            System.out.println("--- Empty ---");
        }
    }

    private static String displayMenuAndGetInput()
    {
        System.out.println("1. Add a Word");
        System.out.println("2. Remove a Word");
        System.out.println("3. End Program");
        String temp = scan.nextLine();
        return temp;
    }
}