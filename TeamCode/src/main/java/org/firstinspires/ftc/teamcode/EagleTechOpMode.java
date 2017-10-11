package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "EagleTechOpMode")
public class EagleTechOpMode extends OpMode {

    private DcMotor wm1, wm2;
    private Servo c1, c2;
//    private DcMotor slideMotor;

    @Override
    public void init() {
        wm1 = hardwareMap.dcMotor.get("wm1");
        wm2 = hardwareMap.dcMotor.get("wm2");
//        slideMotor = hardwareMap.dcMotor.get("sm");

        c1 = hardwareMap.servo.get("c1");
        c2 = hardwareMap.servo.get("c2");
    }

    @Override
    public void loop() {
        wm1.setPower(gamepad1.left_stick_y);
        wm2.setPower(gamepad1.right_stick_y);

        if (gamepad1.left_trigger > 0.5) {
            c1.setPosition(0.5);
            c2.setPosition(0.5);
        } else {
            c1.setPosition(0);
            c2.setPosition(0.9);
        }
//        slideMotor.setPower(gamepad1.a ? 1 : (gamepad1.b ? -1 : 0)); // if a, power = 1, if b, power = -1, else 0
    }
}
