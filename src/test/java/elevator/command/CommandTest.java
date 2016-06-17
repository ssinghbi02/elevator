package elevator.command;

import elevator.command.Command;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by ssinghbi02 on 16/06/2016.
 */
public class CommandTest {

    private Command command;

    @Test(expected = IllegalArgumentException.class)
    public void testParseRawCommandIfCommandIsInvalid1(){
        command = new Command("2,4-1,4-2,6-8");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseRawCommandIfCommandIsInvalid2(){
        command = new Command("2:4-1,4-2,6-");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseRawCommandIfCommandIsInvalid3(){
        command = new Command("2:4-1,4-2,6");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseRawCommandIfCommandIsInvalid4(){
        command = new Command("2:4-1,4,2");
    }

    @Test
    public void testParseRawCommandIfCommandIsValid() {
        command = new Command("2:4-1,4-2");
        assertThat(command.getPaths().size(), is(2));
        assertThat(command.getInitialFloor(), is(2));

        command = new Command("3:7-9,3-7,5-8,7-11,11-1");
        assertThat(command.getPaths().size(), is(5));
        assertThat(command.getInitialFloor(), is(3));
    }
}
