package elevator.command;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Test class for {@link Path}
 */
public class PathTest {


    @Test
    public void testPathCreation() {
        Path path = new Path(5, 3);
        assertThat(path.getStartFloor(), is(5));
        assertThat(path.getEndFloor(), is(3));
    }

    @Test
    public void testPathUpDirection() {
        Path path = new Path(2, 4);
        assertThat(path.getDirection(), is(Direction.UP));
    }

    @Test
    public void testPathDownDirection() {
        Path path = new Path(5, 3);
        assertThat(path.getDirection(), is(Direction.DOWN));
    }


}
