package elevator.command;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Test class for {@code Direction}
 */
public class DirectionTest {

    @Test
    public void testDirection() {
        assertThat(Direction.values().length, is(2));
        assertThat(Direction.values()[0], is(Direction.UP));
        assertThat(Direction.values()[1], is(Direction.DOWN));
    }
}
