package Game1024;

import java.util.*;

public class Game1024 {
	public static int gameScore;
	public static Boolean isGameWon = false;
	
	public void getGridStructure(int [][] grid) {
		 
		String space = " ";
		
		for(int i=0; i<grid.length; i++) {
			for(int j=0; j<grid[i].length; j++){
				if(j==0) {
					System.out.print("\n+------+");
				} else {
					System.out.print("------+");
				}
			}

			System.out.println();
			for(int j=0; j<grid[i].length; j++) {
				if(grid[i][j] == 0) {
					if(j==0) {
						System.out.print("|      |");
					}
					else {
						System.out.print("      |");
					}
				} else {
					int noOfDigits = (int)(Math.log10(grid[i][j])+1);
					int noOfSpaces = 4 - noOfDigits;
					if(j==0) {
						System.out.print("| " + space.repeat(noOfSpaces) + grid[i][j] + " |");
					}
					else {
						System.out.print(" " + space.repeat(noOfSpaces) + grid[i][j] + " |");
					}
				}
			}
		}
		
		for(int j=0;j<grid[0].length;j++) {
			if(j==0) {
				System.out.print("\n+------+");
			} else {
				System.out.print("------+");
			}
		}
		System.out.println();
	}
	
	private static int randomNumber(int dimentions) {
		Random random = new Random();
		return random.nextInt(dimentions);
	}
	
	public static String displayGameOptions(int [][] grid) {
		Scanner in = new Scanner(System.in);
		System.out.println("Choose from these options below to play the game");
		String nextStep;
		
		boolean correctEntryFound = false;
		do {
			System.out.println("W for UP");
			System.out.println("S for DOWN");
			System.out.println("A for LEFT");
			System.out.println("D for RIGHT");
			
			nextStep = in.nextLine();
			
			// IF USER ENTERS THE FOLLOWING
			if(nextStep.equals("W") || nextStep.equals("S") || nextStep.equals("A") || nextStep.equals("D")) {
				correctEntryFound = true;
			} else {
				// draw the grid and repeat (TASK)
				Game1024 game = new Game1024();
				game.getGridStructure(grid);
			}
			
		} while(correctEntryFound == false);
		
		return nextStep;
	}
	
	// adding scores
	public static void updateScore(int scoreFromMove) {
		if(scoreFromMove == 1024 && !isGameWon) {
			isGameWon = true;
			System.out.println("You WON!!");
		}
		gameScore += scoreFromMove;
	}
	
