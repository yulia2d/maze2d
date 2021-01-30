package pojo;

public class Door implements RoomSide {
	private Room prevRoom;
	private Room nextRoom;

	public Door(Room prevRoom, Room nextRoom) {
		this.prevRoom = prevRoom;
		this.nextRoom = nextRoom;
	}

	@Override
	public SideType getSideType() {
		return SideType.DOOR;
	}

	public Room getPrevRoom() {
		return prevRoom;
	}

	public Room getNextRoom() {
		return nextRoom;
	}
}
  