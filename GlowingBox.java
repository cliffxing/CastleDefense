import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class GlowingBox extends Actor
{
    private GreenfootImage image= new GreenfootImage("hi.png");
    private GreenfootImage image2= new GreenfootImage("crosshair.png");
    private GreenfootImage image3= new GreenfootImage("delete box.png");
    
    private GreenfootImage blank =  new GreenfootImage (1, 1);
    /**
     * Act - do whatever the GlowingBox wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    

    /**
     * Creates an instance of glowing box by setting the image
     */
    public GlowingBox()
    {

        setImage(image);

        
    }
    
    /**
     * Sets the image of the glowing box
     * 
     * @param x     differentiates how each box will "glow".
     */

    public void setImage(int x)
    {
        if (x == 1)
        {
            setImage(blank);
        }
        else if (x==2)
        {
            setImage(image);
        }
        
        else if (x==3)
        {
            setImage(image2);
        }
        else if (x==4)
        {
            setImage(image3);
        }
    }
}
