package pojo;

public class Wall implements RoomSide {
	@Override
	public SideType getSideType() {
		return SideType.WALL;
	}
}
