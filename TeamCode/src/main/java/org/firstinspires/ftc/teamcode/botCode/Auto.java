package org.firstinspires.ftc.teamcode.botCode;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.MecanumDrive;

/*
   TODO:
    In order to setup roadrunner, you need to plug new values into different files...
    - MecanumDrive
    - TwoDeadWheelLocalizer OR ThreeDeadWheelLocalizer
    Follow those TO DO comments and fill in the tuning values, then come back to work on this file.

   TODO:
    Roadrunner official site: https://learnroadrunner.com/
    Roadrunner guide: https://rr.brott.dev/docs/
    Need example code? https://rr.brott.dev/docs/v1-0/guides/centerstage-auto/
*/

@Autonomous(name="Auto", group="Autonomous")
public class Auto extends LinearOpMode {

    // TODO: Action resources (will be needed for using other mechanisms)
    //  https://rr.brott.dev/docs/v1-0/actions/ <- Action Overview

    /* Startup Code (pre-initialized) */
    DcMotorEx arm = hardwareMap.get(DcMotorEx.class, "arm");

    //Declare Actions
    Action setPos(int extenstion) {return new setPos(extenstion);}

    //Setup Actions
    public class setPos implements Action {
        //Link the input variable using a constructor
        int extenstion;
        public setPos(int extenstion) {this.extenstion = extenstion;}

        //Run a "mini op-mode"
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            arm.setVelocity(5000);
            arm.setTargetPosition(extenstion);
            //Return false to end looping, true to continue looping
            return false;
        }
    }

    @Override
    public void runOpMode() throws InterruptedException {

        // TODO: Trajectory resources
        //  https://learnroadrunner.com/trajectory-sequence.html <- Trajectory builder blocks
        //  https://rr.brott.dev/docs/v1-0/builder-ref/ <- Unique trajectory paths

        /* Setup Code (initialized) */
        Pose2d initialPose = new Pose2d(0, 0, 0); //Starting Position
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose); //Auto Class

        //Set the bot to its initial position safely
        Actions.runBlocking(setPos(0));

        //Trajectory builders
        TrajectoryActionBuilder trajectoryName = drive.actionBuilder(initialPose)
                .setTangent(Math.toRadians(0)) //Set the robots direction of travel (in radians)
                .lineToX(24); //Create a trajectory path

        /* Other trajectory commands I found useful
         * - splineTo(Vector2d, tangent)
         * - splineToSplineHeading(Pose2d, tangent)
         * - stopAndAdd(action)
         * - afterTime(timeInSeconds, action)
         * - waitSeconds(timeInMilliseconds)
         */

        /* Executing Code (post-initialized) */
        waitForStart();

        //Run your trajectories/actions
        Actions.runBlocking(
                new SequentialAction(
                        setPos(1000), //Set's the arm to that position
                        trajectoryName.build() //Run that trajectory
                )
        );
    }
}
