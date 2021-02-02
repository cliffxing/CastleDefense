import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Skull extends Monster
{
    //Array holding images for animation
    private GreenfootImage[] images = new GreenfootImage[17];
    
    //Facilliates animation
    private int currentFrame = 1;
    private int currentDeathFrame=1;
    private int acts;
    private int animationTimer; 

    private MyWorld world;
    
    /** 
     * Creates nee instance of Skull with 8000 health
     */
    public Skull()
    {

        for( int i=0; i<images.length; i++ )
        {
            images[i] = new GreenfootImage( "sprite_skull" + String.format("%02d", i) + ".png" );
        }


        if (health<=0)
        {
            removeLane();    
        }
        health = 8000;
        healthBar = new StatBar (health, health,this, 100, 5, 45);

        acts = 0;
        animationTimer = 7;
        damage = 1;

        setImage(images[currentFrame]);

        world = (MyWorld) getWorld();
    }

    public void act() 
    {
        super.act();
        
        //If alive, Skull will walk towards castle
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
        
        //If health is depleted, Skull is removed from lane
        if (health <=0)
        {

            removeLane();

        }

        acts++;
    }    
}
