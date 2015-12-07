import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.awt.Graphics2D;

/**
 * Simple class that serves to be an Actor to display the image.
 * 
 * (Revised 11/14 to avoid crashing if user cancels import operation).
 * 
 * @author Jordan Cohen
 * @version November 2013, revised
 */
public class ImageHolder extends Actor
{
    private GreenfootImage imageToDisplay;

    /**
     * Construct an ImageHolder with a file name. If there is an error, 
     * show a blank GreenfootImage.
     * 
     * @param fileName  Name of image file to be displayed.
     */
    public ImageHolder (String fileName)
    {
        openFile (fileName);
    }

    /**
     * Attempt to open a file and assign it as this Actor's image
     * 
     * @param fileName  Name of the image file to open (must be in this directory)
     * @return boolean  True if operation successful, otherwise false
     */
    public boolean openFile (String fileName)
    {
        try {
            if (fileName != null)
            {
                imageToDisplay = new GreenfootImage (fileName);
                setImage(imageToDisplay);
            }
            else
                return false;
        }
        catch (IllegalArgumentException e)
        {
            return false;
        }
        return true;
    }

    public void changeImage(BufferedImage bi)
    {
        try 
        {
            imageToDisplay = new GreenfootImage(convertToGI(bi));
            setImage(imageToDisplay);
        }
        catch (IllegalArgumentException e)
        {
        }
    }

    /**
     * Allows access to my awtImage - the backing data underneath the GreenfootImage class.
     * 
     * @return BufferedImage returns the backing image for this Actor as an AwtImage
     */
    public BufferedImage getBufferedImage ()
    {
        return this.getImage().getAwtImage();
    }

    private GreenfootImage convertToGI (BufferedImage bi)
    {
        GreenfootImage returnImage = new GreenfootImage(bi.getWidth(), bi.getHeight());
        BufferedImage backingImage = returnImage.getAwtImage();
        Graphics2D backingGraphics = (Graphics2D)backingImage.getGraphics();
        backingGraphics.drawImage(bi,null,0,0);

        return returnImage;
    }
}
