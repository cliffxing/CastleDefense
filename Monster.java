import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public abstract class Monster extends Actor
{
    Defender d;
    protected int frameL=0;
    protected boolean laneChecked = false;
    protected int damage =1;
    protected int lane;
    protected boolean frontEmpty = true;

    protected int health = 1000;
    protected StatBar healthBar; 
    protected MyWorld world;
    private boolean meteorInflicted = false;

    Explosion e;
    
    public void act()
    {
        
        healthBar.update(health);
        if (laneChecked == false)
        {
            lane = getLane(this.getY());
        }
        checkMeteor();

    }
    
    /**
     * Checks to see if monster is hit by a meteor + explosion (for splash damage)
     */
    protected void checkMeteor()
    {
        e= (Explosion)getOneObjectAtOffset(getImage().getWidth()/20, 0, Explosion.class);
        
        if (e!=null)
        {
            if (meteorInflicted == false)
            {
                health = health - 1000;
                meteorInflicted = true;
            }
 
        }
        else{
            meteorInflicted = false;
        }
    }

    /**
     * Removes monster from grid lane
     */ 
    protected void removeLane()
    {
        if (lane == 1)
        {

            MyWorld.Lane1Monsters.remove(this);
            getWorld().removeObject(this);

        }

        else if (lane == 2)
        {
            MyWorld.Lane2Monsters.remove(this);
            getWorld().removeObject(this);

        }

        else if (lane == 3)
        {
            MyWorld.Lane3Monsters.remove(this);
            getWorld().removeObject(this);

        }

        else if (lane == 4)
        {
            MyWorld.Lane4Monsters.remove(this);
            getWorld().removeObject(this);

        }

        else if (lane == 5)
        {
            MyWorld.Lane5Monsters.remove(this);
            getWorld().removeObject(this);

        }
        MyWorld.monsterAdded();
    }
    
    /**
     * Sets the monster's grid lane.
     * 
     * @param x         the lane number that the monster will spawn
     */
    protected void setLane(int x)
    {
        lane = x;
    }
    
    /**
     * Animates the monster using an array of images and causes the actor to move at a specified speed
     * 
     * @param images        the images for the animation
     * @param currentFrame  the current frame of animation
     * @param speed         the speed at which the monsters are moving
     */
    protected void animateRight(GreenfootImage[] images, int currentFrame, int speed)
    {
        setImage(images[currentFrame]);
        move(speed);
    }
    
    /**
     * Sets whether or not monster is inflicted by the meteor
     * 
     * @param x             the state at whether the monster was inflicted by the meteor
     */
    protected void setInflicted(boolean x)
    {
        meteorInflicted = x;
    }
    
    /**
     * Uses an array of images and frame number to animate the death of the monster.
     * 
     * @param images        the images for the death animation
     * @param currentFrame  the current frame of the animation
     */
     protected void animateDeath(GreenfootImage[] images, int currentFrame)
    {
        
        //If it reaches the final frame, monster is removed 
        if (currentFrame == images.length)
        {
            removeLane();
        }
        
        //iterates through the images array, thus animating the "death"
        else
        {
            setImage(images[currentFrame]);
        }
    }

    /**
     * Checks to see if the monster reaches the edge of the world. If so, removes the object from the world and decreases the amount of 
     * remaining lives
     */
    protected void checkEdges(){
        if (getX() > getWorld().getBackground().getWidth() + getImage().getWidth()){
            world.monsterAdded();
            getWorld().removeObject(this);
            world.decreaseLives();
        }
    }
    
    /**
     * Checks to see if monster collides with a defender. If so, it deals damage to the defender.
     */
    protected void checkInFront()
    {
        d = (Defender)getOneObjectAtOffset(getImage().getWidth()/4, 0, Defender.class);
        if (d != null )
        {

            frontEmpty= false;
            d.takeDamage(damage);
        }

        else
        {
            frontEmpty = true;
        }
    }
    
    /**
     * Animates the monster (with no movement)
     * 
     * @param images        the images for the animation
     * @param currentFrame  the frame of animation
     */
    protected void animate(GreenfootImage[] images, int currentFrame)
    {
        setImage(images[currentFrame]);
    }
    
    /**
     * Adds a health bar to the world for each monster
     * 
     * @param w The world that the health bar is added to
     */
    public void addedToWorld (World w)
    {
        w.addObject (healthBar, this.getX(), this.getY()-60);
        world = (MyWorld) w;
        healthBar.update(health);
    }
    
    /**
     * Decreases the monster's health
     * 
     * @param damage    the damage dealt to the monsters    
     */
    protected void takeDamage(int damage)
    {
        health = health - damage;
        
    }
    
    /**
     * Gets the lane that the monster is in and returns it
     * 
     * @param y         the lane number the monster is in
     * @return int      1 if int y is between 130 and 236, 2 if int y is between 246 and 342, 3 if int y is between 352 and 458, 4 if int y is 
     *                  between 468 and 574, 5 if int y is between 584 and 690, and -1 if int y does not match any of the previous statements.
     */
    protected int getLane(int y)
    {

        if (y > 130 && y < 236)
        {
            MyWorld.addToLane(this, 1);
            laneChecked = true;
            return 1;
        }

        else if (y >246 && y < 342)
        {
            MyWorld.addToLane(this, 2);
            laneChecked = true;
            return 2;
        }

        else if (y >352 && y < 458)
        {
            MyWorld.addToLane(this, 3);
            laneChecked = true;
            return 3;
        }

        else if (y>468 && y < 574)
        {
            MyWorld.addToLane(this, 4);
            laneChecked = true;
            return 4;
        }

        else if (y >584 && y< 690)
        {
            MyWorld.addToLane(this, 5);
            laneChecked = true;
            return 5;
        }
        
        return -1;
    }

}
