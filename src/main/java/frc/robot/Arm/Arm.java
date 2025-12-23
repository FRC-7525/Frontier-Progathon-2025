package frc.robot.Arm;

import static frc.robot.Globalconstants.*;


public class Arm {
    ArmIO io;
    private static Arm intance;

    private Arm() {
        super();
        this.io = switch (ROBOT_MODE) {
            case REAL -> new ArmIOReal();
            case SIM -> new ArmIOSim();
            case TESTING -> new ArmIOReal();    
        };
    }

    public static Arm getInstance() {
        if (intance == null) {
            intance = new Arm();
        }
        return intance;
    }

    public ArmStates getState() {
        return getState();
    }

    protected void runState() {
        io.setVelocity(getState().getTargetVelocity());
        io.setAngle(getState().getTargetAngle());
        io.logData();
    
    }
        

}
