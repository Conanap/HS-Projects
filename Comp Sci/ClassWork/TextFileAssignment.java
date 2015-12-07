/**
 * Text File Manipulation Task - (Mr. Cohen, 2013)
 * ===============================================
 * 
 * 1. You will add code in areas marked with CAPITALIZED
 *    comments. You may also add methods and other classes
 *    as necessary, but don't make other changes to main().
 * 
 * 2. Please change the name of the class to LastFirst
 *    before you submit. Make sure this change is reflected
 *    in the file name AND the class name.
 *    
 * Application (5): 
 *  - Applies concepts from lesson (closing file, reading
 *    ArrayList to screen, writing new file) correctly
 * 
 */

// Imports
import java.util.Scanner;
import java.util.ArrayList;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.lang.IndexOutOfBoundsException;

public class TextFileAssignment
{
    // Class objects:
    private static Scanner scan;
    private static ArrayList<String> textFileContents;
    private static ArrayList<String> updatedText;
    private static String path;
    private static boolean appendToFile,loop=true;
    private static Scanner inp = new Scanner (System.in);
    public static void main (String[] args)
    {
        // Initialize objects
        textFileContents = new ArrayList<String>();
        updatedText = new ArrayList<String>();
        try {
            System.out.println("Type in the input file path without the file extensions");
            path=inp.nextLine()+".txt";
            scan = new Scanner (new File (path));
            // Make use of two interesting new methods found on the Scanner API
            while (scan.hasNext())
            {
                // Use the ArrayList's add() method and the Scanner's nextLine() method
                textFileContents.add(scan.nextLine()); 
            }

            // WRITE CONTENTS OF updatedText TO A TEXT FILE
            outputOperations();
        }
        //         catch (FileNotFoundException e)
        //         {
        //             System.out.println("File not found");
        //         }
        catch (IOException e)
        {
            System.out.println("Error reading data");
        }

        // ADD A FINALLY BLOCK HERE TO CLOSE THE SCANNER
        finally
        {
            if (scan!=null)
                scan.close();
        }
    }

    private static void outputOperations()
    {
        Scanner input = new Scanner (System.in);
        loop=true;
        do
        {
            try
            {
                System.out.println("For converting input text to capitalization, choose 1");
                System.out.println("For removing a specific line, choose 2");
                if(input.nextInt()==1)
                {
                    input.close();
                    // Now, let's read through the ArrayList and build a new
                    // ArrayList for holding the new data:
                    for (String line : textFileContents)
                    {
                        // READ DATA FROM textFileContents AND USE STRING'S toUpperCase() 
                        // METHOD TO CONVERT TO CAPS, AND ADD() IT TO NEW ARRAYLIST
                        // updateText.
                        updatedText.add(line.toUpperCase());
                    }

                    // DISPLAY THE CONTENTS OF THE ARRAYLIST ON THE SCREEN
                    // (HINT: ANOTHER FOR LOOP GOES HERE)
                    for(String lines : updatedText)
                    {
                        System.out.println(lines);
                    }
                    writeFileText();
                    loop=false;
                }
                else if(input.nextInt()==2)
                {
                    input.close();
                    int removeChoice;
                    Scanner input2 = new Scanner(System.in);
                    System.out.println("Which line number would you like to remove? Please enter the number of it only");
                    for (String line : textFileContents)
                    {
                        int x =1;
                        System.out.println(x+". "+line);
                    }
                    boolean lego=true;
                    do
                    {
                        removeChoice=input2.nextInt()-1;
                        try
                        {
                            textFileContents.remove(removeChoice);
                            lego=false;
                        }
                        catch(IndexOutOfBoundsException e)
                        {
                            System.out.println("Not a valid choice.");   
                        }
                    }while(lego);
                }

                else
                    System.out.println("Invalid entry. Please try again");
            }
            catch(InputMismatchException e)
            {
                System.out.println("Invalid entry. Please try again");
            }
        }while(loop);
    }

    private static void WriteToFile() throws IOException
    {
        FileWriter write = new FileWriter(path,appendToFile);
        PrintWriter printLine=new PrintWriter (write);
        for(String s : updatedText)
        {
            printLine.printf("%s"+"%n",s);
        }
        printLine.close();
    }

    private static void writeFileText()
    {
        boolean loopy=false;
        do{
            try
            {
                System.out.println("Please enter output path without the file extension");
                path=inp.nextLine()+".txt";
                File file = new File (path);
                if (file.createNewFile())
                {
                    WriteToFile();
                }
                else
                {
                    System.out.println("Unable to create file, file may already exist");
                    System.out.println("Would you like to replace the original text? y/n");
                    String replace = inp.nextLine();
                    if(replace.equals("y")||replace.equals("Y")||replace.equals("Yes")||replace.equals("yes"))
                        appendToFile=false;
                    else if(replace.equals("n")||replace.equals("N")||replace.equals("No")||replace.equals("no"))
                        appendToFile=true;
                    else
                    {
                        System.out.println("Invalid entry, not replacing text");
                        appendToFile=true;
                    }
                    WriteToFile();
                }
                loopy=false;
                System.out.println("Writing operation successful");
                inp.close();
            }
            catch(IOException e)
            {
                System.out.println("An error occurred during the operation");
                loopy=false;
            }
            catch(InputMismatchException e)
            {
                System.out.println("Not a valid entry. Please try again");
                loopy=true;
            }
        }while(loopy);
    }
}