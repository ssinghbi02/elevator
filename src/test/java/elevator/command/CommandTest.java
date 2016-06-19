package elevator.command;

import elevator.exception.CommandException;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Test class for {@code Command}
 */
public class CommandTest {

    @Test(expected = CommandException.class)
    public void testParseRawCommandIfCommandIsInvalidScenario1() {
        Command command = new Command("2,4-1,4-2,6-8");
    }

    @Test(expected = CommandException.class)
    public void testParseRawCommandIfCommandIsInvalidScenario2() {
        Command command = new Command("2:4-1,4-2,6-");
    }

    @Test(expected = CommandException.class)
    public void testParseRawCommandIfCommandIsInvalidScenario3() {
        Command command = new Command("2:4-1,4-2,6");
    }

    @Test(expected = CommandException.class)
    public void testParseRawCommandIfCommandIsInvalidScenario4() {
        Command command = new Command("2:4-1,4,2");
    }

    @Test(expected = CommandException.class)
    public void testParseRawCommandIfCommandIsInvalidScenario5() {
        Command command = new Command("2:4-1,5-2,-3-2");
    }


    @Test
    public void testParseRawCommandIfCommandIsValid() {
        Command command = new Command("2:4-1,4-2");
        assertThat(command.getPaths().size(), is(2));
        assertThat(command.getInitialFloor(), is(2));

        command = new Command("3:7-9,3-7,5-8,7-11,11-1");
        assertThat(command.getPaths().size(), is(5));
        assertThat(command.getInitialFloor(), is(3));
    }
}
