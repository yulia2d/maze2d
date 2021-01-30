package pojo;

public class Location {
    private final int x;
    private final int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Location add(Location newLocation) {
        return new Location(x + newLocation.getX(), y + newLocation.getY());
    }
}
