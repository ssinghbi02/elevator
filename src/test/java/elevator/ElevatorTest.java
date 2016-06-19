package elevator;

import elevator.command.Command;
import elevator.controller.Controller;
import elevator.exception.CommandException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Test class for {@link Elevator}
 */
@RunWith(MockitoJUnitRunner.class)
public class ElevatorTest {
    private Elevator elevator;

    @Mock
    private Controller controller;

    @Before
    public void setUp() {
        elevator = new Elevator(controller, 12);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExecuteIfInstructionFileIsMissing() {
        elevator.readInstructionFile("non_exists_file.txt");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testElevatorIfMaxFloorIsNegativeOrZero() {
        elevator = new Elevator(controller, -2);
    }

    @Test(expected = CommandException.class)
    public void testThrowExceptionIfInstructionsAreInCorrect() {
        elevator.readInstructionFile("test_invalid.txt");
    }

    @Test
    public void testExecuteIfInstructionsAreCorrect() {
        elevator.execute();
        Mockito.verify(controller, Mockito.times(6)).calculateFloorPath(Mockito.any(Command.class));
        Mockito.verify(controller, Mockito.times(6)).calculateDistance(Mockito.any(List.class));
    }

    @Test
    public void testReadInstructionsIfInstructionsAreCorrect() {
        elevator.readInstructionFile("test_valid.txt");
        assertThat(elevator.getCommands().size(), is(6));
    }

}
