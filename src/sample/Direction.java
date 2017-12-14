package sample;

public enum Direction {
    NORTH, EAST, WEST, SOUTH;

    public Direction getOpposite () {
        switch (this) {
            case NORTH:
                return SOUTH;
            case EAST:
                return WEST;
            case SOUTH:
                return NORTH;
            case WEST:
                return EAST;
        }
        return null;
    }
}
