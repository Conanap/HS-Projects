import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.lang.IllegalStateException;
/**
 * Write a description of class miniMap here.
 * 
 * @author Albion Fung 
 * @version 0.01
 */
public class miniMap extends Actor
{
    private GreenfootImage mini = new GreenfootImage(151,100);
    private int xSpawn, ySpawn,xMax,yMax, playerX, playerY,drawPx, drawPy;
    private int[][] map;
    private boolean mapLoaded=false;
    /**
     * Act - do whatever the miniMap wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public miniMap() 
    {
        base();
    }

    public void buildMap()
    {
        spawnLoc();
    }

    public void base()
    {
        setImage(mini);
        mini.setColor(Color.BLACK);
        mini.fillRect(0,0,151,79);
    }

    public void updateMap()
    {
        int yDraw,x,y;
        spawnLoc();
        player();
        base();
        if(ySpawn==0)
        {
            yDraw=14;
            y =20;
        }
        else
        {
            yDraw=19;
            y=0;
        }
        boolean space;
        for(int i = ySpawn; i < (ySpawn+yDraw); i++)
        {
            x = 1;
            for(int j = xSpawn; j < (xSpawn+37);j++)
            {
                if(map[i][j]!=0)
                    space=false;
                else
                    space=true;
                drawMinRect(x,y,space);
                x+=4;
            }
            y+=4;
        }
        drawPlayer();
    }

    private void player()
    {
        Test test = (Test)getWorld();
        try
        {
            playerX=test.playerX();
            playerY=test.playerY();
            if(xSpawn>=5)
                drawPx=21;
            else
                drawPx=1;
            if(ySpawn==0)
                drawPy=8;
            else
                drawPy=7;
            for(int i = 3;i <= 1074; i+=40)
            {
                if(playerX >=i&& playerX<=(i+40))
                    break;
                else
                    drawPx+=4;
            }

            for(int i = 68;i <= 696; i+=40)
            {
                if(ySpawn==0&&playerY<=67)
                    drawPy=8;
                else if(playerY >=i&& playerY<=(i+40))
                    break;
                else
                    drawPy+=4;
            }
        }
        catch(IllegalStateException e)
        {
        }
    }

    private void spawnLoc()
    {
        Test test = (Test)getWorld();
        xSpawn=test.mapx();
        ySpawn=test.mapy();
        if(xSpawn>=5)
            xSpawn-=5;
        if(xSpawn<=562)
            xMax=xSpawn+37;
        else
            xMax=599;

        if(!mapLoaded)
        {
            this.map=test.getMap();
            mapLoaded=true;
        }
    }

    private void drawMinRect(int blockX, int blockY,boolean space)
    {
        setImage(mini);
        if(!space)
            mini.setColor(Color.GREEN);
        else
            mini.setColor(Color.BLACK);
        mini.fillRect(blockX,blockY,4,4);
    }

    private void drawPlayer()
    {
        mini.setColor(Color.RED);
        mini.fillOval(drawPx,drawPy, 4,4);
    }

    public void mapMod (int xInt, int yInt, int type)
    {
        this.map[xInt][yInt]= type;
    }
}
