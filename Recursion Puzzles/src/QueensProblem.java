/**
 * 
 * @author Arun Agarwal Queens Problem
 *
 */
public class QueensProblem 
{

	public int[][] Queenssolution;
	
	//Constructor
	public QueensProblem() 
	{
		//Creates "Board"
		Queenssolution = new int[8][8];
		
		//Setting all values in board to 0 to start
		for (int i = 0; i < 8; i++) 
		{
		
			for (int j = 0; j < 8; j++) 
			{
		
				Queenssolution[i][j] = 0;
		
			}
		
		}
		
	}
	
	public void solveProblem() 
	{
		
		if(placeQueens(0))
		{
			
			//print the result
			
			for (int i = 0; i < 8; i++) 
			{
			
				for (int j = 0; j < 8; j++) 
				{
					//Space + queen location (a '1')
					System.out.print(" | " + Queenssolution[i][j]);
				
				}
				//These lines help to make it look more like a Chess Board
				System.out.print("|");
				System.out.println();
				System.out.println(" _______________________________");
			}
			
		}
		//Last Resort: If it cannot find any solution
		else
		{
			System.out.println("No Possible Solutions");
		}
		
	}
	
	//AKA the boolean solve method given in the pdf
	public boolean placeQueens(int queen) 
	{
		
		//Placing Queens one at a time, one in each column
		//If the number of Queens == 8, we have solved the problem
		if(queen == 8)
		{
			return true;
		}
		
		for (int row = 0; row < 8; row++) 
		{
			
			//If the Queen can be placed at that position, then do it (put a 1)
			
			if (canPlace(Queenssolution, row, queen)) 
			{
				Queenssolution[row][queen] = 1;
			
				//Solve and Place next Queen
			
				if(placeQueens(queen+1))
				{
					return true;
				}
			
				//If the above did not work to place, we need to backtrack from
				//Queen + 1 to Queen
				Queenssolution[row][queen] = 0;
			}
		}
		
		//If all the above did not work, that means no solution exists.
		//This is a last resort to return false, but in our case,
		//we know it will work.
		return false;
	}
	
	//Method to check whether the Queen can be placed at chosen place
	public boolean canPlace(int[][] board, int row, int column) 
	{
		//SOMETHING TO NOTE: Since we add Queens column by column,
		//the columns after the queen we are currently trying to add
		//don't need to be checked because they will be checked later
		//once queens are added to those rows
		
		//First, checking to make sure no queens are in that row
		for (int i = 0; i < column; i++) 
		{
			if (board[row][i] == 1) 
			{
				return false;
			}
		}
		

		//Second, checking to make sure no queens in positive-slope diagonal
		//Needed to make a for loop with two parameters because separating
		//into two different for loops would then check values that we don't want to check.
		for (int i = row, j = column; i >= 0 && j >= 0; i--, j--) 
		{
			if (board[i][j] == 1) 
			{
				return false;
			}	
		}
		
		//Third, checking to make sure no queens in negative-slope diagonal
		//Again, needed to make a for loop with two parameters
		for (int i = row, j = column; i < board.length && j >= 0; i++, j--) 
		{
			if (board[i][j] == 1) 
			{
				return false;
			}
		}
		
		//If queen passes all three above tests, we can safely place it, so return true.
		return true;
	}
	
	//Test/Main Method
	public static void main(String[] args) 
	{
		QueensProblem aSolution = new QueensProblem();
		aSolution.solveProblem();
	}

}