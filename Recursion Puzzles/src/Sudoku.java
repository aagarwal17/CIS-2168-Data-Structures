/**
 * 
 * @author Arun Agarwal Sudoku Problem
 *
 */
import java.util.*;

public class Sudoku 
{
	
	private int[][] SudokuSolution;
	private int max = 9;
	
	//Constructor
	public Sudoku(int sudokuBoard[][])
	{
		//Creates "Board"
		sudokuBoard = SudokuSolution;
		//I will set values for the board in the main method this time!
	}
	
	public void solve()
	{
		if (!backtrackSolve())
		{
			System.out.println("The given sudoku board cannot be solved!");
		}
		
		System.out.println("  __________________________________");
		
		for (int i = 0; i < max; i++)
		{
			for (int j = 0; j < max; j++)
			{
				System.out.println(" | " + SudokuSolution[i][j]);
			}
			
			//These lines help to make it look more like a Sudoku Board
			System.out.print("|");
			System.out.println();
			System.out.println("  __________________________________");
		}
	}
	
	public boolean isValidPlacement(int row, int col, int num)
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
	
	//This is basically the solve method in the pdf
	public boolean backtrackSolve() 
	{
		int i = 0, j = 0;

		boolean isThereEmptyCell = false;

		for (int ii = 0; ii < max && !isThereEmptyCell; ii++) {

		for (int jj = 0; jj < max && !isThereEmptyCell; jj++) {

		if (SudokuSolution[ii][jj] == 0) {

		isThereEmptyCell = true;

		i = ii;

		j = jj;

		}

		}

		}

		if (!isThereEmptyCell) {

		return true;

		}

		for (int x = 1; x < 10; x++) 
		{

		if (isValidPlacement(i, j, x)) 
		{

		SudokuSolution[i][j] = x;

		if (backtrackSolve()) 
		{

		return true;

		}

		SudokuSolution[i][j] = 0; //////////////////// failed.////////////////////////

		}
		//SudokuSolution[i][j] = 0;
		}

		/////////////////////////// Backtracking/////////////////////////////////

		return false;
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

		new Sudoku(new int[][] 
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

		}).solve();

	}
}
