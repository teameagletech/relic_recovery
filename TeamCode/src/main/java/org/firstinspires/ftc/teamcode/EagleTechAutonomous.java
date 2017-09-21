package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

@Autonomous(name = "EagleTechAutonomous")
public class EagleTechAutonomous extends OpMode {

//    OpenGLMatrix lastLocation = null;

//    String format(OpenGLMatrix transformationMatrix) {
//        return (transformationMatrix != null) ? transformationMatrix.formatAsTransform() : "null";
//    }

    VuforiaLocalizer vuforia;
    VuforiaTrackables relicTrackables;
    VuforiaTrackable relicTemplate;

    DcMotor wm1, wm2;

    @Override
    public void init() {
        // Vuforia setup code
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        parameters.vuforiaLicenseKey = "AViOO0H/////AAAAGdOe07SFvkp9o8ayO1bk4XuDzjkGt9iAYhWO7gNOXYyRgcKIqt/Emv1z47NNWKJrRJahGxnoOUYzDaTvKspZCbeAuvna+XJbdvJoECZ1DDEdo/iwXL55N39Y7Jv6veJKnr4FycQROZGBU+r0Ac/EfMomkWXulsarNQuTMLiHIgikYqf+sfjVx1CO648O3WOtPEfTrfPmJB/rvo2NqG8kLmZ218EhwXgWsEGoqb3e24WJimftXKRXuH/4VzIiQLj8p+K84LurwqJjGnq8q3RRzaUCcgrLnQ1RoqA0FT7+OLIRbMkxJCfHnvqsgeKTzJCcjJX5oJPR2jYubleXblt+VKpQ43t6x5/yLYSbRFy9bYoH";
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate");

        wm1 = hardwareMap.dcMotor.get("wm1");
        wm2 = hardwareMap.dcMotor.get("wm2");
    }

    @Override
    public void start() {
        relicTrackables.activate();
        resetStartTime();
    }

    @Override
    public void loop() {

        if (getRuntime() < 1) {
            // Turn around for 1 second
            wm1.setPower(1);
            wm2.setPower(-1);
        } else {
            // Stop motors
            wm1.setPower(0);
            wm2.setPower(0);

            RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
            telemetry.addLine(vuMark.name()); // LEFT, RIGHT, CENTER, or UNKNOWN
        }

        telemetry.update();
    }

    @Override
    public void stop() {
        relicTrackables.deactivate();
    }

}
