package sample;

public class Robot {

    private int xLoc;
    private int yLoc;

    //This is our "inventory"
    private int redGems;
    private int greenGems;
    private int yellowGems;

    private boolean hasArm;

    MazeCell currentLocation;

    public Robot(int xLoc, int yLoc, MazeCell currentLocation) {
        this.xLoc = xLoc;
        this.yLoc = yLoc;
        this.currentLocation = currentLocation;
    }

    public void pickUp () {
        if (hasArm) {
            if (currentLocation.gem != null) {
                switch (currentLocation.gem) {
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
                currentLocation.gem = null;
                printInventory();
            } else {
                System.out.println("Nothing here to pick up.");
            }
        } else if (currentLocation.hasArm) {
            System.out.println("Picked up the arm!");
            hasArm = true;
            currentLocation.hasArm = false;
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
        if(currentLocation.nConnection != null && yLoc > 0) {
            yLoc--;
            currentLocation = currentLocation.nConnection;
            return true;
        }
        return false;
    }


    public boolean moveEast () {
        if (currentLocation.eConnection != null && xLoc < 9) {
            xLoc++;
            currentLocation = currentLocation.eConnection;
            return true;
        }
        return false;
    }

    public boolean moveSouth () {
        if (currentLocation.sConnection != null && yLoc < 9) {
            yLoc++;
            currentLocation = currentLocation.sConnection;
            return true;
        }
        return false;
    }

    public boolean moveWest () {
        if (currentLocation.wConnection != null && xLoc > 0) {
            xLoc--;
            currentLocation = currentLocation.wConnection;
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

    public int getRedGems() {
        return redGems;
    }

    public int getGreenGems() {
        return greenGems;
    }

    public int getYellowGems() {
        return yellowGems;
    }

    public boolean hasArm() {
        return hasArm;
    }
}
