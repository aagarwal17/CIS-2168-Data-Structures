import java.util.Arrays;
import java.util.Random;
/*
 * Arun Agarwal Data Structures Insertion Sort Class
 */
public class InsertionSort
{
	private int[] data; // array of values
	private int compCount;
	private int swapCount;
	private static final Random generator = new Random(System.currentTimeMillis());
	
	//Constructor
	public InsertionSort(int size)
	{
	       data = new int[size];
	       // fill array with random ints in range 10-99
	       for (int i = 0; i < data.length-1; i++)
	       {
	    	   data[i] = 10 + generator.nextInt(99);
	       }
	       
	}
	
	//The Sort method
	public void sort()
	{
	   compCount=0;
	   swapCount=0;
	   int insert; // temporary variable to hold element to insert
	   
	   for (int next = 1; next < data.length; next++ )
	   {
	       compCount++;
		   //store value in current element
		   insert = data[next];
		   //initialize location to place element
		   int moveItem = next;
		   //search for place to put current element
		   while (moveItem > 0 && data[moveItem - 1] > insert )
		   {
			   //shift element right one slot
			   data[moveItem] = data[moveItem - 1];
			   moveItem--;
		   }
		   data[moveItem] = insert; // place inserted element
		   //printPass( next, moveItem ); // output pass of algorithm
	   } 
	   System.out.println("Number of comparisons in Insertion Sort " + compCount);
	   System.out.println("Number of Swaps in Insertion Sort "+ swapCount);
	}
	
	/*
	//print a pass of the algorithm
	public void printPass( int pass, int index )
	{
	   System.out.print( String.format( "after pass %2d: ", pass ) );
	   // output elements till swapped item
	   for ( int i = 0; i < index; i++ )
	   System.out.print( data[ i ] + " " );
	   System.out.print( data[ index ] + "* " ); // indicate swap
	   // finish outputting array
	   for ( int i = index + 1; i < data.length; i++ )
	   System.out.print( data[ i ] + " " );
	  
	   System.out.print( "\n " ); // for alignment
	   // indicate amount of array that is sorted
	   for( int i = 0; i <= pass; i++ )
	   System.out.print( "-- " );
	   System.out.println( "\n" ); // add endline
	}*/
	
	public String toString()
	{
		return Arrays.toString(data);
	}
}

