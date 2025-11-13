package sample;
import robocode.*;
import java.awt.Color;

/**
 * SawHookBot - um robô com "duas serras horizontais" e "um gancho vertical"
 * Baseado no conceito enviado por Pitoco.
 */
public class SawHookBot extends AdvancedRobot {

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
            // Alinha o canhão com o inimigo e dispara forte
            setTurnGunRight(getHeading() - getGunHeading() + e.getBearing());
            fire(3); // potência máxima
            ahead(distance / 2);
        } else {
            // "Serras horizontais" – ataque giratório médio
            setTurnGunRight(getHeading() - getGunHeading() + e.getBearing());
            fire(1.5);
            // Gira o corpo simulando as serras
            setTurnRight(30);
            ahead(30);
        }
    }

    // Quando é atingido por uma bala
    public void onHitByBullet(HitByBulletEvent e) {
        // Recuar e girar (evasão)
        back(50);
        setTurnRight(60);
    }

    // Quando bate em uma parede
    public void onHitWall(HitWallEvent e) {
        // Afasta-se e muda de direção
        back(80);
        turnRight(90);
    }
}
