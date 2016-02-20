package edu.stuy.robot.commands.auton;

import static edu.stuy.robot.RobotMap.SLOT_ANGLE_TO_GOAL_2;
import static edu.stuy.robot.RobotMap.SLOT_ANGLE_TO_GOAL_3;
import static edu.stuy.robot.RobotMap.SLOT_ANGLE_TO_GOAL_4;
import static edu.stuy.robot.RobotMap.SLOT_ANGLE_TO_GOAL_5;

import edu.stuy.robot.RobotMap;
import edu.stuy.robot.commands.HopperRunCommand;
import edu.stuy.robot.commands.SetupforShotCommand;
import edu.stuy.robot.commands.ShooterSetHighCommand;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossObstacleThenShootCommand extends CommandGroup {

    public double correspondingAngle(Integer position) {
        if (position == 2) {
            return SLOT_ANGLE_TO_GOAL_2;
        } else if (position == 3) {
            return SLOT_ANGLE_TO_GOAL_3;
        } else if (position == 4) {
            return SLOT_ANGLE_TO_GOAL_4;
        } else {
            return SLOT_ANGLE_TO_GOAL_5;
        }
    }

    public CrossObstacleThenShootCommand(Command obstacle, Integer position) {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        // addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        // addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
        
        
        addSequential(obstacle);
        addSequential(new AlignWithWallCommand(0.5));
        addSequential(new SetDistanceFromWallCommand(RobotMap.DISTANCE_TO_WALL, 0.5));
        addSequential(new RotateDrivetrainCommand(correspondingAngle(position)));
        // TODO: Fix RotateDrivetrainCommand to work once we have PID tuning
        addSequential(new SetupforShotCommand());
        addSequential(new ShooterSetHighCommand());
        addSequential(new HopperRunCommand(true));
    }
}
