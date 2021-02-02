import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Counter extends Actor
{   
    
    private GreenfootImage counter;
    private Color backingTextColour;
    private Color borderOvalColour;
    private Font font;
    private String label;
    private int points;
    private int textXPosition;
    private int textYPosition;
    
    //Creates a default counter that is 200x30, displays the amount of points (starting from zero)
    //and has a black and pink colour scheme
    
    /**
     * Creates a default counter that is 200x30, displays the amount of points (starting from
     * zero), and has a black and pink colour scheme
     */
    public Counter()
    {
        counter = new GreenfootImage(200,30);
        label = "Points: ";
        points = 0;
        
        backingTextColour = Color.PINK;
        borderOvalColour = Color.BLACK;
        
        font = new Font("Arial", true, false, 15);
        counter.setFont(font);
        textXPosition = counter.getWidth()/3;
        textYPosition = counter.getHeight()/2+5;
        
        update(points);
        setImage(counter); 
        
    }
    
    /**
     * Creates a counter with a customized width and height, displays the amount of points 
     * (starting from zero), and has a black and pink colour scheme. 
     * 
     * @param width The width of the counter
     * @param height The height of the counter
     */
    public Counter (int width, int height)
    {
        counter = new GreenfootImage(width,height);
        label = "Points: ";
        points = 0;
        
        backingTextColour = Color.PINK;
        borderOvalColour = Color.BLACK;
        
        font = new Font("Arial", true, false, 15);
        counter.setFont(font);
        textXPosition = counter.getWidth()/3;
        textYPosition = counter.getHeight()/2+5;;
        
        update(points);
        
        
        setImage(counter); 
        
    }

    /**
     * Creates a customized counter with inputted width, height, font size, and label.
     * It displays a numerical value (starting from zero), and has a black and pink colour scheme.
     * 
     * 
     * @param width The width of the counter
     * @param height The height of the counter
     * @param fontSize The size of the font used to display the score
     * @param text The label used to describe the score (ie. "Lives: ")
     */
    public Counter (int width, int height, int fontSize, String text)
    {
        counter = new GreenfootImage(width,height);
        label = text;
        points = 0;
        
        backingTextColour = Color.PINK;
        borderOvalColour = Color.BLACK;
        
        font = new Font("Arial", true, false, fontSize);
        counter.setFont(font);
        textXPosition = counter.getWidth()/3;
        textYPosition = counter.getHeight()/2+5;
        
        update(points);
        setImage(counter); 
        
    }
    
    /**
     * Creates a customized counter with inputted label and starting value. It is 200x30 and
     * has a black and pink colour scheme
     * 
     * @param text The label or prefix that will be displayed by the counter
     * @param startValue The starting numerical value displayed by the counter
     */
    public Counter (String text, int startValue)
    {
        counter = new GreenfootImage(200,30);
        label = text;
        points = startValue;
        
        backingTextColour = Color.PINK;
        borderOvalColour = Color.BLACK;
        
        font = new Font("Arial", true, false, 15);
        counter.setFont(font);
        textXPosition = counter.getWidth()/3;
        textYPosition = counter.getHeight()/2+5;
        
        update(points);
        setImage(counter); 
        
    }
    
    /**
     * Creates a customized counter with inputted width, height, and colour scheme (based on
     * RGB values inputted)
     * 
     * @param width The width of the counter
     * @param height The height of the counter
     * @param r1 The R value of the backing and text colour (a number between 0 and 255)
     * @param g1 The G value of the backing and text colour (a number between 0 and 255)
     * @param b1 The B value of the backing and text colour (a number between 0 and 255)
     * @param r2 The R value of the border and oval colour (a number between 0 and 255)
     * @param g2 The G value of the border and oval colour (a number between 0 and 255)
     * @param b2 The B value of the border and oval colour (a number between 0 and 255)
     */
    public Counter (int width, int height, int r1, int g1, int b1, int r2, int g2, int b2)
    {
        counter = new GreenfootImage(width,height);
        label = "Points";
        points = 0;
        
        backingTextColour = new Color(r1,g1,b1);
        borderOvalColour = new Color(r2,g2,b2);
        
        font = new Font("Arial", true, false, 15);
        counter.setFont(font);
        
        update(points);
        setImage(counter); 
        
    }
    
    /**
     * Adds to the current value of points based on the given increment value (if it is a
     * positive number the amount will increase, if it is negative the amount will decrease)
     * 
     * @param increment The value at which the amount of points will change
     */
    public void addPoints(int increment)
    {
        points += increment;
        update(points);
    }
    
    /**
     * Sets the colour of the border, backing, oval and text to the given RGB values (requires
     * the input of two RGB codes)
     * 
     * @param r1 The R value of the border and oval colour (a number between 0 and 255)
     * @param g1 The G value of the border and oval colour (a number between 0 and 255)
     * @param b1 The B value of the border and oval colour (a number between 0 and 255)
     * @param r2 The R value of the backing and text colour (a number between 0 and 255)
     * @param g2 The G value of the backing and text colour (a number between 0 and 255)
     * @param b2 The B value of the backing and text colour (a number between 0 and 255)
     */
    public void setColour(int r1, int g1, int b1, int r2, int g2, int b2)
    {
        borderOvalColour = new Color(r1,g1,b1);
        backingTextColour = new Color(r2,g2,b2);
        update(borderOvalColour, backingTextColour);
        
    }
    
    /**
     * Sets the current value of the counter based on inputted value and can be used to set
     * the starting value of the amount of points displayed.
     * 
     * @param value The new value that the counter will display
     */
    public void setValue(int value)
    {
        points = value;
        update(points);
        
    }
  
    
    /**
     * Sets the label displayed by the counter (eg. "Score: " or "Lives: ") 
     * 
     * @param text The string that will be displayed by the counter
     */
    public void setLabel(String text)
    {
        label = text;
        update(points);
    }
    
    /**
     * Sets the font size, whether or not the text is bolded and italicized, and the x and y
     * coordinate of the text relative to the counter
     * 
     * @param fontSize The size of the font
     * @param isBold True if the text is bolded, false if it isn't
     * @param isItalicized True if the text is italicized, false if it isn't
     * @param x The x-coordinate of the text relative to the counter
     * @param y The y-coordinate of the text relative to the counter
     */
    public void setFontInfo(int fontSize, boolean isBolded, boolean isItalicized, int x, int y)
    {
        Font myFont = new Font("Arial", isBolded, isItalicized, fontSize);
        update(myFont, x, y);
    }
    
    /**
     * Returns the current amount of points displayed by the counter
     * 
     * @return int The value of the points variable
     */
    public int getPoints()
    {
        return points;
    }

    /** 
     * Updates the numerical value (score) displayed on the counter (draws it on)
     * 
     * @param score The value that is going to be outputted by the counter (current amount of 
     * points)
     */
    private void update(int score)
    {
        counter.setColor(borderOvalColour);
        counter.fill();
        counter.setColor(backingTextColour);
        counter.fillRect(3,3, counter.getWidth()-6, counter.getHeight()-6);
        counter.setColor(borderOvalColour);
        counter.fillOval(0,4, counter.getWidth(), counter.getHeight()-9);

        counter.setColor(backingTextColour);
        String scoreText = label + score;
        counter.drawString(scoreText, textXPosition, textYPosition);
    }
    
    /**
     * Updates the colour scheme of the counter
     * 
     * @param borderOvalCol The new colour of the border and oval
     * @param backingTextCol The new colour of the backingTextCol
     */
    private void update(Color borderOvalCol, Color backingTextCol)
    {

        borderOvalColour = borderOvalCol;
        backingTextColour = backingTextCol;
        
        counter.setColor(borderOvalColour);
        counter.fill();
        counter.setColor(backingTextColour);
        counter.fillRect(3,3, counter.getWidth()-6, counter.getHeight()-6);
        counter.setColor(borderOvalColour);
        counter.fillOval(0,4, counter.getWidth(), counter.getHeight()-9);
        
        counter.setColor(backingTextColour);
        String scoreText = label + points;
        counter.drawString(scoreText, textXPosition, textYPosition);
    
    }
    
    /**
     * Updates the font displayed by the counter
     * 
     * @param newFont The new font that will be used to display the information
     * @param xCoordinate The x-coordinate of the text relative to the counter
     * @param yCoordinate The y-coordinate of the text relative to the counter
     */
    private void update(Font newFont, int xCoordinate, int yCoordinate)
    {
        textXPosition = xCoordinate;
        textYPosition = yCoordinate;
        
        counter.setColor(borderOvalColour);
        counter.fill();
        counter.setColor(backingTextColour);
        counter.fillRect(3,3, counter.getWidth()-6, counter.getHeight()-6);
        counter.setColor(borderOvalColour);
        counter.fillOval(0,4, counter.getWidth(), counter.getHeight()-9);
        counter.setColor(backingTextColour);
        
        counter.setFont(newFont);
        String scoreText = label + points;
        counter.drawString(scoreText, textXPosition, textYPosition);
        
        
    }
    

}
