package org.usfirst.frc.team6624.robot.customClasses;

/**
 * Class used to encapsulate (x,y) coordinates
 */
public class Vector2 {
	public double X;
	public double Y;
	
	public Vector2(double x, double y)  {
		this.X = x;
		this.Y = y;
	}
	
	@Override
	public String toString() {
		return "(" + X + ", " + Y + ")";
	}
	
	public static Vector2 Zero = new Vector2(0, 0);
	
	/**
	 * Returns the distance between the 2 provided Vector2s
	 */
	public static double getDistance(Vector2 v1, Vector2 v2) {
		return Math.sqrt( Math.pow(v2.X - v1.X, 2) + Math.pow(v2.Y - v1.Y, 2) );
	}
}
