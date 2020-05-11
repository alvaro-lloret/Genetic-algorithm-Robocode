package engine;

import java.util.Random;

import robocode.control.*;

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
		
		
		// Create the robots and place them in a random position
		RobotSpecification[] modelRobots = engine.getLocalRepository ("supersample.SuperRamFire*, supersample.OptimalSuperTracker*");
		RobotSpecification[] existingRobots = new RobotSpecification[2];
		RobotSetup[] robotSetups = new RobotSetup[2];
		
		long seed = 1;
		Random rnd = new Random(seed);
		
		/** Robot1: SuperRamFire **/
		
		int startRow1 = rnd.nextInt(NUMTILEROWS);
		int startCol1 = rnd.nextInt(NUMTILECOLS);
		
		double InitialRobot1Row = startRow1 * PIXELSPERTILE + (PIXELSPERTILE/2);
		double InitialRobot1Col = startCol1 * PIXELSPERTILE + (PIXELSPERTILE/2);
		
		existingRobots[0] = modelRobots[0];
		robotSetups[0] = new RobotSetup(InitialRobot1Row, InitialRobot1Col, 0.0);
		
		
		/** Robot2: OptimalSuperTracker **/
		
		int startRow2 = rnd.nextInt(NUMTILEROWS);
		int startCol2 = rnd.nextInt(NUMTILECOLS);
		
		double InitialRobot2Row = startRow2 * PIXELSPERTILE + (PIXELSPERTILE/2);
		double InitialRobot2Col = startCol2 * PIXELSPERTILE + (PIXELSPERTILE/2);
		
		existingRobots[1] = modelRobots[1];
		robotSetups[1] = new RobotSetup(InitialRobot2Row, InitialRobot2Col, 0.0);
		
		
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