package pojo;


public class Room {
	private Location location;

	private RoomSide upperSide;
	private RoomSide leftSide;
	private RoomSide rightSide;
	private RoomSide lowerSide;

	private boolean treasure;
	private int treasurePoints;

	public String printRoom() {
		return location.toString();
	}
}
