public class Player {
    private String name;
    private Room currentRoom;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    /**
     * Attempt to move forward in the given direction. Returns true if the move succeeded,
     * false if a wall is in the way.
     *
     * @return true if the move completed, false if it did not
     */
    public boolean move(Direction direction) {
        if (currentRoom.getBorder(direction)) {
            return false;
        } else {
            currentRoom = currentRoom.getNeighbors().get(direction);
            return true;
        }
    }
}