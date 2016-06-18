package elevator.controller;

import elevator.command.Command;
import elevator.command.Path;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * Test class for {@link ModeAController}
 */
@RunWith(MockitoJUnitRunner.class)
public class ModeAControllerTest {

    private Controller controller;

    @Mock
    private Command command;

    @Before
    public void setUp() {
        controller = new ModeAController();
    }


    @Test
    public void testCalculateFloorPathIfCommandHasSinglePath() {
        ArrayList<Path> paths = new ArrayList<>();
        paths.add(new Path(3, 5));
        Mockito.when(command.getPaths()).thenReturn(paths);
        Mockito.when(command.getInitialFloor()).thenReturn(9);
        List<Integer> floorPath = controller.calculateFloorPath(command);
        assertThat(floorPath.size(), is(3));
        assertThat(floorPath, equalTo(Arrays.asList(9, 3, 5)));


    }

    @Test
    public void testCalculateFloorPathIfCommandHasMultiplePath() {
        ArrayList<Path> paths = new ArrayList<>();
        paths.add(new Path(4, 6));
        paths.add(new Path(3, 5));
        Mockito.when(command.getPaths()).thenReturn(paths);
        Mockito.when(command.getInitialFloor()).thenReturn(9);
        List<Integer> floorPath = controller.calculateFloorPath(command);
        assertThat(floorPath.size(), is(5));
        assertThat(floorPath, equalTo(Arrays.asList(9, 4, 6, 3, 5)));
    }


    @Test(expected = IllegalArgumentException.class)
    public void testCalculateDistanceIfFloorPathIsEmpty() {
        Integer distance = controller.calculateDistance(Arrays.asList());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateDistanceIfFloorPathIsNull() {
        Integer distance = controller.calculateDistance(null);
    }

    @Test
    public void testCalculateDistanceIfFloorPathHasStartPointOnly() {
        Integer distance = controller.calculateDistance(Arrays.asList(4));
        assertThat(distance, is(0));
    }

    @Test
    public void testCalculateDistance() {
        Integer distance = controller.calculateDistance(Arrays.asList(9, 5, 3, 2, 1));
        assertThat(distance, is(8));
    }
}
