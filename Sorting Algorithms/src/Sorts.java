import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JOptionPane;
/*
 * Arun Agarwal Sorting Algorithms
 * Insertion Sort, Quick Sort, Merge Sort
 */
public class Sorts 
{
	private int[] data; // array of values
	private int comparisonCount;
	private int swapCount;
	private static final Random generator = new Random(System.currentTimeMillis());
	
	//Constructor
	public Sorts(int size)
	{
	       data = new int[size];
	       // fill array with random ints in range 1-99
	       for (int i = 0; i < data.length-1; i++)
	       {
	    	   data[i] = 1 + generator.nextInt(99);
	       }
	    comparisonCount = 0;
	    swapCount = 0;
	       
	}
	
	//The Insertion Sort method
	public int[] InsertionSort()
	{
    	int[] result = new int[2];
	   comparisonCount=0;
	   swapCount=0;
	   int insert; // temporary variable to hold element to insert
	   
	   //Loop over data.length - 1 elements
	   for (int next = 1; next < data.length; next++ )
	   {
	       comparisonCount++;
		   //store value in current element
		   insert = data[next];
		   // initialize location to place element
		   int moveItem = next;
		   // search for place to put current element
		   while (moveItem > 0 && data[moveItem - 1] > insert )
		   {
			   // shift element right one slot
			   data[moveItem] = data[moveItem - 1];
			   moveItem--;
		   } 
		   data[moveItem] = insert; // place inserted element
	   } 
	   System.out.println("Number of comparisons in Insertion Sort " + comparisonCount);
	   System.out.println("Number of Swaps in Insertion Sort "+ swapCount);
	   
	   result[0] = comparisonCount;
	   result[1] = swapCount;
	   return result;
	}
	
	//The Quick Sort Method
	public int[] QuickSort()
	{
		int[] result = new int[2];
		quickSortHelper(0, data.length - 1);
		System.out.println("Number of Comparisons for QuickSort " + comparisonCount);
		System.out.println("Number of Swaps for QuickSort "+ swapCount);
		
		result[0] = comparisonCount;
		result[1] = swapCount;
		return result;
	}
	
	// recursive method to sort array
	private void quickSortHelper(int left, int right)
	{
	   comparisonCount=0;
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
			   comparisonCount++;
			   if (right == pivot)
			   return pivot; //partitioning completed
			   --right; //move left one element
		   }
		   swap(pivot, right); //move right element into correct location
		   swapCount++;
		  
