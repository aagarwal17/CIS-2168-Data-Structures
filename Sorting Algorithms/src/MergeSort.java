import java.util.Random;
/*
 * Arun Agarwal Data Structures MergeSort Class
 */
public class MergeSort
{
	private int[] data; // array of values
	private static final Random generator = new Random();
	private int swapCount;
	private int compCount;
	
	//create array of given size and fill with random integers
	public MergeSort(int size)
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
	   sortArray(0, data.length - 1); // split entire array
	   System.out.println("Number of Comparisons in MergeSort " + compCount);
	   System.out.println("Number of Swaps in MergeSort " + swapCount);
	}
	
	//Splits array, sorts subarrays and merges subarrays into sorted array
	private void sortArray(int low, int high)
	{
	   compCount=0;
	   swapCount=0;
	   // test base case; size of array equals 1
	   if ((high - low) >= 1) // if not base case
	   {
		   int middle1 = (low + high) / 2; // calculate middle of array
		   int middle2 = middle1 + 1; // calculate next element over
		   // output split step
		   //System.out.println( "split: " + subarray( low, high ) );
		   //System.out.println( " " + subarray( low, middle1 ) );
		   //System.out.println( " " + subarray( middle2, high ) );
		   //System.out.println();
		   // split array in half; sort each half (recursive calls)
		   sortArray(low, middle1); // first half of array
		   sortArray(middle2, high); // second half of array
		   // merge two sorted arrays after split calls return
		   merge (low, middle1, middle2, high);
	   } // end if
	} // end method sortArray
	
	// merge two sorted subarrays into one sorted subarray
	private void merge(int left, int middle1, int middle2, int right)
	{
	   int leftIndex = left; // index into left subarray
	   int rightIndex = middle2; // index into right subarray
	   int combinedIndex = left; // index into temporary working array
	   int[] combined = new int[data.length]; // working array
	   // output two subarrays before merging
	   //System.out.println( "merge: " + subarray( left, middle1 ) );
	   //System.out.println( " " + subarray( middle2, right ) );
	  
	   // merge arrays until reaching end of either
	   while (leftIndex <= middle1 && rightIndex <= right)
	   {
		   // place smaller of two current elements into result
		   // and move to next space in arrays
		   if (data[leftIndex] <= data[rightIndex])
			   combined[combinedIndex++] = data[leftIndex++];
		   else
			   combined[combinedIndex++] = data[rightIndex++];
	   }
	  
	   // if left array is empty
	   if (leftIndex == middle2)
		   // copy in rest of right array
		   while (rightIndex <= right)
			   combined[combinedIndex++] = data[rightIndex++];
	   else // right array is empty
		   //copy in rest of left array
		   while (leftIndex <= middle1)
			   combined[combinedIndex++] = data[leftIndex++];
	   // copy values back into original array
	   for (int i = left; i <= right; i++)
		   data[i] = combined[i];
	}

	//Method to output certain values in array
	public String subarray(int low, int high)
	{
		StringBuilder temporary = new StringBuilder();
		// output spaces for alignment
		for (int i = 0; i < low; i++)
			temporary.append(" ");
		// output elements left in array
		for (int i = low; i <= high; i++)
			temporary.append(" " + data[ i ]);
		return temporary.toString();
	}
	
	public String toString()
	{
		return subarray(0, data.length - 1);
	}
}