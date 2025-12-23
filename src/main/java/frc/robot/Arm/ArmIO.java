package frc.robot.Arm;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularVelocity;

public interface ArmIO {
    public void setAngle(Angle position);

    public void setVelocity(AngularVelocity velocity);

    public void logData();
     
}