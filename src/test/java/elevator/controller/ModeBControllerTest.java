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
 * Test class for {@link ModeBController}
 */
@RunWith(MockitoJUnitRunner.class)

public class ModeBControllerTest {

    private Controller controller;

    @Mock
    private Command command;

    @Before
    public void setUp() {
        controller = new ModeBController();
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
        paths.add(new Path(1, 5));
        paths.add(new Path(1, 6));
        paths.add(new Path(1, 5));
        Mockito.when(command.getPaths()).thenReturn(paths);
        Mockito.when(command.getInitialFloor()).thenReturn(9);
        List<Integer> floorPath = controller.calculateFloorPath(command);
        assertThat(floorPath.size(), is(4));
        assertThat(floorPath, equalTo(Arrays.asList(9, 1, 5, 6)));
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
        Integer distance = controller.calculateDistance(Arrays.asList(7, 11, 10, 6, 5, 6, 8, 12, 7, 4, 8, 9));
        assertThat(distance, is(30));
    }}
