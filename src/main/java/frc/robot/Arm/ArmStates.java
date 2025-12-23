package frc.robot.Arm;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularVelocity;

import static frc.robot.Arm.ArmConstants.*;

public enum ArmStates{
    IDLE("Idle", IDLE_POSITION, IDLE_VELOCITY),
    SHOOTING("Shooting",SHOOTING_POSITION, SHOOTING_VELOCITY),
    SCORING("Scoring",SCORING_POSITION, SCORING_VELOCITY),
    PASSING("Passing",PASSING_POSITION, PASSING_VELOCITY);

    Angle targetAngle;
    AngularVelocity targetAngularVelocity; 
    String stateString;

    ArmStates(String stateString, Angle targetAngle, AngularVelocity targetAngularVelocity) {
        this.stateString = stateString;
        this.targetAngle = targetAngle;
        this.targetAngularVelocity = targetAngularVelocity;
    }
    public AngularVelocity getTargetVelocity() {
        return targetAngularVelocity;
    }
    public Angle getTargetAngle() {
        return targetAngle;
    }
    public String getStateString() {
        return stateString;
    }
    public ArmStates getState() {;
        return this;
    }
    
}
