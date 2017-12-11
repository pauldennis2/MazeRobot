package sample;

public class Robot {

    private int xLoc;
    private int yLoc;

    public Robot(int xLoc, int yLoc) {
        this.xLoc = xLoc;
        this.yLoc = yLoc;
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
