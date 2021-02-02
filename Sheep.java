import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Sheep extends Defender
{
    private GreenfootImage[] healthyImages = new GreenfootImage[16];
    private GreenfootImage[] injuredImages = new GreenfootImage[16];
    private GreenfootImage[] dyingImages = new GreenfootImage[16];
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
        
        //determines the sheep's image based of their health
        if (acts % animationTimer == 0)
        {
            if(health > 1000)
            {
                animate(healthyImages, currentFrame);
            }
            else if(health > 500 && health < 1000)
            {
                animate(injuredImages, currentFrame);
            }
            else{
                animate(dyingImages, currentFrame);
            }
            currentFrame++;
        }

        if(currentFrame == healthyImages.length)
        {
            currentFrame = 0;
        }
        acts++;
    }    

    /**
     * Rotates the sheep through animation frames. Creates a new instance of sheep and gives it 1500 health in its health bar.
     * 
     * @param y         the y coordinate of the sheep
     * @param x         the x coordinate of the sheep
     */

    public Sheep(int y, int x)
    {
        xCoord= x;
        yCoord = y;

        timer=0;
        
        //In charge of healthy sheep animation
        for( int i=0; i<healthyImages.length; i++ )
        {
            healthyImages[i] = new GreenfootImage( "sprite_sheep_healthy" + String.format("%02d", i) + ".png" );
        }

        //In charge of injured sheep animation
        for( int i=0; i<injuredImages.length; i++ )
        {
            injuredImages[i] = new GreenfootImage( "sprite_sheep_injured" + String.format("%02d", i) + ".png" );
        }

        //In charge of dying sheep animation
        for( int i=0; i<dyingImages.length; i++ )
        {
            dyingImages[i] = new GreenfootImage( "sprite_sheep_dying" + String.format("%02d", i) + ".png" );
        }
        
        //initializes the healthbar of the sheep
        health = 1500;
        acts = 0;
        animationTimer = 5;

        healthBar = new StatBar (health, health,this, 100, 5, 45);

        setImage(healthyImages[currentFrame]);
    }
}
