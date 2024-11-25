package org.firstinspires.ftc.teamcode.botCode;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MecanumDrive;

/**
 * This is a basic file to start a roadrunner autonomous.
 * Need example code? https://rr.brott.dev/docs/v1-0/guides/centerstage-auto/
 */

@Autonomous(name="Auto", group="Autonomous")
public class Auto extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        //Write your setup code here
        Pose2d initialPose = new Pose2d(0, 0, 0); //Starting Position
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose); //Auto Class

        /*
        Action resources (will be needed for using other mechanisms)
        https://rr.brott.dev/docs/v1-0/actions/ <- Action Overview
         */

        /*
        Trajectory resources
        https://learnroadrunner.com/trajectory-sequence.html <- Trajectory builder blocks
        https://rr.brott.dev/docs/v1-0/builder-ref/ <- Unique trajectory paths
        */

        TrajectoryActionBuilder trajectoryName = drive.actionBuilder(initialPose) //Trajectory Builder
                .setTangent(Math.toRadians(0)) //Set the robots direction of travel (in radians)
                ; //Create a trajectory path

        waitForStart();
        //Write your executing code here
        Actions.runBlocking(
                new SequentialAction(
                        trajectoryName.build() //Runs the trajectory
                )
        );
    }
}
