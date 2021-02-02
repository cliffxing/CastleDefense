import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Arrow extends Projectile
{
    private GreenfootImage image= new GreenfootImage("arrow.png");
    /**
     * Sets the image of the Arrow
     */
    public Arrow()
    {
        type  = 1;
        setImage(image);
    }
    /**
     * Act - do whatever the Arrow wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        attack(-4);
    }    
}
