/**
 * CODING QUIZ QUESTION - THE TEXT MAPPER
 * 
 * Before you begin, please RENAME this class (at the class
 * declaration, as well as the file name) to LastFirstMapper.
 * 
 * Mapper is a class that creates and maintains a 2d array.
 * 
 * The array starts off as a 10x10 list of O's (upper case "o" - NOT zero)
 * and is altered by input from a file.
 * 
 * The text file to be read is called "requests.txt"
 * 
 * Each line within requests.txt contains 2 ints and a single letter.
 * For example:
 * 4 6 F   
 * 
 * This would form a request to replace the contents of the array at [4][6]
 * with the letter F. The file requests.txt can have any number of requests
 * inside it. The program will output the initial setup (all O's) as well
 * as the final configuration, including the result of all requests.
 * 
 * Follow the instructions in the comments carefully. Please do not make
 * changes to the existing code, and do not delete the existing comments.
 * 
 * @author Albion Fung
 * @version 0.0.0
 */
//imports
import java.util.Scanner;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import java.lang.ArrayIndexOutOfBoundsException;

import java.io.File;
import java.io.FileNotFoundException;

public class FungAlbionMapper
{
    private static char[][] map;
    private static ArrayList<String> requests;
    private static Scanner fileScan;
    private static int sucOp=0;//counting successful operations
    private static int unsucOp=0;//counting unsuccessful operations
    public static void main (String[] args)
    {
        map = new char[10][10];

        // USE NESTED FOR LOOPS TO POPULATE THE map ARRAY WITH ALL 'O's
        // (Capital letter 'O', not zero '0')
        for(int x = 0; x < 10; x++)
        {
            for (int y = 0; y < 10; y++)
            {
                map[y][x]='O';//mactching the output style of displayMap()
            }
        }
        System.out.println("Starting Map:");
        displayMap();

        // array is populated by getRequestsFromFile() method, which returns
        // an ArrayList.
        requests = getRequestsFromFile();
        // Method call to apply requests to map
        processRequests(requests);

        System.out.println("\nFinal Map:");
        displayMap();
    }

    private static void displayMap ()
    {
        // USE NESTED FOR LOOPS TO DISPLAY CONTENTS OF map 2D ARRAY.
        for(int x = 0; x < 10; x++)
        {
            for (int y = 0; y < 10; y++)
            {
                System.out.print(map[y][x]);//actual output on the example output is this way
            }
            System.out.println();
        }
        System.out.println();//for formatting
    }

    private static ArrayList<String> getRequestsFromFile ()
    {
        ArrayList<String> fileInput = new ArrayList<String>();
        // USE A TRY/CATCH/FINALLY BLOCK TO OPEN "requests.txt" AND
        // LOAD ITS CONTENTS INTO THE ARRAYLIST fileInput. MAKE SURE TO
        // CATCH EXCEPTIONS AND CLOSE THE SCANNER APPROPRIATELY.
        try
        {
            fileScan = new Scanner(new File("requests.txt"));//creating scanner with directory of requests.txt
            try
            {
                for(;;)
                {
                    fileInput.add(fileScan.nextLine());//add lines from file to array list, stops when there's no lines left
                }
            }
            catch (NoSuchElementException e)
            {
            }
            finally
            {
                fileScan.close();//closes scanner as long as it was opened
            }
        }
        catch(FileNotFoundException e)//when requests.txt cannot be found in root folder/permission does not allow it to
        {
            System.out.println("File not found, no changes to the map will be made");
        }

        return fileInput;
    }

    private static void processRequests (ArrayList<String> requests)
    {
        int xCo, yCo;
        // USE A FOR-EACH LOOP TO PROCESS ALL OF THE REQUESTS. REMEMBER
        // THAT EACH REQUEST IS A PAIR OF NUMBERS AND A LETTER IN THE FORMAT:
        // 7 9 E
        // YOU CAN ASSUME THAT THE VALUES ARE SINGLE DIGITS (4 6 H) BUT YOU
        // SHOULD NOT ASSUME THAT THEY ARE ALL VALID. FOR EXAMPLE, AN INVALID
        // LINE WOULD BE "5 T T".. THIS WILL CAUSE AN EXCEPTION WHEN TRYING TO
        // PARSE THE INT. THESE ERRORS SHOULD BE CAUGHT, AND YOU SHOULD OUTPUT
        // A WARNING BUT ENSURE THAT THE PROGRAM CONTINUES TO PROCESS THE REST
        // OF THE REQUESTS (NOT CRASH OR QUIT).
        //
        // AFTER ALL REQUESTS HAVE BEEN PROCESSED, OUTPUT A SUMMARY TELLING THE
        // USER HOW MANY OPERATIONS WERE SUCCESSFUL AND HOW MANY FAILED (THREW
        // AN EXCEPTION).
        for(String s : requests)//for each string in array list of string requets
        {//the following method, when a character not a number is inputted, will make it out of bounds.
            xCo = s.charAt(0) - 48;//get the first number, which should be the x coordinate
            yCo = s.charAt(2) - 48;//get the second number, which should be y coordinate
            try
            {
                map[xCo][yCo] = s.charAt(4);//get the third character and replace original
                sucOp++;//add a count to successful operations if no error
            }
            catch(ArrayIndexOutOfBoundsException e)
            {
                unsucOp++;//add a count to unsuccessful operations when exception caught
            }
        }

        System.out.println(sucOp+" successful operation(s)");//displaying the amount of successful and unsuccessful operations after all operations are complete
        System.out.println(unsucOp+ " unsuccessful operation(s)");
    }
}