import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Archer extends Defender
{
    private GreenfootImage[] attack = new GreenfootImage[37];
    private int currentFrame;
    protected int attackAct = 0;

    private int acts;
    private int animationTimer;

    private int animationAttackTimer;
    private boolean inAnim;
    /**
     * Act - do whatever the Archer wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();

        //Checks to see if the archer's health is greater than 0
        if (health>0)
        {
            lane = getLane(this.getY());
        }
        checkAttack();
        acts++;
    }    

    /**
     * Rotates the archer through animation frames. Creates a new instance of Archer and gives it 400 health on its health bar.
     * 
     * @param y         the y coordinate of the archer
     * @param x         the x coordinate of the archer
     */
    
    public Archer(int y, int x)
    {
        yCoord = y;
        xCoord = x;
        currentFrame = 0;
        
        //in charge of the attack animation
        for( int i=0; i<attack.length; i++ )
        {
            attack[i] = new GreenfootImage( "sprite_archer" + String.format("%02d", i) + ".png" );
        }
        
        //sets the values for the healthbar of the archer
        health = 400;
        healthBar = new StatBar (health, health,this, 100, 5, 45);
        
        acts = 0;
        animationTimer = 5;
        animationAttackTimer = 4;
        inAnim = false;

        setImage(attack[currentFrame]);
    }
    
    /**
     * Animates the archer when attacking the monsters. On animation frame 27,the archer will spawn an arrow into the world.
     */
    public void attackMonster()
    {
        if (currentFrame == 1)
        {
            inAnim = false;
        }
        else if(currentFrame == 9)
        {
            inAnim = true;
        }
        if (acts % animationAttackTimer == 0)
        {
            animate(attack, currentFrame);
            currentFrame++;
            
            //Checks to fire the projectile at animation frame 27
            if (currentFrame == 27)
            {
                if (shot == false)
                {
                    getWorld().addObject(new Arrow(), this.getX(), this.getY());
                    shot = true;
                }
            }
            else if(currentFrame == attack.length)
            {
                currentFrame = 0;
                shot = false;
            }
        }
    }
    
    /**
     * @return boolean     the animation frame the archers are on
     */
    public boolean getinAnim()
    {
        return inAnim;
    }
}
