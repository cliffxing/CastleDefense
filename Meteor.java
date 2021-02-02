import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Meteor extends Projectile
{
    private GreenfootImage meteorImage= new GreenfootImage("meteor.png");
    private int destinationX;
    private int destinationY;
    
    /**
     * Act - do whatever the Meteor wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        //Moves towards a specific destination; upon reaching it, adds Explosion actor to the world and
        //meteor removes itself from world
        turnTowards(destinationX, destinationY);
        move(8);

        if (this.getX()>=destinationX-10 && this.getY() >= destinationY-5 || this.getX()>=destinationX+10 && this.getY() >= destinationY-5)
        {
            
            getWorld().addObject(new Explosion(4), this.getX(), this.getY());
            getWorld().removeObject(this);
        }
    }    
    
    /**
     * Creates a new instance of a meteor, setting its image and determining where the meteor will land
     * 
     * @param x     the x coordinate of where the meteor will land
     * @param y     the y coordinate of where the meteor will land
     */
    public Meteor(int x, int y)
    {
        setImage(meteorImage);
        destinationX =x;
        destinationY = y;
        type = 2;
    }
    
    
}
