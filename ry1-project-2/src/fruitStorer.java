import bagel.Image;

/**
 * the class used for tree, hoard, stockpile
 */
public class fruitStorer extends gameObject {
    private int fruitNumber;


    /**
     * constructor for the fruitStorer class
     *
     * @param actor image of the object
     * @param x     x coordinate
     * @param y     y coordinate
     * @param name  name of the object
     */
    public fruitStorer(Image actor, double x, double y, String name) {
        super(actor, x, y, name);

        if (name.equals("Tree")) {
            fruitNumber = 3;
        } else {
            fruitNumber = 0;
        }
    }


    /**
     * used to add 1 fruit for the object
     */
    public void addFruit() {
        this.fruitNumber++;
    }

    /**
     * used to reduce 1 fruit for the object
     */
    public void reduceFruit() {
        this.fruitNumber--;
    }

    /**
     * used to check whether the given gameObject is a fruitStorer
     *
     * @return boolean
     */
    @Override
    public boolean hasFruit() {
        String temp = this.getName();
        return temp.equals("Stockpile") || temp.equals("Hoard") || temp.equals("Tree");
    }


    /**
     * used to get the number of fruit of a fruitStorer object
     *
     * @return int number of fruit the object has
     */
    @Override
    public int getFruitNumber() {
        return fruitNumber;
    }
}
