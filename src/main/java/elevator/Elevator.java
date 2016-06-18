package elevator;

import elevator.command.Command;
import elevator.controller.Controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.util.Assert.notNull;

/**
 * This Elevator class reads instruction from input file,
 * parse and process instructions.
 * This elevator runs in two different mode 'A'  and 'B' which is determined by controller.
 */
public class Elevator {

    private static final String INSTRUCTION_FILE = "input.txt";
    private final Controller controller;
    private final int maxFloor;
    private final Logger LOG=Logger.getLogger(Elevator.class.getName());
    private List<Command> commands = new ArrayList<>();

    public Elevator(final Controller controller, final int maxFloor) {
        if (maxFloor <= 0) {
            throw new IllegalArgumentException("max floor must be positive number");
        }
        this.controller = controller;
        this.maxFloor = maxFloor;
    }

    /**
     * Executes the instructions given in input instruction file.
     */
    public void execute(){
        readInstructionFile(INSTRUCTION_FILE);
        commands.forEach(command -> {
            List<Integer> seq = controller.calculateFloorPath(command);
            processFloorSequence(seq);
        });
    }

    /**
     * Reads and parse input instruction file and convert into commands.
     *
     * @param fileName instruction file
     */
    public void readInstructionFile(String fileName){
        notNull(fileName);
        URL fileurl = ClassLoader.getSystemResource(fileName);
        notNull(fileurl);

        try (Stream<String> stream = Files.lines(Paths.get(fileurl.toURI()))) {
            List<String> rawCommands = new ArrayList<>();
            stream.collect(Collectors.toCollection(() -> rawCommands));
            commands = rawCommands.stream().map(Command::new).collect(Collectors.toList());
        } catch (IOException | URISyntaxException | IllegalArgumentException ex) {
            LOG.log(Level.WARNING, "Error in reading the file");
        }
    }

    public List<Command> getCommands() {
        return commands;
    }

    public int getMaxFloor() {
        return maxFloor;
    }

    /**
     * display floor path and distance.
     *
     * @param seq elevator flow sequence
     */
    private void processFloorSequence(List<Integer> seq) {
        StringBuilder builder = new StringBuilder();
        seq.forEach(i -> builder.append(i + " "));
        System.out.println(builder.toString() + "(" + controller.calculateDistance(seq) + ")");
    }

}
