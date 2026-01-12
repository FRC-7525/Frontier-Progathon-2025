package frc.robot;

public class Globalconstants {
    public enum RobotMode {
        REAL,
        SIM,
        TESTING
    }
    
	public static final RobotMode ROBOT_MODE = "Crash".equals(System.getenv("CI_NAME")) ||
    !Robot.isReal()
    ? RobotMode.SIM
    : RobotMode.REAL;
}
