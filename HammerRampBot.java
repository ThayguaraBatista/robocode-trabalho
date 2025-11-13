package sample;
import robocode.*;
import java.awt.Color;

/**
 * HammerRampBot - Robô com um martelo vertical e uma rampa frontal
 * Baseado no conceito enviado por Pitoco.
 * Simula virar inimigos e golpeá-los com o martelo.
 */
public class HammerRampBot extends AdvancedRobot {

    public void run() {
        // Aparência: corpo metálico com martelo dourado e rampa laranja
        setBodyColor(Color.gray);
        setGunColor(Color.orange);
        setRadarColor(Color.yellow);
        setBulletColor(Color.red);
        setScanColor(Color.white);

        // Loop principal: varredura e movimentação
        while (true) {
            setTurnRadarRight(360); // varredura completa
            ahead(100);
            turnRight(30);
        }
    }

    // Quando detecta um inimigo
    public void onScannedRobot(ScannedRobotEvent e) {
        double distance = e.getDistance();

        // Alinha o canhão e corpo com o inimigo
        double turnGunAmt = getHeading() - getGunHeading() + e.getBearing();
        setTurnGunRight(turnGunAmt);
        setTurnRight(e.getBearing());

        if (distance < 120) {
            // --- ATAQUE DE RAMPA ---
            // Simula empurrar e virar o inimigo
            setAhead(50);
            fire(1); // tiro leve durante o empurrão
            execute();

            // --- GOLPE DE MARTELO ---
            // recua e dispara com potência máxima
            back(30);
            fire(3); // o "martelo" desce com força
            ahead(20);

        } else if (distance < 300) {
            // Aproxima-se para o ataque
            setAhead(distance / 2);
            fire(1.5);
        } else {
            // Muito longe: rastreia e reposiciona
            setTurnRadarRight(30);
        }
    }

    // Quando é atingido por uma bala
    public void onHitByBullet(HitByBulletEvent e) {
        // Reação: recua e gira, simulando evasão
        back(50);
        turnRight(45);
    }

    // Quando colide com um robô
    public void onHitRobot(HitRobotEvent e) {
        if (e.isMyFault()) {
            // Se bateu no inimigo, simula levantar ele com a rampa
            fire(2);
            back(40);
        }
    }

    // Quando colide com uma parede
    public void onHitWall(HitWallEvent e) {
        back(80);
        turnRight(90);
    }
}