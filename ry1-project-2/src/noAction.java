import bagel.Image;

/**
 * used when an object that has no actions(pad, pool, fence) is read in the input csv
 */
public class noAction extends gameObject {

    /**
     * constructor of the noAction class
     *
     * @param actor image of the object
     * @param x     x coordinate
     * @param y     y coordinate
     * @param name  name of the object
     */
    public noAction(Image actor, double x, double y, String name) {
        super(actor, x, y, name);
    }

    /**
     * used to differentiate noAction class from fruitStorer class
     *
     * @return false as noAction class does not have fruit
     */
    @Override
    public boolean hasFruit() {

        return false;
    }


}
