import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;

public class Maze {
    private final int width;
    private final int length;
    private Room[][] maze;

    public Maze(int width, int length) {
        this.width = width;
        this.length = length;
    }

    public void create() {
        if (width < 4) {
            throw new IllegalArgumentException("Width should be minimum 4");
        }
        if (length < 4) {
            throw new IllegalArgumentException("Length should be minimum 4");
        }
        maze = new Room[width][length];
        for (int x = 0; x < width; x++) { // Create the maze rooms
            for (int y = 0; y < length; y++) {
                maze[x][y] = new Room(new Location(x, y));
            }
        }
        EnumSet<Direction> directions = EnumSet.allOf(Direction.class);
        for (int x = 0; x < width; x++) { // Set for each room its neighbours
            for (int y = 0; y < length; y++) {
                for (Direction direction : directions) {
                    Location neighborLocation = maze[x][y].getLocation().add(direction.getValue());
                    if (validLocation(neighborLocation)) {
                        maze[x][y].setNeighbor(direction, maze[neighborLocation.getX()][neighborLocation.getY()]);
                    }
                }
            }
        }

        // Random starting point from which to build the maze path
        int randomX = ThreadLocalRandom.current().nextInt(0, width);
        int randomY = ThreadLocalRandom.current().nextInt(0, length);

        // Starting room for the create path
        Room currentRoom = maze[randomX][randomY];
        Stack<Room> path = new Stack<>();
        path.push(currentRoom);
        int unvisitedCount = width * length - 1;

        // For each room that was unvisited (must visit all rooms in the maze)
        while (unvisitedCount > 0) {
            if (path.empty()) {
                throw new IllegalStateException("path should not be empty");
            }

            currentRoom = path.peek();
            currentRoom.setVisited(true);

            // For the current room set in its neighbours their relative direction to him
            ArrayList<Room> nextRooms = new ArrayList<>();
            for (Map.Entry<Direction, Room> entry : currentRoom.getNeighbors().entrySet()) {
                Room neighbor = entry.getValue();
                if (!(neighbor.isVisited())) {
                    neighbor.setRelativeDirection(entry.getKey());
                    nextRooms.add(neighbor);
                }
            }

            // If there are no rooms to go to from the current room, continue with the while loop
            if (nextRooms.size() == 0) {
                path.pop();
                continue;
            }

            // Choose randomly the next room to go to
            Room nextRoom = nextRooms.get(ThreadLocalRandom.current().nextInt(0, nextRooms.size()));
            Direction nextRoomRelativeDirection = nextRoom.getRelativeDirection();
            if (!currentRoom.getBorders().get(nextRoomRelativeDirection)) {
                throw new IllegalStateException("current room should not have this border");
            }

            currentRoom.setBorderAsDoor(nextRoomRelativeDirection);
            Direction oppositeDirection = nextRoomRelativeDirection.getOppositeDirection();
            if (!nextRoom.getBorder(oppositeDirection)) {
                throw new IllegalStateException("next room should not have this border");
            }
            nextRoom.setBorderAsDoor(oppositeDirection);
            unvisitedCount--;
            path.add(nextRoom);
        }

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < length; y++) {
                Room room = maze[x][y];
                if (y == length - 1 && !room.getBorder(Direction.UP)) {
                    throw new IllegalStateException("Top row should have this border");
                }
                if (y == 0 && !room.getBorder(Direction.DOWN)) {
                    throw new IllegalStateException("Bottom row should have this border");
                }
                if (x == width - 1 && !room.getBorder(Direction.RIGHT)) {
                    throw new IllegalStateException("Right column should have this border");
                }
                if (x == 0 && !room.getBorder(Direction.LEFT)) {
                    throw new IllegalStateException("Left column should have this border");
                }
                for (Map.Entry<Direction, Room> entry : room.getNeighbors().entrySet()) {
                    Room neighbor = entry.getValue();
                    Direction direction = entry.getKey();
                    Direction oppositeDirection = direction.getOppositeDirection();
                    if (room.getBorder(direction) != neighbor.getBorder(oppositeDirection)) {
                        throw new IllegalStateException("Mismatched neighbor borders");
                    }
                }
            }
        }
    }

    // Make sure we are in bounds of the maze
    private boolean validLocation(Location location) {
        return (location.getX() >= 0
                && location.getX() < width
                && location.getY() >= 0
                && location.getY() < length);
    }

    @Override
    public String toString() {
        StringBuilder returnString = new StringBuilder();
        char[][] cellDrawing = new char[2 * width + 1][2 * length + 1];

        for (int y = 0; y < 2 * length; y++) {
            for (int x = 0; x < 2 * width; x++) {
                cellDrawing[x][y] = ' ';
            }
        }
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < length; y++) {
                int xOffset = 2 * x;
                int yOffset = 2 * y;
                if (maze[x][y].getBorder(Direction.UP)) {
                    cellDrawing[xOffset][yOffset + 2] =
                            cellDrawing[xOffset + 1][yOffset + 2] =
                                    cellDrawing[xOffset + 2][yOffset + 2] = '*';
                }
                if (maze[x][y].getBorder(Direction.RIGHT)) {
                    cellDrawing[xOffset + 2][yOffset] =
                            cellDrawing[xOffset + 2][yOffset + 1] =
                                    cellDrawing[xOffset + 2][yOffset + 2] = '*';
                }
                if (maze[x][y].getBorder(Direction.DOWN)) {
                    cellDrawing[xOffset][yOffset] =
                            cellDrawing[xOffset + 1][yOffset] =
                                    cellDrawing[xOffset + 2][yOffset] = '*';
                }
                if (maze[x][y].getBorder(Direction.LEFT)) {
                    cellDrawing[xOffset][yOffset] =
                            cellDrawing[xOffset][yOffset + 1] =
                                    cellDrawing[xOffset][yOffset + 2] = '*';
                }
            }
        }
        for (int x = 0; x < 2 * width + 1; x++) {
            returnString.append("*");
        }
        returnString.append("\n");
        for (int y = (2 * length) - 1; y >= 0; y--) {
            for (int x = 0; x < 2 * width; x++) {
                returnString.append(cellDrawing[x][y]);
            }
            returnString.append("*\n");
        }
        return returnString.toString();
    }
}