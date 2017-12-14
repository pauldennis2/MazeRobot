package sample;

import java.util.Random;

public enum Gem {
    RED, GREEN, YELLOW;

    public static Gem getRandomGem () {
        Random random = new Random();
        int gem = random.nextInt(3);
        switch (gem) {
            case 0:
                return RED;
            case 1:
                return GREEN;
            case 2:
                return YELLOW;
        }
        return null;
    }
}
