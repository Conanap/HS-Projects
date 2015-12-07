/**
 *   The class handles all manipulation to the image.
 *   @author Albion Fung, imported code written by Jordan Cohen
 *   @version 0.1.3
 */

import java.awt.image.BufferedImage;
import java.util.List;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

public class Processor  
{
    private static int satCount;
    /**
     * Switches the blue and green parameter
     * 
     * @param bi    The BufferedImage (passed by reference) to change.
     */
    public static void blueGreen (BufferedImage bi)
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
                //repackage the pixels and put it back into the image
                int newColour = packagePixel (red, blue, green, alpha);
                bi.setRGB (x, y, newColour);
            }
        }

    }

    /**
     * Switches the red and blue parameter
     * @param bi   The BufferImage (passed by reference) to change
     */
    public static void redBlue (BufferedImage bi)
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

                int newColour = packagePixel (blue, green, red, alpha);
                bi.setRGB (x, y, newColour);
            }
        }
    }

    /**
     * Rotates red, bue, and green parameters. Use twice to return to original color
     * @param bi The BufferedImage (passed by reference) to change
     */
    public static void redBlueGreen (BufferedImage bi)
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

                int newColour = packagePixel (green, blue, red, alpha);
                bi.setRGB (x, y, newColour);
            }
        }
    }

    /**
     * Flips the image horizontally
     * @param bi The BufferedImage (passed by reference) to change
     */
    public static void flipHorizontal (BufferedImage bi)
    {

        int xSize = bi.getWidth();
        int ySize = bi.getHeight();

        // Temp image, to store pixels as we reverse everything
        BufferedImage newBi = new BufferedImage (xSize, ySize, 3);

        //making such that the newBi is the horizontal reverse of bi
        for(int x = 0; x < xSize;x++)
        {
            for(int y = 0; y < ySize; y++)
            {
                //xSize - x - 1 is equal to the pixel on the other side. Eg when x = 0, this would give the pixel to the very right of the image
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

        //copying newBi onto bi. Since newBi is already reversed, this would reverse bi.
        setNewBi(xSize, ySize, bi, newBi);
    }

    /**
     * Flips the image vertically.
     * @param bi The BufferedImage (passed by reference) to change
     */
    public static void flipVertical (BufferedImage bi)
    {

        int xSize = bi.getWidth();
        int ySize = bi.getHeight();

        // Temp image, to store pixels as we reverse everything
        BufferedImage newBi = new BufferedImage (xSize, ySize, 3);

        //reversing the image vertically and storing into newBi
        for(int x = 0; x < xSize;x++)
        {
            for(int y = 0; y < ySize; y++)
            {
                //same theory as flipping horizontal, but for y coordinates
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
        //setting the bi to new bi
        setNewBi(xSize, ySize, bi, newBi);
    }

    /** 
     * Mirrors the right side of the image on to the left
     * @param bi The BufferedImage (passed by reference) to change
     */
    public static void mirrorRightSide(BufferedImage bi)
    {
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();
        
        //similar code as flipping horizontal, but since we did not create a newBi, it will be changing pixels as it copies, creating a mirroring effect
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

    /**
     * Rotates the image 90 degrees clockwise
     * @param bi The BufferedImage (passed by reference) to change
     * @return newBi The rotated Image
     */
    public static BufferedImage rotateCW90 (BufferedImage bi)
    {
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();
        //creating a new buffered image that would fit the rotated image
        BufferedImage newBi = new BufferedImage(ySize,xSize,3);

        //when rotated, the bottom becomes the left most column. Therefore we start with counting from bottom to top for y as x, but x can carry on as normal for y.
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

    /**
     * Rotates the image 90 degrees counter clockwise
     * @param bi The BufferedImage (passed by reference) to change
     * @return newBi returns the rotated image
     */
    public static BufferedImage rotateCCW90 (BufferedImage bi)
    {
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();

        //creating a buffer image that would fit the rotated image
        BufferedImage newBi = new BufferedImage(ySize,xSize,3);

        
        //same theory as rotating clock wise, but right most x is now at top and top most y is now at left
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

    /**
     * Changes the image to a gray scale
     * @param bi The BufferedImage (passed by reference) to change
     */
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
                //weighing the color differently to give a slightly better appearence 
                red = green = blue = (int)(red * 0.299 + green * 0.587 + blue * 0.114);
                int newColour = packagePixel (red, green, blue, alpha);
                bi.setRGB (x, y, newColour);
            }
        }
    }

    /**
     * Changes the image to a brown - yellow scale
     * @param bi The BufferedImage (passed by reference) to change
     */
    public static void yellowScale (BufferedImage bi)
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
                //weighing the color differently to give a slightly better appearence
                red = green = (int)(red * 0.351 + green * 0.355 + blue * 0.294);
                blue =0;
                int newColour = packagePixel (red, green, blue, alpha);
                bi.setRGB (x, y, newColour);
            }
        }
    }

    /**
     * Change sthe image to have negative colors
     * @param bi The BufferedImage (passed by reference) to change
     */
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
                //change colors to their negative
                int red = 255-rgbValues[1];
                int green = 255-rgbValues[2];
                int blue = 255-rgbValues[3];
                int newColour = packagePixel (red, green, blue, alpha);
                bi.setRGB (x, y, newColour);
            }
        }
    }

    /**
     * Makes the image brighter
     * @param bi The BufferedImage (passed by reference) to change
     */
    public static void brighter (BufferedImage bi)
    {
        if(satCount<=6)
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
                    //Brighter = more white, white = all color =255, therefore brighter = all color closer to 255
                    if(red<250)
                        red+=3;
                    int green = rgbValues[2];
                    if(green<250)
                        green+=3;
                    int blue = rgbValues[3];
                    if(blue<250)
                        blue+=3;
                    int newColour = packagePixel (red, green, blue, alpha);
                    bi.setRGB (x, y, newColour);
                }
            }
            satCount++;//when this gets to 6, it does not let the image get any brighter so it doesnt lose the colors. 6 is  usually already really bright
        }
        else
            System.out.println("Cannot make the image brighter or it will lose the color differentiation");
    }

    /**
     * Makes the image darker
     * @param bi The BufferedImage (passed by reference) to change
     */
    public static void darker (BufferedImage bi)
    {
        if(satCount>=(-6))
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
                    //Opposite theory of brighter
                    if(red>5)
                        red-=3;
                    int green = rgbValues[2];
                    if(green>5)
                        green-=3;
                    int blue = rgbValues[3];
                    if(blue>5)
                        blue-=3;
                    int newColour = packagePixel (red, green, blue, alpha);
                    bi.setRGB (x, y, newColour);
                }
            }
            satCount--;//Same theory as brighter
        }
        else
            System.out.println("Cannot make the image brighter or it will lose the color differentiation");
    }

    /**
     * Switches Red and green
     * @param bi The BufferedImage (passed by reference) to change
     */
    public static void redGreen (BufferedImage bi)
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
                int newColour = packagePixel (green, red, blue, alpha);
                bi.setRGB (x, y, newColour);
            }
        }
    }
    
    /**
     * Makes a copy of the buffered image. Code provided by Jordan Cohen
     * @param bi The BufferedImage (passed by reference) to change
     */
    public static BufferedImage deepCopy(BufferedImage bi) 
    {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultip = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultip, null);
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

    /**
     * Set bi's color to newBi's
     * @param bi The BufferedImage (passed by reference) to change
     * @param newBi The BufferedImage for bi to change to
     */
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
