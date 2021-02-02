import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Button extends Actor
{
    //help facilliate mouse info and button info
    private MouseInfo m;
    private boolean mouseOver;
    private boolean clicked;
    private int imageType;
    private boolean isDeleting;
    private boolean isDeleteButton;
    private GreenfootSound buttonClickSound;
    
    //Button images
    private GreenfootImage blacksmithImage= new GreenfootImage("blacksmithbutton.png");
    private GreenfootImage archerImage= new GreenfootImage("archerbutton.png");
    private GreenfootImage dragonImage= new GreenfootImage("dragonbutton.png");
    private GreenfootImage buttonImage2= new GreenfootImage("itemframe.png");
    private GreenfootImage newGameImage = new GreenfootImage("newgamebutton.png");
    private GreenfootImage newGameImage2 = new GreenfootImage("hoverednewgamebutton.png");
    private GreenfootImage meteorImage= new GreenfootImage("meteorbutton.png");
    private GreenfootImage sheepImage = new GreenfootImage("sheepbutton.png");
    private GreenfootImage deleteImage = new GreenfootImage("delete.png");
    private GreenfootImage deletePressed = new GreenfootImage("delete pressed.png");
    private GreenfootImage nextWave = new GreenfootImage("nextwavebutton.jpg");
    private GreenfootImage nextWave2 = new GreenfootImage("nextwavebuttonhovered.jpg");
    private GreenfootImage loadGame = new GreenfootImage("loadgamebutton.png");
    private GreenfootImage loadGame2 = new GreenfootImage("loadgamebuttonhovered.png");
    private GreenfootImage defenderManual = new GreenfootImage("defendermanualbutton.png");
    private GreenfootImage defenderManual2 = new GreenfootImage("defendermanualbuttonhovered.png");
    private GreenfootImage monsterManual = new GreenfootImage("monstermanualbutton.png");
    private GreenfootImage monsterManual2 = new GreenfootImage("monstermanualbuttonhovered.png");
    private GreenfootImage back = new GreenfootImage("backButton.png");
    private GreenfootImage back2 = new GreenfootImage("backButtonHovered.png");
    private GreenfootImage toMainScreen = new GreenfootImage("mainmenubutton.png");
    private GreenfootImage toMainScreen2 = new GreenfootImage("mainmenubuttonhovered.png");
    
    
     

    /**
     * Creates new instance of Button class
     * 
     * @param buttonTupe The numerical value assigned to a particular button type
     * @param isDelete Boolean of whether or not it is a delete button
     */
    public Button(int buttonType, boolean isDelete)
    {
        if (!isDelete)
        {
            imageType = buttonType;
            mouseOver = false;
            clicked = false;
            isDeleteButton = false;
        }
        else{
            isDeleting = false;
            isDeleteButton = isDelete;
        }
        buttonClickSound = new GreenfootSound("Button Click.mp3");
    }
    

    /**
     * Act - do whatever the Button wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(isDeleteButton == false)
        {
            if (!clicked)
            {   
                //Sets the image depending on whether or not clicked
                if (imageType == 1)
                {
                    setImage(blacksmithImage);
                }
                else if (imageType == 2)
                {
                    setImage(archerImage);
                }
                else if (imageType == 3)
                {
                    setImage(dragonImage);
                }
                else if(imageType == 5)
                {
                    setImage(meteorImage);
                }
                else if (imageType == 6)
                {
                    setImage(sheepImage);
                }
                else if (imageType == 8)
                {
                    setImage(nextWave);
                }
                else if (imageType==9)
                {
                    setImage(loadGame);
                }
                else if (imageType==10)
                {
                    setImage(defenderManual);
                }
                else if (imageType==11)
                {
                    setImage(monsterManual);
                }
                
                else if (imageType==12)
                {
                    setImage(back);
                }
                else if (imageType == 13)
                {
                    setImage(toMainScreen);
                }
                
            }
        }
        
        //Mouse tracking
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
            
            //Gets whether it was a right or left click, plays clicking sound
            if (Greenfoot.mouseClicked(this))
            {
                if (m.getButton() == 1)
                { 
                    clicked = true;                    
                } 
                else if (m.getButton () == 3)
                { 
                    clicked = true;
                }
                buttonClickSound.play();
            }
            
            
            if (clicked)
            {
                if(isDeleteButton == false)
                {
                    //Changes image to the "clicked" version of the button
                    if (imageType != 7)
                    {
                        setImage(buttonImage2);
                    }

                }
                else
                {   
                    //Currently deleting
                    isDeleting = true;
                }

            } 

        }
        
        //The following changes the image of the button depending on whether or not it is being hovered over
        if (imageType == 4)
        {
            if (mouseOver == false)
            {
   
                    setImage(newGameImage);
                

            }
            else
            {

                    setImage(newGameImage2);
                

            }
        }
        else if (imageType==8)
        {
            if (mouseOver == false)
            {
                setImage(nextWave);
            }
            else
            {
                setImage(nextWave2);
            }
        }
        
        else if (imageType==9)
        {
            if (mouseOver == false)
            {
                setImage(loadGame);
            }
            else
            {
                setImage(loadGame2);
            }
        }
        
        else if (imageType == 10)
        {
            if (mouseOver == false)
            {
                setImage(defenderManual);
            }
            else
            {
                setImage(defenderManual2);
            }
        }
        
        else if (imageType == 11)
        {
            if (mouseOver == false)
            {
                setImage(monsterManual);
            }
            else
            {
                setImage(monsterManual2);
            }
        }
        
        else if (imageType == 12)
        {
            if (mouseOver == false)
            {
                setImage(back);
            }
            else
            {
                setImage(back2);
            }
        }
        else if (imageType == 13)
        {
            if (mouseOver == false)
            {
                setImage(toMainScreen);
            }
            else
            {
                setImage(toMainScreen2);
            }
        }

        if(isDeleteButton == true)
        {
            if(isDeleting == true)
            {
                setImage(deletePressed);
            }
            else if(isDeleting == false)
            {
                setImage(deleteImage);
            }
        }
    }    
    
    /**
     * Returns whether or not the button has been clicked
     * 
     * @return boolean True if button has been clicked, false otherwise
     */
    public boolean getClicked(){
        return clicked;
    }
    
    /**
     * Sets the clicked variable to false
     */
    public void setClicked()
    {
        clicked = false;
    }
    
    /**
     * Sets the isDeleting variable to false
     */
    public void setIsDeleting()
    {
        isDeleting = false;
    }
    
    /**
     * Sets the image of the button based on a numerical value associated with an image
     * 
     * @param x Numerical value associated with a button image
     */
    public void setImage(int x)
    {
        if (x==1)
        {
            setImage(blacksmithImage);
        }
        else if (x==2)
        {
            setImage(archerImage);
        }
        else if (x==3)
        {
            setImage(dragonImage);
        }
        else if (x==4)
        {
            setImage(newGameImage);
        }

        else if (x==5)
        {
            setImage(meteorImage);
        }
        else if(x==6)
        {
            setImage(sheepImage);
        }
        else if(x==7)
        {
            setImage(deleteImage);
        }

        else if (x==8)
        {
            setImage(nextWave);
        }
        else if (x==9)
        {
            setImage(loadGame);
        }
        else if (x==10)
        {
            setImage(defenderManual);
        }
        else if (x==11)
        {
            setImage(monsterManual);
        }
        
        else if (x==12)
        {
            setImage(back);
        }

    }

}

