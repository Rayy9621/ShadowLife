

/**
 * the interface for all moving gameObjects
 */
public interface moving {

    /**
     * used to set certain gameObjects to a deactivated status
     */
    void deActivated();

    /**
     * used when the certain gameObjects encounter a sign
     *
     * @param sign a sign that has the direction on it
     */
    void signAction(Sign sign);

    /**
     * used when the certain gameObjects reaches a fence
     */
    void fenceAction();

    /**
     * rotates the direction of certain gameObjects 180 degrees clockwise, used when certain conditions are met
     */
    void rotate180();

    /**
     * used when certain gameObjects encounter a tree
     *
     * @param tree the tree that the gameObject encounters
     */
    void treeAction(fruitStorer tree);

    /**
     * used when certain gameObjects encounter a hoard
     *
     * @param hoard the hoard that the gameObject encounters
     */
    void hoardAction(fruitStorer hoard);

    /**
     * used when certain gameObjects encounter a stockpile
     *
     * @param stockpile the stockpile that the gameObject encounters
     */
    void stockpileAction(fruitStorer stockpile);

    /**
     * used to check if the gameObject is active
     *
     * @return false by  default
     */
    Boolean isActive();

    /**
     * used when the certain gameObjects reach a pad
     */
    void padAction();

    /**
     * used when the certain gameObject reaches a gatherer
     */
    void gathererAction();

    /**
     * used when certain gameObject reaches a golden tree
     */
    void gTreeAction();

    /**
     * rotates the direction of certain gameObjects 270 degrees clockwise, used when certain conditions are met
     */
    void rotate270();

    /**
     * rotates the direction of certain gameObjects 90 degrees clockwise, used when certain conditions are met
     */

    void rotate90();


}
