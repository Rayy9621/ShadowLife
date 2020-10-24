import bagel.Image;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.ArrayList;

/**
 * this is the class that is used to read the input file from a string which is the address where the file is stored
 */

public class csvReader {


    private static final String IMAGE_FORMAT = "res/images/%s";
    private static final String PAD_INDICATOR = "Pad";
    private static final String STOCKPILE_INDICATOR = "Stockpile";
    private static final String FENCE_INDICATOR = "Fence";
    private static final String TREE_INDICATOR = "Tree";
    private static final String GTREE_INDICATOR = "GoldenTree";
    private static final String POOL_INDICATOR = "Pool";
    private static final String HOARD_INDICATOR = "Hoard";
    private static final String UP_INDICATOR = "SignUp";
    private static final String LEFT_INDICATOR = "SignLeft";
    private static final String RIGHT_INDICATOR = "SignRight";
    private static final String DOWN_INDICATOR = "SignDown";
    private static final String GATHERER_INDICATOR = "Gatherer";
    private static final String THIEF_INDICATOR = "Thief";


    private static final String PAD_FILE = String.format(IMAGE_FORMAT, "pad.png");
    private static final String STOCKPILE_FILE = String.format(IMAGE_FORMAT, "cherries.png");
    private static final String FENCE_FILE = String.format(IMAGE_FORMAT, "fence.png");
    private static final String TREE_FILE = String.format(IMAGE_FORMAT, "tree.png");
    private static final String GTREE_FILE = String.format(IMAGE_FORMAT, "gold-tree.png");
    private static final String POOL_FILE = String.format(IMAGE_FORMAT, "pool.png");
    private static final String HOARD_FILE = String.format(IMAGE_FORMAT, "hoard.png");
    private static final String UP_FILE = String.format(IMAGE_FORMAT, "up.png");
    private static final String LEFT_FILE = String.format(IMAGE_FORMAT, "left.png");
    private static final String RIGHT_FILE = String.format(IMAGE_FORMAT, "right.png");
    private static final String DOWN_FILE = String.format(IMAGE_FORMAT, "down.png");
    private static final String GATHERER_FILE = String.format(IMAGE_FORMAT, "gatherer.png");
    private static final String THIEF_FILE = String.format(IMAGE_FORMAT, "thief.png");

    /**
     * this method returns the stationary objects in the csv file at the address in the input
     *
     * @param address this is the address which the csv file should be stored
     * @return List<gameObject> returns an arraylist of the gameObject class
     */
    //a method to get the information from csv file to a List of List of String;
    public static List<gameObject> getObjects(String address) {
        List<gameObject> actors = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(address))) {
            int count = 0;
            String text;
            String[] line;

            // Read through the input line by line
            while ((text = br.readLine()) != null) {
                line = text.split(",");
                if (Integer.parseInt(line[1]) > 1024 || Integer.parseInt(line[1]) < 0 ||
                        Integer.parseInt(line[2]) > 768 || Integer.parseInt(line[2]) < 0) {
                    System.out.println("error: in file \"<" + line[0] + ">\" at line <" + count + ">");
                    System.exit(-1);
                }
                switch (line[0]) {
                    case HOARD_INDICATOR:
                        actors.add(new fruitStorer(new Image(HOARD_FILE),
                                Double.parseDouble(line[1]), Double.parseDouble(line[2]), line[0]));
                        break;
                    case TREE_INDICATOR:
                        actors.add(new fruitStorer(new Image(TREE_FILE),
                                Double.parseDouble(line[1]), Double.parseDouble(line[2]), line[0]));
                        break;
                    case STOCKPILE_INDICATOR:
                        actors.add(new fruitStorer(new Image(STOCKPILE_FILE),
                                Double.parseDouble(line[1]), Double.parseDouble(line[2]), line[0]));
                        break;
                    case PAD_INDICATOR:
                        actors.add(new noAction(new Image(PAD_FILE),
                                Double.parseDouble(line[1]), Double.parseDouble(line[2]), line[0]));
                        break;
                    case FENCE_INDICATOR:
                        actors.add(new noAction(new Image(FENCE_FILE),
                                Double.parseDouble(line[1]), Double.parseDouble(line[2]), line[0]));
                        break;
                    case GTREE_INDICATOR:
                        actors.add(new noAction(new Image(GTREE_FILE),
                                Double.parseDouble(line[1]), Double.parseDouble(line[2]), line[0]));
                        break;
                    case POOL_INDICATOR:
                        actors.add(new noAction(new Image(POOL_FILE),
                                Double.parseDouble(line[1]), Double.parseDouble(line[2]), line[0]));
                        break;
                    case UP_INDICATOR:
                        actors.add(new Sign(new Image(UP_FILE),
                                Double.parseDouble(line[1]), Double.parseDouble(line[2]), "UP"));
                        break;
                    case DOWN_INDICATOR:
                        actors.add(new Sign(new Image(DOWN_FILE),
                                Double.parseDouble(line[1]), Double.parseDouble(line[2]), "DOWN"));
                        break;
                    case LEFT_INDICATOR:
                        actors.add(new Sign(new Image(LEFT_FILE),
                                Double.parseDouble(line[1]), Double.parseDouble(line[2]), "LEFT"));
                        break;
                    case RIGHT_INDICATOR:
                        actors.add(new Sign(new Image(RIGHT_FILE),
                                Double.parseDouble(line[1]), Double.parseDouble(line[2]), "RIGHT"));
                        break;
                    case GATHERER_INDICATOR: THIEF_INDICATOR:
                        break;
                    default:
                        System.out.println("error: in file \"<" + address + ">\" at line <" + count + ">");
                        System.exit(-1);

                }
                count++;

            }
        } catch (IOException e) {
            System.out.println("error: file \"<" + address + ">\" not found");
            System.exit(-1);
        }

        return actors;
    }

    /**
     * this method returns the moving actors in the csv file at the address in the input
     *
     * @param address this is the address which the csv file should be stored
     * @return List<gameObject> returns an arraylist of the gameObject class
     */
    public static List<gameObject> getActors(String address) {
        List<gameObject> actors = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(address))) {
            String text;
            String[] line;

            // Read through the input line by line
            while ((text = br.readLine()) != null) {
                line = text.split(",");
                switch (line[0]) {
                    case GATHERER_INDICATOR:
                        actors.add(new gatherer(new Image(GATHERER_FILE), Double.parseDouble(line[1]), Double.parseDouble(line[2]), GATHERER_INDICATOR));
                        break;
                    case THIEF_INDICATOR:
                        actors.add(new thief(new Image(THIEF_FILE), Double.parseDouble(line[1]), Double.parseDouble(line[2]), THIEF_INDICATOR));
                        break;
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return actors;
    }


}
