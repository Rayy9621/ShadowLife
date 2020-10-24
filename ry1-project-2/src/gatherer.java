import bagel.Image;
import bagel.util.Point;


/**
 * this class is used when the csv reader reads an gatherer actor
 */
public class gatherer extends gameObject implements moving {
    private final Image actor = new Image("res/images/gatherer.png");
    private String direction;
    private Boolean carrying;
    private Boolean active;

    private static final String LEFT_DIRECTION = "LEFT";
    private static final String RIGHT_DIRECTION = "RIGHT";
    private static final String UP_DIRECTION = "UP";
    private static final String DOWN_DIRECTION = "DOWN";
    private static final double MOVE_SIZE = 64;

    /**
     * constructor for the gatherer class, used when the csv reader reads a gatherer
     *
     * @param actor the image of the class that will appear on the screen
     * @param x     x coordinate of the gatherer
     * @param y     y coordinate of the gatherer
     * @param name  the name of the object
     */
    public gatherer(Image actor, double x, double y, String name) {
        super(actor, x, y, name);
        this.carrying = false;
        this.active = true;
        direction = LEFT_DIRECTION;
    }


    /**
     * used when a gatherer encounters a tree
     *
     * @param tree the tree that the gatherer encounters
     */
    @Override
    public void treeAction(fruitStorer tree) {
        if (!this.carrying && tree.getFruitNumber() > 0) {
            this.carrying = true;
            tree.reduceFruit();
            rotate180();
        }
    }

    /**
     * used when a gatherer encounters a golden tree
     */
    @Override
    public void gTreeAction() {
        if (!this.carrying) {
            this.carrying = true;
            rotate180();
        }
    }

    /**
     * used when a gatherer encounters a hoard
     *
     * @param hoard the hoard that the gatherer encounters
     */
    @Override
    public void hoardAction(fruitStorer hoard) {
        if (this.carrying) {
            hoard.addFruit();
            this.carrying = false;
        }
        rotate180();
    }

    /**
     * used when a gatherer encounters a stockpile
     *
     * @param stockpile the stockpile that the gatherer encounters
     */
    @Override
    public void stockpileAction(fruitStorer stockpile) {
        if (this.carrying) {
            stockpile.addFruit();
            this.carrying = false;
        }
        rotate180();
    }

    /**
     * rotates the gatherer's direction 180 degrees clockwise, used when certain conditions are met
     */
    @Override
    public void rotate180() {
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

    /**
     * rotates the gatherer's direction 90 degrees clockwise, used when certain conditions are met
     */
    public void rotate90() {
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
     * rotates the gatherer's direction 90 degrees anti-clockwise, used when certain conditions are met
     */
    public void rotate270() {
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
     * used to set the gatherer to a deactivated status
     */
    @Override
    public void deActivated() {
        this.active = false;

    }


    /**
     * used to move the gatherer 1 tile in it's direction
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
     * used when the gatherer encounters a sign
     *
     * @param sign a sign that has the diretion on it
     */
    @Override
    public void signAction(Sign sign) {
        setDirection(sign.getDirection());
    }

    /**
     * used when the gatherer reaches a fence
     */
    @Override
    public void fenceAction() {
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
     * used every tick in the game
     */
    @Override
    public void onTick() {
        if (isActive()) {
            move();
        }
    }

    /**
     * used to check if the gatherer is active
     */
    @Override
    public Boolean isActive() {
        return this.active;
    }

    /**
     * used to get the direction of the gatherer
     *
     * @return direction a string that contains the gatherer's direction
     */
    public String getDirection() {
        return this.direction;
    }

    /**
     * used to set the direction of gatherer
     *
     * @param direction a direction we wish to set the gatherer's direction to
     */
    public void setDirection(String direction) {
        this.direction = direction;
    }
}
