import bagel.Image;
import bagel.util.Point;

/**
 * this is the thief class which inherits from the gameObject class and extends the moving interface
 */
public class thief extends gameObject implements moving{

    private final Image actor = new Image("res/images/thief.png");
    private String direction;
    private Boolean carrying;
    private Boolean active;
    private Boolean consuming;


    private static final String LEFT_DIRECTION = "LEFT";
    private static final String RIGHT_DIRECTION = "RIGHT";
    private static final String UP_DIRECTION = "UP";
    private static final String DOWN_DIRECTION = "DOWN";
    private static final double MOVE_SIZE = 64;

    /** constructor for the thief class, used when the csv reader reads a thief
     * @param actor the image of the class that will appear on the screen
     * @param x x coordinate of the thief
     * @param y y coordinate of the thief
     * @param name the name of the object
     */
    public thief(Image actor, double x, double y, String name) {
        super(actor, x, y, name);
        this.carrying = false;
        this.active=true;
        direction = UP_DIRECTION;
        consuming = false;
    }


    /**
     * used to set the thief to a deactivated status
     */
    @Override
    public void deActivated() {
        active = false;
    }


    /**
     * used to move the thief 1 tile in it's direction
     */

    public void move() {
        switch (this.getDirection()) {
            case LEFT_DIRECTION:
                setLocationX(new Point(getLocation().x - MOVE_SIZE, getLocation().y));
                break;
            case RIGHT_DIRECTION:
                setLocationX(new Point(getLocation().x + MOVE_SIZE, getLocation().y));
                break;
            case UP_DIRECTION:
                setLocationY(new Point(getLocation().x, getLocation().y - MOVE_SIZE));
                break;
            case DOWN_DIRECTION:
                setLocationY(new Point(getLocation().x, getLocation().y + MOVE_SIZE));
                break;
        }

    }

    /**
     * rotates the thief's direction 90 degrees clockwise when certain conditions are met
     */
    public void rotate90(){
        switch (this.getDirection()) {
            case LEFT_DIRECTION:
                setDirection(UP_DIRECTION);
                break;
            case RIGHT_DIRECTION:
                setDirection(DOWN_DIRECTION);
                break;
            case UP_DIRECTION:
                setDirection(RIGHT_DIRECTION);
                break;
            case DOWN_DIRECTION:
                setDirection(LEFT_DIRECTION);
                break;
        }

    }


    /**
     * rotates the thief's direction 90 degrees anti-clockwise, used when certain conditions are met
     */
    public void rotate270(){
        switch (this.getDirection()) {
            case LEFT_DIRECTION:
                setDirection(DOWN_DIRECTION);
                break;
            case RIGHT_DIRECTION:
                setDirection(UP_DIRECTION);
                break;
            case UP_DIRECTION:
                setDirection(LEFT_DIRECTION);
                break;
            case DOWN_DIRECTION:
                setDirection(RIGHT_DIRECTION);
                break;
        }

    }
    /**
     * rotates the thief's direction 180 degrees clockwise, used when certain conditions are met
     */
    @Override
    public void rotate180(){
        switch (this.getDirection()) {
            case LEFT_DIRECTION:
                setDirection(RIGHT_DIRECTION);
                break;
            case RIGHT_DIRECTION:
                setDirection(LEFT_DIRECTION);
                break;
            case UP_DIRECTION:
                setDirection(DOWN_DIRECTION);
                break;
            case DOWN_DIRECTION:
                setDirection(UP_DIRECTION);
                break;
        }
    }

    /** used when a thief encounters a tree
     * @param tree the tree that the thief encounters
     */
    @Override
    public void treeAction(fruitStorer tree) {
        if (!this.carrying && tree.getFruitNumber()>0){
            carrying = true;
            tree.reduceFruit();
        }
    }
    /** used when a thief encounters a golden tree
     *
     */
    @Override
    public void gTreeAction(){
        if(!this.carrying){
            this.carrying = true;
        }
    }
    /** used when a thief encounters a hoard
     * @param hoard the hoard that the thief encounters
     */
    @Override
    public void hoardAction(fruitStorer hoard) {
        if(consuming){
            consuming = false;
            if(!carrying){
                if(hoard.getFruitNumber() >0) {
                    hoard.reduceFruit();
                    carrying = true;
                }
                else {
                    rotate90();
                }
            }
        }
        else if(carrying){
            carrying = false;
            hoard.addFruit();
            rotate90();
        }

    }
    /** used when a thief encounters a stockpile
     * @param stockpile the stockpile that the thief encounters
     */
    @Override
    public void stockpileAction(fruitStorer stockpile){
        if(!this.carrying){
            if(stockpile.getFruitNumber()>0){
                carrying = true;
                consuming = false;
                stockpile.reduceFruit();
                rotate90();
            }
        }
        else{
            rotate90();
        }
    }

    /** used when the thief encounters a sign
     * @param sign a sign that has the diretion on it
     */
    @Override
    public void signAction(Sign sign){
        setDirection(sign.getDirection());
    }

    /**
     * used when the thief reaches a fence
     */
    @Override
    public void fenceAction(){
        switch (this.getDirection()) {
            case LEFT_DIRECTION:
                setLocationX(new Point(getLocation().x + MOVE_SIZE, getLocation().y));
                break;
            case RIGHT_DIRECTION:
                setLocationX(new Point(getLocation().x - MOVE_SIZE, getLocation().y));
                break;
            case UP_DIRECTION:
                setLocationY(new Point(getLocation().x, getLocation().y + MOVE_SIZE));
                break;
            case DOWN_DIRECTION:
                setLocationY(new Point(getLocation().x, getLocation().y - MOVE_SIZE));
                break;
        }
        deActivated();
    }

    /**
     * used when the thief reaches a pad
     */
    @Override
    public void padAction(){
        this.consuming = true;
    }
    /**
     * used when the thief reaches a gatherer
     */
    @Override
    public void gathererAction(){
        rotate270();
    }
    /**
     * used every tick in the game
     */
    @Override
    public void onTick() {
        if (isActive()) {
            move();
        }
    }
    /**
     * used to check if the thief is active
     */
    @Override
    public Boolean isActive(){
        return this.active;
    }

    /**
     * used to get the direction of the thief
     *
     * @return direction a string that contains the thief's direction
     */
    public String getDirection() {
        return this.direction;
    }

    /** used to set the direction of thief
     * @param direction a direction we wish to set the thief's direction to
     */
    public void setDirection(String direction) {
        this.direction = direction;
    }
}
