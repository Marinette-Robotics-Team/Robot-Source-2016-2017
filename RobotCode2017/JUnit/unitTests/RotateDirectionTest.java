package org.usfirst.frc.team6624.robot.unitTests;

import static org.junit.Assert.*;

import org.usfirst.frc.team6624.robot.Robot;
import org.usfirst.frc.team6624.robot.subsystems.Gyroscope;

public class RotateDirectionTest {

	@org.junit.Test
	public void test() {
		
		int[][][] data = {
				{
					{ 0, 90 },
					{1}
				},
				{
					{ 120, 30 },
					{-1}
				},
				{
					{ 10, 350 },
					{-1}
				},
				{
					{270, 20},
					{1}
				}
		};
		
		for (int i = 0; i < data.length; i++) {
			double current = data[i][0][0];
			double goal = data[i][0][1];
			double direction = data[i][1][0];
			assertEquals(current + " => " + goal + " = " + direction, direction, getDir(current, goal), 0.01);
		}
	}
	
	private int getDir(double current, double goal) {
		
		int rotateDirection;
		
		if (Math.max(goal, current) - Math.min(goal, current) <= 180) {
			if (Math.min(goal, current) == current) {
				rotateDirection = 1;
			}
			else {
				rotateDirection = -1;
			}
		}
		else {
			if (Math.min(goal, current) == current)  {
				rotateDirection = -1;
			}
			else {
				rotateDirection = 1;
			}
		}
		
		return rotateDirection;
		
	}

}
