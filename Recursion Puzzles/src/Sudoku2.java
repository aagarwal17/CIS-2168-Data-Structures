/**
 * 
 * @author Arun Agarwal Sudoku Problem
 *
 */
import java.util.*;

public class Sudoku2 
{
	
	private static int[][] SudokuSolution;
	private static int max = 9;
	
	//Constructor
	public Sudoku2(int sudokuBoard[][])
	{
		//Creates "Board"
		SudokuSolution = sudokuBoard;
		//I will set values for the board in the main method this time!
	}
	
	public static void solveSudoku()
	{
		
		//first try to solve at row 0, going to each column for that row
		if (solve(0,0))
		{
			System.out.println("  __________________________________");
			for (int i = 0; i < max; i++)
			{
				for (int j = 0; j < max; j++)
				{
					System.out.print(" | " + SudokuSolution[i][j]);
				}
				
				//These lines help to make it look more like a Sudoku Board
				System.out.print("|");
				System.out.println();
				System.out.println("  __________________________________");
			}
		}
		//Last Resort: If it cannot find any solution
		else
		{
			System.out.println("There is no possible Sudoku solution for the given board!");
		}
	}
	
	
	
	public static boolean solve(int row,int col)
	{
		if(col == SudokuSolution.length) 
		{
			row++;
			col = 0;
		}
		if(row==SudokuSolution.length) 
		{
			return true;
		}
		
		if(SudokuSolution[row][col] != 0 ) 
		{
			return solve(row,col+1);
		}
		
		int num = SudokuSolution[row][col];
		
		for (int i = num; i <= 9; i++)
		{
			if(isValidPlacement(row,col,i))
			{
				SudokuSolution[row][col] = i;				
				if(solve(row,col+1))
				{
					return true;
				}
			}
		}
		SudokuSolution[row][col] = num;
		
		return false;
	}	
		
	public static boolean isValidPlacement(int row, int col, int num)
	{
		//First, check to see if the num is already in that row
		for (int j = 0; j < max; j++)
		{
			if (SudokuSolution[row][j] == num)
			{
				return false;
			}
		}
		
		//Second, check to see if the num is already in that column
		for (int i = 0; i < max; i++)
		{
			if (SudokuSolution[i][col] == num)
			{
				return false;
			}
		}
		
		//Third, check to see if the num is already in the subBox (3x3)
		//create variables for the first row and column in the subBoxes
		int subBoxRow = row - (row % 3);
		int subBoxCol = col - (col % 3);
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				if (SudokuSolution[subBoxRow + i][subBoxCol + j] == num)
				{
					return false;
				}
			}
		}
		
		////If num passes all three above tests, we can safely place it, so return true.
		return true;
		
	}
	
	
	public static void main (String [] args)
	{
		System.out.println("Input grid is: ");
		System.out.println("  __________________________________");
		int[][] grid = new int[][] 
		{

		{3, 0, 6, 5, 0, 8, 4, 0, 0},

		{5, 2, 0, 0, 0, 0, 0, 0, 0},

		{0, 8, 7, 0, 0, 0, 0, 3, 1},

		{0, 0, 3, 0, 1, 0, 0, 8, 0},

		{9, 0, 0, 8, 6, 3, 0, 0, 5},

		{0, 5, 0, 0, 9, 0, 6, 0, 0},

		{1, 3, 0, 0, 0, 0, 2, 5, 0},

		{0, 0, 0, 0, 0, 0, 0, 7, 4},

		{0, 0, 5, 2, 0, 6, 3, 0, 0}

		};

		for (int i = 0; i < 9; i++) 
		{

			for (int j = 0; j < 9; j++) 
			{
				System.out.print(" | " + grid[i][j]);
			}

			//These lines help to make it look more like a Sudoku Board
			System.out.print("|");
			System.out.println();
			System.out.println("  __________________________________");
		}

		System.out.println("Output grid is: ");

		Sudoku2 aSolution = new Sudoku2(grid);
		aSolution.solveSudoku();

	}
}
