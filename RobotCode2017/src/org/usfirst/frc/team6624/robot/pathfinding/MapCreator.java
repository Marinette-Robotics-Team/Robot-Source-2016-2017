package org.usfirst.frc.team6624.robot.pathfinding;

import java.util.ArrayList;

import org.usfirst.frc.team6624.robot.customClasses.MapObject;
import org.usfirst.frc.team6624.robot.customClasses.Vector2;
import org.xguzm.pathfinding.grid.GridCell;

/**
 * This class handles the creation of the pathfinding map
 *
 */
public class MapCreator {
	
	//map constants
	public static final int MAP_WIDTH = 10;
	public static final int MAP_HEIGHT = 10;
	
	public static final double BOX_SIZE = 4.25; //this should be the size of the robot in feet
	
	
	static GridCell[][] map = new GridCell[MAP_WIDTH][MAP_HEIGHT];
	

	
	/**
	 * Generates the filed obstacle map with origin in lowr left
	 * @return Obstacle map of GridCell
	 */
	public static GridCell[][] createMap() {
		//list of objects
		
		/*ArrayList<Vector2[]> object1List = new ArrayList<Vector2[]>();
		object1List.add(new Vector2[] { new Vector2(3, 3), new Vector2(4, 5) });
		object1List.add(new Vector2[] { new Vector2(4, 2), new Vector2(4, 0)} );
		
		MapObject object1 = new MapObject(object1List);
		
		
		ArrayList<Vector2[]> object2List = new ArrayList<Vector2[]>();
		object2List.add(new Vector2[] { new Vector2(6, 7), new Vector2(6, 8) });
		object2List.add(new Vector2[] { new Vector2(7, 8), new Vector2(8, 8) });
		object2List.add(new Vector2[] { new Vector2(6, 6), new Vector2(5, 6)} );
		
		MapObject object2 = new MapObject(object2List);
		
		
		//compile list of map objects
		
		ArrayList<MapObject> objects = new ArrayList<MapObject>();
		objects.add(object1);
		objects.add(object2);
		
		*/
		
		ArrayList<Vector2[]> object1List = new ArrayList<Vector2[]>();
		//object1List.add(new Vector2[] { new Vector2(2, 3), new Vector2(3, 3) } );
		//object1List.add(new Vector2[] { new Vector2(3, 4), new Vector2(4, 4) } );
		//object1List.add(new Vector2[] { new Vector2(4, 5), new Vector2(5, 5) } );
		//object1List.add(new Vector2[] { new Vector2(5, 6), new Vector2(6, 6) } );
		//object1List.add(new Vector2[] { new Vector2(6, 7), new Vector2(7, 7) } );
		
		object1List.add(new Vector2[] { new Vector2(3, 9), new Vector2(3, 6) } );
		object1List.add(new Vector2[] { new Vector2(2, 0), new Vector2(2, 3) } );
		object1List.add(new Vector2[] { new Vector2(2, 3), new Vector2(4, 3) } );
		object1List.add(new Vector2[] { new Vector2(4, 3), new Vector2(3, 2) } );
		object1List.add(new Vector2[] { new Vector2(5, 1), new Vector2(5, 1) } );
		object1List.add(new Vector2[] { new Vector2(6, 2), new Vector2(6, 5) } );
		object1List.add(new Vector2[] { new Vector2(8, 7), new Vector2(8, 7) } );
		object1List.add(new Vector2[] { new Vector2(9, 8), new Vector2(9, 8) } );
		
		MapObject object1 = new MapObject(object1List);
		
		//compile list of map objects
		
		ArrayList<MapObject> objects = new ArrayList<MapObject>();
		objects.add(object1);
		
		
		
		//Don't touch anything below this line
		//-----------------------------------------------------
		
		//add empty objects to Grid
		for (int y = 0; y < MAP_HEIGHT; y++) {
			for (int x = 0; x < MAP_WIDTH; x++) {
				//y inverted to make origin in lower left
				map[x][y] = new GridCell(x, y, true);
			}
		}
		
		//add solid objects
		for (MapObject obj : objects) {
			for (Vector2[] region : obj.regions) {
				for (int y = (int) Math.min(region[0].Y, region[1].Y); y <= (int) Math.max(region[0].Y, region[1].Y); y++) {
					for (int x = (int) Math.min(region[0].X, region[1].X); x <= (int) Math.max(region[0].X, region[1].X); x++) {
						map[x][y] = new GridCell(x, y, false);
					}
				}
			}
		}
		
		//debug printout
		//printed in reverse (because this library is weird)
		for (int y = 0; y < MAP_HEIGHT; y++) {
			System.out.print("[");
			for (int x = 0; x < MAP_WIDTH; x++) { 
				// y inverted for lower left origin
				System.out.print(getIntFromBool(map[x][MAP_HEIGHT - y - 1].isWalkable()) + ",");
			}
			System.out.println("]\n");
		}
		
		return map;
		
	}
	
	private static int getIntFromBool(Boolean bool) {
		if (bool)
			return 1;
		else
			return 0;
	}
	
}
