package elevator.command;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Path path = (Path) o;

        if (getStartFloor() != path.getStartFloor()) return false;
        return getEndFloor() == path.getEndFloor();

    }

    @Override
    public int hashCode() {
        int result = getStartFloor();
        result = 31 * result + getEndFloor();
        return result;
    }

    @Override
    public String toString() {
        return "Path{" +
                "startFloor=" + startFloor +
                ", endFloor=" + endFloor +
                ", direction=" + direction +
                '}';
    }
}
