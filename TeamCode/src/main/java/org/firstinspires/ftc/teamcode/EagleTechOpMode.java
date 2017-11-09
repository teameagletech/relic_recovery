package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by ishanarya on 11/6/17.
 */

@TeleOp(name = "EagleTechOpMode")
public class EagleTechOpMode extends OpMode {

    private DcMotor rightDrive, leftDrive;
    private DcMotor pullMotor;
    private Servo rightHand, leftHand;

    private final double rightHandClosedPosition = 0.518;
    private final double leftHandClosedPosition = 0.523;
    private final int pullMotorMaxTurn = 7750;
    private final int pullMotorMinTurn = 0;

    @Override
    public void init() {

        rightDrive = hardwareMap.dcMotor.get("rightDrive");
        leftDrive = hardwareMap.dcMotor.get("leftDrive");
        pullMotor = hardwareMap.dcMotor.get("pullMotor");

        rightHand = hardwareMap.servo.get("rightHand");
        leftHand = hardwareMap.servo.get("leftHand");
        rightHand.setPosition(0);
        leftHand.setPosition(1);

    }

    @Override
    public void loop() {

        leftDrive.setPower(gamepad1.left_stick_y);
        rightDrive.setPower(-gamepad1.right_stick_y);

        if(gamepad1.left_bumper) {
            rightHand.setPosition(rightHandClosedPosition);
            leftHand.setPosition(leftHandClosedPosition);
        } else {
            rightHand.setPosition(0);
            leftHand.setPosition(1);
        }
        if(gamepad1.dpad_up) {
            if(pullMotor.getCurrentPosition() < pullMotorMaxTurn) {
                pullMotor.setPower(0.5);
            }
        } else if(gamepad1.dpad_down) {
            if(pullMotor.getCurrentPosition() > pullMotorMinTurn) {
                pullMotor.setPower(-0.5);
            }
        } else {
            pullMotor.setPower(0);
        }
        telemetry.addData("Pull Motor Position", pullMotor.getCurrentPosition());
    }
}