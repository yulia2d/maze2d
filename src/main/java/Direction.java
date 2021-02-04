public enum Direction {
    UP(new Location(0, 1)),
    DOWN(new Location(0, -1)),
    LEFT(new Location(-1, 0)),
    RIGHT(new Location(1, 0));

    private final Location value;

    Direction(Location value) {
        this.value = value;
    }

    public Location getValue() {
        return value;
    }

    public Direction getOppositeDirection() {
        switch (this) {
            case UP:
                return DOWN;
            case DOWN:
                return UP;
            case LEFT:
                return RIGHT;
            case RIGHT:
                return LEFT;
        }
        return null;
    }
}