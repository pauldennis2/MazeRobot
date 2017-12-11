package sample;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Maze {

    MazeCell start;
    MazeCell end;
    MazeCell[][] cells;
    Random random;

    SmartBot robot;

    MazeCell armLoc;
    List<MazeCell> importantLocations;

    int reds = 0;
    int greens = 0;
    int yellows = 0;

    public Maze () {
        cells = new MazeCell[10][10];
        importantLocations = new ArrayList<>();
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                cells[x][y] = new MazeCell(x, y);
            }
        }

        random = new Random();
        start = cells[random.nextInt(10)][random.nextInt(10)];

        robot = new SmartBot(start.xLoc, start.yLoc);
        end = cells[random.nextInt(10)][random.nextInt(10)];

        armLoc = cells[random.nextInt(10)][random.nextInt(10)];

        for (int i =0; i < 5; i++) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);
            cells[x][y].gem = Gem.getRandomGem();
            importantLocations.add(cells[x][y]);
            switch (cells[x][y].gem) {
                case RED:
                    reds++;
                    break;
                case GREEN:
                    greens++;
                    break;
                case YELLOW:
                    yellows++;
                    break;
            }
        }

        System.out.println("Created gems (RGY): " + reds + " " + greens + " " + yellows);

        System.out.println("Start = (" + start.xLoc + ", " + start.yLoc + ")");
        System.out.println("End = (" + end.xLoc + ", " + end.yLoc + ")");
        importantLocations.add(start);
        importantLocations.add(end);
        importantLocations.add(armLoc);
        createRandomConnections();
        //removeSomeConnections();
    }

    private void createRandomConnections () {
        while (!MazeCell.areConnected(importantLocations)) {
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

    private void removeSomeConnections () {
        for (int i = 0; i < 200; i++) {
            //Pick a random square;
            int x = random.nextInt(10);
            int y = random.nextInt(10);
            MazeCell first = cells[x][y];
            Set<MazeCell> connected = first.getConnectedCells();
            //If the square is connected pick a random connection
            if (connected != null && connected.size() > 0) {
                //If removing the DIRECT connection between the cells leaves them still connected
                //Remove the direct connection
                int r = random.nextInt(connected.size());
                int j = 0;
                MazeCell randomCell;
                for (MazeCell cell : connected) {
                    //R + L = J! GOT REFERENCES ON BLAST
                    if (j == r) {
                        randomCell = cell;
                    }
                    j++;
                }
                //Remove connection
            }
        }
    }

    public static boolean isInBounds (int x, int y) {
        return (x >= 0 && x <= 9) && (y >= 0 && y <=9);
    }
}
