package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class NavxSubsystem extends SubsystemBase {
    // We are mounting directly on the MXP port, so use SPI for low-latency
    public final AHRS ahrs = new AHRS(SPI.Port.kMXP);

    public NavxSubsystem() {

    }

    public boolean isReady() {
        return ahrs.isConnected() && !ahrs.isCalibrating();
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("IMU_CompassHeading", ahrs.getCompassHeading());
    }
}
