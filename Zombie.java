import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Zombie extends Monster
{
    //Arrays holding images for animation
    private GreenfootImage[] images = new GreenfootImage[12];
    private GreenfootImage[] death = new GreenfootImage[10];
    
    //Helps facillitate animation
    private int currentFrame = 1;
    private int currentDeathFrame=1;
    private int acts;
    private int animationTimer; 
    
    private MyWorld world;
    
    /**
     * Creates a new instance of Zombie with 1000 health
     */
    public Zombie()
    {
        
        //The following for loops fill the image arrays with the images needed for animation
        for( int i=0; i<images.length; i++ )
        {
            images[i] = new GreenfootImage( "sprite_zombie" + String.format("%02d", i) + ".png" );
        }
        
        for( int i=0; i<death.length; i++ )
        {
            death[i] = new GreenfootImage( "sprite_zombie_death" + String.format("%02d", i) + ".png" );
        }

        if (health<=0)
        {
            removeLane();    
        }
        health = 750;
        healthBar = new StatBar (health, health,this, 100, 5, 45);
        
        acts = 0;
        animationTimer = 7;

        setImage(images[currentFrame]);
        
        world = (MyWorld) getWorld();
        
    }

    public void act() 
    {
        super.act();
        
        //While the Zombie is alive, it walks towards the castle
        if (health>0)
        {
            checkInFront();

            checkEdges();
            if (frontEmpty==true)
            {
                if (acts % animationTimer == 0)
                {
                    animateRight(images, currentFrame, 5);
                    currentFrame++;
                }

                if(currentFrame == images.length)
                {
                    currentFrame = 0;
                }
            }
        }
        
        //If Zombie loses all its health, death sequence is animated
        if (health <=0)
        {
            if (acts % animationTimer == 0)
            {
                animateDeath(death, currentDeathFrame);
                currentDeathFrame++;
            }

            if(currentFrame == images.length)
            {
                currentDeathFrame = 0;
            }

        }

        acts++;
    }    
}
