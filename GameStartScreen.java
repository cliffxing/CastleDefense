import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class GameStartScreen extends World
{
    private Button newGameButton;
    private Button loadGameButton;
    private Button monsterManual;
    private Button defenderManual;
    private MyWorld gameScreen;
    private GreenfootImage image;
    private GreenfootSound mainScreenSong = new GreenfootSound("mainScreenTheme.mp3") ;
    
     /**
     * Sets the background for the starting screen, and creates
     * 4 buttons (to new game, load game, defender manual and
     * monster manual), which direct the player to the
     * screen that they would like to go to
     * 
     */
    public GameStartScreen()
    {    
        // Create a new world with 1080x720 cells with a cell size of 1x1 pixels.
        super(1080, 720, 1);
        
        image = new GreenfootImage("startscreen.jpg");
        setBackground(image);
        newGameButton = new Button(4,false);
        loadGameButton = new Button(9, false);
        defenderManual = new Button(10, false);
        monsterManual = new Button (11, false);
        newGameButton.setImage(4);
        loadGameButton.setImage(9);
        defenderManual.setImage(10);
        monsterManual.setImage(11);

        addObject(newGameButton, 378, 400);
        addObject(loadGameButton, 702, 400);
        addObject(defenderManual, 378, 550);
        addObject (monsterManual, 702, 550);
        
    }
    
     /**
     * Starts the background song when called
     * 
     */
    public void started()
    {
        mainScreenSong.play();
    }
    
     /**
     * Stops the background song
     * 
     */
    public void stopped()
    {
        mainScreenSong.stop();
    }
    
    public void act()
    {
        if (newGameButton.getClicked() == true)
        {
            Greenfoot.setWorld(new MyWorld());
            mainScreenSong.stop();
        }
        
         if (loadGameButton.getClicked() == true)
        {
            Greenfoot.setWorld(new MyWorld(true));
            mainScreenSong.stop();
        }
        
        if (defenderManual.getClicked() == true)
        {
            Greenfoot.setWorld(new DefenderManual());
        }
        if (monsterManual.getClicked() == true)
        {
            Greenfoot.setWorld(new MonsterManual());
        }
    }
    

}
