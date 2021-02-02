import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Skeleton extends Monster
{
    //Arrays holding images needed for animation
    private GreenfootImage[] attack = new GreenfootImage[10];
    private GreenfootImage[] images = new GreenfootImage[10];
    private GreenfootImage[] death = new GreenfootImage[10];
    
    //Helps facillitate animation
    private int currentFrame;
    private int currentDeathFrame=1;
    private int currentAttackFrame =1;
    protected int attackAct = 0;
    private int acts;
    private int animationTimer;
    private int animationAttackTimer;
    
    private boolean inAnim;
    private boolean moveForward;
    
    /**
     * Creates new instance of Skeleton with 800 health
     */
    public Skeleton()
    {
        for( int i=0; i<images.length; i++ )
        {
            images[i] = new GreenfootImage( "sprite_skelly_walk" + String.format("%02d", i) + ".png" );
        }
        
        for( int i=0; i<death.length; i++ )
        {
            death[i] = new GreenfootImage( "sprite_skelly_death" + String.format("%02d", i) + ".png" );
        }

        for( int i=0; i<attack.length; i++ )
        {
            attack[i] = new GreenfootImage( "sprite_skelly_shoot" + String.format("%02d", i) + ".png" );
        }
        health = 800;
        healthBar = new StatBar (health, health,this, 100, 5, 45);
        moveForward = true;
        acts = 0;
        animationTimer = 7;
        health = 800;

        setImage(images[currentFrame]);
        
        world = (MyWorld) getWorld();
        
    }
    
    public void act() 
    {
        super.act();
        
        if (health>0)
        {
            checkInFront();

            checkEdges();
            if (frontEmpty==true)
            {
                if (acts % animationTimer == 0)
                {
                    if(defenderPresent(lane-1) && moveForward == false)
                    {
                        animate(attack, currentAttackFrame);
                        currentAttackFrame++;
                        if (currentAttackFrame == 7)
                        {
                            getWorld().addObject(new SkeletonArrow(), this.getX(), this.getY());
                        }
                    }
                    else 
                    {
                        animateRight(images, currentFrame, 5);
                        currentFrame++;
                    }
                }

                if(currentFrame == images.length)
                {
                    moveForward = false;
                    currentFrame = 0;
                }
                
                if(currentAttackFrame == attack.length)
                {
                    moveForward = true;
                    currentAttackFrame = 0;
                }
                
            }
        }
        

        if (health <=0)
        {
            if (acts % animationTimer == 0)
            {
                animateDeath(death, currentDeathFrame);
                currentDeathFrame++;
            }

            if(currentFrame == images.length)
            {
                removeLane();
            }

        }

        acts++;
    }  
    
    /**
     * Checks to see if there is a defender in the same row as the Skeleton.
     * 
     * @param row       the lane that the skeleton is in 
     * @return boolean  returns true if there is a defender in the lane, returns false if there is no defender in the lane.
     */
    
    private boolean defenderPresent(int row)
    {
        for (Defender d : world.getGameGrid()[row])
        {
            if(d != null)
            {
                return true;
            }
        }
        return false;
    }
}
