package edu.stuy.robot.commands;

import java.util.Arrays;

import edu.stuy.robot.Robot;
import edu.stuy.robot.cv.StuyVisionModule;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CVReadAndPrintCommand extends Command {

    public CVReadAndPrintCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        try {
            long start = System.currentTimeMillis();
            double[] vec = Robot.vision.processImage();
            System.out.println("\n\n\n\n\n\n\n\n\n\nprocessImage took " + (System.currentTimeMillis() - start) + "ms");
            System.out.println("Reading is: " + Arrays.toString(vec)); // Arrays.toString returns "null" is vec is null
            System.out.println("Distance is: " + StuyVisionModule.findDistanceToGoal(vec));
            if (vec != null) {
                System.out.println("Angle X is: " + StuyVisionModule.frameXPxToDegrees(vec[0]));
                System.out.println("Angle Y is: " + StuyVisionModule.frameYPxToDegrees(vec[1]));
                System.out.println("Y to horiz: " + StuyVisionModule.yInFrameToDegreesFromHorizon(vec[1]));
            }
        } catch (Exception e) {
            System.err.println("\n\n\n\nGeneric exception caught in CVReadAndPrintCommand:");
            e.printStackTrace();
            System.err.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
