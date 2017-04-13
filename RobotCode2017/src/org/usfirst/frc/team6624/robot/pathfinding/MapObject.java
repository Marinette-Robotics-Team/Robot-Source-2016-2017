package org.usfirst.frc.team6624.robot.pathfinding;

import java.util.ArrayList;

import org.usfirst.frc.team6624.robot.customClasses.Vector2;

public class MapObject {
	
	public ArrayList<Vector2[]> regions;
	
	public MapObject(ArrayList<Vector2[]> regions) {
		this.regions = regions;
	}
	
}
