package frc.robot.intake;
import static frc.robot.intake.IntakeConstants.*;

import edu.wpi.first.units.measure.Angle;


public enum IntakeStates {
    INTAKING(INTAKE_SPEED, INTAKE_POSITION),
    IDLE(IDLE_SPEED, IDLE_POSITION),
    PASSING(PASSING_SPEED, PASSING_POSITION);

    public final double speed;
    public final Angle position;
    private IntakeStates(double speed, Angle position) {
        this.speed = speed;
        this.position = position;
    }

    public double getSpeed() {
        return speed;
    }

    public Angle getPosition() {
        return position;
    }
}
