package sample;

import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;
import robocode.HitByBulletEvent;
import robocode.HitWallEvent;
import java.awt.Color;

/**
 * SawHookBot - um robô com "duas serras horizontais" e "um gancho vertical"
 */
public class SonegaBot extends AdvancedRobot {

    public void run() {
        // Cores temáticas (serras prateadas e gancho vermelho)
        setBodyColor(Color.darkGray);
        setGunColor(Color.gray);
        setRadarColor(Color.red);
        setBulletColor(Color.orange);
        setScanColor(Color.yellow);

        // Gira radar continuamente procurando inimigos
        while (true) {
            setTurnRadarRight(360);
            ahead(50);
            back(50);
        }
    }

    // Quando um robô é detectado
    public void onScannedRobot(ScannedRobotEvent e) {
        double distance = e.getDistance();

        // "Gancho vertical" – investida se estiver perto
        if (distance < 150) {
            setTurnGunRight(getHeading() - getGunHeading() + e.getBearing());
            fire(3); // potência máxima
            ahead(distance / 2);
        } else {
            // "Serras horizontais" – ataque giratório médio
            setTurnGunRight(getHeading() - getGunHeading() + e.getBearing());
            fire(1.5);
            setTurnRight(30);
            ahead(30);
        }
    }

    // Quando é atingido por uma bala
    public void onHitByBullet(HitByBulletEvent e) {
        back(50);
        setTurnRight(60);
    }

    // Quando bate em uma parede
    public void onHitWall(HitWallEvent e) {
        back(80);
        turnRight(90);
    }
}
