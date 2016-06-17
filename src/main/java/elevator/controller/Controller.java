package elevator.controller;

import elevator.command.Command;

import java.util.List;

/**
 * Controller main is to understand the commands and
 * convert into floor path which elevator can understand.
 * This also calculates distance for a given floor path.
 *
 */
public interface Controller {
    /**
     * Compute floor path for given command.
     *
     * @param command command input
     * @return floor path sequence
     */
    List<Integer> calculateFloorPath(final Command command);

    /**
     * Computes the distance for given floor path.
     *
     * @param floorPath sequence of floor numbers.
     * @return distance
     */
    Integer calculateDistance(List<Integer> floorPath);
}
