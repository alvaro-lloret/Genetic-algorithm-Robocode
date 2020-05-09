package engine;

import java.util.ArrayList;

import robocode.control.BattleSpecification;
import robocode.control.BattlefieldSpecification;
import robocode.control.RobocodeEngine;
import robocode.control.RobotSetup;
import robocode.control.RobotSpecification;

public class Launcher {
	
	public final static int PIXELSPERTILE = 64;
    public final static int NUMTILEROWS = 13;
    public final static int NUMTILECOLS = 10;
    
	public static void main(String[] args) {
		// Create the RobocodeEngine
		RobocodeEngine engine = new RobocodeEngine(new java.io.File("C:/Robocode"));
																	// Run from C:/Robocode 
		 
		// Show the Robocode battle view          
		engine.setVisible(true); 
		 
		// Create the battlefield
		int NumPixelRows = NUMTILEROWS * PIXELSPERTILE;
		int NumPixelCols = NUMTILECOLS * PIXELSPERTILE;		 
		
		BattlefieldSpecification battlefield = new BattlefieldSpecification(NumPixelRows, NumPixelCols); 
																				// 832x640 
		 
		// Setup battle parameters
		int numberOfRounds = 5;
		long inactivityTime = 10000000;
		double gunCoolingRate = 1.0;
		int sentryBorderSize = 50;
		boolean hideEnemyNames = false;
		
		
		/** Create obstacles and place them at random so that no pair of obstacles are at the same position **/
		RobotSpecification[] modelRobots = engine.getLocalRepository ("supersample.SuperRamFire*, supersample.OptimalSuperTracker*");
		RobotSpecification[] existingRobots = new RobotSpecification[2];
		RobotSetup[] robotSetups = new RobotSetup[2];
				
		double InitialObstacleRow, InitialObstacleCol;
		
		existingRobots[NdxObstacle] = modelRobots[0];
		robotSetups[NdxObstacle]= new RobotSetup(InitialObstacleRow, InitialObstacleCol, 0.0);
		
		/** Create the agent and place it in a random position without obstacle **/
		existingRobots[NumObstacles] = modelRobots[1];
		double InitialAgentRow = startRow * PIXELSPERTILE + (PIXELSPERTILE/2);   
		double InitialAgentCol = startCol * PIXELSPERTILE + (PIXELSPERTILE/2);   
		robotSetups[NumObstacles] = new RobotSetup(InitialAgentRow, InitialAgentCol, 0.0); 
				
		
		/** Create and run the battle **/ 
		BattleSpecification battleSpec = new BattleSpecification(battlefield, numberOfRounds,
																	inactivityTime, gunCoolingRate,
																		sentryBorderSize, hideEnemyNames,
																			existingRobots, robotSetups);
		 
		
		
		// Run our specified battle and let it run till it is over 
		engine.runBattle(battleSpec, true); // waits till the battle finishes 
		 
		// Cleanup our RobocodeEngine
		engine.close();
		 
		// Make sure that the Java VM is shut down properly 
		System.exit(0); 

	}

}
