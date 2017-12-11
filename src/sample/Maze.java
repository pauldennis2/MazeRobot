package sample;

import java.util.Random;

public class Maze {

    MazeCell start;
    MazeCell end;
    MazeCell[][] cells;
    Random random;

    Robot robot;

    public Maze () {
        cells = new MazeCell[10][10];
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                cells[x][y] = new MazeCell(x, y);
            }
        }

        random = new Random();
        start = cells[random.nextInt(10)][random.nextInt(10)];

        robot = new Robot(start.xLoc, start.yLoc);
        end = cells[random.nextInt(10)][random.nextInt(10)];
        System.out.println("Start = (" + start.xLoc + ", " + start.yLoc + ")");
        System.out.println("End = (" + end.xLoc + ", " + end.yLoc + ")");
        createRandomConnections();
    }

    private void createRandomConnections () {
        while (!start.isConnectedTo(end)) {
            int randomX = random.nextInt(10);
            int randomY = random.nextInt(10);
            int direction = random.nextInt(4);
            switch (direction) {
                case 0: //North
                    if (isInBounds(randomX, randomY - 1)) {
                        cells[randomX][randomY].nConnection = cells[randomX][randomY - 1];
                        cells[randomX][randomY - 1].sConnection = cells[randomX][randomY];
                        System.out.println("Connected (" + randomX + ", " + randomY + ") north.");
                    }
                    break;
                case 1://East
                    if (isInBounds(randomX + 1, randomY)) {
                        cells[randomX][randomY].eConnection = cells[randomX + 1][randomY];
                        cells[randomX + 1][randomY].wConnection = cells[randomX][randomY];
                        System.out.println("Connected (" + randomX + ", " + randomY + ") east.");
                    }
                    break;
                case 2://South
                    if (isInBounds(randomX, randomY + 1)) {
                        cells[randomX][randomY].sConnection = cells[randomX][randomY + 1];
                        cells[randomX][randomY + 1].nConnection = cells[randomX][randomY];
                        System.out.println("Connected (" + randomX + ", " + randomY + ") south.");
                    }
                    break;
                case 3://West
                    if (isInBounds(randomX - 1, randomY)) {
                        cells[randomX][randomY].wConnection = cells[randomX - 1][randomY];
                        cells[randomX - 1][randomY].eConnection = cells[randomX][randomY];
                        System.out.println("Connected (" + randomX + ", " + randomY + ") west.");
                    }
                    break;
            }
        }
        System.out.println("Finished making connections!");
    }

    public static boolean isInBounds (int x, int y) {
        return (x >= 0 && x <= 9) && (y >= 0 && y <=9);
    }
}
