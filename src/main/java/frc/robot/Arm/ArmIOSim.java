package frc.robot.Arm;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.wpilibj.simulation.FlywheelSim;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;

import static edu.wpi.first.units.Units.Degree;
import static edu.wpi.first.units.Units.RotationsPerSecond;
import static frc.robot.Arm.ArmConstants.*;

import org.littletonrobotics.junction.Logger;
public class ArmIOSim implements ArmIO {
    FlywheelSim wheelSim;
    SingleJointedArmSim armSim;
    PIDController positiController;
    PIDController velocityController;
    AngularVelocity targetVelocity;
    Angle targetAngle;

    public ArmIOSim() {
        wheelSim = WHEEL_SIM;
        armSim = ARM_SIM;

        positiController = PIVOT_CONTROLLER.get();
        velocityController = WHEEL_CONTROLLER.get();
        targetVelocity = RotationsPerSecond.of(0);
        targetAngle = Degree.of(0);
    
    }


    
    
    
    


@Override
public void setAngle(Angle position) {
    armSim.setInputVoltage(positiController.calculate(armSim.getAngleRads()));
    targetAngle = position;
}

@Override
public void setVelocity(AngularVelocity velocity) {
    wheelSim.setInputVoltage(velocityController.calculate(wheelSim.getAngularVelocityRadPerSec(), velocity.in(RotationsPerSecond)));
    targetVelocity = velocity;
}   

@Override
public void logData() {
    Logger.recordOutput("Arm/Wheel speed", wheelSim.getAngularVelocityRPM() / 60);
    Logger.recordOutput( "Arm/Arm Angle", armSim.getAngleRads());
    Logger.recordOutput("Arm/Target position", targetAngle);
    Logger .recordOutput("Arm/Target velocity", targetVelocity);
    
}
}