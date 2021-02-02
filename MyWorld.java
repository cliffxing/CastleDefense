import greenfoot.*;
import java.util.Arrays;
import java.util.ArrayList;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class MyWorld extends World
{
    //Sound effects
    private GreenfootSound placeBoard = new GreenfootSound("place.wav");
    private GreenfootSound errorSound = new GreenfootSound("error.wav");
    private GreenfootSound battleThemeSong = new GreenfootSound("battleScreenTheme.mp3");
    
    //Game buttons
    private Button button = new Button(1, false);
    private Button button2 = new Button(2, false);
    private Button button3 = new Button(3, false);
    private Button button4 = new Button(5, false);
    private Button button5 = new Button(6, false);
    private Button deleteButton = new Button(7, true);
    
    private GlowingBox box = new GlowingBox();

    private int randomize;
    private int laneRandomize; 
    private int monsterAdded;
    private int xCoord;
    private int yCoord;
    private Counter wavesCounter;

    private static boolean mouseTracking = false;
    private static int previousLane =0;

    //Initialise Lane Spawn Check 
    private LaneSpawnCheck[] laneChecks;

    private int lane;

    private Counter moneyCounter;
    private Counter livesCounter;
    public int money;
    private int lives;
    private int acts;
    private static int waveNum = 1;
    private static int numMonsters;
    
    //User info variables
    private UserInfo user;
    private int spawnRate;
    private String storedGameGrid0;
    private String storedGameGrid1;
    private String storedGameGrid2;
    private String storedGameGrid3;
    private String storedGameGrid4;

    private boolean load;

    private MouseInfo mouse;
    private MouseInfo mouse2;

    private Blacksmith bs;
    private Archer arch;
    private Dragon drag;
    private Sheep sheep;

    private Defender[][]gameGrid = new Defender[5][9];
    public static ArrayList<Monster> Lane1Monsters = new ArrayList<Monster>();
    public static ArrayList<Monster> Lane2Monsters = new ArrayList<Monster>();
    public static ArrayList<Monster> Lane3Monsters = new ArrayList<Monster>();
    public static ArrayList<Monster> Lane4Monsters = new ArrayList<Monster>();
    public static ArrayList<Monster> Lane5Monsters = new ArrayList<Monster>();

    /**
     * Creates new instance of MyWorld and loads the game screen
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1080, 720, 1, false); 
        loadGame();
    }
    
    /**
     * Creates new instance of MyWorld based on whether or not to use saved user data
     * 
     * @param load True if saved data should be used, false otherwise
     */
    public MyWorld(boolean load)
    {
        super(1080, 720, 1, false); 
        this.load = load;
        loadGame();
    }

    private void loadGame()
    {
        setBackground(new GreenfootImage("Background.png"));

        //Wave num is saved in the first int
        if (UserInfo.isStorageAvailable() && load==true) {
            user = UserInfo.getMyInfo();
            waveNum = user.getInt(0);
            money = user.getInt(1);
            lives = user.getInt(2);
            storedGameGrid0 = user.getString(0);
            storedGameGrid1 = user.getString(1);
            storedGameGrid2 = user.getString(2);
            storedGameGrid3 = user.getString(3);
            storedGameGrid4 = user.getString(4);
            gridSetter(storedGameGrid0, 0);
            gridSetter(storedGameGrid1, 1);
            gridSetter(storedGameGrid2, 2);
            gridSetter(storedGameGrid3, 3);
            gridSetter(storedGameGrid4, 4);
        }
        else{
            user = UserInfo.getMyInfo();
            waveNum = 1;
            money = 200;
            lives = 3;

            storedGameGrid0 = "";
            storedGameGrid1 = "";
            storedGameGrid2 = "";
            storedGameGrid3 = "";
            storedGameGrid4 = "";
        }
        setPaintOrder(DollarSign.class, StatBar.class, Projectile.class);

        spawnRate = 300;
        numMonsters =0;
        Lane1Monsters.clear();
        Lane2Monsters.clear();
        Lane3Monsters.clear();
        Lane4Monsters.clear();
        Lane5Monsters.clear();

        addObject(button, 750, 65);
        button.setImage(1);
        addObject(button2, 620, 65);
        button2.setImage(2);
        addObject(button3, 490, 65);
        button3.setImage(3);
        addObject(button4, 360, 65);
        button4.setImage(5);
        addObject(button5, 230, 65);
        button5.setImage(6);
        addObject(deleteButton, 100, 65);
        deleteButton.setImage(7);

        //Places a lane at a random position between 0 and 3 all inclusive
        lane = 0;
        laneChecks = new LaneSpawnCheck [5];
        for (int i = 0; i < 5; i++){
            // initialize each lane's spawn checker, passing it's lane number so it can be
            // "self-aware" of which lane it is - this isn't necessary right now, but might
            // be useful in the future to support additional features
            laneChecks[i] = new LaneSpawnCheck(i);
            addObject (laneChecks[i], 25, getYPosition(i));
        }        

        //initializes the money counter
        moneyCounter = new Counter(250, 35,0,0,0,0,255,0);
        moneyCounter.setLabel("Money: $");
        moneyCounter.setFontInfo(12, true, false, 80, 22);
        addObject(moneyCounter, 950, 20);
        moneyCounter.setValue(money);

        livesCounter = new Counter(250, 35,0,0,0,255,0,0);
        livesCounter.setLabel("lives: ");
        livesCounter.setFontInfo(12, true, false, 93, 22);
        addObject(livesCounter, 950, 60);
        lives = 3;
        livesCounter.setValue(lives);
        if(waveNum < 4)
        {
            spawnRate = 800 - 220*(waveNum-1);
        }
        else{
            spawnRate = 100;
        }
        wavesCounter = new Counter(250, 35);
        wavesCounter.setLabel("Wave Number: ");
        wavesCounter.setFontInfo(12, true, false, 70, 22);
        wavesCounter.setValue(waveNum);
        addObject(wavesCounter, 950, 100);
        Greenfoot.setSpeed(50);
        acts = 1;
    }

    public void act()
    {
        battleThemeSong.play();
        battleThemeSong.setVolume(35);
        if(acts % spawnRate == 0 && acts >= 700)
        {
            if (monsterAdded < (waveNum * 6))
            {
                generateMonsters();
            }
            money += 1;
            moneyCounter.setValue(money);
        }
        if(numMonsters == waveNum * 6)
        {
            waveNum++;
            monsterAdded=0;
            waveEnd();
            numMonsters=0;
            Greenfoot.setWorld(new WaveCompleteScreen(this));
        }
        mouse = Greenfoot.getMouseInfo();
        if (deleteButton.getClicked() == true && mouse !=null)
        {
            mouseTracking = false;

            if (Greenfoot.mouseClicked(null)  )
            {

                int x = mouse.getX();
                int y = mouse.getY();
                removeObject(box);

                xCoord = getXPosition(getGridXCoordinate(x));
                yCoord = getYPosition(getGridYCoordinate(y));

                if (xCoord >0 && yCoord > 0 )
                {
                    Defender d = gameGrid[getGridYCoordinate(y)][getGridXCoordinate(x)];
                    if (d instanceof Blacksmith)
                    {
                        removeObject(d);

                        gameGrid[getGridYCoordinate(y)][getGridXCoordinate(x)] =  null;
                        money += 25;
                        moneyCounter.setValue(money);
                    }
                    else if (d instanceof Archer)
                    {
                        removeObject(d);

                        gameGrid[getGridYCoordinate(y)][getGridXCoordinate(x)] =  null;
                        money += 50;
                        moneyCounter.setValue(money);
                    }
                    else if (d instanceof Dragon)
                    {
                        removeObject(d);

                        gameGrid[getGridYCoordinate(y)][getGridXCoordinate(x)] =  null;
                        money += 100;
                        moneyCounter.setValue(money);
                    }
                    else if (d instanceof Sheep)
                    {
                        removeObject(d);

                        gameGrid[getGridYCoordinate(y)][getGridXCoordinate(x)] =  null;
                        money += 25;
                        moneyCounter.setValue(money);
                    }
                }
                deleteButton.setClicked();
                deleteButton.setIsDeleting();
            }

            else
            {
                box.setImage(1);
                addObject(box, mouse.getX(), mouse.getY());
                addGlowingBox(mouse.getX(), mouse.getY(), 2);
            }
        }

        else if (button.getClicked() == true && mouse !=null)
        {
            addDefender("bs", button, 50, 0);
        }
        else if (button2.getClicked() == true && mouse !=null )
        {
            addDefender("arch", button2, 100, 0);
        }
        else if (button3.getClicked() == true && mouse !=null )
        {
            addDefender("drag", button3, 200, 0);
        }
        else if (button4.getClicked() == true && mouse !=null )
        {
            addDefender("meteor", button4, 300, 1);
        }
        else if (button5.getClicked() == true && mouse !=null)
        {
            addDefender("sheep", button5, 50, 0);
        }
        acts++;
    }
    
    /**
     * Starts the music and sets the volume of the background song
     * when the world is ran
     * 
     */
    public void started()
    {
        battleThemeSong.play();
        battleThemeSong.setVolume(50);
    }
    

    /**
     * Pauses the background music when the world
     * is paused/stopped
     * 
     */
    public void stopped()
    {
        battleThemeSong.stop();
    }

    /**
     * Returns the current wave number of the game
     * 
     * @return int the value of the current wave
     */
    public static int getWaveNum()
    {
        return waveNum;
    }

    
    /**
     * Adds defender to the grid when a tile is pressed. Also
     * deducts from the player's money depending on the
     * cost of the defender being placed.
     * 
     * @param defenderType      The type of defender being added
     * @param button        The button that was pressed to place defender
     * @param money         The cost of the defender being placed
     * @param boxType       The type of mouse tracking icon (crosshair, bluebox, redbox, etc.)
     */
    private void addDefender(String defenderType, Button button, int money, int boxType)
    {
        mouseTracking = false;

        if (Greenfoot.mouseClicked(null)  )
        {

            removeObject(box);
            Defender defenderObject;
            int x = mouse.getX();
            int y = mouse.getY();

            xCoord = getXPosition(getGridXCoordinate(x));
            yCoord = getYPosition(getGridYCoordinate(y));

            if (xCoord >0 && yCoord > 0 )
            {

                if (gameGrid[getGridYCoordinate(y)][getGridXCoordinate(x)]==null && this.money >= money)
                {
                    if (defenderType.equals("bs"))
                    {
                        //add blacksmith to game grid
                        bs = new Blacksmith (getGridYCoordinate(y),getGridXCoordinate(x));
                        addObject(bs, xCoord,yCoord);
                        gameGrid[getGridYCoordinate(y)][getGridXCoordinate(x)] =  bs;
                        this.money -= money;
                        moneyCounter.setValue(this.money);
                        placeBoard.play();
                    }
                    else if(defenderType.equals("arch"))
                    {
                        //add archer to game grid
                        arch = new Archer(getGridYCoordinate(y),getGridXCoordinate(x) );
                        gameGrid[getGridYCoordinate(y)][getGridXCoordinate(x)] = arch;
                        addObject(arch, xCoord,yCoord);
                        this.money -= money;
                        moneyCounter.setValue(this.money);
                        placeBoard.play();
                    }
                    else if(defenderType.equals("drag"))
                    {
                        //add dragon to game grid
                        drag = new Dragon(getGridYCoordinate(y),getGridXCoordinate(x));
                        gameGrid[getGridYCoordinate(y)][getGridXCoordinate(x)] = drag;
                        addObject(drag, xCoord,yCoord);
                        this.money -= money;
                        moneyCounter.setValue(this.money);
                        placeBoard.play();
                    }
                    else if(defenderType.equals("sheep"))
                    {
                        //add sheep to game grid
                        sheep = new Sheep (getGridYCoordinate(y),getGridXCoordinate(x));
                        addObject(sheep, xCoord,yCoord);

                        gameGrid[getGridYCoordinate(y)][getGridXCoordinate(x)] =  sheep;
                        this.money -= money;
                        moneyCounter.setValue(this.money);
                        placeBoard.play();
                    }
                    else if(defenderType.equals("meteor"))
                    {
                        //add meteor to game grid
                        launchMeteor(xCoord, yCoord);
                        this.money -= money;
                        moneyCounter.setValue(this.money);
                        placeBoard.play();
                    }
                }
                else 
                {
                    errorSound.play();
                    Greenfoot.delay(20);
                    errorSound.stop();
                }
            }
            button.setClicked();
        }

        else
        {
            box.setImage(boxType+2);
            addObject(box, mouse.getX(), mouse.getY());
            addGlowingBox(mouse.getX(), mouse.getY(), boxType);
        }
    }

    /**
     * Launches meteor item from the top of the 
     * world
     * 
     * @param x         the y coordinate of the destination of the meteor
     * @param y         the x coordinate of the destination of the meteor
     */
    public void launchMeteor(int x, int y)
    {
        Meteor met = new Meteor(x, y);
        addObject(met, 780, 0);
    }

    /**
     * Adds monster objects into the monster
     * arraylists depending on which 
     * lane it was spawned in.
     * 
     * @param x        The monster that is to be added into the ArrayList
     * @param lane     The lane that the monster was spawned in
     */
    public static void addToLane(Monster x, int lane)
    {
        if (lane ==1)
        {
            Lane1Monsters.add(x);
        }

        else if (lane == 2)
        {
            Lane2Monsters.add(x);
        }

        else if (lane == 3)
        {
            Lane3Monsters.add(x);
        }

        else if (lane == 4)
        {
            Lane4Monsters.add(x);
        }

        else if (lane == 5)
        {
            Lane5Monsters.add(x);
        }
    }

    
    /**
     * Adds the box icon into the world (the mouse tracking
     * icon when user is choosing where to place defenders)
     * 
     * @param x         the x coordinate of where the glowing box location will be set
     * @param y         the y coordinate of where the glowing box location will be set
     * @param type      type of box icon required (crosshair, bluebox, etc.)
     */
    private void addGlowingBox(int x, int y, int type)
    {
        if (type==0) //regular 
        {
            if ( getXPosition(getGridXCoordinate(x))>0 && getYPosition(getGridYCoordinate(y))>0)
            {
                box.setImage(2);
                box.setLocation(getXPosition(getGridXCoordinate(x)), getYPosition(getGridYCoordinate(y))); 
            }
        }

        else if (type == 1) //crosshair
        {
            if ( getXPosition(getGridXCoordinate(x))>0 && getYPosition(getGridYCoordinate(y))>0)
            {
                box.setImage(3);
                box.setLocation(getXPosition(getGridXCoordinate(x)), getYPosition(getGridYCoordinate(y))); 
            }
        }

        else if (type == 2) //delete
        {
            if ( getXPosition(getGridXCoordinate(x))>0 && getYPosition(getGridYCoordinate(y))>0)
            {
                box.setImage(4);
                box.setLocation(getXPosition(getGridXCoordinate(x)), getYPosition(getGridYCoordinate(y))); 
            }
        }

    }

    /**
     * Calls the add glowing box method to 
     * where the current mouse is
     * 
     */
    private void mouseTrack()
    {
        if (mouseTracking = true)
        {
            addGlowingBox(mouse2.getX(), mouse2.getY(), 1);
        }
    }

    
     /**
     * Randomly spawns monsters into the world depending on 
     * the wave number. Randomly spawns zombies, spirits, skeletons 
     * and skulls into random lanes.
     * 
     */
    public void generateMonsters()
    {
        lane = Greenfoot.getRandomNumber(5);
        int randNum = Greenfoot.getRandomNumber(100);

        if(lane < 5)
        {
            if(laneChecks[lane].monsterPresent() == false && waveNum == 1 && randNum < 50)
            {
                addObject(new Zombie(), 20, getYPosition(lane));
                monsterAdded++;
            }
            else if (laneChecks[lane].monsterPresent() == false && waveNum == 2)
            {
                if(randNum < 50)
                {
                    //spawn zombie
                    addObject(new Zombie(), 20, getYPosition(lane));
                    monsterAdded++;
                }
                else if (randNum > 50)
                {
                    //spawn spirit
                    addObject(new Spirit(), 20, getYPosition(lane));
                    monsterAdded++;
                }
            }
            else if(laneChecks[lane].monsterPresent() == false && waveNum == 3)
            {
                if(randNum < 33)
                {
                    //spawn zombie
                    addObject(new Zombie(), 20, getYPosition(lane));
                    monsterAdded++;
                }
                else if(randNum > 33 && randNum < 66)
                {
                    //spawn spirit
                    addObject(new Spirit(), 20, getYPosition(lane));
                    monsterAdded++;
                }
                else if(randNum > 66)
                {
                    //spawn skeleton
                    addObject(new Skeleton(), 20, getYPosition(lane));
                    monsterAdded++;
                }
            }
            else if (laneChecks[lane].monsterPresent() == false && waveNum == 4)
            {
                if(randNum < 25)
                {
                    //spawn zombie
                    addObject(new Zombie(), 20, getYPosition(lane));
                    monsterAdded++;
                }
                else if(randNum > 25 && randNum < 50)
                {
                    //spawn spirit
                    addObject(new Spirit(), 20, getYPosition(lane));
                    monsterAdded++;
                }
                else if(randNum > 50 && randNum < 75)
                {
                    //spawn skeleton
                    addObject(new Skeleton(), 20, getYPosition(lane));
                    monsterAdded++;
                }
                else if (randNum > 75)
                {
                    //spawn skull
                    addObject(new Skull(), 20, getYPosition(lane));
                    monsterAdded++;
                }
            }
            else if (laneChecks[lane].monsterPresent() == false && waveNum > 4)
            {
                if(randNum <= 10)
                {
                    //spawn zombie
                    addObject(new Zombie(), 20, getYPosition(lane));
                    monsterAdded++;
                }
                else if(randNum > 10 && randNum < 25)
                {
                    //spawn spirit
                    addObject(new Spirit(), 20, getYPosition(lane));
                    monsterAdded++;
                }
                else if(randNum >= 25 && randNum < 70)
                {
                    //spawn skeleton
                    addObject(new Skeleton(), 20, getYPosition(lane));
                    monsterAdded++;
                }
                else if (randNum >= 70)
                {
                    //spawn skull
                    addObject(new Skull(), 20, getYPosition(lane));
                    monsterAdded++;
                }
            }
        }
    }

     /**
     * Returns the  Xcoordinate on the game grid (5x9) given its
     * corresponding x and y value in the world
     * 
     * @param x     the x coordinate in the world
     * 
     * @return int  the x coordinate on the grid
     * 
     */
    private int getGridXCoordinate (int x)
    {
        if (x >28 && x < 134)
        {
            return 0;
        }

        else if (x >144 && x < 250)
        {
            return 1;
        }

        else if (x >260 && x < 366)
        {
            return 2;
        }

        else if (x>376 && x < 482)
        {
            return 3;
        }

        else if (x >492 && x < 598)
        {
            return 4;
        }

        else if (x > 608 && x <714)
        {
            return 5;
        }

        else if (x >724 && x < 830)
        {
            return 6;
        }

        else if (x >840 && x < 946)
        {
            return 7;
        }

        else if (x >956 && x < 1062)
        {
            return 8;
        }
        return -1;
    }

     /**
     * Returns the  Ycoordinate on the game grid (5x9) given its
     * corresponding x and y value in the world
     * 
     * @param y     the y coordinate in the world
     * 
     * @return int  the y coordinate on the grid
     * 
     */
    private int getGridYCoordinate(int y)
    {
        if (y > 130 && y < 236)
        {
            return 0;
        }

        else if (y >246 && y < 342)
        {
            return 1;
        }

        else if (y >352 && y < 458)
        {
            return 2;
        }

        else if (y>468 && y < 574)
        {
            return 3;
        }

        else if (y >584 && y< 690)
        {
            return 4;
        }

        return -1;
    }

    //Credit to Mr Cohen
    private int getYPosition (int inLane)
    {
        // Manually input values based on the background graphic
        switch (inLane)
        {
            case 0: 
            return 182;

            case 1:
            return 297;

            case 2:
            return 412;

            case 3:
            return 527;

            case 4:
            return 642;
        }  
        // In case an invalid value is passed in
        return 0;
    }

    /**
     * Returns the Xcoordinate of the world given the corresonding coordinate
     * on the game grid
     * 
     * @param i     the x coordinate on grid
     * 
     * @return int  the x coordinate in the world
     * 
     */
    private static int getXPosition (int i)
    {
        switch (i)
        {
            case 0:
            return 81;

            case 1:
            return 197;

            case 2:
            return 313;

            case 3:
            return 422;

            case 4:
            return 545;

            case 5:
            return 661;

            case 6:
            return 777;

            case 7:
            return 893;

            case 8:
            return 1009;

        }
        return 0;
    }

     /**
     * Increments the money variable when money is collected
     * from the blacksmith
     */
    public void addMoney()
    {
        //adds money
        money += 50;
        moneyCounter.setValue(money);
    }

     /**
     * Decreases the lives count
     * when a monster reaches the edge of the world
     * 
     */
    public void decreaseLives()
    {
        
        lives -= 1;
        livesCounter.setValue(lives);
        if (lives <= 0)
        {
            ending();
            Greenfoot.setWorld(new GameOverScreen());
            battleThemeSong.stop();
        }
    }

    /**
     * Returns the gamegrid holding all the
     * defenders in the world
     * 
     * @return Defender[][] the gamegrid holding the defenders
     * 
     */
    public Defender[][] getGameGrid()
    {
        return gameGrid;
    }

     /**
     * Sets a spot on the grid given coordinates and 
     * the object that is to be set
     * 
     * @param x     the x coordinate where the object is to be placed
     * @param y     the y coordinate where the object is to be placed
     * @param object    the defender object that will be added into the grid
     * 
     */
    public void setGameGrid(int x, int y, Defender object)
    {
        gameGrid[x][y] = object;
    }

    /**
     * Loads the grid with defenders from previous
     * data (using userInfo class). Reads strings
     * for the respective lanes and places defenders in spots
     * depending on the string's contents.
     * 
     * @param str    String containing the contents of the row's defenders
     * @param row    The row of the String being read
     * 
     */
    private void gridSetter(String str, int row){
        //Intialize variables
        Archer arch;
        Dragon drag;
        Blacksmith bs;
        Sheep sheep;
        //If the string is not null
        if(str != null)
        {
            //Iterates over the string
            for (int i = 0; i < str.length(); i++){
                //Looks at the character at the ith position
                char c = str.charAt(i); 
                
                //Based on the letter there adds that defender to the ith position in the grid
                if(c == 'a')
                {
                    arch = new Archer(row, i);
                    addObject(arch, getXPosition(i), getYPosition(row));
                    gameGrid[row][i] = arch;
                }
                else if(c == 'b')
                {
                    bs = new Blacksmith(row, i);
                    addObject(bs, getXPosition(i), getYPosition(row));
                    gameGrid[row][i] = bs;
                }
                else if(c == 'd')
                {
                    drag = new Dragon(row, i);
                    addObject(drag, getXPosition(i), getYPosition(row));
                    gameGrid[row][i] = drag;
                }
                else if(c == 's')
                {
                    sheep = new Sheep(row, i);
                    addObject(sheep, getXPosition(i), getYPosition(row));
                    gameGrid[row][i] = sheep;
                }
                else if(c == 'n')
                {
                    gameGrid[row][i] = null;
                }
            }
        }
    }

    /**
     * Creates strings of the defenders currently in the world
     * using the gamegrid array so that the strings can be 
     * read when loading previous user data. 
     * 
     * @param row   The row of the String being created   
     * @return String   Returns the row String with each character representing a defender (n signifies no defender there)
     */
    private String getDefenders(int row)
    {
        String str = "";
        //Iterates over the grid at the index passed as a parameter
        for (Defender d : this.getGameGrid()[row])
        {
            //If the defender at the position is not null 
            //Encode the defender based on their first letter example "a", "b" etc...
            if(d != null)
            {
                if(d instanceof Archer)
                {
                    str += "a";
                }
                else if(d instanceof Dragon)
                {
                    str += "d";
                }
                else if(d instanceof Blacksmith)
                {
                    str += "b";
                }
                else if(d instanceof Sheep)
                {
                    str += "s";
                }
            }
            else
            {
                str += "n";
            }
        }
        //returns the encoded string
        return str;
    }

    /**
     * Increments the numMonsters variable when
     * a monster is killed or leaves the world
     * 
     */
    public static void monsterAdded()
    {
        numMonsters++;
    }

    /**
     * Prepares all the wave variables 
     * for the next wave when a 
     * level is beat. Also stores all the 
     * users information (defender positions, 
     * lives, money, etc.) into the userInfo 
     * class so if user returns, the next wave 
     * will be properly loaded.
     * 
     */
    private void waveEnd()
    {
        if (spawnRate >= 100)
        {
            spawnRate -= 220;
        }
        
        wavesCounter.setValue(waveNum);
        user.setInt(0, waveNum);
        user.setInt(1, money);
        user.setInt(2, lives);

        storedGameGrid0 = getDefenders(0);
        storedGameGrid1 = getDefenders(1);
        storedGameGrid2 = getDefenders(2);
        storedGameGrid3 = getDefenders(3);
        storedGameGrid4 = getDefenders(4);

        user.setString(0, storedGameGrid0);
        user.setString(1, storedGameGrid1);
        user.setString(2, storedGameGrid2);
        user.setString(3, storedGameGrid3);
        user.setString(4, storedGameGrid4);
        
        user.store();
    }

    /**
     * Called when player loses, clears all of the userInfo data
     * (player restarts from beginning when game is over)
     * 
     * 
     */
    private void ending()
    {
        wavesCounter.setValue(waveNum);
        user.setInt(0, 1);
        user.setInt(1, 250);
        user.setInt(2, 3);

        user.setString(0, "");
        user.setString(1, "");
        user.setString(2, "");
        user.setString(3, "");
        user.setString(4, "");

        user.store();
    }
}


