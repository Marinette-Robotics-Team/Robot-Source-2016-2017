package org.usfirst.frc.team6624.robot.customClasses;

import java.util.ArrayList;

public class Path {
	
	final double DEGREE_THRESHOLD = 5;
	
	ArrayList<Vector2> path = new ArrayList<Vector2>();
	
	public void add(Vector2 position) {
		path.add(position);
	}
	
	public Vector2 get(int i) {
		return path.get(i);
	}
	
	public int size() {
		return path.size();
	}
	
	public int simplify(Vector2 startingPos) {
		
		System.out.println("Loopin'from 0 to " + (path.size() - 1));
		
		for (int i = 0; i < path.size() - 1; i++) { // exclude end point, as it cannot be removed.
			
			System.out.println("i: " + i);
			
			System.out.println("Path: " + path.get(i));
			
			Vector2 previousPos;
			Vector2 currentPos = path.get(i);
			Vector2 nextPos = path.get(i + 1);
			
			if (i != 0) { 
				previousPos = path.get(i - 1);
			}
			else {
				previousPos = startingPos;
			}
			
			double incomingAngle = getAngle(previousPos, currentPos);
			double outgoingAngle = getAngle(currentPos, nextPos);
			
			System.out.println("INC: " + incomingAngle);
			System.out.println("OUT: " + outgoingAngle);
			
			if (Math.abs(incomingAngle - outgoingAngle) <= DEGREE_THRESHOLD) {
				
				System.out.println("PATH BURNED PAINFULLY: " + path.get(i));
				
				path.remove(i);
				
				i -= 1;
			}
			else {
				System.out.println("PATH SAVED (for now): " + path.get(i));
			}
			
			
		}
		
		return path.size();
	}
	
	private double getAngle(Vector2 v1, Vector2 v2) {
		return Math.toDegrees( Math.atan2(v2.Y - v1.Y, v2.X - v1.X) );
	}
	
	@Override
	public String toString() {
		String ret = "{ ";
		for (Vector2 vector : path) {
			ret += vector.toString() + ", ";
		}
		ret += "}";
		
		return ret;
	}
}
