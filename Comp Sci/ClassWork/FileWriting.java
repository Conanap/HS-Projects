import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;
public class FileWriting
{
    private static String path = "input.txt";
    private static boolean appendToFile = false;//if it should delete everything and put in the new line or just add stuff
    private static Scanner scan = new Scanner(System.in);
    public FileWriting(String inPath)
    {
        path=inPath;
    }

    public FileWriting(String inPath, boolean append)
    {
        path = inPath;
        appendToFile=append;
    }

    public static void writeToFile(int [] last, int nums)
    {
        appendToFile=false;
        for(int x = 0; x < nums; x++)
        {
            try
            {
                WriteToFile(String.valueOf(last[x]));
            }
            catch(IOException e)
            {
                System.out.println("Writing not possible");
            }
            appendToFile=true;
        }
    }

    private static void WriteToFile (String text) throws IOException
    {
        FileWriter write = new FileWriter(path,appendToFile);
        PrintWriter printLine = new PrintWriter (write);

        printLine.printf("%s",text+"\n");
        printLine.close();
    }
}
