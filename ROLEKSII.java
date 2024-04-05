import robocode.*;
//import java.awt.Color;

// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html

/**
 * ROLEKSII - a robot by (your name here)
 */
public class ROLEKSII extends Robot
{
	 
    // Variables to store enemy information
    private double enemyBearing;
    private double enemyDistance;
    private double enemyHeading;
    private double enemyVelocity;
    private long lastScanTime;

    public void run() {
        // Initialization code
        while (true) {
            // Move robot
            ahead(100);
            turnGunRight(360);
        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        // Update enemy information
        enemyBearing = e.getBearing();
        enemyDistance = e.getDistance();
        enemyHeading = e.getHeadingRadians();
        enemyVelocity = e.getVelocity();
        lastScanTime = getTime();

        // Predict enemy position and aim gun
        predictEnemyPosition();
    }

    private void predictEnemyPosition() {
        // Calculate time to reach predicted position
        long currentTime = getTime();
        long deltaTime = currentTime - lastScanTime;
        double predictedDistance = deltaTime * enemyVelocity;

        // Predict enemy position
        double enemyX = getX() + Math.sin(enemyHeading) * (enemyDistance + predictedDistance);
        double enemyY = getY() + Math.cos(enemyHeading) * (enemyDistance + predictedDistance);

        // Calculate angle to enemy
        double angleToEnemy = Math.atan2(enemyX - getX(), enemyY - getY());
        
        // Turn gun to aim at predicted position
        turnGunRightRadians(Utils.normalRelativeAngle(angleToEnemy - getGunHeadingRadians()));
    }

    private long getTime() {
        return getTime() / 1000;
    }
}
