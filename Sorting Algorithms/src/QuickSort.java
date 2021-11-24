import java.util.Random;
/*
 * Arun Agarwal Sorting Algorithm QuickSort
 */
public class QuickSort
{
	private int[] data; // array of values
	private static Random generator = new Random();
	private int compCount;
	private int swapCount;
	
	// create array of given size and fill with random integers
	public QuickSort(int size)
	{
       data = new int[size];
       // fill array with random ints in range 10-99
       for (int i = 0; i < data.length-1; i++)
       {
    	   data[i] = 10 + generator.nextInt(99);
       }
	   compCount=0;
	   swapCount=0;
	  
	}

	public void sort()
	{
	  
	   quickSortHelper(0, data.length - 1);
	   System.out.println("Number of Comparisons for QuickSort " + compCount);
	   System.out.println("Number of Swaps for QuickSort "+ swapCount);
	}
	
	// recursive method to sort array
	private void quickSortHelper(int left, int right)
	{
	   compCount=0;
	   swapCount=0;
	   int pivot = partition(left, right);
	   if (left < pivot - 1) // if more than one element on left
		   quickSortHelper(left, pivot - 1); // sort left side
	   if (pivot + 1 < right) // if more than one element on right
		   quickSortHelper(pivot + 1, right); // sort right side
	  
	}
	
	//Partition the given range and return final index of pivot
	private int partition(int left, int right)
	{
	   int pivot = left;
	   swapCount++;
	   // loop until two edges meet
	   while (true)
	   {
		   //search for data to right of pivot greater than pivot
		   while (data[right] >= data[pivot])
		   {
			   compCount++;
			   if (right == pivot)
			   return pivot; //partitioning completed
			   --right; //move left one element
		   }
		   swap(pivot, right); //move right element into correct location
		   swapCount++;
		  
		   pivot = right--; //update pivot location and move right edge left
		   while (data[left] <= data[pivot])
		   {
			   compCount++;
			   if (left == pivot)
			   return pivot; //partitioning completed
			   ++left; //move right one element
		   }
		   swap(pivot, left); //move left element into correct location
		   swapCount++;
		   pivot = left++; //update pivot location and move left edge right
	   } 
	}
	
	//Swapping two elements
	private void swap(int first, int second)
	{
	   int temporary = data[first]; // store first in temporary
	   data[first] = data[second]; // replace first with second
	   data[second] = temporary; // put temporary in second
	}
	

	public String toString()
	{
		StringBuilder temporary = new StringBuilder();

		for (int element : data)
			temporary.append(element + " ");
		
		temporary.append( "\n" );
		return temporary.toString();
	}
}