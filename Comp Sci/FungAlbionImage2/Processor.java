/**
 * Starter code for Processor - the class that processes images.
 * <p>
 * This class manipulated Java BufferedImages, which are effectively 2d arrays
 * of pixels. Each pixel is a single integer packed with 4 values inside it.
 * <p>
 * I have included two useful methods for dealing with bit-shift operators so
 * you don't have to. These methods are unpackPixel() and packagePixel() and do
 * exactly what they say - extract red, green, blue and alpha values out of an
 * int, and put the same four integers back into a special packed integer. 
 * 
 * @author Jordan Cohen 
 * @version November 2013
 */

import java.awt.image.BufferedImage;
import java.util.List;

public class Processor  
{
    /**
     * Example colour altering method by Mr. Cohen. This method will
     * increase the blue value while reducing the red and green values.
     * 
     * Demonstrates use of packagePixel() and unpackPixel() methods.
     * 
     * @param bi    The BufferedImage (passed by reference) to change.
     */
    public static void blueify (BufferedImage bi)
    {
        // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();

        // Using array size as limit
        for (int x = 0; x < xSize; x++)
        {
            for (int y = 0; y < ySize; y++)
            {
                // Calls method in BufferedImage that returns R G B and alpha values
                // encoded together in an integer
                int rgb = bi.getRGB(x, y);

                // Call the unpackPixel method to retrieve the four integers for
                // R, G, B and alpha and assign them each to their own integer
                int[] rgbValues = unpackPixel (rgb);
                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];

                // make the pic BLUE-er
                if (blue <= 255)
                    blue += 2;
                if (red >= 50)
                    red--;
                if (green >= 50)
                    green--;

                int newColour = packagePixel (red, green, blue, alpha);
                bi.setRGB (x, y, newColour);
            }
        }

    }

    public static void flipHorizontal (BufferedImage bi)
    {

        int xSize = bi.getWidth();
        int ySize = bi.getHeight();

        // Temp image, to store pixels as we reverse everything
        BufferedImage newBi = new BufferedImage (xSize, ySize, 3);

        /**
         * INSERT TWO LOOPS HERE:
         * - FIRST LOOP MOVES DATA INTO A SECOND, TEMPORARY IMAGE WITH PIXELS FLIPPED
         *   HORIZONTALLY
         * - SECOND LOOP MOVES DATA BACK INTO ORIGINAL IMAGE
         * 
         * Note: Due to a limitation in Greenfoot, you can get the backing BufferedImage from
         *       a GreenfootImage, but you cannot set or create a GreenfootImage based on a 
         *       BufferedImage. So, you have to use a temporary BufferedImage (as declared for
         *       you, above) and then copy it, pixel by pixel, back to the original image.
         *       Changes to bi in this method will affect the GreenfootImage automatically.
         */ 
        for(int x = 0; x < xSize;x++)
        {
            for(int y = 0; y < ySize; y++)
            {
                int rgb = bi.getRGB(xSize-x-1,y);

                int[] rgbValues = unpackPixel (rgb);
                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];

                int newColour = packagePixel (red, green, blue, alpha);
                newBi.setRGB (x, y, newColour);
            }
        }

        for(int x = 0; x < xSize;x++)
        {
            for(int y = 0; y < ySize; y++)
            {
                int rgb = newBi.getRGB(x,y);

                int[] rgbValues = unpackPixel (rgb);
                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];

                int newColour = packagePixel (red, green, blue, alpha);
                bi.setRGB (x, y, newColour);
            }
        }
    }

    public static void flipVertical (BufferedImage bi)
    {

        int xSize = bi.getWidth();
        int ySize = bi.getHeight();

        // Temp image, to store pixels as we reverse everything
        BufferedImage newBi = new BufferedImage (xSize, ySize, 3);

        /**
         * INSERT TWO LOOPS HERE:
         * - FIRST LOOP MOVES DATA INTO A SECOND, TEMPORARY IMAGE WITH PIXELS FLIPPED
         *   HORIZONTALLY
         * - SECOND LOOP MOVES DATA BACK INTO ORIGINAL IMAGE
         * 
         * Note: Due to a limitation in Greenfoot, you can get the backing BufferedImage from
         *       a GreenfootImage, but you cannot set or create a GreenfootImage based on a 
         *       BufferedImage. So, you have to use a temporary BufferedImage (as declared for
         *       you, above) and then copy it, pixel by pixel, back to the original image.
         *       Changes to bi in this method will affect the GreenfootImage automatically.
         */ 
        for(int x = 0; x < xSize;x++)
        {
            for(int y = 0; y < ySize; y++)
            {
                int rgb = bi.getRGB(x,ySize-y-1);

                int[] rgbValues = unpackPixel (rgb);
                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];

                int newColour = packagePixel (red, green, blue, alpha);
                newBi.setRGB (x, y, newColour);
            }
        }

        setNewBi(xSize, ySize, bi, newBi);
    }

    public static void mirrorRightSide(BufferedImage bi)
    {
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();
        /**
         * INSERT TWO LOOPS HERE:
         * - FIRST LOOP MOVES DATA INTO A SECOND, TEMPORARY IMAGE WITH PIXELS FLIPPED
         *   HORIZONTALLY
         * - SECOND LOOP MOVES DATA BACK INTO ORIGINAL IMAGE
         * 
         * Note: Due to a limitation in Greenfoot, you can get the backing BufferedImage from
         *       a GreenfootImage, but you cannot set or create a GreenfootImage based on a 
         *       BufferedImage. So, you have to use a temporary BufferedImage (as declared for
         *       you, above) and then copy it, pixel by pixel, back to the original image.
         *       Changes to bi in this method will affect the GreenfootImage automatically.
         */ 
        for(int x = 0; x < xSize;x++)
        {
            for(int y = 0; y < ySize; y++)
            {
                int rgb = bi.getRGB(xSize-x-1,y);

                int[] rgbValues = unpackPixel (rgb);
                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];

                int newColour = packagePixel (red, green, blue, alpha);
                bi.setRGB (x, y, newColour);
            }
        }
    }

    public static BufferedImage rotateCW90 (BufferedImage bi)
    {
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();

        BufferedImage newBi = new BufferedImage(ySize,xSize,3);

        for(int x = 0; x < xSize; x++)
        {
            for(int y = ySize-1; y >= 0; y --)
            {
                int rgb = bi.getRGB(x,y);

                int[] rgbValues = unpackPixel (rgb);
                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];

                int newColour = packagePixel (red, green, blue, alpha);
                newBi.setRGB (ySize-1-y,x, newColour);
            }
        }
        return newBi;
    }

    public static BufferedImage rotateCCW90 (BufferedImage bi)
    {
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();

        BufferedImage newBi = new BufferedImage(ySize,xSize,3);

        for(int x = xSize-1; x >= 0; x--)
        {
            for(int y = 0; y < ySize; y ++)
            {
                int rgb = bi.getRGB(x,y);

                int[] rgbValues = unpackPixel (rgb);
                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];

                int newColour = packagePixel (red, green, blue, alpha);
                newBi.setRGB (y,xSize-1-x, newColour);
            }
        }
        return newBi;
    }

    public static void grayScale (BufferedImage bi)
    {
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();
        for(int x = 0; x < xSize;x++)
        {
            for(int y = 0; y < ySize; y++)
            {
                int rgb = bi.getRGB(x,y);

                int[] rgbValues = unpackPixel (rgb);
                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];
                red = green = blue = (int)(red * 0.299 + green * 0.587 + blue * 0.114);
                int newColour = packagePixel (red, green, blue, alpha);
                bi.setRGB (x, y, newColour);
            }
        }
    }

    public static void negativeColor (BufferedImage bi)
    {
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();
        for(int x = 0; x < xSize;x++)
        {
            for(int y = 0; y < ySize; y++)
            {
                int rgb = bi.getRGB(x,y);

                int[] rgbValues = unpackPixel (rgb);
                int alpha = rgbValues[0];
                int red = 255-rgbValues[1];
                int green = 255-rgbValues[2];
                int blue = 255-rgbValues[3];
                int newColour = packagePixel (red, green, blue, alpha);
                bi.setRGB (x, y, newColour);
            }
        }
    }

    public static void brighter (BufferedImage bi)
    {
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();
        for(int x = 0; x < xSize;x++)
        {
            for(int y = 0; y < ySize; y++)
            {
                int rgb = bi.getRGB(x,y);

                int[] rgbValues = unpackPixel (rgb);
                int alpha = rgbValues[0];
                int red = rgbValues[1];
                if(red<250)
                    red+=5;
                int green = rgbValues[2];
                if(green<250)
                    green+=5;
                int blue = rgbValues[3];
                if(blue<250)
                    blue+=5;
                int newColour = packagePixel (red, green, blue, alpha);
                bi.setRGB (x, y, newColour);
            }
        }
    }

    public static void darker (BufferedImage bi)
    {
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();
        for(int x = 0; x < xSize;x++)
        {
            for(int y = 0; y < ySize; y++)
            {
                int rgb = bi.getRGB(x,y);

                int[] rgbValues = unpackPixel (rgb);
                int alpha = rgbValues[0];
                int red = rgbValues[1];
                if(red>5)
                    red-=1;
                int green = rgbValues[2];
                if(green>5)
                    green-=1;
                int blue = rgbValues[3];
                if(blue>5)
                    blue-=1;
                int newColour = packagePixel (red, green, blue, alpha);
                bi.setRGB (x, y, newColour);
            }
        }
    }
    
    public static void removeAlpha(BufferedImage bi)
    {
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();
        
        BufferedImage newBi = new BufferedImage(ySize,xSize,BufferedImage.TYPE_INT_RGB);
        for(int x = 0; x < xSize;x++)
        {
            for(int y = 0; y < ySize; y++)
            {
                int rgb = bi.getRGB(x,y);

                int[] rgbValues = unpackPixel (rgb);
                int alpha = rgbValues[0];
                int red = rgbValues[1];
                if(red>5)
                    red-=1;
                int green = rgbValues[2];
                if(green>5)
                    green-=1;
                int blue = rgbValues[3];
                if(blue>5)
                    blue-=1;
                int newColour = packagePixel (red, green, blue,alpha);
                bi.setRGB (x, y, newColour);
            }
        }
    }
    
    public static void redGreen (BufferedImage bi)
    {
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();
        
        BufferedImage newBi = new BufferedImage(ySize,xSize,3);
        for(int x = 0; x < xSize;x++)
        {
            for(int y = 0; y < ySize; y++)
            {
                int rgb = bi.getRGB(x,y);

                int[] rgbValues = unpackPixel (rgb);
                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];
                int newColour = packagePixel (green, red, blue, alpha);
                bi.setRGB (x, y, newColour);
            }
        }
    }
    
    public static void sharpening (BufferedImage bi)
    {
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();
        
        BufferedImage newBi = new BufferedImage(ySize,xSize,3);
        for(int x = 0; x < xSize;x++)
        {
            for(int y = 0; y < ySize; y++)
            {
                int rgb = bi.getRGB(x,y);

                int[] rgbValues = unpackPixel (rgb);
                
                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];
                if( (red+green+blue)/3 >=100&&red!=green&&red!=blue)
                {
                    red +=1;
                    green +=1;
                    blue +=1;
                }
                else if ((red+green+blue)/3<100&&red!=green&&red!=blue)
                {
                    red -=1;
                    green -=1;
                    blue -=1;
                }
                
                int newColour = packagePixel (red, green, blue, alpha);
                bi.setRGB (x, y, newColour);
            }
        }
    }

    /**
     * Takes in an rgb value - the kind that is returned from BufferedImage's
     * getRGB() method - and returns 4 integers for easy manipulation.
     * 
     * By Jordan Cohen
     * Version 0.2
     * 
     * @param rgbaValue The value of a single pixel as an integer, representing<br>
     *                  8 bits for red, green and blue and 8 bits for alpha:<br>
     *                  <pre>alpha   red     green   blue</pre>
     *                  <pre>00000000000000000000000000000000</pre>
     * @return int[4]   Array containing 4 shorter ints<br>
     *                  <pre>0       1       2       3</pre>
     *                  <pre>alpha   red     green   blue</pre>
     */
    public static int[] unpackPixel (int rgbaValue)
    {
        int[] unpackedValues = new int[4];
        // alpha
        unpackedValues[0] = (rgbaValue >> 24) & 0xFF;
        // red
        unpackedValues[1] = (rgbaValue >> 16) & 0xFF;
        // green
        unpackedValues[2] = (rgbaValue >>  8) & 0xFF;
        // blue
        unpackedValues[3] = (rgbaValue) & 0xFF;

        return unpackedValues;
    }

    /**
     * Takes in a red, green, blue and alpha integer and uses bit-shifting
     * to package all of the data into a single integer.
     * 
     * @param   int red value (0-255)
     * @param   int green value (0-255)
     * @param   int blue value (0-255)
     * @param   int alpha value (0-255)
     * 
     * @return int  Integer representing 32 bit integer pixel ready
     *              for BufferedImage
     */
    public static int packagePixel (int r, int g, int b, int a)
    {
        int newRGB = (a << 24) | (r << 16) | (g << 8) | b;
        return newRGB;
    }
    
    private static void setNewBi (int xSize, int ySize, BufferedImage bi, BufferedImage newBi)
    {
        for(int x = 0; x < xSize;x++)
        {
            for(int y = 0; y < ySize; y++)
            {
                int rgb = newBi.getRGB(x,y);

                int[] rgbValues = unpackPixel (rgb);
                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];

                int newColour = packagePixel (red, green, blue, alpha);
                bi.setRGB (x, y, newColour);
            }
        }
    }
}
