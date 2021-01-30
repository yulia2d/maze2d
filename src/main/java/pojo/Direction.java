package pojo;

public enum Direction {
    UP(new Location(0, 1)),
    DOWN(new Location(0, -1)),
    LEFT(new Location(1, 0)),
    RIGHT(new Location(-1, 0));

    private Location value;

    Direction(Location value) {
        this.value = value;
    }

    public Location getValue() {
        return value;
    }
}