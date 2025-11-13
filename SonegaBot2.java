package sample;
import robocode.*;
import java.awt.Color;

/**
 * HammerRampBot - Robô com um martelo vertical e uma rampa frontal
 * Simula virar inimigos e golpeá-los com o martelo.
 */
public class SonegaBot2 extends AdvancedRobot {

    public void run() {
        // Aparência
        setBodyColor(Color.gray);
        setGunColor(Color.orange);
        setRadarColor(Color.yellow);
        setBulletColor(Color.red);
        setScanColor(Color.white);

        while (true) {
            setTurnRadarRight(360);
            ahead(100);
            turnRight(30);
        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        double distance = e.getDistance();
        double turnGunAmt = getHeading() - getGunHeading() + e.getBearing();
        setTurnGunRight(turnGunAmt);
        setTurnRight(e.getBearing());

        if (distance < 120) {
            setAhead(50);
            fire(1);
            execute();

            back(30);
            fire(3);
            ahead(20);

        } else if (distance < 300) {
            setAhead(distance / 2);
            fire(1.5);
        } else {
            setTurnRadarRight(30);
        }
    }

    public void onHitByBullet(HitByBulletEvent e) {
        back(50);
        turnRight(45);
    }

    public void onHitRobot(HitRobotEvent e) {
        if (e.isMyFault()) {
            fire(2);
            back(40);
        }
    }

    public void onHitWall(HitWallEvent e) {
        back(80);
        turnRight(90);
    }
}
