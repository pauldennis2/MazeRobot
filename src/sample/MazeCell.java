package sample;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MazeCell {

    MazeCell nConnection;
    MazeCell eConnection;
    MazeCell sConnection;
    MazeCell wConnection;

    int xLoc;
    int yLoc;

    Gem gem;

    public MazeCell(int xLoc, int yLoc) {
        this.xLoc = xLoc;
        this.yLoc = yLoc;
    }

    public MazeCell (int xLoc, int yLoc, Gem gem) {
        this.xLoc = xLoc;
        this.yLoc = yLoc;
        this.gem = gem;
    }

    public boolean isConnectedTo (MazeCell otherCell) {
        return getAllConnectedCells().contains(otherCell);
    }

    public Set<MazeCell> getAllConnectedCells () {
        Set<MazeCell> connectedCells = getConnectedCells();
        int currentSize = connectedCells.size();
        while (true) {
            Set<MazeCell> potentialNewCells = new HashSet<>();
            for (MazeCell cell : connectedCells) {
                for (MazeCell potentialNew : cell.getConnectedCells()) {
                    if (!connectedCells.contains(potentialNew)) {
                        potentialNewCells.add(potentialNew);
                    }
                }
            }
            connectedCells.addAll(potentialNewCells);
            if (connectedCells.size() == currentSize) {//If we have not gained any size (i.e. found new cells)
                break;
            } else {
                currentSize = connectedCells.size();
            }
        }
        return connectedCells;
    }

    //Method returns true if all the cells are interconnected (each is connected to the others)
    public static boolean areConnected (List<MazeCell> cells) {
        //Build the "connection net
        Set<MazeCell> connectedCells = cells.get(0).getAllConnectedCells();
        for (MazeCell cell : cells) {
            if (!connectedCells.contains(cell)) {
                return false;
            }
        }
        return true;
    }

    //Get direct connections only
    public Set<MazeCell> getConnectedCells () {
        Set<MazeCell> connectedCells = new HashSet<>();

        if (nConnection != null) {
            connectedCells.add(nConnection);
        }
        if (eConnection != null) {
            connectedCells.add(eConnection);
        }
        if (sConnection != null) {
            connectedCells.add(sConnection);
        }
        if (wConnection != null) {
            connectedCells.add(wConnection);
        }
        return connectedCells;
    }
}
