import bagel.Image;

/**
 * used when a golden tree is read in the input csv file
 */
public class goldenTree extends gameObject {
    private final Image actor = new Image("res/images/gold-tree.png");

    /**
     * constructor for the goldenTree class
     *
     * @param actor the image of the golden tree
     * @param x     x coordinate
     * @param y     y coordinate
     * @param name  the name of the golden tree
     */
    public goldenTree(Image actor, double x, double y, String name) {
        super(actor, x, y, name);
    }

}