		   pivot = right--; //update pivot location and move right edge left
		   while (data[left] <= data[pivot])
		   {
			   comparisonCount++;
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
	

	
	
	//The Merge Sort Method
	public int[] MergeSort()
	{
		int[] result = new int[2];
	   sortArray(0, data.length - 1); // split entire array
	   System.out.println("Number of Comparisons in MergeSort " + comparisonCount);
	   System.out.println("Number of Swaps in MergeSort " + swapCount);
	   
		result[0] = comparisonCount;
		result[1] = swapCount;
		return result;
	}
	
	//Splits array, sorts subarrays and merges subarrays into sorted array
	private void sortArray(int low, int high)
	{
	   comparisonCount=0;
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

	/*//Method to output certain values in array
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
	}*/
	
	public String toString()
	{
		/*StringBuilder temporary = new StringBuilder();

		for (int element : data)
			temporary.append(element + " ");
		
		temporary.append( "\n" );
		return temporary.toString();*/
		
		return Arrays.toString(data);
	}
	
	public static void saveRecord(String Sort, String Items, String Time, String Comparisons, String Swaps, String filepath)
	{
		try
		{
			FileWriter fw = new FileWriter(filepath, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			pw.println(Sort + "," + Items + "," + Time + "," + Comparisons + "," + Swaps);
			pw.flush();
			pw.close();
			
			JOptionPane.showMessageDialog(null, "Record Saved!");
		}
		catch (Exception E)
		{
			JOptionPane.showMessageDialog(null, "Record Not Saved.");
		}
	}
	public static void main(String[] args)
	{
		   int t= 5000;
		   int t2=10000;
		   int t3=20000;
		   int t4=40000;
		   int t5=80000;
		   int t6=160000;
		   
		   Long b=System.currentTimeMillis();
		   Long a=System.currentTimeMillis();
		   Long b1=System.currentTimeMillis();
		   Long a1=System.currentTimeMillis();
		   Long b2=System.currentTimeMillis();
		   Long a2=System.currentTimeMillis();
		   Long b3=System.currentTimeMillis();
		   Long a3=System.currentTimeMillis();
		   Long b4=System.currentTimeMillis();
		   Long a4=System.currentTimeMillis();
		   Long b5=System.currentTimeMillis();
		   Long a5=System.currentTimeMillis();
		   Long b6=System.currentTimeMillis();
		   Long a6=System.currentTimeMillis();
		   
		   
		   
		   /*
		   int[] randomNumFromOneToHundred = new int[t];
		   int[] randomNumOfPowerOfTwo = new int[t];
		   int[] randomNumFromOneToThousand = new int[t];
		   int[] randomNumFromOneToTenThousand = new int[t];
		   
	       // fill array with random ints in range 1-99
	       for (int i = 0; i < randomNumFromOneToHundred.length-1; i++)
	       {
	    	   randomNumFromOneToHundred[i] = 1 + generator.nextInt(99);
	       }
	       
	       // fill array with random ints in range 1-99
	       for (int i = 0; i < randomNumFromOneToThousand.length-1; i++)
	       {
	    	   randomNumFromOneToThousand[i] = 1 + generator.nextInt(999);
	       }
	       
	       // fill array with random ints in range 1-99
	       for (int i = 0; i < randomNumFromOneToTenThousand.length-1; i++)
	       {
	    	   randomNumFromOneToTenThousand[i] = 1 + generator.nextInt(9999);
	       }
	       
	       
	       
	       int[] result = new int[100];
	       int counter = 0;
	       int power = 1;
	       while (counter <= 99) 
	       {
	           result[counter] = power;
	           power *= 2;
	           counter++;
	       }
	       
	       for (int i = 0; i < randomNumOfPowerOfTwo.length-1; i++)
	       {
	    	   randomNumOfPowerOfTwo[i] = 1 + generator.nextInt(result.length);
	       }
	       
		   System.out.println("********************Insertion Sort ************************");
		   b=System.currentTimeMillis();
		   Sorts i1a = new Sorts(randomNumFromOneToHundred);
		   i1a.InsertionSort();
		   a=System.currentTimeMillis();
		   System.out.println("Insertion sort takes "+(a-b)+ " ms"+ " for " +t);
		   
		   
		   b2=System.currentTimeMillis();
		   Sorts i2a = new Sorts(randomNumOfPowerOfTwo);
		   i2a.InsertionSort();
		   a2=System.currentTimeMillis();
		   System.out.println("Insertion sort takes "+(a2-b2)+ " ms"+ " for " +t);
		   
		   
		   b3=System.currentTimeMillis();
		   Sorts i3a = new Sorts(randomNumFromOneToThousand);
		   i3a.InsertionSort();
		   a3=System.currentTimeMillis();
		   System.out.println("Insertion sort takes "+(a3-b3)+ " ms"+ " for " +t);
		   
		   
		   b4=System.currentTimeMillis();
		   Sorts i4a = new Sorts(randomNumFromOneToTenThousand);
		   i4a.InsertionSort();
		   a4=System.currentTimeMillis();
		   System.out.println("Insertion sort takes "+(a4-b4)+ " ms"+ " for " +t);
		   
		   
		   
		   System.out.println("***********Quick Sort *****************");
		   b=System.currentTimeMillis();
		   Sorts q1a =new Sorts(randomNumFromOneToHundred);
		   q1a.QuickSort();
		   a=System.currentTimeMillis();
		   System.out.println("Quick sort takes "+(a-b)+" ms"+ " for " +t);
		   
		   
		   b2=System.currentTimeMillis();
		   Sorts q2a=new Sorts(randomNumOfPowerOfTwo);
		   q2a.QuickSort();
		   a2=System.currentTimeMillis();
		   System.out.println("Quick sort takes "+(a2-b2)+" ms"+ " for " +t);
		   
		   
		   b3=System.currentTimeMillis();
		   Sorts q3a=new Sorts(randomNumFromOneToThousand);
		   q3a.QuickSort();
		   a3=System.currentTimeMillis();
		   System.out.println("Quick sort takes "+(a3-b3)+" ms"+ " for " +t);
		   
		   
		   b4=System.currentTimeMillis();
		   Sorts q4a=new Sorts(randomNumFromOneToTenThousand);
		   q3a.QuickSort();
		   a4=System.currentTimeMillis();
		   System.out.println("Quick sort takes "+(a4-b4)+" ms"+ " for " +t);
		   
		   
		   
		   System.out.println("*******************MergeSort************************");
		   b=System.currentTimeMillis();
		   Sorts m1a=new Sorts(randomNumFromOneToHundred);
		   m1a.MergeSort();
		   a=System.currentTimeMillis();
		   System.out.println("Merge sort takes "+(a-b)+ " ms"+ " for " +t);
		   
		   
		   b2=System.currentTimeMillis();
		   Sorts m2a= new Sorts(randomNumOfPowerOfTwo);
		   m2a.MergeSort();
		   a2=System.currentTimeMillis();
		   System.out.println("Merge sort takes "+(a2-b2)+ " ms"+ " for " +t);
		   
		   
		   b3=System.currentTimeMillis();
		   Sorts m3a=new Sorts(randomNumFromOneToThousand);
		   m3a.MergeSort();
		   a3=System.currentTimeMillis();
		   System.out.println("Merge sort takes "+(a3-b3)+ " ms"+ " for " +t);
		   
		   
		   b4=System.currentTimeMillis();
		   Sorts m4a=new Sorts(randomNumFromOneToTenThousand);
		   m4a.MergeSort();
		   a4=System.currentTimeMillis();
		   System.out.println("Merge sort takes "+(a4-b4)+ " ms"+ " for " +t);
		   */
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   for (int i = 0; i < 10; i++)
		   {
		   String Sort = "Insertion";
		   String filepath = "data.txt";
		   
		   int[] result = new int[2];
		   
		   System.out.println("********************Insertion Sort ************************");
		   b=System.currentTimeMillis();
		   Sorts i1 = new Sorts(t);
		   result = i1.InsertionSort();
		   a=System.currentTimeMillis();
		   System.out.println("Insertion sort takes "+(a-b)+ " ms"+ " for " +t + " items.");
		   String Items = "" + t; 
		   String comparisons = "" + result[0];
		   String swaps = "" + result[1];
		   String time = "" + (a-b);
		   saveRecord(Sort, Items, time, comparisons, swaps, filepath);
		   
		   b2=System.currentTimeMillis();
		   Sorts i2 = new Sorts(t2);
		   result = i2.InsertionSort();
		   a2=System.currentTimeMillis();
		   System.out.println("Insertion sort takes "+(a2-b2)+" ms"+ " for " +t2+ " items.");
		   Items = "" + t2; 
		   comparisons = "" + result[0];
		   swaps = "" + result[1];
		   time = "" + (a2-b2);
		   saveRecord(Sort, Items, time, comparisons, swaps, filepath);
		   
		   b3=System.currentTimeMillis();
		   Sorts i3 = new Sorts(t3);
		   result = i3.InsertionSort();
		   a3=System.currentTimeMillis();
		   System.out.println("Insertion sort takes "+(a3-b3)+" ms"+ " for "+ t3+ " items.");
		   Items = "" + t3; 
		   comparisons = "" + result[0];
		   swaps = "" + result[1];
		   time = "" + (a3-b3);
		   saveRecord(Sort, Items, time, comparisons, swaps, filepath);
		   
		   b4=System.currentTimeMillis();
		   Sorts i4 = new Sorts(t4);
		   result = i4.InsertionSort();
		   a4=System.currentTimeMillis();
		   System.out.println("Insertion sort takes "+(a4-b4)+" ms" + " for "+ t4+ " items.");
		   Items = "" + t4; 
		   comparisons = "" + result[0];
		   swaps = "" + result[1];
		   time = "" + (a4-b4);
		   saveRecord(Sort, Items, time, comparisons, swaps, filepath);
		   
		   b5=System.currentTimeMillis();
		   Sorts i5 = new Sorts(t5);
		   result = i5.InsertionSort();
		   a5=System.currentTimeMillis();
		   System.out.println("Insertion sort takes "+(a5-b5)+" ms" + " for "+ t5+ " items.");
		   Items = "" + t5; 
		   comparisons = "" + result[0];
		   swaps = "" + result[1];
		   time = "" + (a5-b5);
		   saveRecord(Sort, Items, time, comparisons, swaps, filepath);
		   
		   b6=System.currentTimeMillis();
		   Sorts i6 = new Sorts(t6);
		   result = i6.InsertionSort();
		   a6=System.currentTimeMillis();
		   System.out.println("Insertion sort takes "+(a6-b6)+" ms" + " for "+ t6+ " items.");
		   Items = "" + t6; 
		   comparisons = "" + result[0];
		   swaps = "" + result[1];
		   time = "" + (a6-b6);
		   saveRecord(Sort, Items, time, comparisons, swaps, filepath);
		   
		   
		  
		   
		   Sort = "Quick";
		   
		   System.out.println("***********Quick Sort *****************");
		   b=System.currentTimeMillis();
		   Sorts q1=new Sorts(t);
		   result = q1.QuickSort();
		   a=System.currentTimeMillis();
		   System.out.println("Quick sort takes "+(a-b)+" ms"+ " for " +t+ " items.");
		   Items = "" + t; 
		   comparisons = "" + result[0];
		   swaps = "" + result[1];
		   time = "" + (a-b);
		   saveRecord(Sort, Items, time, comparisons, swaps, filepath);
		   
		   b2=System.currentTimeMillis();
		   Sorts q2=new Sorts(t2);
		   result = q2.QuickSort();
		   a2=System.currentTimeMillis();
		   System.out.println("Quick sort takes "+(a2-b2)+" ms"+ " for " +t2+ " items.");
		   Items = "" + t2; 
		   comparisons = "" + result[0];
		   swaps = "" + result[1];
		   time = "" + (a2-b2);
		   saveRecord(Sort, Items, time, comparisons, swaps, filepath);
		   
		   b3=System.currentTimeMillis();
		   Sorts q3=new Sorts(t3);
		   result = q3.QuickSort();
		   a3=System.currentTimeMillis();
		   System.out.println("Quick sort takes "+(a3-b3)+" ms"+ " for "+ t3+ " items.");
		   Items = "" + t3; 
		   comparisons = "" + result[0];
		   swaps = "" + result[1];
		   time = "" + (a3-b3);
		   saveRecord(Sort, Items, time, comparisons, swaps, filepath);
		   
		   b4=System.currentTimeMillis();
		   Sorts q4=new Sorts(t4);
		   result = q4.QuickSort();
		   a4=System.currentTimeMillis();
		   System.out.println("Quick sort takes "+(a4-b4)+" ms" + " for "+ t4+ " items.");
		   Items = "" + t4; 
		   comparisons = "" + result[0];
		   swaps = "" + result[1];
		   time = "" + (a4-b4);
		   saveRecord(Sort, Items, time, comparisons, swaps, filepath);
		   
		   b5=System.currentTimeMillis();
		   Sorts q5=new Sorts(t5);
		   result = q5.QuickSort();
		   a5=System.currentTimeMillis();
		   System.out.println("Quick sort takes "+(a5-b5)+" ms" + " for "+ t5+ " items.");
		   Items = "" + t5; 
		   comparisons = "" + result[0];
		   swaps = "" + result[1];
		   time = "" + (a5-b5);
		   saveRecord(Sort, Items, time, comparisons, swaps, filepath);
		   
		   b6=System.currentTimeMillis();
		   Sorts q6=new Sorts(t6);
		   result = q6.QuickSort();
		   a6=System.currentTimeMillis();
		   System.out.println("Quick sort takes "+(a6-b6)+" ms" + " for "+ t6+ " items.");
		   Items = "" + t6; 
		   comparisons = "" + result[0];
		   swaps = "" + result[1];
		   time = "" + (a6-b6);
		   saveRecord(Sort, Items, time, comparisons, swaps, filepath);
		   
		   
		   
		   
		   Sort = "Merge";
		   
		   System.out.println("*******************MergeSort************************");
		   b=System.currentTimeMillis();
		   Sorts m1=new Sorts(t);
		   result = m1.MergeSort();
		   a=System.currentTimeMillis();
		   System.out.println("Merge sort takes "+(a-b)+ " ms"+ " for " +t+ " items.");
		   Items = "" + t; 
		   comparisons = "" + result[0];
		   swaps = "" + result[1];
		   time = "" + (a-b);
		   saveRecord(Sort, Items, time, comparisons, swaps, filepath);
		   
		   b2=System.currentTimeMillis();
		   Sorts m2=new Sorts(t2);
		   result = m2.MergeSort();
		   a2=System.currentTimeMillis();
		   System.out.println("Merge sort takes "+(a2-b2)+ " ms"+ " for " +t2+ " items.");
		   Items = "" + t2; 
		   comparisons = "" + result[0];
		   swaps = "" + result[1];
		   time = "" + (a2-b2);
		   saveRecord(Sort, Items, time, comparisons, swaps, filepath);
		   
		   b3=System.currentTimeMillis();
		   Sorts m3=new Sorts(t3);
		   result = m3.MergeSort();
		   a3=System.currentTimeMillis();
		   System.out.println("Merge sort takes "+(a3-b3)+" ms"+ " for "+ t3+ " items.");
		   Items = "" + t3; 
		   comparisons = "" + result[0];
		   swaps = "" + result[1];
		   time = "" + (a3-b3);
		   saveRecord(Sort, Items, time, comparisons, swaps, filepath);
		   
		   b4=System.currentTimeMillis();
		   Sorts m4=new Sorts(t4);
		   result = m4.MergeSort();
		   a4=System.currentTimeMillis();
		   System.out.println("Mege sort takes "+(a4-b4)+ " ms" + " for "+ t4+ " items.");
		   Items = "" + t4; 
		   comparisons = "" + result[0];
		   swaps = "" + result[1];
		   time = "" + (a4-b4);
		   saveRecord(Sort, Items, time, comparisons, swaps, filepath);
		   
		   b5=System.currentTimeMillis();
		   Sorts m5=new Sorts(t5);
		   result = m5.MergeSort();
		   a5=System.currentTimeMillis();
		   System.out.println("Merge sort takes "+(a5-b5)+" ms" + " for "+ t5+ " items.");
		   Items = "" + t5; 
		   comparisons = "" + result[0];
		   swaps = "" + result[1];
		   time = "" + (a5-b5);
		   saveRecord(Sort, Items, time, comparisons, swaps, filepath);
		   
		   b6=System.currentTimeMillis();
		   Sorts m6=new Sorts(t6);
		   result = m6.MergeSort();
		   a6=System.currentTimeMillis();
		   System.out.println("Merge sort takes "+(a6-b6)+" ms" + " for "+ t6+ " items.");
		   Items = "" + t6; 
		   comparisons = "" + result[0];
		   swaps = "" + result[1];
		   time = "" + (a6-b6);
		   saveRecord(Sort, Items, time, comparisons, swaps, filepath);
		   
		   }

		   
	}
	
}
