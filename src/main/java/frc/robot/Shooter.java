package frc.robot;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.controller.PIDController;

enum ShooterStates {
    IDLE, 
    SHOOTING,
    LONGSHOOT,
}

public class Shooter {
    
    private ShooterStates state;  
    private TalonFX leftMotor; 
    private TalonFX rightMotor;
    private PIDController motorcontrollerleft;
    private PIDController motorcontrollerright;
    private TalonFX rotationalMotor;


    public Shooter() {
        state = ShooterStates.IDLE;
        motorcontrollerright = new PIDController(0.0002, 0, 0);
        motorcontrollerleft = new PIDController(0.0002, 0, 0); //PID Tune values /// change these to try to make the robot to drive straight
        leftMotor = new TalonFX(Constants.Shooter.LEFT_MOTOR_ID);
        rightMotor = new TalonFX(Constants.Shooter.RIGHT_MOTOR_ID);
        rotationalMotor = new TalonFX(Constants.Shooter.ROTATE_MOTOR_ID);
    }

    public void setState(ShooterStates state) {
        this.state = state;
    }

    public void periodic() {
        switch (state) {
            case IDLE:
                leftMotor.set(0);
                rightMotor.set(0);
                rotationalMotor.set(35);
                break; 
            case SHOOTING: 
                rotationalMotor.set(Constants.Shooter.SHOOTING_SPEED);
                leftMotor.set(motorcontrollerleft.calculate(leftMotor.getVelocity().getValueAsDouble(), Constants.Shooter.SHOOTING_RPS));
                rightMotor.set(motorcontrollerright.calculate(rightMotor.getVelocity().getValueAsDouble(), Constants.Shooter.SHOOTING_RPS));
                break;
            case LONGSHOOT:
                rotationalMotor.set(Constants.Shooter.LONGSHOOTING_SPEED); 
                leftMotor.set(motorcontrollerleft.calculate(leftMotor.getVelocity().getValueAsDouble(), Constants.Shooter.LONGSHOOTING_RPS));
                rightMotor.set(motorcontrollerright.calculate(rightMotor.getVelocity().getValueAsDouble(), Constants.Shooter.LONGSHOOTING_RPS));          
                break;
        }
    }
}