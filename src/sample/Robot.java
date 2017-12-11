package sample;

public class Robot {

    private int xLoc;
    private int yLoc;

    private int redGems;
    private int greenGems;
    private int yellowGems;

    private boolean hasArm;

    public Robot(int xLoc, int yLoc) {
        this.xLoc = xLoc;
        this.yLoc = yLoc;
    }

    public void pickUp (MazeCell cell) {
        //TODO: change so can pickup arm without arm
        if (hasArm) {
            if (cell.gem != null) {
                switch (cell.gem) {
                    case RED:
                        redGems++;
                        break;
                    case GREEN:
                        greenGems++;
                        break;
                    case YELLOW:
                        yellowGems++;
                        break;
                }
                cell.gem = null;
                printInventory();
            } else {
                System.out.println("Nothing here to pick up.");
            }
        } else {
            System.out.println("Cannot pick up gems. Have no arms.");
        }
    }

    public void printInventory () {
        if (hasArm) {
            System.out.println("I have an arm and I have (RGY) gems: " + redGems + " " + greenGems + " " + yellowGems);
        } else {
            System.out.println("I have no arm, so I cannot have an inventory");
        }
    }

    public boolean moveNorth () {
        if (yLoc > 0) {
            yLoc--;
            return true;
        }
        return false;
    }

    public boolean moveEast () {
        if (xLoc < 9) {
            xLoc++;
            return true;
        }
        return false;
    }

    public boolean moveSouth () {
        if (yLoc < 9) {
            yLoc++;
            return true;
        }
        return false;
    }

    public boolean moveWest () {
        if (xLoc > 0) {
            xLoc--;
            return true;
        }
        return false;
    }

    public int getxLoc() {
        return xLoc;
    }

    public int getyLoc() {
        return yLoc;
    }
}
