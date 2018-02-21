/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team6905;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
// If you rename or move this class, update the build.properties file in the project root
public class Robot extends IterativeRobot {
	
	private static final String DEFAULT_AUTO = "Default";
	private static final String CUSTOM_AUTO = "My Auto";
	private String autoSelected;
	private SendableChooser<String> chooser = new SendableChooser<>();
	private DifferentialDrive drive;
	private TalonSRX lift, right, left;
	private Joystick driveJoystick;
	private int autoState = 0;
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		Spark left1 = new Spark(0);
		Spark left2 = new Spark(1);
		SpeedControllerGroup groupLeft = new SpeedControllerGroup(left1, left2);
		
		Spark right1 = new Spark(2);
		Spark right2 = new Spark(3);
		SpeedControllerGroup groupRight = new SpeedControllerGroup(right1, right2);
		
		lift = new TalonSRX(10);
		right = new TalonSRX(20);
		left = new TalonSRX(30);
		
		drive = new DifferentialDrive(groupLeft, groupRight);
		
		driveJoystick = new Joystick(0);
		
		chooser.addDefault("Do Nothing", DEFAULT_AUTO);
		chooser.addObject("Run Auto", CUSTOM_AUTO);
		SmartDashboard.putData("Auto choices", chooser);
	}
	
	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 * <p>
	 * <p>You can add additional auto modes by adding additional comparisons to
	 * the switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
		autoSelected = chooser.getSelected();
		autoState = 0;
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
	}
	
	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		switch (autoSelected) {
			case CUSTOM_AUTO:
				// Put custom auto code here
				break;
			case DEFAULT_AUTO:
			default:
				// Put default auto code here
				break;
		}
	}
	
	
	
	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		drive.arcadeDrive(driveJoystick.getY(), driveJoystick.getX());
		
		if (driveJoystick.getRawButton(5)) {
			lift.set(ControlMode.PercentOutput, 20);
		}
		else if (driveJoystick.getRawButton(3)) {
			lift.set(ControlMode.PercentOutput, -20);
		}
		else {
			lift.set(ControlMode.PercentOutput, 0);
		}
		
		if (driveJoystick.getRawButton(6)) {
		
		}
		else if (driveJoystick.getRawButton(4)) {
		
		}
		else {
		
		}
	}
	
	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
		drive.arcadeDrive(driveJoystick.getY(), driveJoystick.getX());
	}
	
	public void setAutoState(int state) {
		autoState = state;
	}
}
