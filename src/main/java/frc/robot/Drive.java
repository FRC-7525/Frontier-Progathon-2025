package frc.robot;

import java.io.File;
import java.io.IOException;

import edu.wpi.first.wpilibj.Filesystem;
import swervelib.parser.SwerveParser;
import swervelib.SwerveDrive;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.Odometry;
import edu.wpi.first.math.util.Units;
import swervelib.telemetry.SwerveDriveTelemetry;
import swervelib.telemetry.SwerveDriveTelemetry.TelemetryVerbosity;


public class Drive {
    private double MAXIMUM_SPEED;
    private File swerveJsonDirectory;
    private SwerveDrive swerveDrive;

    public Drive() {
        MAXIMUM_SPEED = Constants.Drive.MAXIMUM_SPEED;
        swerveJsonDirectory = new File(Filesystem.getDeployDirectory(),"swerve");
        try {
            swerveDrive = new SwerveParser(swerveJsonDirectory).createSwerveDrive(MAXIMUM_SPEED);
        } catch (IOException e) {
           
            e.printStackTrace();
        }
        SwerveDriveTelemetry.verbosity = TelemetryVerbosity.HIGH;
        swerveDrive.resetOdometry(new Pose2d(5, 5, Rotation2d.fromDegrees(0)));
    }
    
    public void periodic(double translationX, double translationY, double angularRotationX) {
        swerveDrive.drive(new Translation2d(-translationX * swerveDrive.getMaximumChassisAngularVelocity(),
            -translationY * -swerveDrive.getMaximumChassisAngularVelocity()),
            angularRotationX * swerveDrive.getMaximumChassisAngularVelocity(), 
                    true, false);
    }
}


