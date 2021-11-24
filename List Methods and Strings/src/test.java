import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Arun Agarwal/ Test Class
 *
 */
public class test 
{
	public static void main (String [] args)
	{
		List <Integer> myList = new ArrayList<Integer>();
		myList.add(1);
		myList.add(3);
		myList.add(4);
		myList.add(9);
		myList.add(7);
		myList.add(2);
		myList.add(12);
		myList.add(15);
		myList.add(17);
		myList.add(1);
		System.out.println("Unique? " + Methods.unique(myList));
		System.out.println("All Multiples of: " + Methods.allMultiples(myList, 3));
		Methods.removeAllInstances(myList,7);
		System.out.println("New List: " + myList);
		
		
		List <String> strlist = new ArrayList<String>();
		strlist.add("apples");
		strlist.add("add");
		strlist.add("I");
		strlist.add("end");
		strlist.add("add");
		strlist.add("to");
		strlist.add("is");
		strlist.add("bananas");
		strlist.add("like");
		strlist.add("eat");
		strlist.add("eat");
		strlist.add("I");
		System.out.println("Strings of Size" + Methods.allStringsOfSize(strlist, 7));
		Methods.removeAllInstances(strlist,"I");
		System.out.println("New List: " + strlist);
		
		String sentence = ("Once upon a time, I went to y'all's bathroom!. "
				+ "I had to spend $10? (It was weird & dumb)");
		System.out.println("String to List of Words " + Methods.stringToListOfWords(sentence));
		
		
		List <Integer> myList1 = new ArrayList<Integer>();
		List <Integer> myList2 = new ArrayList<Integer>();
		List <String> myList3 = new ArrayList<String>();
		List <String> myList4 = new ArrayList<String>();
		myList2.add(1);
		myList2.add(3);
		myList2.add(4);
		myList2.add(9);
		myList1.add(4);
		myList1.add(9);
		myList1.add(1);
		myList1.add(3);
		myList3.add("I");
		myList3.add("AM");
		myList3.add("A");
		myList3.add("GUY");
		myList4.add("A");
		myList4.add("GUY");
		myList4.add("I");
		myList4.add("AM");
		System.out.println("isPermutation: " + Methods.isPermutation(myList1, myList2));
		System.out.println("isPermutation: " + Methods.isPermutation(myList3, myList4));
	}
}
