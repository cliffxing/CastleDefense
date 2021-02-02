import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Fireball extends Projectile
{
    private GreenfootImage[] images = new GreenfootImage[3]; //
    private int acts;
    private int currentFrame;
    private int animationTimer;
    
    /**
     * Creates an instance of a fireball, providing it with three images to use as its animation.
     */
    public Fireball()
    {
        damage = 500;
        type  = 1;
        animationTimer = 5;
        currentFrame = 1;
        images[0] = new GreenfootImage("fireball.png");
        images[1] = new GreenfootImage("fireball2.png");
        images[2] = new GreenfootImage("fireball3.png");
        setImage(images[0]);
    }
    
    public void act() 
    {
        attack(-3);
        
        super.act();
        if (acts % animationTimer == 0)
        {
            animate(images, currentFrame);
            currentFrame++;
        }

        if(currentFrame == images.length)
        {
            currentFrame = 0;
        }
    }  
}
