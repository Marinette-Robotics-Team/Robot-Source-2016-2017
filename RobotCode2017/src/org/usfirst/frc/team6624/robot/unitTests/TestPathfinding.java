package org.usfirst.frc.team6624.robot.unitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import org.usfirst.frc.team6624.robot.commands.PathfindToCoords;
import org.usfirst.frc.team6634.robot.customClasses.Vector2;
import org.xguzm.pathfinding.grid.GridCell;

public class TestPathfinding {

	@Test
	public void test() {
		
		ArrayList<GridCell> list = PathfindToCoords.getPathLocs(new Vector2(1,8),  new Vector2(8, 1));
		
		for (GridCell cell : list) {
				System.out.print(cell + ",");
		}
		
		ArrayList<GridCell> pointList = PathfindToCoords.removeUnnecessaryPoints(list);
		
		
		System.out.println();
		
		for (GridCell cell: pointList) {
			System.out.print(cell + ",");
		}
		
		//fail("Not yet implemented");
	}

}
