import robocode.*;

// CircleBot - a robot that moves in a big circle and shoots enemies
public class CircleBot extends AdvancedRobot {

    // run method - main method for the robot
    public void run() {
        // Set the radar to turn right infinitely
        setTurnRadarRight(Double.POSITIVE_INFINITY);

        // Start moving in a big circle
        while (true) {
            // Move forward
            ahead(100);

            // Turn right 10 degrees
            turnRight(10);
        }
    }

    // onScannedRobot method - called when an enemy is detected
    public void onScannedRobot(ScannedRobotEvent e) {
        // Calculate the angle to the enemy
        double angleToEnemy = getHeading() + e.getBearing();

        // Calculate the gun turn angle to face the enemy
        double gunTurnAngle = Utils.normalRelativeAngleDegrees(angleToEnemy - getGunHeading());

        // Turn the gun to face the enemy
        turnGunRight(gunTurnAngle);

        // Fire at the enemy with maximum power
        fire(3);
    }
}
