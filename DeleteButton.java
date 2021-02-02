import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class DeleteButton extends Actor
{
    private MouseInfo m;
    private GreenfootImage deleteImage = new GreenfootImage("delete.png");
    private boolean isDelete;
    private boolean mouseOver;
    private boolean clicked;
    
    public DeleteButton()
    {
        setImage(deleteImage);
        isDelete = false;
        mouseOver = false;
        clicked = false;
    }
    /**
     * Act - do whatever the DeleteButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        m = Greenfoot.getMouseInfo();
        if (m != null)
        {
            if (!mouseOver && Greenfoot.mouseMoved(this))
            {
                mouseOver = true;
            }
            
            if (mouseOver && Greenfoot.mouseMoved(null) && !Greenfoot.mouseMoved(this))
            {
                mouseOver = false;
            }
            // Clicked State
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

            if (clicked)
            {
                isDelete = true;
            } 

        }  
    }
    

}
