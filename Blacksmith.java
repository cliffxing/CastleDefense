import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Blacksmith extends Defender
{
    private GreenfootImage[] images = new GreenfootImage[16];
    private int currentFrame = 1;
    private int timer;
    private int acts;

    private int animationTimer;
    /**
     * Act - do whatever the Blacksmith wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
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
        
        //Checks to see if blacksmith has health and add a delay before spawning money
        if (acts == 1000 && health>0)
        {
            getWorld().addObject(new DollarSign(), this.getX(), this.getY()); //deletes object after certain time
            acts = 0;
            return;
        }
        acts++;
    }    

    /**
     * Rotates the blacksmith through animation frames. Creates a new instance of blacksmith and gives it 600 health in its health bar.
     * 
     * @param y         the y coordinate of the blacksmith
     * @param x         the x cooridnate of the blacksmith
     */

    public Blacksmith(int y, int x)
    {
        xCoord= x;
        yCoord = y;

        timer=0;
        
        //In charge of the blacksmith animation
        for( int i=0; i<images.length; i++ )
        {
            images[i] = new GreenfootImage( "sprite_blacksmith" + String.format("%02d", i) + ".png" );
        }
        
        //Initializes the values for the blacksmith's health bar
        health = 600;
        healthBar = new StatBar (health, health,this, 100, 5, 45);

        acts = 0;
        animationTimer = 5;

        setImage(images[currentFrame]);
    }
}
