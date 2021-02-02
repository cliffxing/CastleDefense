import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Dragon extends Defender
{
    private GreenfootImage[] images = new GreenfootImage[16]; //
    private int acts;
    private int currentFrame;
    private int animationTimer;
    private int timer;
    /**
     * Act - do whatever the Dragon wants to do. This method is called whenever
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

        //checks to see if the dragon is alive
        if (health>0)
        {
            lane = getLane(this.getY());
        }

        checkAttack();
        acts++;
    }    

    /**
     * Rotates the Dragon through animation frames. Create a new instance of Dragon and gives it 1000 health in its health bar.
     * 
     * @param y         the y coordinate of the Dragon
     * @param x         the x coordinate of the Dragon
     */
    public Dragon(int y, int x)
    {
        yCoord = y;
        xCoord = x;
        animationTimer = 5;
        currentFrame = 1;
        timer = 1;
        
        //In charge of the dragon animation
        for( int i=0; i<images.length; i++ )
        {
            images[i] = new GreenfootImage( "sprite_baby_dragon" + String.format("%02d", i) + ".png" );
        }
        
        //initializes the health bar of the dragon
        health = 1000;
        setImage(images[0]);
        healthBar = new StatBar (health, health,this, 100, 5, 45);
    }

    /**
     * Animates the Dragon when attacking the monsters.
     */

    protected void attack()
    {
        timer--;
        
        //sets a delay in between firing fireball at the monsters
        if(timer == 0)
        {
            if (shot == false)
            {
                getWorld().addObject(new Fireball(), this.getX(), this.getY());
                shot = true;
            }
        }

        else 
        {
            currentFrame=0;
            shot = false;
        }

        
        //resets the timer
        if(timer == 0)
        {
            timer = 500;
        }

    }
}
