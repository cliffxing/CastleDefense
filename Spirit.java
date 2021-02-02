import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Arrays;

public class Spirit extends Monster
{
    //Arrays holding images needed for animation
    private GreenfootImage[] images = new GreenfootImage[12];
    private GreenfootImage[] death = new GreenfootImage[14];
    private GreenfootImage[] dodgeImages = new GreenfootImage[7];
    
    //Variables that facillitate animation
    private int currentFrame = 1;
    private int currentDodgeFrame = 1;
    private int currentDeathFrame=1;
    private int acts;
    private int animationTimer;
    private boolean dodging=false;
   
    //Variables that facillitate "dodging"
    private int dodgeTimer;
    private boolean dodge;
    private int projectilesDodged;
    private boolean blockEmpty;
    
    private Projectile p;
    private Defender dFar;
    private Monster m;
    private MyWorld world;

    /**
     * Creates a new instance of a Spirit with 600 health
     */
    public Spirit()
    {
        //The following for loops fill the image arrays with the images needed for animation
        for( int i=0; i<images.length; i++ )
        {
            images[i] = new GreenfootImage( "sprite_spirit" + String.format("%02d", i) + ".png" );
        }

        for( int i=0; i<death.length; i++ )
        {
            death[i] = new GreenfootImage( "sprite_spirit_death" + String.format("%02d", i) + ".png" );
        }

        for( int i=0; i<(int)(dodgeImages.length); i++ )
        {
            dodgeImages[i] = new GreenfootImage( "sprite_spirit_dodge" + String.format("%01d", i) + ".png" );
        }
        
        acts = 0;
        animationTimer = 7;

        setImage(images[currentFrame]);
        projectilesDodged = 0;
        blockEmpty = true;
        health = 600;
        healthBar = new StatBar (health, health,this, 100, 5, 45);
        
        world = (MyWorld) getWorld();

    }

    public void act() 
    {
        super.act();
        

        if (health>0)
        {
            checkInFront();

            checkEdges();
            
            //If no defenders are in front and Spirit is not currently dodging a projectile, 
            //it continues walking towards the castle
            if (frontEmpty==true && !dodge)
            {
                if (acts % animationTimer == 0 )
                {
                    animateRight(images, currentFrame, 7);
                    currentFrame++;
                }

                if(currentFrame == images.length)
                {
                    currentFrame = 0;
                }
            }
            //if dodge = true, it uses the dodgeImages array to animate the dodging sequence
            else if(dodge && blockEmpty)
            {
                if (acts % animationTimer-3 == 0 )
                {  
                    animateRight(dodgeImages, currentFrame, 3);
                    currentFrame++;
                }

                if(currentFrame == dodgeImages.length)
                {
                    currentFrame = 0;
                    dodge = false;
                }
            }
        }
        //if the Spirit loses all of its health, death sequence is animated
        else
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
    
    /** 
     * Returns the amount of projectiles dodged by the spirit
     * 
     * @return int  Number of projectiles dodged by the spirit
     */
    public int getProjectilesDodged()
    {
        return projectilesDodged;
    }

    /** 
     * Increases the value of projectiles dodged by one
     */
    public void addProjectilesDodged()
    {
        projectilesDodged++;
    }
    
    /**
     * Sets the "dodging" variable to true, so that the Spirit is currently dodging
     */
    public void dodge()
    {
        dodging = true;
    }
    
    //Checks to see whether a defender is in front and if Spirit has come in contact with an arrow
    protected void checkInFront()
    {
        d = (Defender)getOneObjectAtOffset(getImage().getWidth()/4, 0, Defender.class);
        dFar = (Defender)getOneObjectAtOffset(getImage().getWidth(), 0, Defender.class);
        p = (Projectile)getOneObjectAtOffset(getImage().getWidth(), 0, Projectile.class);
        m = (Monster)getOneObjectAtOffset(getImage().getWidth(), 0, Monster.class);
        if (d != null )
        {

            frontEmpty= false;
            d.takeDamage(damage);
        }

        else
        {
            frontEmpty = true;
        }
        
        if (dFar == null)
        {
            blockEmpty= true;
        }
        //If touched by an arrow and has not already dodged, dodge is true
        if (p != null && projectilesDodged == 0 && m == null && frontEmpty)
        {
            if (p instanceof Arrow || p instanceof Fireball)
            {
                dodge = true;
                currentFrame = 0;
            }
        }
    }
    
    /**
     * Checks to see if the area in front is empty
     * 
     * @return      return true if empty, return false if not empty
     */
    public boolean getFrontEmpty()
    {
        return frontEmpty;
    }
}
