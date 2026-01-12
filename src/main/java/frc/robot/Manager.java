package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Arm.Arm;

public class Manager {
    private Arm arm;
    private ManagerStates robotState;

    private XboxController xboxController = new XboxController(0);
    
    enum ManagerStates {
        IDLE,
        INTAKE,
        SHOOTING,
        EEDING,
        SCORING_AMP,
        DRIVE
    }

    public Manager() {
        intake  = new Intake(); 
        shooter = new Shooter();
        arm = new AmpBar();
        drive = new Drive(); 

        robotState = ManagerStates.IDLE; 

        
    }

    public void periodic() {
        arm.periodic();
        shooter.periodic();
        intake.periodic();
        drive.periodic(controller.getLeftX().getAsDouble(), controller.getLeftY().getAsDouble(), controller.getRightX().getAsDouble());
        
        switch (robotState) {
            case IDLE:
                shooter.setState(ShooterStates.IDLE);
                intake.setState(IntakeStates.IDLE);
                ampBar.setState(AmpStates.IN);

                if (controller.getBButtonPressed()) {
                    robotState = ManagerState.INTAKING; 
                }
                else if (controller.getYButtonPressed()) {
                    robotState = ManagerState.SHOOTING; 
                }
                else if (controller.getAButtonPressed()) {
                    robotState = ManagerState.FEEDING;
                }
                break;
            case INTAKING: 
                intake.setState(IntakeStates.INTAKING);
                ampBar.setState(AmpStates.IN);
                shooter.setState(ShooterStates.IDLE);
                if (controller.getBButtonPressed()) {
                    robotState = ManagerState.IDLE; 
                }
                break; 
            case SHOOTING:
                shooter.setState(ShooterStates.SHOOTING); 
                intake.setState(IntakeStates.PASSING);
                ampBar.setState(AmpStates.IN);
                if (controller.getYButtonPressed()) {
                    robotState = ManagerState.IDLE; 
                }
                break; 
            case FEEDING:
                arm.setState(AmpStates.FEEDING);
                intake.setState(IntakeStates.SLOW);
                shooter.setState(ShooterStates.SLOW);
                
                if (ampBar.hasNote()) {
                    robotState = ManagerState.HOLDING_NOTE_AMP;
                }
            case HOLDING_NOTE_AMP:
                ampBar.setState(AmpStates.OUT);
                shooter.setState(ShooterStates.IDLE);
                intake.setState(IntakeStates.IDLE);
                
                if (controller.getAButtonPressed()) {
                    robotState = ManagerState.SHOOTING_AMP;
                }
            case SHOOTING_AMP:
                shooter.setState(ShooterStates.IDLE);
                intake.setState(IntakeStates.IDLE);
                ampBar.setState(AmpStates.SHOOTING);

                if (controller.getAButtonPressed()) {
                    robotState = ManagerState.IDLE;
                }

            default:
                break;
        }
    }
}
