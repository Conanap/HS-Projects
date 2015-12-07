import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.File;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.lang.IndexOutOfBoundsException;
import java.awt.Color;

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
    private String fileName;
    private String showSaveType = ".png";

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
    private TextButton effectTitle;
    private TextButton fileOps;
    private TextButton editTitle;
    private TextButton optionTitle;
    private TextButton undo;
    private TextButton redo;
    private TextButton brownScale;
    private TextButton viewRGB;
    private TextButton toolBar;
    private TextButton switchBG;
    private TextButton switchRB;
    private TextButton switchRGB;

    private boolean saveAsPNG=true;
    private boolean fileMenu=false;
    private boolean effectMenu=false;
    private boolean editMenu=false;
    private boolean optionMenu=false;
    private boolean showingRGB=false;

    private int currentAction;

    private ArrayList<BufferedImage> backUps;

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
        negative = new TextButton ( "  Negative                     " ,15);
        brighter = new TextButton ( "  Brighter                      " ,15);
        darker = new TextButton ( "  Darker                        " ,15);
        saveType = new TextButton ( "      Save as a "+showSaveType+"      " ,15);
        changeSave = new TextButton ( "  Change save format " ,15);
        switchRG = new TextButton ( "  Switch R & G               " ,15);
        effectTitle = new TextButton ( "  Effects▼   ");
        fileOps = new TextButton ( "    File▼ " );
        editTitle = new TextButton ("Edit▼  ");
        optionTitle = new TextButton (" Options▼ ");
        undo = new TextButton ("Undo   (Ctrl + z)",15);
        redo = new TextButton ("Redo    (Ctrl + y)",15);
        brownScale = new TextButton (" Change to yellow scale",15);
        toolBar = new TextButton ("                                                                                                                                                                                                                                                                                                                               ");
        switchBG = new TextButton (" Switch B & G                " ,15);
        switchRB = new TextButton (" Switch R & B                " ,15);
        switchRGB = new TextButton(" Switch RGB                  " ,15);

        fileName=STARTING_FILE;

        //initializing the hackUps
        backUps = new ArrayList<BufferedImage>();

        //counter used for back up
        currentAction=0;

        // Add objects to the screen
        addObject (image, 400, 300);
        addObject (toolBar,0,12);
        addObject (effectTitle, 129,12);
        addObject (fileOps,38,12);
        addObject (editTitle,215,12);
        addObject (optionTitle,294,12);

        //first back up of original image
        backUps.add(Processor.deepCopy(image.getBufferedImage()));
        currentAction++;
    }

    /**
     * Act() method just checks for mouse input... Not going to do anything else here
     */
    public void act ()
    {
        checkClicks();
    }

    private void checkClicks()
    {
        //when the mouse is clicked
        if (Greenfoot.mouseClicked(null))
        {
            //when horizontal reverse is chosen
            if (Greenfoot.mouseClicked(hRevButton))
            {
                //calls processor to flip it horizontally
                Processor.flipHorizontal(image.getBufferedImage());
                //backs this new image up
                runBackUp(backUps);
                //counter +1 so that next back up wont over write this one
                currentAction++;
            }//the rest of the effects and edit options follow the same protocal, so it will not be commented unless there is soemthing different

            //when openFile button is selected
            else if (Greenfoot.mouseClicked(openFile))
            {
                openFile ();//call openFile method
                //removing all back ups.
                backUps.clear();
                currentAction=0;//reset counter to 0
                //first back up of original image
                backUps.add(Processor.deepCopy(image.getBufferedImage()));
                currentAction++;
            }

            else if(Greenfoot.mouseClicked(vRevButton))
            {
                Processor.flipVertical(image.getBufferedImage());
                runBackUp(backUps);
                currentAction++;
            }

            else if(Greenfoot.mouseClicked(rightMirror))
            {
                Processor.mirrorRightSide(image.getBufferedImage());
                runBackUp(backUps);
                currentAction++;
            }

            else if(Greenfoot.mouseClicked(rotateCW))
            {
                image.changeImage(Processor.rotateCW90(image.getBufferedImage()));
                runBackUp(backUps);
                currentAction++;
            }

            else if(Greenfoot.mouseClicked(rotateCCW))
            {
                image.changeImage(Processor.rotateCCW90(image.getBufferedImage()));
                runBackUp(backUps);
                currentAction++;
            }

            else if(Greenfoot.mouseClicked(grayScale))
            {
                Processor.grayScale(image.getBufferedImage());
                runBackUp(backUps);
                currentAction++;
            }

            else if(Greenfoot.mouseClicked(negative))
            {
                Processor.negativeColor(image.getBufferedImage());
                runBackUp(backUps);
                currentAction++;
            }

            else if (Greenfoot.mouseClicked(brighter))
            {
                Processor.brighter(image.getBufferedImage());
                runBackUp(backUps);
                currentAction++;
            }

            else if (Greenfoot.mouseClicked(darker))
            {
                Processor.darker(image.getBufferedImage());
                runBackUp(backUps);
                currentAction++;
            }

            else if (Greenfoot.mouseClicked(brownScale))
            {
                Processor.yellowScale(image.getBufferedImage());
                runBackUp(backUps);
                currentAction++;
            }

            //if change save format is selected
            else if (Greenfoot.mouseClicked(changeSave))
            {
                //if currently will save as a png
                if(saveAsPNG)
                {
                    //change the string for display to .jpg, make saving as png false
                    showSaveType=".jpg";
                    saveAsPNG=false;
                }
                //if currently will save as a jpg
                else if (!saveAsPNG)
                {
                    // change string to .png for display, make saving as png true
                    showSaveType=".png";
                    saveAsPNG=true;
                }
                if(!saveAsPNG)//if and else for formatting reasons as the buttons behave slightly differently with different characters
                    saveType.update("      Save as a "+showSaveType+"       ");
                else
                    saveType.update("      Save as a "+showSaveType+"      ");
                addObject (saveType, 400,500);
            }

            //saves when the save button is selected
            else if (Greenfoot.mouseClicked(saveType))
                save(showSaveType, fileName);//calls the save method

            else if(Greenfoot.mouseClicked(switchRG))
            {
                Processor.redGreen(image.getBufferedImage());
                runBackUp(backUps);
                currentAction++;
            }

            else if(Greenfoot.mouseClicked(switchBG))
            {
                Processor.blueGreen(image.getBufferedImage());
                runBackUp(backUps);
                currentAction++;
            }

            else if(Greenfoot.mouseClicked(switchRB))
            {
                Processor.redBlue(image.getBufferedImage());
                runBackUp(backUps);
                currentAction++;
            }

            else if(Greenfoot.mouseClicked(switchRGB))
            {
                Processor.redBlueGreen(image.getBufferedImage());
                runBackUp(backUps);
                currentAction++;
            }

            //if the file menu is selected
            else if (Greenfoot.mouseClicked(fileOps))
            {
                //check if the menu is open, if false
                if(!fileMenu)
                {
                    //adds the button to form the menu
                    addObject (openFile, 68, 35);
                    addObject (saveType, 70,57);
                    addObject (changeSave, 69,81);
                    //states that the menu is now open
                    fileMenu=true;
                    //update the menu selector
                    fileOps.updateTitle("    File▲ ");
                    //closes all other menus if they are currently open
                    if(effectMenu)
                        removeEffectMenu();
                    if(editMenu)
                        removeEditMenu();
                    if(optionMenu)
                        removeOptionMenu();
                }
                else//if the menu is already open, it closes the menu
                    removeFileMenu();
            }//all other menus follow the same protocal, so it will not be commented but only noted as a menu

            //for the Effects menu
            else if (Greenfoot.mouseClicked(effectTitle))
            {
                if (!effectMenu)
                {
                    addObject (grayScale,153,38);
                    addObject (brownScale,153,62);
                    addObject (brighter,153,86);
                    addObject (darker,153,109);
                    addObject (switchRG, 153,133);
                    addObject (switchBG, 153,157);
                    addObject(switchRB,153,181);
                    addObject(switchRGB,153,205);
                    addObject (negative,153,229);
                    effectMenu=true;
                    effectTitle.updateTitle("  Effects▲   ");
                    if(fileMenu)
                        removeFileMenu();
                    if(editMenu)
                        removeEditMenu();
                    if(optionMenu)
                        removeOptionMenu();
                }
                else
                    removeEffectMenu();
            }

            //for the Edit menu
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
                }
                else
                    removeEditMenu();
            }

            //for the Options menu
            else if (Greenfoot.mouseClicked(optionTitle))
            {
                if(!optionMenu)
                {
                    optionTitle.updateTitle(" Options▲ ");
                    addObject(undo,314,38);
                    addObject(redo,315,62);
                    optionMenu=true;
                    if(editMenu)
                        removeEditMenu();
                    if(fileMenu)
                        removeFileMenu();
                    if(effectMenu)
                        removeEffectMenu();
                }
                else
                    removeOptionMenu();
            }

            else if(Greenfoot.mouseClicked(undo))
            {
                try
                {
                    image.changeImage(backUps.get(currentAction-2));//after an action, the number is autmatically increased, and with the first increase after the first back up,
                    //the counter must be subtracted by 2 to get the correct back up
                    //uses changeImage because in case there was a rotation
                    currentAction--;//reducing counter by 1 because now on 1 action back
                }
                catch( IndexOutOfBoundsException e)
                {
                    currentAction++;//at max undo prevents counter from going to negative
                }
            }
            else if(Greenfoot.mouseClicked(redo))
            {
                try
                {
                    image.changeImage(backUps.get(currentAction));//as stated above, the counter is actually 1 ahead. so getting the current gives the redo
                    currentAction++;//add one because now on 1 action forward
                }
                catch(IndexOutOfBoundsException e)
                {
                    currentAction--;//at max redo, prevents counter from going out of bounds
                }
            }
            else//when the use clicks on empty space where no button exists, it removes all the menus
            {
                removeFileMenu();
                removeEffectMenu();
                removeEditMenu();
                removeOptionMenu();
            }
        }
        else if (Greenfoot.isKeyDown("control")&&Greenfoot.isKeyDown("z"))//contrl + z and contrl + y also works for redo and undo, but note that it takes key strokes very quickly. It is ridiculously hard to get 1 redo or undo. Short cut for multiple undos
        {//also serves as a reset button
            try
            {
                image.changeImage(backUps.get(currentAction-2));
                currentAction--;
            }
            catch( IndexOutOfBoundsException e)
            {
                currentAction++;
            }
        }
        else if (Greenfoot.isKeyDown("control")&&Greenfoot.isKeyDown("y"))
        {
            try
            {
                image.changeImage(backUps.get(currentAction));
                currentAction++;
            }
            catch(IndexOutOfBoundsException e)
            {
                currentAction--;
            }
        }
    }

    /**
     * Creates a back up of the image
     * @param backUps Take the array list used for backing up and puts the back up in there
     */
    private void runBackUp(ArrayList<BufferedImage> backUps)
    {
        try//when an action is done, this removes all the backups ahead (redos) of the current action.
        {//using the try, when it gets out of bounds the loop autmatically stops
            for(int x = currentAction;;)
            {
                backUps.remove(x);
            }
        }
        catch (IndexOutOfBoundsException e) {}
        backUps.add(Processor.deepCopy(image.getBufferedImage()));//creates a back up after erasing the undos
    }

    /**
     * removes the Options menu
     */
    private void removeOptionMenu()
    {
        optionMenu=false;
        optionTitle.update(" Options▼ ");
        removeObject(undo);
        removeObject(redo);
    }

    /**
     * removes the effects menu
     */
    private void removeEffectMenu()
    {
        removeObject (grayScale);
        removeObject (brownScale);
        removeObject (negative);
        removeObject (brighter);
        removeObject (darker);
        removeObject (switchRG);
        removeObject (switchRB);
        removeObject (switchBG);
        removeObject (switchRGB);
        effectMenu=false;
        effectTitle.update("  Effects▼   ");
    }

    /**
     * removes the edit menu
     */
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

    /**
     * removes the file menu
     */
    private void removeFileMenu()
    {
        removeObject (openFile);
        removeObject (saveType);
        removeObject (changeSave);
        fileMenu=false;
        fileOps.update("    File▼ ");
    }

    /**
     * Saves the current image with the modifications made
     * @param saveType The string that indicates if it is saving as a jpg or a png
     * @param fileName The name of the file that the user wants to save as
     */
    private void save(String saveType, String fileName)
    {
        String saveFileName = JOptionPane.showInputDialog("Input file name (no extension)");
        saveFileName += saveType;
        try//catching io errors
        {
            if(saveAsPNG)// if saving as a png
            {
                File f = new File (saveFileName);
                ImageIO.write(image.getImage().getAwtImage(), "png", f);
            }
            else if(!saveAsPNG)// if saving as a jpg
            {
                if(fileName.contains(".png"))//starting image is png
                {
                    BufferedImage bi = image.getBufferedImage();
                    int xSize = bi.getWidth();
                    int ySize = bi.getHeight();
                    //convert to jpg 3 channel
                    BufferedImage newBi = new BufferedImage(xSize,ySize,BufferedImage.TYPE_INT_RGB);
                    for(int x = 0; x < xSize;x++)
                    {
                        for(int y = 0; y < ySize; y++)
                        {
                            newBi.setRGB(x,y,bi.getRGB(x,y));
                        }
                    }
                    File f = new File (saveFileName);
                    System.out.println(newBi.getColorModel().hasAlpha());
                    ImageIO.write(newBi, "jpg", f);
                }
                else if(fileName.contains(".jpg"))//if starting image is jpg
                {//removing alpha channels added durring processes and mods
                    BufferedImage bi = image.getBufferedImage();
                    int xSize = bi.getWidth();
                    int ySize = bi.getHeight();
                    //converting to 3 channel
                    BufferedImage newBi = new BufferedImage(xSize,ySize,BufferedImage.TYPE_INT_RGB);
                    for(int x = 0; x < xSize;x++)
                    {
                        for(int y = 0; y < ySize; y++)
                        {
                            newBi.setRGB(x,y,bi.getRGB(x,y));
                        }
                    }
                    File f = new File (saveFileName);
                    System.out.println(newBi.getColorModel().hasAlpha());
                    ImageIO.write(newBi, "jpg", f);
                }
            }
        }
        catch (IOException e)
        {
            System.out.println("An unknown error has occurred");
        }
    }

    /**
     * Allows the user to open a new image file. Code provided by Jordan Cohen
     */
    private void openFile ()
    {
        // Use a JOptionPane to get file name from user
        String fileName = JOptionPane.showInputDialog("Please input a value");
        this.fileName=fileName;
        // If the file opening operation is successful, update the text in the open file button
        image.openFile (fileName);
    }
}