	// moving up function
	public static int [][] moveUp(int [][] grid) {
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid.length; j++) {
				int currentValue = grid[i][j];
				
				if(currentValue != 0) {
					if(i != 0) { // boundary above
						for(int k = i-1; k >= 0; k--) {
							if(grid[k][j] != 0) {
								if(currentValue == grid[k][j]) {
									// multiply the current value by 2
									grid[k][j] = 2 * currentValue;
									currentValue = grid[k][j];
									// set the previous tile to empty
									grid[k+1][j] = 0;
									updateScore(currentValue);// updating score
								} else {
									currentValue = -1;
								}
							} else {
								grid[k][j] = currentValue;
								grid[k+1][j] = 0;
							}
						}
					}
				}
			}
		}
		return grid;
	}
	
	// function to move down
	public static int [][] moveDown(int [][] grid) {
		// looping through the grid
		for(int i = grid.length-1; i >= 0; i--) {
			//System.out.println("i "+i);
			for(int j = 0; j < grid.length; j++) {
				//System.out.println("j "+j);
				int currentValue = grid[i][j];
				//System.out.println("curr "+currentValue);
				
				if(currentValue != 0) {
					if(i != grid.length-1) { // boundary down
						for(int k = i+1; k < grid.length; k++) {
							//System.out.println("k "+k);
							if(grid[k][j] != 0) { 
								// if value found
								if(currentValue == grid[k][j]) {
									//System.out.println("NEW curr "+currentValue);
									// double the value
									grid[k][j] = 2 * currentValue;
									currentValue = grid[k][j];
									//System.out.println("DOUBLED curr "+currentValue);
									grid[k-1][j] = 0; // make previous cell empty
									updateScore(currentValue); // updating score
								} else {
									currentValue = -1;
								}
								
								
							} else {
								grid[k][j] = currentValue;
								grid[k-1][j] = 0;
							}
						}
					}
				}
		   }
		}
		return grid;
	}
	// FUNCTION TO MOVE LEFT
	public static int [][] moveLeft(int [][] grid) {
		for(int j = 0; j < grid.length; j++) {
			for(int i = 0; i < grid.length; i++) {
				int currentValue = grid[i][j];
				
				if(currentValue != 0) {
					if(j != 0) { // boundary left
						for(int k = j-1; k >= 0; k--) {
							if(grid[i][k] != 0) { 
								if(currentValue == grid[i][k]) {
									grid[i][k] = 2 * currentValue;
									currentValue = grid[i][k];
									grid[i][k+1] = 0;
									updateScore(currentValue);
								} else {
									currentValue = -1;
								}
							} else {
								grid[i][k] = currentValue;
								grid[i][k+1] = 0;
							}
						}
					}
				}
		   }
	    }
		return grid;
	}
	
	// function to move right
	public static int [][] moveRight(int [][] grid) {
		for(int j = grid.length-1; j >= 0; j--) {
			for(int i = 0; i < grid.length; i++) {
				int currentValue = grid[i][j];
				
				if(currentValue != 0) {
					if(j != grid.length-1) { // boundary right
						for(int k = j+1; k < grid.length; k++) {
							if(grid[i][k] != 0) {
								if(currentValue == grid[i][k]) {
									grid[i][k] = 2 * currentValue;
									currentValue = grid[i][k];
									grid[i][k-1] = 0; // 
									updateScore(currentValue);
								} else {
									currentValue = -1;
								}
							} else {
								grid[i][k] = currentValue;
								grid[i][k-1] = 0; //
							}
						}
					}
				}
		   }
	    }
		return grid;
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		System.out.println("Welcome To The 2048 Game");
		System.out.println("Enter a number between 4 to 8: ");
		
		int dimentions;
		boolean validDimention = false;
		
		do {
			dimentions = in.nextInt();
			
			if(dimentions >= 4 && dimentions <= 8) {
				validDimention = true;
			} else {
				System.out.println("Enter your grid dimentions again:");
			}
		} while(validDimention == false);
		
		int[][] grid = new int [dimentions][dimentions];
		int gameJump = 1;
		
		Game1024 game = new Game1024();
		
		int i = Game1024.randomNumber(dimentions);
		int j = Game1024.randomNumber(dimentions);
		grid[i][j] = gameJump;  // GAME JUMP
		
		boolean foundPosition = false;
		while(foundPosition == false) {
			i = Game1024.randomNumber(dimentions);
			j = Game1024.randomNumber(dimentions);
			
			if(grid[i][j] == 0) {
				grid[i][j] = gameJump; // GAME JUMP
				foundPosition = true;
			}
		}
		game.getGridStructure(grid);
		
		boolean gameEnd = false;
		while(!gameEnd) {
			String nextStep = displayGameOptions(grid);
			
			if(nextStep.equals("W")) {
				grid = moveUp(grid);
			}
			if(nextStep.equals("A")) {
				grid = moveLeft(grid);
			}
			if(nextStep.equals("S")) {
				grid = moveDown(grid);
			}
			if(nextStep.equals("D")) {
				grid = moveRight(grid);
			}
			
			foundPosition = false;
			while(foundPosition == false) {
				i = Game1024.randomNumber(dimentions);
				j = Game1024.randomNumber(dimentions);
				
				if(grid[i][j] == 0) {
					grid[i][j] = gameJump; // GAME JUMP
					foundPosition = true;
				}
			}
			game.getGridStructure(grid);
			
			System.out.println("Current score: " + Game1024.gameScore);
		}
	}
}
