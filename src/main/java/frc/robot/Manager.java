package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.intake.Intake;
import frc.robot.intake.IntakeStates;
import frc.robot.Arm.Arm;
import frc.robot.Arm.ArmStates;

public class Manager {
    private Arm arm;
    private Shooter shooter;
    private Intake intake;
    private ManagerStates robotState;
    private Drive drive;

    private XboxController controller = new XboxController(0);
    
    enum ManagerStates {
        IDLE,
        INTAKING,
        SHOOTING,
        FEEDING,
        SCORING_AMP,
        DRIVE
    }

    public Manager() {
        intake = new Intake(); 
        shooter = new Shooter();
        arm = new Arm();
        drive = new Drive(); 

        robotState = ManagerStates.IDLE; 

        
    }

    public void periodic() {
        arm.periodic();
        shooter.periodic();
        intake.periodic();
        drive.periodic(controller.getLeftX(), controller.getLeftY(), controller.getRightX());
        
        switch (robotState) {
            case IDLE:
                shooter.setState(ShooterStates.IDLE);
                intake.setState(IntakeStates.IDLE);
                arm.setState(ArmStates.IDLE);

                if (controller.getBButtonPressed()) {
                    robotState = ManagerStates.INTAKING; 
                }
                else if (controller.getYButtonPressed()) {
                    robotState = ManagerStates.SHOOTING; 
                }
                else if (controller.getAButtonPressed()) {
                    robotState = ManagerStates.FEEDING;
                }
                break;
            case INTAKING: 
                intake.setState(IntakeStates.INTAKING);
                arm.setState(ArmStates.IDLE);
                shooter.setState(ShooterStates.IDLE);
                if (controller.getBButtonPressed()) {
                    robotState = ManagerStates.IDLE; 
                }
                break; 
            case SHOOTING:
                shooter.setState(ShooterStates.SHOOTING); 
                intake.setState(IntakeStates.PASSING);
                arm.setState(ArmStates.SHOOTING);
                if (controller.getYButtonPressed()) {
                    robotState = ManagerStates.IDLE; 
                }
                break; 
            case FEEDING:
                arm.setState(ArmStates.PASSING);
                intake.setState(IntakeStates.INTAKING);
                shooter.setState(ShooterStates.SHOOTING);
                


            default:
                break;
        }
    }
}
