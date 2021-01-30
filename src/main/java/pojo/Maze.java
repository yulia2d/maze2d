package pojo;

import java.util.EnumSet;
import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;

public class Maze {
    private Room[][] maze;

    public void create(int width, int length) {
        if (width < 4) {
            throw new IllegalArgumentException("Width should be minimum 4");
        }
        if (length < 4) {
            throw new IllegalArgumentException("Length should be minimum 4");
        }
        maze = new Room[width][length];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < length; y++) {
                maze[x][y] = new Room(new Location(x,y));
            }
        }
        EnumSet<Direction> directions = EnumSet.allOf(Direction.class);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < length; y++) {
                for (Direction direction : directions) {
                    Location neighborLocation = maze[x][y].getLocation().add(direction.getValue());
                    maze[x][y].setNeighbor(direction, maze[neighborLocation.getX()][neighborLocation.getY()]);
                }
            }
        }

        int randomX = ThreadLocalRandom.current().nextInt(0, width);
        int randomY = ThreadLocalRandom.current().nextInt(0, length);

        Room currentRoom = maze[randomX][randomY];
        Stack<Room> path = new Stack<Room>();
        path.push(currentRoom);
        int unvisitedCount = width * length - 1;
        while (unvisitedCount > 0) {
            if (path.empty()) {
                throw new IllegalStateException("path should not be empty");
            }

            currentRoom = path.peek();
            currentRoom.setVisited(true);


        }
    }
}
