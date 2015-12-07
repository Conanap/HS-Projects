import java.lang.ArrayIndexOutOfBoundsException;
public class ArrayRecursionProblem
{
    public static void main (String[] args)
    {
        // Setup and display starter grid:
        char[][] grid = setupGrid();            
        displayGrid(grid);

        // Call floodGrid with appropriate parameters to 
        // flood the char 'T' too all spaces bounded by '*'s
        System.out.println("\n");
        floodGrid (grid, 5, 5, 'T', '*');
        displayGrid(grid);
    }

    /*
     * Prepare the grid for this exercise.
     * 
     * You can change this method to try different
     * configurations and make sure your flood recursive
     * algorithm works
     */
    private static char[][] setupGrid ()
    {
        char[][] grid = new char[10][10];
        // Flood with .
        for (int i = 0; i < grid.length; i++)
        {
            for (int j = 0; j < grid[0].length; j++)
            {
                grid[i][j] = '.';
            }
        }

        // Add "walls" of *
        for (int i = 1; i < grid.length - 1; i++)
        {
            grid[i][1] = '*';
            grid[i][grid.length-2] = '*';
        }
        for (int i = 2; i < grid.length - 2; i++)
        {
            grid[1][i] = '*';
            grid[grid.length-2][i] = '*';
        }
        return grid;
    }

    /*
     * Simple method to display contents of 2d array.
     * Put in it's own method to keep code clean!
     */
    private static void displayGrid (char[][] grid)
    {
        for (int i = 0; i < grid.length; i++)
        {
            for (int j = 0; j < grid[0].length; j++)
            {
                // Display with i and j reversed, to
                // "rotate" it so that [][] represents
                // x and y, sort of...
                System.out.print(grid[j][i]);
            }
            System.out.println();
        }
    }

    /**
     * Grid-flooding method. Will use a recursively flood the given grid, 
     * at the given pixel, with a given character. For example:
     * 
     * floodGrid (grid, 5, 5, 'T', '*') 
     * 
     * ... Will try to move in every direction, changing everything
     * that isn't a '*'(bounder) or a 'T'(flooder) into a 'T'.
     * 
     * Notice - void return type. Base case will return nothing:
     * return;
     * 
     * @param char[][]  A copy of the grid to manipulate
     * @param int       the x coordinate to try to change
     * @param int       the y coordinate to try to change
     * @param char      the character you wish to fill with (flooder)
     * @param char      the character that will serve as a wall (bounder)
     */
    public static void floodGrid (char[][] grid, int x, int y, char flooder, char bounder)
    {
        // base case: char is already a flooder or a bounder
        if (grid[x][y] == flooder|| grid[x][y] == bounder)
            return;

        grid[x][y]='T';
        try
        {
            floodGrid(grid,x+1,y,flooder,bounder);
        }
        catch(ArrayIndexOutOfBoundsException e){}
        try
        {
            floodGrid(grid,x-1,y,flooder,bounder);
        }
        catch(ArrayIndexOutOfBoundsException e){}
        try
        {
            floodGrid(grid,x,y+1,flooder,bounder);
        }
        catch(ArrayIndexOutOfBoundsException e){}
        try
        {
            floodGrid(grid,x,y-1,flooder,bounder);
        }
        catch(ArrayIndexOutOfBoundsException e){}
    }
}