package pojo;


import java.util.Map;

public class Room {
	private Location location;
	private Map<Direction, Boolean> borders;
	private Map<Direction, Room> neighbors;
	private boolean visited;
	private boolean treasure;

	public Room(Location location) {
		this.location = location;
	}

	public Location getLocation() {
		return location;
	}

	public void setNeighbor(Direction direction, Room neighbor) {
		this.neighbors.put(direction, neighbor);
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
}
