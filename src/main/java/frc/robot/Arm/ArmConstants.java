package frc.robot.Arm;

import static edu.wpi.first.units.Units.Degree;
import static edu.wpi.first.units.Units.RotationsPerSecond;

import java.util.function.Supplier;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.wpilibj.simulation.FlywheelSim;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;

import static frc.robot.Globalconstants.*;
public class ArmConstants {
    public static final Angle IDLE_POSITION = Degree.of(0); 
    public static final Angle SHOOTING_POSITION = Degree.of(90); 
    public static final Angle SCORING_POSITION = Degree.of(125); 
    public static final Angle PASSING_POSITION = Degree.of(0); 

    public static final AngularVelocity IDLE_VELOCITY = RotationsPerSecond.of(0);
    public static final AngularVelocity SHOOTING_VELOCITY = RotationsPerSecond.of(0);
    public static final AngularVelocity SCORING_VELOCITY = RotationsPerSecond.of(40);
    public static final AngularVelocity PASSING_VELOCITY = RotationsPerSecond.of(5);

    	public static final Supplier<PIDController> PIVOT_CONTROLLER = () ->
		switch (ROBOT_MODE) {
			case REAL -> new PIDController(0.1, 0, 0);
			case SIM -> new PIDController(0.1, 0, 0);
			case TESTING -> new PIDController(0.1, 0, 0);
		};
	public static final Supplier<PIDController> WHEEL_CONTROLLER = () ->
		switch (ROBOT_MODE) {
			case REAL -> new PIDController(0.1, 0, 0);
			case SIM -> new PIDController(0.1, 0, 0);
			case TESTING -> new PIDController(0.1, 0, 0);
		};

    public static final FlywheelSim WHEEL_SIM = new FlywheelSim(LinearSystemId.createFlywheelSystem(DCMotor.getNEO(1), 1, 1),DCMotor.getNEO(1));

    public static final SingleJointedArmSim ARM_SIM = new SingleJointedArmSim(
		LinearSystemId.createSingleJointedArmSystem(DCMotor.getNEO(1), 0.192383865, 67.5),
		DCMotor.getNEO(1),
		67.5,
		.3,
		0,
		Units.degreesToRadians(180),
		true,
		0
	);
}
