package elevator;

import elevator.controller.Controller;
import elevator.controller.ModeAController;
import elevator.controller.ModeBController;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ModeBController modeBElevatorController = new ModeBController();
        ModeAController modeAElevatorController = new ModeAController();

        //create and run elevator with mode A
        System.out.println("Elevator in mode A");
        runElevator(modeAElevatorController, 12);

        //create and run elevator with mode B
        System.out.println("Elevator in mode B");
        runElevator(modeBElevatorController, 12);

    }

    private static void runElevator(Controller controller, int maxFloor) {
        Elevator elevator1 = new Elevator(controller, maxFloor);
        elevator1.execute();
    }
}
