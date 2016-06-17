package elevator.command;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class takes raw command and convert into list of instruction.
 *
 */
public class Command {
    private List<Path> paths;
    private final String rawCommand;
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
            throw new IllegalArgumentException("Initial floor should be delimited by ':' ");
        }
        String[] tokens = rawCommand.split(":");
        Arrays.asList(tokens[1].split(","))
                .forEach(token -> {
                    String[] paths = token.split("-");
                    if (paths.length != 2)
                        throw new IllegalArgumentException("Given path command is invalid");
                    try {
                        Integer.valueOf(paths[0]);
                        Integer.valueOf(paths[1]);
                    } catch (NumberFormatException ex) {
                        throw new IllegalArgumentException("input command is invalid");
                    }
                });
    }

}
