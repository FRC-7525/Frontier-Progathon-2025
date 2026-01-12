package frc.robot.intake;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.XboxController;

public class Intake {

    XboxController controller;
    TalonFX wheelsMotor;
    TalonFX pivotMotor;
    IntakeStates state;
    
    public Intake() {
        controller = new XboxController(0);
        wheelsMotor = new TalonFX(1);
        pivotMotor = new TalonFX(2);
        state = IntakeStates.IDLE;
    }

    public void periodic() {
        wheelsMotor.set(state.getSpeed());
        pivotMotor.setPosition(state.getPosition());
    }
}
