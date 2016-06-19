package elevator.command;


import elevator.exception.CommandException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class takes raw command and convert into list of instruction.
 *
 */
public class Command {
    private final String rawCommand;
    private List<Path> paths;
    private Integer initialFloor;

    /**
     * Constructor.
     * @param rawCommand plain string raw command
     */
    public Command(final String rawCommand) {
        validate(rawCommand);
        this.rawCommand = rawCommand;
        parseRawCommand();
    }

    public List<Path> getPaths() {
        return paths;
    }

    public Integer getInitialFloor() {
        return initialFloor;
    }

    /**
     * Parse the raw text command and
     * convert in set of instruction.
     */
    private void parseRawCommand() {
        String[] tokens = rawCommand.split(":");
        this.initialFloor = Integer.valueOf(tokens[0]);
        this.paths = Arrays.asList(tokens[1].split(","))
                .stream()
                .map(item -> new Path(Integer.valueOf(item.split("-")[0]), Integer.valueOf(item.split("-")[1])))
                .collect(Collectors.toList());
    }

    /**
     * This mathod takes the raw text command and
     * validates it.
     * @param rawCommand raw text command
     */
    private void validate(String rawCommand) {
        int index = rawCommand.indexOf(":");
        if (index == -1) {
            throw new CommandException("Initial floor should be delimited by ':' ");
        }
        String[] tokens = rawCommand.split(":");
        Arrays.asList(tokens[1].split(","))
                .forEach(token -> {
                    String[] paths = token.split("-");
                    if (paths.length != 2)
                        throw new CommandException("Given path command is invalid");

                    try {
                        Integer startFloor = Integer.valueOf(paths[0]);
                        Integer endFloor = Integer.valueOf(paths[1]);
                        if (startFloor < 0 || endFloor < 0) {
                            throw new CommandException("start or end floor is negative");
                        }
                    } catch (NumberFormatException ex) {
                        throw new CommandException("input command is invalid");
                    }
                });
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Command command = (Command) o;

        if (!rawCommand.equals(command.rawCommand)) return false;
        if (!getPaths().equals(command.getPaths())) return false;
        return getInitialFloor().equals(command.getInitialFloor());

    }

    @Override
    public int hashCode() {
        int result = rawCommand.hashCode();
        result = 31 * result + getPaths().hashCode();
        result = 31 * result + getInitialFloor().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Command{" +
                "rawCommand='" + rawCommand + '\'' +
                ", paths=" + paths +
                ", initialFloor=" + initialFloor +
                '}';
    }
}
