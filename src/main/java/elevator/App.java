package elevator;

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

        //create elevator with mode A
        System.out.println("Elevator in mode A");
        Elevator elevator1 = new Elevator(modeAElevatorController, 12, 0);
        elevator1.execute();

        //create elevator with mode B
        System.out.println("Elevator in mode B");
        Elevator elevator2 = new Elevator(modeBElevatorController, 12, 0);
        elevator2.execute();
    }
}
