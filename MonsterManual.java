import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class MonsterManual extends World
{
    private GreenfootImage image;
    private Button back;


    /**
     * Sets the background for the monster manual screen
     * and creates a 'back' button that returns the user back to the
     * main starting screen
     * 
     */
    public MonsterManual()
    {
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1080, 720, 1); 
        image = new GreenfootImage("MonsterManual.jpg");
        setBackground(image);
        back = new Button(12, false);
        back.setImage(12);
        addObject(back, 540, 550);
    }

    public void act()
    {
        if (back.getClicked())
        {
            Greenfoot.setWorld(new GameStartScreen());
        }
    }
}