import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class SkeletonArrow extends Projectile
{
    private GreenfootImage image= new GreenfootImage("skelly arrow.png");

    /**
     * Creates new instance of SkeletonArrow
     */
    public SkeletonArrow()
    {
        setImage(image);
        damage = 100;
    }
    
    public void act() 
    {
        monsterAttack(4);
    }    
}
