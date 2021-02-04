import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public class Room {
	private Location location;
	private Map<Direction, Boolean> borders;
	private Map<Direction, Room> neighbors;
	private boolean visited;
	private boolean treasure;
	private Direction relativeDirection; //Used for building the maze

	public Room(Location location) {
		this.location = location;
		this.borders = new HashMap<>();
		EnumSet<Direction> directions = EnumSet.allOf(Direction.class);
		for(Direction direction : directions) {
			borders.put(direction, true);
		}
		this.neighbors = new HashMap<>();
	}

	public Location getLocation() {
		return location;
	}

	public void setNeighbor(Direction direction, Room neighbor) {
		this.neighbors.put(direction, neighbor);
	}

	public Map<Direction, Room> getNeighbors() {
		return neighbors;
	}

	public Map<Direction, Boolean> getBorders() {
		return borders;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public boolean isTreasure() {
		return treasure;
	}

	public void setTreasure(boolean treasure) {
		this.treasure = treasure;
	}

	public void setRelativeDirection(Direction relativeDirection) {
		this.relativeDirection = relativeDirection;
	}

	public Direction getRelativeDirection() {
		return relativeDirection;
	}

	public void setBorderAsDoor(Direction direction) {
		this.borders.put(direction, false);
	}

	public boolean getBorder(Direction direction) {
		return borders.get(direction);
	}
}
