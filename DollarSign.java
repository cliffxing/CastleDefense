import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class DollarSign extends Actor
{
    private MouseInfo m;
    private boolean clicked;
    Counter c;
    private MyWorld world;
    private int acts;
    /**
     * Creates new instance of DollarSign class
     */
    public DollarSign()
    {
        setImage("dollar sign.png");
        clicked = false;
        acts = 0;
    }
    
    /**
     * Adds this object to a specified world
     * 
     * @param w World that this object is added to
     */
    public void addedToWorld(World w)
    {
        world = (MyWorld) getWorld();
    }
    
    public void act() 
    {
        m = Greenfoot.getMouseInfo();
        if (m!=null)
        {
            if (Greenfoot.mouseClicked(this))
            {
                if (m.getButton() == 1)
                { // main LEFT mouse button
                    clicked = true;                    
                } 
                else if (m.getButton () == 3)
                { // RIGHT mouse button
                    clicked = true;
                }
            }
        }
        if (clicked==true)
        {
            collectMoney();

        }
        
        if (acts >= 900)
        {
            world.removeObject(this);
        }
        acts++;
    }

    private void collectMoney()
    {
        //Moves towards the money counter and removes itself from world once getting there
        turnTowards(900,65);
        move(10);

        c = (Counter)getOneObjectAtOffset(getImage().getWidth()/8, 0, Counter.class);
        if (c != null )
        {
            world.addMoney();
            world.removeObject(this);
        }

        
    }
}