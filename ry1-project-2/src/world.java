import bagel.Font;
import bagel.Image;
import bagel.util.Point;

import java.util.List;

/**
 * used to create a world of the game
 */
public class world {

    private static final Image GATHERER = new Image("res/images/gatherer.png");
    private static final Image THIEF = new Image("res/images/thief.png");

    public static final String IMAGE_FORMAT = "res/images/%s";
    private static final String PAD_INDICATOR = "Pad";
    private static final String STOCKPILE_INDICATOR = "Stockpile";
    private static final String FENCE_INDICATOR = "Fence";
    private static final String TREE_INDICATOR = "Tree";
    private static final String GTREE_INDICATOR = "GoldenTree";
    private static final String POOL_INDICATOR = "Pool";
    private static final String HOARD_INDICATOR = "Hoard";
    private static final String GATHERER_INDICATOR = "Gatherer";
    private static final String THIEF_INDICATOR = "Thief";
    private static final String SIGN_INDICATOR = "Sign";

    private final Image background;
    private final List<gameObject> objects;
    private final List<gameObject> actors;
    //private int actorsNumber;

    private final Font gameFont = new Font("res/VeraMono.ttf", 24);

    /**
     * constructor of the world class
     *
     * @param name address of the file which is to be read
     */
    public world(String name) {
        background = new Image(String.format(IMAGE_FORMAT, "background.png"));
        actors = csvReader.getActors(name);
        objects = csvReader.getObjects(name);
        //actorsNumber = actors.size();
    }

    /**
     * used to draw the world from the attributes of the world class
     */
    public void generateWorld() {
        background.drawFromTopLeft(0, 0);
        for (gameObject object : objects) {
            object.drawObject();
            if (object.hasFruit()) {

                gameFont.drawString(String.valueOf(object.getFruitNumber()), object.getLocation().x, object.getLocation().y);
            }
        }

        for (gameObject actor : actors) {
            actor.drawObject();
        }

    }

    /**
     * used every tick that the world passes
     */
    public void onTick() {

        for (int j = 0; j < actors.size(); j++) {
            if (actors.get(j).getName().equals("Gatherer") || actors.get(j).getName().equals("Thief")) {
                actors.get(j).onTick();
                Point temp = actors.get(j).getLocation();
                innerLoop:
                for (gameObject object : objects) {
                    if (object.getLocation().equals(temp)) {
                        switch (object.getName()) {
                            case TREE_INDICATOR:
                                actors.get(j).treeAction((fruitStorer) object);
                                break;
                            case HOARD_INDICATOR:
                                actors.get(j).hoardAction((fruitStorer) object);
                                break;
                            case STOCKPILE_INDICATOR:
                                actors.get(j).stockpileAction((fruitStorer) object);
                                break;
                            case SIGN_INDICATOR:
                                actors.get(j).signAction((Sign) object);
                                break;
                            case FENCE_INDICATOR:
                                actors.get(j).fenceAction();
                                break;
                            case PAD_INDICATOR:
                                actors.get(j).padAction();
                                break;
                            case POOL_INDICATOR:
                                if (actors.get(j).getName().equals("Gatherer")) {
                                    gathererMitosis(actors, (gatherer) actors.get(j));
                                    j = j + 1;

                                } else {
                                    thiefMitosis(actors, (thief) actors.get(j));
                                    j++;
                                }
                                break innerLoop;
                            case GTREE_INDICATOR:
                                actors.get(j).gTreeAction();
                                break;

                        }

                    }
                }

            }

        }
        for (int j = 0; j < actors.size(); j++) {
            if (actors.get(j).getName().equals("Thief")) {
                for (gameObject actor : actors) {
                    if (actors.get(j).getLocation().equals(actor.getLocation()) && actor.getName().equals(GATHERER_INDICATOR)) {
                        actors.get(j).rotate270();
                    }
                }
            }
        }
    }

    /**
     * used to check if all gatherers and thieves are deactivated in the world class
     *
     * @return boolean whether there are still gatherers or thieves active
     */
    public Boolean checkActive() {
        int i = 0;
        for (gameObject actor : actors) {
            if (actor.getName().equals("Gatherer") || actor.getName().equals("Thief")) {
                if (actor.isActive()) {
                    i = 1;
                }
            }
        }
        return i != 0;
    }

    /**
     * used to print the amount of fruit each stockpile and hoard as after the end of simulation
     */
    public void printFruits() {
        for (gameObject actor : objects) {
            if (actor.getName().equals("Stockpile") || actor.getName().equals("Hoard")) {
                System.out.println(actor.getFruitNumber());
            }
        }
    }

    /**
     * used when a gatherer encounters a mitosis pool
     *
     * @param actors the list of moving actors (gatherers, thieves)
     * @param curr   the gatherer that encounters a mitosis pool
     */
    public void gathererMitosis(List<gameObject> actors, gatherer curr) {

        gatherer temp1 = new gatherer(GATHERER, curr.getLocation().x, curr.getLocation().y, GATHERER_INDICATOR);
        temp1.setDirection(curr.getDirection());
        gatherer temp2 = new gatherer(GATHERER, curr.getLocation().x, curr.getLocation().y, GATHERER_INDICATOR);
        temp2.setDirection(curr.getDirection());
        actors.remove(curr);
        temp1.rotate90();
        temp2.rotate270();
        temp1.move();
        temp2.move();
        actors.add(0, temp1);
        actors.add(0, temp2);

    }

    /**
     * used when a thief encounters a mitosis pool
     *
     * @param actors the list of moving actors (gatherers, thieves)
     * @param curr   the thief that encounters a mitosis pool
     */
    public void thiefMitosis(List<gameObject> actors, thief curr) {

        thief temp1 = new thief(THIEF, curr.getLocation().x, curr.getLocation().y, THIEF_INDICATOR);
        temp1.setDirection(curr.getDirection());
        thief temp2 = new thief(THIEF, curr.getLocation().x, curr.getLocation().y, THIEF_INDICATOR);
        temp2.setDirection(curr.getDirection());
        actors.remove(curr);
        temp1.rotate90();
        temp2.rotate270();
        temp1.move();
        temp2.move();
        actors.add(0, temp1);
        actors.add(0, temp2);

    }


}
