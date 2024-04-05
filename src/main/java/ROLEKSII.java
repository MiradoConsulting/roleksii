import robocode.DeathEvent;
import robocode.Robot;
import robocode.ScannedRobotEvent;
import static robocode.util.Utils.normalRelativeAngleDegrees;

import java.awt.*;


public class ROLEKSII extends Robot {

	public void run() {
		setBodyColor(Color.black);
		setGunColor(Color.red);
		setRadarColor(Color.green);
		setBulletColor(Color.yellow);
		setScanColor(Color.red);

		// Move to a corner
		goCorner();

		int gunIncrement = 3;

		while (true) {
			turnLeft(90);
			ahead(5000);
		}
	}

	public void goCorner() {
		turnRight(normalRelativeAngleDegrees(0 - getHeading()));
		// Move to that wall
		ahead(5000);
		// Turn to face the corner
		turnLeft(90);
		// Move to the corner
		ahead(5000);
		// Turn gun to starting point
		turnGunLeft(90);
	}

	public void onScannedRobot(ScannedRobotEvent e) {
		smartFire(e.getDistance());
	}

	public void smartFire(double robotDistance) {
		if (robotDistance > 400) {
			return;
		} else if (robotDistance > 200 || getEnergy() < 15) {
			fire(1);
		} else if (robotDistance > 50) {
			fire(2);
		} else {
			fire(3);
		}
	}
}