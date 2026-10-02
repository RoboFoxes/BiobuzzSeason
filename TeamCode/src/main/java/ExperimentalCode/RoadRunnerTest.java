package ExperimentalCode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.trajectory.TrajectoryBuilder;
import com.acmerobotics.roadrunner.drive.MecanumDrive;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "RR Test Auto", group = "Autonomous")
public class RoadRunnerTest extends LinearOpMode {

    @Overrideimport com.acmerobotics.roadrunner.geometry.
    public void runOpMode() {
        // Create the drive (uses the MecDynamic config in virtual_robot)
        MecanumDrive drive = new MecanumDrive(hardwareMap);

        // Starting pose: center of field, facing 0°
        Pose2d startPose = new Pose2d(0, 0, 0);
        drive.setPoseEstimate(startPose);

        // Build a simple trajectory: forward 20", turn 90°, forward 20"
        Trajectory trajectory = new TrajectoryBuilder(startPose)
                .lineTo(new Vector2d(20, 0))
                .turn(Math.toRadians(90))
                .lineTo(new Vector2d(20, 20))
                .build();

        waitForStart();

        if (isStopRequested()) return;

        // Follow the trajectory (blocking)
        drive.followTrajectoryAsync(trajectory);
        while (opModeIsActive() && !isStopRequested()) {
            drive.update();
        }
    }
}