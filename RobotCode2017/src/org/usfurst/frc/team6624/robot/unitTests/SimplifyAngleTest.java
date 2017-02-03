package org.usfurst.frc.team6624.robot.unitTests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.usfirst.frc.team6624.robot.subsystems.Gyroscope;

public class SimplifyAngleTest {

	@Test
	public void testSimplifyAngle() {
		
		assertEquals("360 => 0", 0, Gyroscope.simplifyAngle(360), 0.01);
		assertEquals("-360 => 0", 0, Gyroscope.simplifyAngle(-360), 0.01);
		assertEquals("45 => 45", 45, Gyroscope.simplifyAngle(45), 0.01);
		assertEquals("720 => 0", 0, Gyroscope.simplifyAngle(720), 0.01);
		assertEquals("-30 => 330", 330, Gyroscope.simplifyAngle(-30), 0.01);
		assertEquals("770 => 50", 50, Gyroscope.simplifyAngle(770), 0.01);
		assertEquals("181 => 181", 181, Gyroscope.simplifyAngle(181), 0.01);
	}

}
