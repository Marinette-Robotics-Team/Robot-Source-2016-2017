package org.usfirst.frc.team6634.robot.customClasses;

import java.util.ArrayList;

public class Path {
	
	final double DEGREE_THRESHOLD = 5;
	
	ArrayList<Vector2> path = new ArrayList<Vector2>();
	
	public void add(Vector2 position) {
		path.add(position);
	}
	
	public Vector2 getNext() {
		Vector2 returnedPosition = path.get(0);
		path.remove(0);
		
		return returnedPosition;
	}
	
	public int size() {
		return path.size();
	}
	
	public int simplify() {
		for (int i = 1; i < path.size() - 1; i++) { // exclude end points, as they cannot be removed.
			Vector2 previousPos = path.get(i - 1);
			Vector2 currentPos = path.get(i);
			Vector2 nextPos = path.get(i + 1);
			
			double incomingAngle = getAngle(previousPos, currentPos);
			double outgoingAngle = getAngle(currentPos, nextPos);
			
			if (Math.abs(incomingAngle - outgoingAngle) <= DEGREE_THRESHOLD) {
				path.remove(i);
			}
		}
		
		return path.size();
	}
	
	private double getAngle(Vector2 v1, Vector2 v2) {
		return Math.toDegrees( Math.atan2(v2.Y - v1.Y, v2.X - v1.X) );
	}
}
