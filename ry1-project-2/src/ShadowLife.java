import bagel.*;

import java.util.*;


/**
 * the class that contains the game ShadowLife and all of its parts
 */
public class ShadowLife extends AbstractGame {




    private int count = 0;
    private List<gameObject> gameActors;
    private final world gameWorld;
    private final int maxTicks;
    private final int tickRate;

    /**
     * constructor of the ShadowLife class
     *
     * @param tickRate  the rate which a tick is in milliseconds
     * @param maxTicks  the max number of ticks before the game times out
     * @param worldFile the csv file that will contain the actors of the game
     */
    public ShadowLife(int tickRate, int maxTicks, String worldFile) {
        super(1024, 768, "ShadowLife");
        gameActors = csvReader.getObjects(worldFile);
        gameWorld = new world(worldFile);
        this.maxTicks = maxTicks;
        this.tickRate = tickRate;

    }

    /**
     * the main function of ShadowLife
     *
     * @param args command line input of the game
     */
    public static void main(String[] args) {
        if (Integer.parseInt(args[0]) < 0 || Integer.parseInt(args[1]) < 0) {
            System.out.println("usage: ShadowLife <tick rate> <max ticks> <world file>");
            System.exit(-1);
        } else if (args.length < 3) {
            System.out.println("usage: ShadowLife <tick rate> <max ticks> <world file>");
            System.exit(-1);
        }

        ShadowLife game = new ShadowLife(Integer.parseInt(args[0]), Integer.parseInt(args[1]), args[2]);
        game.run();
    }

    /**
     * used to update the state of the game every tick
     *
     * @param input input from the abstractGame class
     */
    @Override
    public void update(Input input) {
        if (!gameWorld.checkActive()) {
            System.out.println(count + " ticks");
            gameWorld.printFruits();
            System.exit(-1);
        }
        gameWorld.generateWorld();
        gameWorld.onTick();


        try {
            Thread.sleep(tickRate);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        count++;
        if (count > this.maxTicks) {
            System.out.println("Timed out");
            System.exit(-1);
        }


    }
}
