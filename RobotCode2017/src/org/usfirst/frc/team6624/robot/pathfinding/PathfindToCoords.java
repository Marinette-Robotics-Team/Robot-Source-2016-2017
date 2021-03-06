package org.usfirst.frc.team6624.robot.pathfinding;

import java.util.ArrayList;

import org.usfirst.frc.team6624.robot.Robot;
import org.usfirst.frc.team6624.robot.commands.drive.DriveToCoords;
import org.usfirst.frc.team6624.robot.commands.drive.DriveTurn;
import org.usfirst.frc.team6624.robot.customClasses.Vector2;
import org.usfirst.frc.team6624.robot.libs.LinearRegression;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.xguzm.pathfinding.grid.GridCell;
import org.xguzm.pathfinding.grid.NavigationGrid;
import org.xguzm.pathfinding.grid.finders.AStarGridFinder;
import org.xguzm.pathfinding.grid.finders.GridFinderOptions;

/**
 *
 * This function takes a Vector2 for a final destination, and uses the environment set in MapCreator to 
 * pathfind to the Vector2
 * 
 * 
 * It break and A* path into a series of line sgments and drives along those segments.
 *
 *
 */
public class PathfindToCoords extends CommandGroup {
	
	static final float R_SQUARED_MIN = 0.8f;
	
	Vector2 currentPosition;

    public PathfindToCoords(Vector2 destination, double finalRotation) {
    	
    	requires(Robot.drive);
    	requires(Robot.gyroscope);
    	
    	this.currentPosition = Robot.drive.position;
    	
    	
    	//convert current position and destination to grid coords
    	Vector2 destinationGrid = new Vector2((int)(destination.X / MapCreator.BOX_SIZE), (int)(destination.Y / MapCreator.BOX_SIZE));
    	Vector2 currentPositionGrid = new Vector2((int)(currentPosition.X / MapCreator.BOX_SIZE), (int)(currentPosition.Y / MapCreator.BOX_SIZE));
    	
    	//line up with nearest A* square
    	addSequential(new DriveToCoords(new Vector2(currentPositionGrid.X * MapCreator.BOX_SIZE, currentPosition.Y * MapCreator.BOX_SIZE)));
    	
    	//get and goto points along path
    	ArrayList<GridCell> gotoPoints = removeUnnecessaryPoints( getPathLocs(currentPositionGrid, destinationGrid) );
    	
    	for (GridCell cell : gotoPoints) {
    		addSequential(new DriveToCoords(new Vector2 (MapCreator.BOX_SIZE * cell.x, MapCreator.BOX_SIZE * cell.y)));
    	}
    	
    	//go to final position
    	addSequential(new DriveToCoords(destination));
    	
    	//reset to final angle
    	//TODO: FIX PID VALUES BEFORE TESTING
    	addSequential(new DriveTurn(finalRotation));

    }
    
    //gets points along best path to destination
    public static ArrayList<GridCell> getPathLocs(Vector2 currentPosition, Vector2 finalPosition) {
    	//based on readme for pathfinding library:
    	//https://github.com/xaguzman/pathfinding
    	
    	NavigationGrid<GridCell> navGrid = new NavigationGrid<GridCell>(Robot.drive.map, true);  
    	
    	//pathfinder options
    	GridFinderOptions opt = new GridFinderOptions();
    	opt.allowDiagonal = false;
    	
    	AStarGridFinder<GridCell> finder = new AStarGridFinder<GridCell>(GridCell.class, opt);
    	
    	//return list of cells in path
    	return (ArrayList<GridCell>) finder.findPath((int)currentPosition.X, (int)currentPosition.Y, (int)finalPosition.X, (int)finalPosition.Y, navGrid);
    	
    	
    }
    
    //filters out unnecessary points using R^2 regression to simplify path
    public static ArrayList<GridCell> removeUnnecessaryPoints(ArrayList<GridCell> pathLocs) {
    	ArrayList<GridCell> finalPoints = new ArrayList<GridCell>();
    	
    	ArrayList<GridCell> currentRegression = new ArrayList<GridCell>();
    	
    	for (GridCell cell: pathLocs) {
    		currentRegression.add(cell);
	    	
	    	double[] x_coords = new double[currentRegression.size()];
	    	double[] y_coords = new double[currentRegression.size()];
	    	
	    	for (int i = 0; i < currentRegression.size(); i++) {
	    		x_coords[i] = currentRegression.get(i).x;
	    		y_coords[i] = currentRegression.get(i).y;
	    	}
	    	
	    	LinearRegression reg = new LinearRegression(x_coords, y_coords);
	    	
	    	if (reg.R2() < R_SQUARED_MIN) {
	    		//add second from final cell to list (it was the last point within r^2)
	    		finalPoints.add(currentRegression.get(currentRegression.size() - 2));
	    		
	    		//store second to last and last (will be needed for next leg of calcuation)
	    		GridCell lastCell = currentRegression.get(currentRegression.size() - 1);
	    		GridCell secondToLastCell = currentRegression.get(currentRegression.size() - 2);
	    		
	    		//reset currentRegression
	    		currentRegression = new ArrayList<GridCell>();
	    		
	    		//add second to last and last back in
	    		currentRegression.add(secondToLastCell);
	    		currentRegression.add(lastCell);
	    	}
    	
    	}
    	
    	//make sure last point is added in
    	if (finalPoints.get(finalPoints.size() - 1) != pathLocs.get(pathLocs.size() - 1)) {
    		finalPoints.add(pathLocs.get(pathLocs.size() - 1));
    	}
    	
    	return finalPoints;
    }
}
