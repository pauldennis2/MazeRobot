package sample;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SmartBot extends Robot {

    List<String> log;

    public SmartBot (int xLoc, int yLoc) {
        super(xLoc, yLoc);
        log = new ArrayList<>();
        System.out.println("SmartBot created at (" + xLoc + ", " + yLoc + ")");
    }

    public void makeMove () {
        Random random = new Random();
        int direction = random.nextInt(4);
        String dirString = "";
        boolean success = false;
        switch (direction) {
            case 0:
                success = moveNorth();
                dirString = "North";
                break;
            case 1:
                success = moveEast();
                dirString = "East";
                break;
            case 2:
                success = moveSouth();
                dirString = "South";
                break;
            case 3:
                success = moveWest();
                dirString = "West";
                break;
        }
        if (success) {
            log.add("Successfully moved " + dirString + " to (" + getxLoc() + ", " + getyLoc() + ")");
        } else {
            log.add("Tried to move " + dirString + " but couldn't.");
        }
    }

    public static void main(String[] args) {
        SmartBot smartBot = new SmartBot(0, 0);
        for (int i = 0; i < 10; i++) {
            smartBot.makeMove();
        }

        smartBot.log.forEach(System.out::println);
    }
}
