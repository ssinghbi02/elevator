package elevator.controller;

import elevator.command.Command;
import elevator.command.Direction;
import elevator.command.Path;
import elevator.controller.Controller;

import java.util.*;

/**
 * {@inheritDoc}
 */
public class ModeBController implements Controller {


    @Override
    /**
     * {@inheritDoc}
     */
    public List<Integer> calculateFloorPath(Command command) {

        List<Integer> sequence = new ArrayList<>();
        sequence.add(command.getInitialFloor());
        List<Path> upQueue = new ArrayList<>();
        List<Path> downQueue = new ArrayList<>();
        Path current = null;
        Path previous = null;
        for(Path path : command.getPaths()) {
            current = path;
            if(previous != null && previous.getDirection() != current.getDirection()) {
                if(previous.getDirection() == Direction.UP)
                    convertingQueueInOrderedSeq(upQueue, Direction.UP, sequence);
                else
                    convertingQueueInOrderedSeq(downQueue, Direction.DOWN, sequence);
            }

            boolean status = (path.getDirection() == Direction.UP) ? upQueue.add(path) : downQueue.add(path);
            previous = current;

        }
        if(!upQueue.isEmpty()) {
            convertingQueueInOrderedSeq(upQueue, Direction.UP, sequence);
        }
        if(!downQueue.isEmpty()) {
            convertingQueueInOrderedSeq(downQueue, Direction.DOWN, sequence);
        }
        return sequence;
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public Integer calculateDistance(List<Integer> floorPath) {
        if(floorPath == null) {
            throw new IllegalArgumentException("floor path is null");
        }
        Integer distance = 0;
        Integer previousFloorNo = null;
        for(Integer floorNo : floorPath) {
            if(previousFloorNo == null) {
                previousFloorNo = floorNo;
                continue;
            }
            Integer distanceBetCurrentAndNext = floorNo - previousFloorNo;
            distance = distance + (distanceBetCurrentAndNext<0 ?distanceBetCurrentAndNext*-1 : distanceBetCurrentAndNext);
            previousFloorNo = floorNo;
        }
        return distance;
    }

    /**
     * This method takes input queue and update to output floor sequence
     *
     * @param queue list of input path
     * @param direction current direction
     * @param sequence output sequence to be updated
     */
    private void convertingQueueInOrderedSeq(final List<Path> queue, final Direction direction, List<Integer> sequence) {
        Set<Integer> seq = (direction == Direction.UP) ? new TreeSet<>() : new TreeSet<>(Collections.reverseOrder());
        for (Path command : queue) {
            seq.add(command.getStartFloor());
            seq.add(command.getEndFloor());
        }
        Integer firstElement = seq.iterator().next();
        if( sequence.size() > 0 && sequence.get(sequence.size() - 1) == seq.iterator().next()) {
            seq.remove(firstElement);
        }
        sequence.addAll(seq);
        queue.clear();
    }
}
