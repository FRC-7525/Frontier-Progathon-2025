package frc.robot.Arm;

import static frc.robot.Globalconstants.*;


public class Arm {
    ArmIO io;
    private static Arm intance;

    public Arm() {
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

    public void periodic() {
        io.setVelocity(getState().getTargetVelocity());
        io.setAngle(getState().getTargetAngle());
        io.logData();
    
    }
        

}
