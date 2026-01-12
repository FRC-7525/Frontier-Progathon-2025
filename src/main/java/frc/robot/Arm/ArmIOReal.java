package frc.robot.Arm;

import static frc.robot.Arm.ArmConstants.WHEEL_CONTROLLER;

import org.littletonrobotics.junction.Logger;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularVelocity;

import static edu.wpi.first.units.Units.Degrees;
import static edu.wpi.first.units.Units.RotationsPerSecond;
import static frc.robot.Arm.ArmConstants.*;

public class ArmIOReal implements ArmIO {

    SparkMax pivotMotor;
    SparkMax wheelMotor;
    PIDController positionController;
    PIDController velocityController;
    AngularVelocity targetVelocity;
    Angle targetAngle;

    public ArmIOReal() {
        wheelMotor = new SparkMax(3, MotorType.kBrushless);
        pivotMotor = new SparkMax(4, MotorType.kBrushless);

        positionController = PIVOT_CONTROLLER.get();
        velocityController = WHEEL_CONTROLLER.get();
    }


    @Override
    public void setAngle(Angle position) {
        pivotMotor.set(positionController.calculate(pivotMotor.getAbsoluteEncoder().getPosition(), position.in(Degrees)));
        targetAngle = position;
    }

    @Override
    public void setVelocity(AngularVelocity velocity) {
        wheelMotor.set(velocityController.calculate(wheelMotor.getAbsoluteEncoder().getVelocity(), velocity.in(RotationsPerSecond)));
        targetVelocity = velocity;
    }

    @Override
    public void logData() {
        Logger.recordOutput("Arm/ Wheel speed", wheelMotor.getAbsoluteEncoder().getVelocity());
        Logger.recordOutput("Arm/ Arm Angle", pivotMotor.getAbsoluteEncoder().getPosition());
        Logger.recordOutput("Arm/ Target position", targetAngle);
        Logger.recordOutput("Arm/ Target velocity", targetVelocity);
    }

}