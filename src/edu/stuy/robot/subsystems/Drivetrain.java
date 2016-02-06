package edu.stuy.robot.subsystems;

import static edu.stuy.robot.RobotMap.FRONT_LEFT_MOTOR_CHANNEL;
import static edu.stuy.robot.RobotMap.FRONT_RIGHT_MOTOR_CHANNEL;
import static edu.stuy.robot.RobotMap.REAR_LEFT_MOTOR_CHANNEL;
import static edu.stuy.robot.RobotMap.REAR_RIGHT_MOTOR_CHANNEL;
import static edu.stuy.robot.RobotMap.GYRO_P;
import static edu.stuy.robot.RobotMap.GYRO_I;
import static edu.stuy.robot.RobotMap.GYRO_D;

import edu.stuy.util.TankDriveOutput;
import edu.stuy.robot.commands.DrivetrainTankDriveCommand;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {

	private CANTalon leftFrontMotor;
	private CANTalon rightFrontMotor;
	private CANTalon leftRearMotor;
	private CANTalon rightRearMotor;
	private RobotDrive robotDrive;
	private ADXRS450_Gyro gyro;
	private PIDController pid;
	private TankDriveOutput out;

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	public Drivetrain() {
		leftFrontMotor = new CANTalon(FRONT_LEFT_MOTOR_CHANNEL);
		rightFrontMotor = new CANTalon(FRONT_RIGHT_MOTOR_CHANNEL);
		leftRearMotor = new CANTalon(REAR_LEFT_MOTOR_CHANNEL);
		rightRearMotor = new CANTalon(REAR_RIGHT_MOTOR_CHANNEL);
		robotDrive = new RobotDrive(leftFrontMotor, leftRearMotor, rightFrontMotor, rightRearMotor);

		out = new TankDriveOutput(robotDrive);
		gyro = new ADXRS450_Gyro();
		pid = new PIDController(GYRO_P, GYRO_I, GYRO_D, gyro, out);

		// pid.setInputRange(0, 360);
		// pid.setContinuous();
		gyro.reset();
		gyro.setPIDSourceType(PIDSourceType.kDisplacement);
		gyro.calibrate();
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new DrivetrainTankDriveCommand());
	}

	public void tankDrive(double left, double right) {
		robotDrive.tankDrive(left, right);
	}

	public double getGyroAngle() {
		return gyro.getAngle() % 360;
	}
}
