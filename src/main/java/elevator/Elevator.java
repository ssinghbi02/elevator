package elevator;

import elevator.command.Command;
import elevator.controller.Controller;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.springframework.util.Assert.*;

/**
 * This Elevator class reads instruction from input file,
 * parse and process instructions.
 * This elevator runs in two different mode 'A'  and 'B'.
 */
public class Elevator {

    private final Controller controller;
    private List<Command> commands = new ArrayList<>();
    private final int topFloor;
    private final int groundFloor;
    private final Logger LOG=Logger.getLogger(Elevator.class.getName());

    public Elevator(final Controller elevatorController, final int topFloor, final int groundFloor) {
        this.controller = elevatorController;
        this.topFloor = topFloor;
        this.groundFloor = groundFloor;
    }

    public void execute(){
        readInstructionFile("input.txt");
        commands.forEach(command -> {
            List<Integer> seq = controller.calculateFloorPath(command);
            StringBuilder builder = new StringBuilder();
            seq.forEach(i -> {builder.append(i + " ");});
            System.out.println(builder.toString() + "(" + controller.calculateDistance(seq) + ")");
        });
    }

    public void readInstructionFile(String fileName){
        notNull(fileName);
        URL fileurl = ClassLoader.getSystemResource(fileName);
        notNull(fileurl);

        try (Stream<String> stream = Files.lines(Paths.get(ClassLoader.getSystemResource(fileName).toURI()))) {
            List<String> rawCommands = new ArrayList<>();
            stream.collect(Collectors.toCollection(() -> rawCommands));
            commands = rawCommands.stream().map(rawCommand -> new Command(rawCommand)).collect(Collectors.toList());
        } catch (Exception e) {
            LOG.log(Level.WARNING, "Error in reading the file");
        }
    }

    public List<Command> getCommands() {
        return commands;
    }

    public int getTopFloor() {
        return topFloor;
    }

    public int getGroundFloor() {
        return groundFloor;
    }
}
