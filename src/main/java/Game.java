import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private Maze maze;
    private List<Player> players;

    public void init(int mazeWidth, int mazeLength, int numberOfPlayers) {
        maze = new Maze(mazeWidth, mazeLength);
        maze.create();

        players = new ArrayList<>(numberOfPlayers);
        for (int i = 0; i < numberOfPlayers; i++) {
            Player player = new Player("Player" + i);
            player.setCurrentRoom(maze.getStartingRoom());
            players.add(player);
        }
    }

    public void start() throws IOException {
        boolean treasureFound = false;
        System.out.println(maze.printMaze(players));
        while (!treasureFound) {
            for (Player player : players) {
                System.out.println("Player '"+player.getName() + "', choose a direction to go [UP, DOWN, LEFT, RIGHT]:");
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

                // Reading data using readLine
                String directionString = reader.readLine();
                Direction direction = Direction.valueOf(directionString.toUpperCase());
                player.move(direction);
                if (player.getCurrentRoom().hasTreasure()) {
                    //TODO: update player score.
                    treasureFound = true;
                }
            }
            System.out.println(maze.printMaze(players));
        }
    }
}
