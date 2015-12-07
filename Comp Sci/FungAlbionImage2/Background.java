import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.File;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import java.io.IOException;
/**
 * Starter code for Image Manipulation Array Assignment.
 * 
 * The class Processor contains all of the code to actually perform
 * transformation. The rest of the classes serve to support that
 * capability. This World allows the user to choose transformations
 * and open files.
 * 
 * Add to it and make it your own!
 * 
 * @author Jordan Cohen
 * @version November 2013
 */
public class Background extends World
{
    // Constants:
    private final String STARTING_FILE = "pic.jpg";

    // Objects and Variables:
    private ImageHolder image;

    private TextButton hRevButton;
    private TextButton openFile;
    private TextButton vRevButton;
    private TextButton rightMirror;
    private TextButton rotateCW;
    private TextButton rotateCCW;
    private TextButton grayScale;
    private TextButton negative;
    private TextButton brighter;
    private TextButton darker;
    private TextButton saveType;
    private TextButton changeSave;
    private TextButton switchRG;
    private TextButton sharpen;
    private TextButton effectTitle;
    private TextButton fileOps;
    private TextButton editTitle;
    private TextButton optionTitle;
    private TextButton undo;
    private TextButton redo;
    private TextButton viewTitle;

    private String fileName;
    private String showSaveType = ".png";

    private boolean saveAsPNG=true;
    private boolean fileMenu=false;
    private boolean effectMenu=false;
    private boolean editMenu=false;
    private boolean optionMenu=false;
    private boolean viewMenu=false;

    /**
     * Constructor for objects of class Background.
     * 
     */
    public Background()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 

        // Initialize buttons and the image
        image = new ImageHolder(STARTING_FILE);
        hRevButton = new TextButton ("  Flip Horizontal                                     ",15);
        vRevButton = new TextButton ( "  Flip Vertical                                          ",15);
        openFile = new TextButton ("          Open File          ",15);
        rightMirror = new TextButton ( "  Mirror (Right side)                                ",15);
        rotateCW = new TextButton ( "  Rotate clockwise 90 degrees                " ,15);
        rotateCCW = new TextButton ( "  Rotate anti clockwise 90 degrees         " ,15);
        grayScale = new TextButton ( "  Change to gray Scale  " ,15);
        negative = new TextButton ( "  Negative                      " ,15);
        brighter = new TextButton ( "  Brighter                       " ,15);
        darker = new TextButton ( "  Darker                         " ,15);
        saveType = new TextButton ( "      Save as a "+showSaveType+"      " ,15);
        changeSave = new TextButton ( "  Change save format " ,15);
        switchRG = new TextButton ( "  Switch R & G               " ,15);
        sharpen = new TextButton ( "  Sharpen                       ",15);
        effectTitle = new TextButton ( "  Effects▼   ");
        fileOps = new TextButton ( "    File▼ " );
        editTitle = new TextButton ("Edit▼  ");
        optionTitle = new TextButton (" Options▼ ");
        undo = new TextButton ("Undo");
        redo = new TextButton ("Redo");
        viewTitle = new TextButton ("View▼ ");

