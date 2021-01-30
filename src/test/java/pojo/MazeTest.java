package pojo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MazeTest {
    private Maze maze;

    @Before
    public void setUp() throws Exception {
        maze = new Maze();
    }

    @Test
    public void testGenerateMaze4x4() {
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFailedToGenerateMaze_WidthSizeTooSmall() {
        maze.create(3, 4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFailedToGenerateMaze_LengthSizeTooSmall() {
        maze.create(4, 3);
    }
}