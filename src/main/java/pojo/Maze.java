package pojo;

import javafx.util.Pair;

public class Maze {
    private Room[][] maze;

    public Maze(int length, int width) {
        maze = new Room[length][width];
    }
}
