import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class WaveCompleteScreen extends World
{
    private GreenfootImage image;
    Button nextWave = new Button(8, false);
    private MyWorld gameScreen;
    private Counter wavesCounter;
    private MyWorld mainWorld;

    /**
     * Sets the background for the victory screen
     * after a user defeats a wave. Also creates a 
     * counter that shows the current wave number completed,
     * as well as a button that proceeds user to the next wave
     * 
     * @param mainWorld   the main gamescreen world the user was previously on
     * 
     */
    public WaveCompleteScreen(MyWorld mainWorld)
    {
        // Create a new world with 1080x720 cells with a cell size of 1x1 pixels.
        super(1080, 720, 1); 
        image = new GreenfootImage("winscreen.jpg");
        setBackground(image);
        nextWave.setImage(8);
        addObject(nextWave, 540, 500);
        wavesCounter = new Counter(250, 50);
        wavesCounter.setLabel("Waves Completed: ");
        wavesCounter.setFontInfo(15, true, false, 50, 30);
        addObject(wavesCounter, 542, 200);
        this.mainWorld = mainWorld;
    }

    public void act()
    {
        //changes the world if the button "next wave" is clicked
        if (nextWave.getClicked()== true)
        {
            Greenfoot.setWorld(mainWorld);
        }

        wavesCounter.setValue(mainWorld.getWaveNum() - 1);

    }
}