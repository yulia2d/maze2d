import pojo.Maze;

public class Main {

	public static void main(String[] args) {
		Maze maze = new Maze(4, 4);
		maze.create();
		System.out.println(maze.toString());
	}

}
