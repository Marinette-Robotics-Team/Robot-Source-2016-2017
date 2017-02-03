package org.usfurst.frc.team6624.robot.unitTests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.usfirst.frc.team6624.robot.subsystems.Gyroscope;

public class AnglesEqualTest {

	final double PERCENT_ERROR = 0.05;
	
	@Test
	public void testAnglesEqual() {
		
		assertEquals("360 = 360", true, Gyroscope.anglesEqual(360, 360, PERCENT_ERROR));
		assertEquals("25 = 143", false, Gyroscope.anglesEqual(25, 143, PERCENT_ERROR));
		assertEquals("0 = 360", true, Gyroscope.anglesEqual(0, 360, PERCENT_ERROR));
		assertEquals("46 = 45", true, Gyroscope.anglesEqual(46, 45, PERCENT_ERROR));
		assertEquals("20 = 25", false, Gyroscope.anglesEqual(20, 25, PERCENT_ERROR));
		assertEquals("370 = 20", false, Gyroscope.anglesEqual(370, 20, PERCENT_ERROR));
	}

}
