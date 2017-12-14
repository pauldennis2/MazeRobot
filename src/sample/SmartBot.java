package sample;

import java.util.*;

public class SmartBot extends Robot {

    //List<String> log;
    List<Direction> log;

    Set<MazeCell> visitedCells;

    ArrayList<MazeCell> importantLocations;

    ArrayList<MazeCell> backTrackCells;

    public SmartBot (int xLoc, int yLoc, MazeCell currentLocation) {
        super(xLoc, yLoc, currentLocation);
        log = new ArrayList<>();
        visitedCells = new HashSet<>();
        importantLocations = new ArrayList<>();
        System.out.println("SmartBot created at (" + xLoc + ", " + yLoc + ")");
        visitedCells.add(currentLocation);
    }

    public void updateImportantLocations () {
        if (!importantLocations.contains(currentLocation)) {
            if (currentLocation.isExit || currentLocation.gem != null) {
                importantLocations.add(currentLocation);
            }
        }
    }

    public void makeMove () {
        if (this.hasArm()) {
            if (importantLocations.size() > 0) {
                MazeCell loc = importantLocations.get(importantLocations.size() - 1);
                if (loc.getMovesSinceVisit() > 0) {
                    backTrack();
                }
                if (loc.getMovesSinceVisit() == 0) {
                    importantLocations.remove(currentLocation);
                }
            }

        } else {
            makeRandomMove();
        }
    }

    public void makeRandomMove () {
        updateImportantLocations();
        Random random = new Random();

        Set<MazeCell> connectedCells = currentLocation.getConnectedCells();
        Set<MazeCell> newCells = new HashSet<>();

        for (MazeCell cell : connectedCells) {
            if (!visitedCells.contains(cell)) {
                newCells.add(cell);
            }
        }
        Direction direction;
        if (newCells.size() == 0) {
            int i = 0;
            int r = random.nextInt(connectedCells.size());
            //Pick a random cell from connectedCells
            MazeCell chosen = null;
            for (MazeCell cell : connectedCells) {
                if (i == r) {
                    chosen = cell;
                }
                i++;
            }
            if (chosen.equals(this.currentLocation.nConnection)) {
                direction = Direction.NORTH;
            } else if (chosen.equals(this.currentLocation.eConnection)) {
                direction = Direction.EAST;
            } else if (chosen.equals(this.currentLocation.sConnection)) {
                direction = Direction.SOUTH;
            } else {
                direction = Direction.WEST;
            }
        } else {
            int i = 0;
            int r = random.nextInt(newCells.size());
            //Pick a random cell from newCells
            MazeCell chosen = null;
            for (MazeCell cell : newCells) {
                if (i == r) {
                    chosen = cell;
                }
                i++;
            }
            direction = null;
            if (chosen.equals(this.currentLocation.nConnection)) {
                direction = Direction.NORTH;
            } else if (chosen.equals(this.currentLocation.eConnection)) {
                direction = Direction.EAST;
            } else if (chosen.equals(this.currentLocation.sConnection)) {
                direction = Direction.SOUTH;
            } else {
                direction = Direction.WEST;
            }
        }

        String dirString = "";
        boolean success = false;

        go(direction);

        if (success) {
            log.add(direction);
            for (MazeCell loc : importantLocations) {
                loc.increaseMovesSinceVisit();
            }
            visitedCells.add(currentLocation);
        } else {
            System.out.println("Tried to move " + dirString + " but couldn't.");
        }

    }

    public void go (Direction direction) {
        switch (direction) {
            case NORTH:
                moveNorth();
                break;
            case EAST:
                moveEast();
                break;
            case SOUTH:
                moveSouth();
                break;
            case WEST:
                moveWest();
                break;
        }
    }

    public void backTrack () {
        Direction last = log.get(log.size() - 1);
        go (last.getOpposite());
        log.remove(log.size() - 1);
        for (MazeCell loc : importantLocations) {
            loc.decreaseMovesSinceVisit();
        }
    }
}
