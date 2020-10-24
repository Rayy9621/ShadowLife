import bagel.util.Point;
import bagel.Image;


/**
 * this is the parent class that contains all the objects in the ShadowLife game
 */
public class gameObject implements moving {
    private final Image actor;
    private Point location;
    private final String name;


    /**
     * constructor of the gameObject class
     *
     * @param actor image of the actor
     * @param x     x coordinate
     * @param y     y coordinate
     * @param name  name of the object
     */
    public gameObject(Image actor, double x, double y, String name) {
        this.actor = actor;
        this.name = name;
        this.location = new Point(x, y);

    }


    /**
     * used to display the gameObject in the game
     */
    public void drawObject() {
        actor.drawFromTopLeft(location.x, location.y);
    }

    /**
     * used to check if the object has any fruits
     *
     * @return false by default
     */
    public boolean hasFruit() {
        return false;
    }

    /**
     * used to check the number of fruits an object has
     *
     * @return 0 by default
     */
    public int getFruitNumber() {
        return 0;
    }

    /**
     * used to get the coordinates of the gameObject
     *
     * @return Point that has the object's x and y coordinate
     */
    public Point getLocation() {
        return location;
    }

    /**
     * used to set the location of the gameObject
     *
     * @param point the point we wish to set the location of the gameObject
     */
    public void setLocationX(Point point) {
        this.location = point;
    }

    /**
     * used to set the location of the gameObject
     *
     * @param point the point we wish to set the location of the gameObject
     */
    public void setLocationY(Point point) {
        this.location = point;
    }

    /**
     * used to get the name of the gameObject
     *
     * @return String name of the gameObject
     */
    public String getName() {
        return this.name;
    }

    /**
     * used for every tick as the action of the gameObject
     */
    public void onTick() {
    }

    /**
     * used to set certain gameObjects to a deactivated status
     */
    @Override
    public void deActivated() {

    }

    /**
     * used when the certain gameObjects encounter a sign
     *
     * @param sign a sign that has the direction on it
     */
    @Override
    public void signAction(Sign sign) {

    }

    /**
     * used when the certain gameObjects reaches a fence
     */
    @Override
    public void fenceAction() {

    }

    /**
     * rotates the direction of certain gameObjects 180 degrees clockwise, used when certain conditions are met
     */
    @Override
    public void rotate180() {

    }

    /**
     * used when certain gameObjects encounter a tree
     *
     * @param tree the tree that the gameObject encounters
     */
    @Override
    public void treeAction(fruitStorer tree) {

    }

    /**
     * used when certain gameObjects encounter a hoard
     *
     * @param hoard the hoard that the gameObject encounters
     */
    @Override
    public void hoardAction(fruitStorer hoard) {

    }

    /**
     * used when certain gameObjects encounter a stockpile
     *
     * @param stockpile the stockpile that the gameObject encounters
     */
    @Override
    public void stockpileAction(fruitStorer stockpile) {

    }

    /**
     * used to check if the gameObject is active
     *
     * @return false by  default
     */
    @Override
    public Boolean isActive() {
        return null;
    }

    /**
     * used when the certain gameObjects reach a pad
     */
    @Override
    public void padAction() {

    }

    /**
     * used when the certain gameObject reaches a gatherer
     */
    @Override
    public void gathererAction() {

    }

    /**
     * used when certain gameObject reaches a golden tree
     */
    @Override
    public void gTreeAction() {

    }

    /**
     * rotates the direction of certain gameObjects 270 degrees clockwise, used when certain conditions are met
     */
    @Override
    public void rotate270() {

    }

    /**
     * rotates the direction of certain gameObjects 90 degrees clockwise, used when certain conditions are met
     */

    @Override
    public void rotate90() {

    }


}
