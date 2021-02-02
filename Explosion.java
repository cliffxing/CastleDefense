import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Explosion extends Actor
{
    private GreenfootImage[] images = new GreenfootImage[4];
    //Counter for the animation to run smoothly
    private int animationCounter = 0;
    //Variable for current frame
    private int currentFrame = 0;
    //For the sound
    private GreenfootSound explosionSound;
    
    Monster m;
    private int damage;
    private boolean inflicted = false;
    /**
     * Sets the sound and image for the class
     */
    public Explosion(int multiplier)
    {
        //Loads sound
        explosionSound = new GreenfootSound("explosion2.wav");
        //Sets sound volume
        explosionSound.setVolume(90);
        //Imports the different frame
        for( int i=0; i<images.length; i++ )
        {
            images[i] = new GreenfootImage( "Explosion_" + i + ".png" );
            images[i].scale(images[i].getWidth()*multiplier,images[i].getHeight()*multiplier);
        }
        //Sets image to the first frame
        setImage(images[0]);
    }
    
    public void act() 
    {
        
        animationCounter++;
        //Only runs every 4 acts
        if(animationCounter % 4 == 0)
        {
            //Displays the next frame
            currentFrame++;
            setImage(images[currentFrame]);
        }
        //If on the last frame
        if(currentFrame == images.length-1)
        {
            //PLays the sound and removes the object
            explosionSound.play();
            getWorld().removeObject(this);
           
            
        }
    }    
}
