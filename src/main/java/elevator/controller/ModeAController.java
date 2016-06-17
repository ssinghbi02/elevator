package elevator.controller;

import elevator.command.Command;
import elevator.command.Path;
import elevator.controller.Controller;

import java.util.ArrayList;
import java.util.List;

/**
 * {@inheritDoc}
 */
public class ModeAController implements Controller {

    @Override
    /**
     * {@inheritDoc}
     */
    public List<Integer> calculateFloorPath(Command command) {
        List<Integer> sequence = new ArrayList<>();
        sequence.add(command.getInitialFloor());
        Path current = null;
        Path previous = null;
        for (Path path : command.getPaths()) {
            current = path;
            if (previous != null && previous.getEndFloor() == current.getStartFloor()) {
                sequence.add(current.getEndFloor());
            } else {
                sequence.add(current.getStartFloor());
                sequence.add(current.getEndFloor());
            }
            previous = current;
        }
        return sequence;
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public Integer calculateDistance(List<Integer> floorPath) {
        if (floorPath == null) {
            throw new IllegalArgumentException("floor path is null");
        }
        Integer distance = 0;
        Integer previousFloorNo = null;
        for (Integer floorNo : floorPath) {
            if (previousFloorNo == null) {
                previousFloorNo = floorNo;
                continue;
            }
            Integer distanceBetCurrentAndNext = floorNo - previousFloorNo;
            distance = distance + (distanceBetCurrentAndNext < 0 ? distanceBetCurrentAndNext * (-1) : distanceBetCurrentAndNext);
            previousFloorNo = floorNo;
        }
        return distance;
    }
}
