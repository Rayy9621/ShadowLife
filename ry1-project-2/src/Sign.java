import bagel.Image;

/**
 * used when a sign is read in the input csv file
 */
public class Sign extends noAction {
    private final String direction;

    /**
     * constructor of the sign class
     *
     * @param actor     image of the sign
     * @param x         x coordinate
     * @param y         y coordinate
     * @param direction the direction that the sign is point towards
     */
    public Sign(Image actor, double x, double y, String direction) {
        super(actor, x, y, "Sign");
        this.direction = direction;
    }

    /**
     * used to get the direction of the sign
     *
     * @return String direction that the sign is point towards
     */
    public String getDirection() {
        return this.direction;
    }


}
