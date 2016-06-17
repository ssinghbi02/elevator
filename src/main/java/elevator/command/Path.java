package elevator.command;

import elevator.command.Direction;

/**
 * This class represent indivisual path of command.
 */
public class Path {
    private final int startFloor;
    private final int endFloor;
    private final Direction direction;

    public Path(final int startFloor, final int endFloor) {
        this.startFloor = startFloor;
        this.endFloor = endFloor;
        this.direction = calculateDirection();
    }

    public int getStartFloor() {
        return startFloor;
    }

    public int getEndFloor() {
        return endFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public Direction calculateDirection() {
        return startFloor > endFloor ? Direction.DOWN : Direction.UP;
    }

}
