import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class GameOverScreen extends World
{
    private GreenfootImage image;
    private GreenfootSound gameOver = new GreenfootSound ("gameOver.mp3");
    private Button toMainScreen;
    private Counter waves;
    
    /**
     * Sets the background for the game over screen and plays
     * the defeat music
     * 
     */
    public GameOverScreen()
    {
        // Create a new world with 1080x720 cells with a cell size of 1x1 pixels
        super(1080, 720, 1); 
        image = new GreenfootImage("gameoverbackground.jpg");
        setBackground(image);
        gameOver.play();
        gameOver.setVolume(40);
        toMainScreen = new Button(13, false);
        toMainScreen.setImage(13);
        addObject(toMainScreen, 540, 550);
        waves = new Counter(250, 35,0,0,0,255,0,0);
        waves.setLabel("Waves Completed: ");
        waves.setFontInfo(12, true, false, 70, 22);
        waves.setValue(MyWorld.getWaveNum()-1);
        addObject(waves, 540, 400);
    }
    
    public void act()
    {
        if (toMainScreen.getClicked())
        {
            Greenfoot.setWorld(new GameStartScreen());
        }
    }

}