        // Add objects to the screen
        addObject (image, 400, 300);
        addObject (effectTitle, 110,12);
        addObject (fileOps,33,12);
        addObject (editTitle,183,12);
        addObject (optionTitle,252,12);
        addObject (viewTitle,322,12);
    }

    /**
     * Act() method just checks for mouse input... Not going to do anything else here
     */
    public void act ()
    {
        checkMouse();
    }

    /**
     * Check for user clicking on a button
     */
    private void checkMouse ()
    {
        // Avoid excess mouse checks - only check mouse if somethething is clicked.
        checkClicks();
    }

    private void checkClicks()
    {
        if (Greenfoot.mouseClicked(null))
        {
            if (Greenfoot.mouseClicked(hRevButton))
                Processor.flipHorizontal(image.getBufferedImage());
            else if (Greenfoot.mouseClicked(openFile))
                openFile ();
            else if(Greenfoot.mouseClicked(vRevButton))
                Processor.flipVertical(image.getBufferedImage());
            else if(Greenfoot.mouseClicked(rightMirror))
                Processor.mirrorRightSide(image.getBufferedImage());
            else if(Greenfoot.mouseClicked(rotateCW))
                image.changeImage(Processor.rotateCW90(image.getBufferedImage()));
            else if(Greenfoot.mouseClicked(rotateCCW))
                image.changeImage(Processor.rotateCCW90(image.getBufferedImage()));
            else if(Greenfoot.mouseClicked(grayScale))
                Processor.grayScale(image.getBufferedImage());
            else if(Greenfoot.mouseClicked(negative))
                Processor.negativeColor(image.getBufferedImage());
            else if (Greenfoot.mouseClicked(brighter))
                Processor.brighter(image.getBufferedImage());
            else if (Greenfoot.mouseClicked(darker))
                Processor.darker(image.getBufferedImage());
            else if (Greenfoot.mouseClicked(changeSave))
            {
                if(showSaveType==".png")
                    showSaveType=".jpg ";
                else if (showSaveType == ".jpg ")
                    showSaveType=".png";
                saveType.update("      Save as a "+showSaveType+"      ");
                addObject (saveType, 400,500);
            }
            else if (Greenfoot.mouseClicked(saveType))
                save(showSaveType);
            else if(Greenfoot.mouseClicked(switchRG))
                Processor.redGreen(image.getBufferedImage());
            else if (Greenfoot.mouseClicked(sharpen))
                Processor.sharpening(image.getBufferedImage());
            else if (Greenfoot.mouseClicked(fileOps))
            {
                if(!fileMenu)
                {
                    addObject (openFile, 56, 35);
                    addObject (saveType, 54,57);
                    addObject (changeSave, 55,80);
                    fileMenu=true;
                    fileOps.updateTitle("    File▲ ");
                    if(effectMenu)
                        removeEffectMenu();
                    if(editMenu)
                        removeEditMenu();
                    if(optionMenu)
                        removeOptionMenu();
                    if(viewMenu)
                        removeViewMenu();
                }
                else
                    removeFileMenu();
            }
            else if (Greenfoot.mouseClicked(effectTitle))
            {
                if (!effectMenu)
                {
                    addObject (grayScale,127,37);
                    addObject (brighter,125,60);
                    addObject (darker,125,83);
                    addObject (negative,126,128);
                    addObject (switchRG, 127,105);
                    addObject (sharpen, 127, 151);
                    effectMenu=true;
                    effectTitle.updateTitle("  Effects▲   ");

                    if(fileMenu)
                        removeFileMenu();
                    if(editMenu)
                        removeEditMenu();
                    if(optionMenu)
                        removeOptionMenu();
                    if(viewMenu)
                        removeViewMenu();
                }
                else
                    removeEffectMenu();
            }
            else if(Greenfoot.mouseClicked(editTitle))
            {
                if(!editMenu)
                {
                    addObject (hRevButton, 309, 38);
                    addObject (vRevButton, 309, 62);
                    addObject (rightMirror, 309, 86);
                    addObject (rotateCW, 309, 110);
                    addObject (rotateCCW, 309, 134);
                    editMenu=true;
                    editTitle.updateTitle("Edit▲  ");
                    if(fileMenu)
                        removeFileMenu();
                    if(effectMenu)
                        removeEffectMenu();
                    if(optionMenu)
                        removeOptionMenu();
                    if(viewMenu)
                        removeViewMenu();
                }
                else
                    removeEditMenu();
            }
            else if (Greenfoot.mouseClicked(optionTitle))
            {
                if(!optionMenu)
                {
                    optionTitle.updateTitle(" Options▲ ");
                    optionMenu=true;
                    if(editMenu)
                        removeEditMenu();
                    if(fileMenu)
                        removeFileMenu();
                    if(effectMenu)
                        removeEffectMenu();
                    if(viewMenu)
                        removeViewMenu();
                }
                else
                    removeOptionMenu();
            }
            else if(Greenfoot.mouseClicked(viewTitle))
            {
                if(!viewMenu)
                {
                    viewTitle.updateTitle(" View▲ ");
                    viewMenu=true;
                    if(editMenu)
                        removeEditMenu();
                    if(fileMenu)
                        removeFileMenu();
                    if(effectMenu)
                        removeEffectMenu();
                    if(optionMenu)
                        removeOptionMenu();
                }
                else
                    removeViewMenu();
            }
        }
    }

    private void removeViewMenu()
    {
        viewMenu=false;
        viewTitle.update(" View▼ ");
    }

    private void removeOptionMenu()
    {
        optionMenu=false;
        optionTitle.update(" Options▼ ");
    }

    private void removeEffectMenu()
    {
        removeObject (grayScale);
        removeObject (negative);
        removeObject (brighter);
        removeObject (darker);
        removeObject (switchRG);
        removeObject (sharpen);
        effectMenu=false;
        effectTitle.update("  Effects▼   ");
    }

    private void removeEditMenu()
    {
        removeObject(hRevButton);
        removeObject(vRevButton);
        removeObject(rightMirror);
        removeObject(rotateCW);
        removeObject(rotateCCW);
        editMenu=false;
        editTitle.update("Edit▼  ");
    }

    private void removeFileMenu()
    {
        removeObject (openFile);
        removeObject (saveType);
        removeObject (changeSave);
        fileMenu=false;
        fileOps.update("    File▼ ");
    }

    private void save(String saveType)
    {
        String fileName = JOptionPane.showInputDialog("Input file name (no extension)");
        if(saveAsPNG)
        {
            fileName += saveType;
            if(saveType==".jpg ")
                Processor.removeAlpha(image.getBufferedImage());
            try
            {
                File f = new File (fileName);
                ImageIO.write(image.getImage().getAwtImage(), "png", f);
            }
            catch (IOException e)
            {
                System.out.println("An unknown error has occurred");
            }
        }
    }

    /**
     * Allows the user to open a new image file.
     */
    private void openFile ()
    {
        // Use a JOptionPane to get file name from user
        String fileName = JOptionPane.showInputDialog("Please input a value");

        // If the file opening operation is successful, update the text in the open file button
        image.openFile (fileName);
    }
}

