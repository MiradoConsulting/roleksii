import robocode.*;
import static robocode.util.Utils.normalRelativeAngleDegrees;

// Extend the AdvancedRobot class from Robocode
public class ROLEKSII extends AdvancedRobot {

    public void run() {
        // Main robot loop
        while (true) {
            // Adjust radar and gun independently of robot movement
            setAdjustRadarForGunTurn(true);
            setAdjustGunForRobotTurn(true);
            
            // Turn the radar to scan for enemies
            // turnRadarRight(360);

              // Move forward
            ahead(100);

            // Turn right 90 degrees
            turnRight(90);
        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        double enemyAbsoluteBearing = getHeadingRadians() + e.getBearingRadians();
        double enemyDistance = e.getDistance();
        double enemyHeading = e.getHeadingRadians();
        double enemyVelocity = e.getVelocity();

        // Estimate time for bullet to reach the enemy
        double bulletPower = Math.min(3, 400 / enemyDistance);
        double bulletSpeed = 20 - bulletPower * 3;
        double time = enemyDistance / bulletSpeed;
        
        // Predict the enemy's future position
        double futureX = getX() + Math.sin(enemyAbsoluteBearing) * enemyDistance + Math.sin(enemyHeading) * enemyVelocity * time;
        double futureY = getY() + Math.cos(enemyAbsoluteBearing) * enemyDistance + Math.cos(enemyHeading) * enemyVelocity * time;
        
        // Calculate gun turn to predicted position
        double gunTurn = normalRelativeAngleDegrees(Math.atan2(futureX - getX(), futureY - getY()) * 180 / Math.PI - getGunHeading());

        // Adjust the gun and fire
        turnGunRight(gunTurn);
        if (getGunHeat() == 0 && Math.abs(getGunTurnRemaining()) < 10) {
            fire(bulletPower);
        }
        
        // Radar lock on target
        double radarTurn = getHeadingRadians() + e.getBearingRadians() - getRadarHeadingRadians();
        setTurnRadarRightRadians(1.9 * normalRelativeAngleDegrees(radarTurn));
    }
}