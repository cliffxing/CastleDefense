import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Arrays;

public abstract class Defender extends Actor
{
    protected int health;
    protected int lane;
    protected int xCoord;
    protected int yCoord;
    protected boolean shot;
    protected StatBar healthBar; //int maxVal, int currVal, Actor owner, int width, int height, int offset
    private MyWorld world;
    
    public void act() 
    {
       healthBar.update(health);
       if (health <=0)
       {
           world.setGameGrid(this.yCoord,this.xCoord, null);
           getWorld().removeObject(this);
       }       
    }    
    
    protected void animate(GreenfootImage[] images, int currentFrame)
    {
        setImage(images[currentFrame]);
    }

    /**
     * Determines the lane that the Defender is placed in.
     * 
     * @param y         the y coordinate of the Defender.
     * @return num      1 if int y is between 130 and 236, 2 if int y is between 246 and 342, 3 if int y is between 352 and 458, 4 if int y is 
     *                  between 468 and 574, 5 if int y is between 584 and 690, and -1 if int y does not match any of the previous statements.
     */
    
    protected int getLane(int y)
    {
        if (y > 130 && y < 236)
        {
            
            return 1;
        }

        else if (y >246 && y < 342)
        {
            return 2;
        }

        else if (y >352 && y < 458)
        {
            return 3;
        }

        else if (y>468 && y < 574)
        {
            return 4;
        }

        else if (y >584 && y< 690)
        {
            return 5;
        }

        return -1;
    }
    
    /**
     * Checks to see if the lane which the defender is in has a monster coming towards the defenders. Sets the value of attack's parameter to 
     * the lane which the monster is in. 
     */
    
    protected void checkAttack()
    {
        if ((lane ==1 && MyWorld.Lane1Monsters.isEmpty()==false) || (lane == 1 && checkMidAnim()))
        {
            
            attack(0);
        }
        
        else if ((lane == 2 && MyWorld.Lane2Monsters.isEmpty() == false) || (lane == 2 && checkMidAnim()))
        {
            
            attack(1);
        }
        
        else if ((lane == 3 && MyWorld.Lane3Monsters.isEmpty() == false) || (lane == 3 && checkMidAnim()))
        {
            
            attack(2);
        }
        
        else if ((lane == 4 && MyWorld.Lane4Monsters.isEmpty() == false) || (lane == 4 && checkMidAnim()))
        {
            attack(3);
        }
        
        else if ((lane == 5 && MyWorld.Lane5Monsters.isEmpty() == false) || (lane == 5 && checkMidAnim()))
        {
            attack(4);
        }
    }
    
    /**
     * Checks to see if the archer is in the middle of an animation. 
     * 
     * @return boolean      return true if the archer is in the middle of its animation, return false if the archer is not in the middle of its 
     *                      animation
     */
    
    private boolean checkMidAnim()
    {
        if (this instanceof Archer)
        {
            Archer arch = (Archer) this;
            //Checks to see if the archer is in the middle of an animation
            
            if (arch.getinAnim() == true)
            {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Call the defender to attack the monster if the monster is in the same row as the defender.
     * 
     * @param row      the row which the defender will attack the monster in 
     */
    
    private void attack(int row)
    {
        Archer arch;
        Dragon drag;
        for (Defender d : world.getGameGrid()[row])
        {
            if(d != null)
            {
                //Checks to see if the defender is an archer
                if(d instanceof Archer)
                {
                    arch = (Archer) d;
                    arch.attackMonster();
                }
                //Checks to see if the defender is an dragon
                else if(d instanceof Dragon)
                {
                    drag = (Dragon) d;
                    drag.attack();
                }
            }
        }
    }

    /**
     * Adds a health bar to the world for each defender
     * 
     * @param w The world that the health bar is added to
     */
    public void addedToWorld (World w)
    {
        //add healthbar to the world and places it above defender
        w.addObject (healthBar, this.getX(), this.getY()-60);
        healthBar.update(health);
        world = (MyWorld) getWorld();
    }
    
    /**
     * Adjusts the health when the Defender takes damage from the Monsters
     * 
     * @param damage    the amount of damage dealt to the Defender
     */
    public void takeDamage(int damage)
    {
        health = health - damage;
    }
}
