import robocode.*;

public class OLEKSII extends Robot {

    public void run() {
        // Initialization code
        while (true) {
            // Move robot
            ahead(100);
            // Turn gun
            turnGunRight(360);
            // Turn radar
            turnRadarRight(360);
        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        // Fire when an enemy is detected
        fire(1);
    }

    public void onHitByBullet(HitByBulletEvent e) {
        // Turn robot to avoid being hit by bullets
        turnRight(90 - e.getBearing());
    }

    public void onHitWall(HitWallEvent e) {
        // Turn robot when hitting a wall
        turnRight(90);
    }
}
