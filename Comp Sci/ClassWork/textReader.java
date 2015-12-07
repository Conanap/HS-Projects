import java.util.Scanner;
import java.util.NoSuchElementException;
import java.io.File;
import java.io.FileNotFoundException;
public class textReader
{
    static private String textName, clear="\n";
    static private Scanner s;
    static private Scanner in = new Scanner(System.in);
    static boolean moreLines = true, read=true;
    static int numLines =0;
    public static void main()
    {
        do
        {
            for(int i=0; i<=100; i++)
            {
                System.out.print(clear);
            }
            System.out.println("Please enter the text file name without the file extensions");
            textName=in.nextLine();
            textName=(textName+".txt");
            try
            {
                s = new Scanner (new File(textName));
                read=false;
            }
            catch(FileNotFoundException e)
            {
                for(int i=0; i<=100; i++)
                {
                    System.out.print(clear);
                }
                System.out.println("Invalid file name");
            }
        }while(read);
        for(int i=0; i<=100; i++)
        {
            System.out.print(clear);
        }
        while(moreLines)
        {

            try
            {
                System.out.println(s.nextLine());
                numLines++;
            }
            catch (NoSuchElementException e)
            {

                moreLines = false;
                System.out.println("End: "+numLines+" lines.");
            }
        }
    }
}
