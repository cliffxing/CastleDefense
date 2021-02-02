import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public abstract class Projectile extends Actor
{
    Monster m;
    Defender d;
    protected int type;
    protected boolean inflicted = false;
    protected int damage = 150;
    protected boolean hasTouchedSpirit = false;
    /**
     * Act - do whatever the Projectile wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {

    }       

    //Checks to see if the projectile has hit the Monsters. If it is a spirit monster, have the spirit monster dodge the first projectile
    protected void attack(int speed)
    {
        if (type !=2)
        {
            m = (Monster)getOneObjectAtOffset(getImage().getWidth()/2, 0, Monster.class);

            if(m instanceof Spirit && !hasTouchedSpirit && m != null)
            {
                Spirit s = (Spirit) m;
                if(s.getProjectilesDodged() == 0 && s.getFrontEmpty())
                {
                    s.addProjectilesDodged();
                    s.dodge();
                    hasTouchedSpirit = true;
                }
            }

            if (hasTouchedSpirit)
            {
                move(speed);
            }
            
            if(m == null)
            {
                move(speed);
                hasTouchedSpirit = false;
            }
            else if(!hasTouchedSpirit)
            {
                m.takeDamage(damage);
                inflicted = true;
                getWorld().removeObject(this);
            }
        }

    }
    
    //Animate the projectiles
    protected void animate(GreenfootImage[] images, int currentFrame)
    {
        setImage(images[currentFrame]);
    }

    //Inflict damage on the monster when the the projectile hits the monster
    protected void monsterAttack(int speed)
    {
        d= (Defender)getOneObjectAtOffset(getImage().getWidth()/2, 0, Defender.class);

        if (d == null)
        {
            move(speed);
        }

        else
        {
            d.takeDamage(damage);
            inflicted = true;
            getWorld().removeObject(this);
        }

    }
}
