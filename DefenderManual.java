import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class DefenderManual extends World
{
    private GreenfootImage image;
    private Button back;

    /**
     * Sets the background for the defender manual world and adds a 
     * 'back' button that returns user back to the main screen
     * 
     */
    public DefenderManual()
    {
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1080, 720, 1); 
        back = new Button(12, false);
        back.setImage(12);
        image = new GreenfootImage("DefenderManual.jpg");
        setBackground(image);
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