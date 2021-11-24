import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import CircularLinkedList.Node;

import java.util.Iterator;
import java.util.*;
public class Practice 
{
	
	
	 public static void doubledValue()
	 {
	 	Scanner in = new Scanner(System.in);
	 	try
	 	{
	 	double input = in.nextDouble();
	 	System.out.println("The doubled value is: " + 2*input);
	 	}
	 	catch (Exception e)
	 	{
	 	System.out.println("You have not entered a double.");
	 	}
	 }
	 //Write a sequence of commands that will remove the tail of a doubly linked list:
	 // tail = tail.prev;
	 // tail.next = null;
	 
	 public static int minPlusMax (List<Integer> list) 
	 //O(n) for both arraylist and linkedlist
	 //however, if you were to use a for (int i = 0; i <...) loop
	 //with a get function for a linked list, this would be O(n^2)
	 //but O(n) for an Arraylist. This should make sense.
	 {
		 int min = Integer.MAX_VALUE;
		 int max = Integer.MIN_VALUE;
		 
		 for (int num: list)
		 { 
			if (num > max)
			{
				max = num;
			}
		 	if (num < min)
		 	{
		 		min = num;
		 	}
		 }
		 return min + max;
	 }	 
	 
	 /*public void removePrefixStrings(List<String> list, String prefix)
	 {
		 //O(n)
		 List<String> output = new ArrayList<>();
		 for (String word : list)
		 {
			 String reversed = "";
			 for (char c : word.toCharArray())
			 {
				 reversed = c + reversed;
			 }
			 output.add(reversed);
		 }
		 
	 }*/
	 
	 public static <E> void removePrefixStrings(List<String> list, String prefix)
	 {
		 int prefixLength = prefix.length();
		 
		 for (int i = 0; i < list.size(); i++)
		 {
			 String word = list.get(i).substring(0,prefixLength);
			 if (prefix.equals(word))
			 {
				 list.remove(i);
				 i--;
			 }
		 }
	 }
	 public void deleteList()
	 {
		/*
		Method 1: Easy
		head = null;
		tail = null;
		size = 0;*/
		
		 /*
		 Method 2: Harder
		 while (head != null)
		 {
			 head = head.next;
			 head.prev = null;
			 size--
		 }
		 head = null;
		 tail = null;*/
	 }
	 
	 
	 public int count (E item)
	 //time complexity: O(n) b/c going through all items once
	 {
		 int count = 0;
		 Node<E> current = head;
		 while(current != null)
		 {
			 if (current.item.equals(item))
			 {
				 count++;
			 }
			 current = current.next;
			 
		 }
	 }
	 
	 public static LinkedList<Integer> merge(List<Integer> listA, List<Integer> listB)
	 {
		 /*
		  * Easy Solution:
		  * LinkedList<Integer> out = new LinkedList<>();
		  * out.addAll(listA);
		  * out.addAll(listB);
		  * Collections.sort(out); //O(nlog(n))
		  * return out;
		  */
		 //Other Solution, O(n) time
		 LinkedList<Integer> out = new LinkedList<>();
		 while(listA.size() > 0 && listB.size() > 0)
		 {
			 if (listA.get(0) < listB.get(0))//constant time because linked list adding to head
			 {
				 out.add(listA.remove(0));
			 }
			 else
			 {
				 out.add(listB.remove(0));
			 }
		 }
		 out.addAll(listA);//minimal time
		 out.addAll(listB);
		 
		 
		 return out;
	 }
	 
	 //You have access to the Node class, that is why there is so 
	 //many errors right now
	 public void reverse()
	 {
		 /*
		  Simple Solution:
		  LinkedList<E> temp = new LinkedList();
		  while(this.size() > 0)
		  {
		  	temp.add(this.remove(0);
		  }
		  while(temp.size() > 0)
		  this.add(0,temp.remove(0);
		  //need to use three pointers for singly linked list, look up if necessary
		  */
		 Node<E> current = head;
		 while(current != null)
		 {
			 Node<E> after = current.next;
			 current.next = current.prev;
			 current.prev = after;
			 current = after;
		 }
		 
		 Node<E> temp = tail;
		 tail = head;
		 head = temp;
	 }
	 
	 
	 public void add(E item)
	 {
		 Node<E> adding = new Node<>(item);
		 if (head == null)
		 {
			 head = adding;
		 }
		 else if (item < head.item)
		 {
			 adding.next = head;
			 head = adding;
		 }
		 else //anywhere else, don't need to check head
		 {
			 Node<E> current = head;
			 while(current.next != null)
			 {
				 if (item < current.next.item)
				 {
					 adding.next = current.next;
					 current.next = adding;
					 break;
				 }
				 current = current.next;
			 }
			 if (current.next == null)
			 {
				 current.next = adding;
			 }
		 }
		 size++;
	 }
	 
	 //out of bounds
		// removing the only thing in the list
		// removing the first thing in the list (need to adjust the last thing in the list to point to the beginning)
		// removing the last thing 
		// removing any other node
		// REMEMBER TO DECREMENT THE SIZE
	 /*
	  * Write an instance method removeDuplicates(), which when called by a SortedLinkedList, removes all duplicate items from the SortedLinkedList.
You can assume the SortedLinkedList is sorted in increasing order and you don't have to check if the list is empty.


You can also assume (for the sake of simplicity) that the SortedLinkedList has only numbers.  The header is below.
	  */
	 public void removeDuplicates()
	 {
		 Node<E> current = head;
		 
		 if (size == 1)
			{
				break;
			}
		 else
		 {
			 while(current.next != null)
			 {
				 Node<E> temp = current;
				 while(temp != null && temp.data == current.data)
				 {
					 temp = temp.next;
				 }
				 current.next = temp;
				 current = current.next;
			 }
		 }
	 }	 
			
	/*
	 * Write an method that creates and returns a new 
	 * SortedLinkedList that is the intersection of two SortedLinkedList objects.  
	 * An intersection of two lists contains only the items that appear in both lists.  
	 * Items will only appear once in an intersection.		 
	 */
	public static SortedLinkedList sortedIntersect(SortedLinkedList A, SortedLinkedList B)
	{
		
		 SortedLinkedList<Integer> out = new SortedLinkedList<Integer>();
		
		 int sizeA = A.size();
		 int sizeB = B.size();
		 
		 //I could not figure out how to store A's start
		 //and B's head separately, so I used indices
		 int indexA = 0;
		 int indexB = 0;
	
		 while(indexA < sizeA && indexB<sizeB)
		 {
			 if(A.get(indexA) == B.get(indexB))
			 {
				 //I was not sure how to access the head of C
				 //so I used the add method
				 out.add(A.get(indexA));
				 indexA++;
				 indexB++;
			 }
			 //These are sorted linked lists!
			 //So...if one is less than the other
			 //we will increment the smaller one
			 //and vice versa (the else part of the loop)
			 else if (A.get(indexA) < B.get(indexB))
			 {
				 indexA++;
			 }
			 else
			 {
				 indexB++;
			 }
		 }
			/*for (Integer item: A)
			{
				if (item.data.equals())
				int count1 = 0;
				int count2 = 0;
				
				//count the number of times item appears in A
				for (int i = 0; i < list1.size(); i++)
				{
					E otherItem = list1.get(i);
					if(item.equals(otherItem))
						count1++;
				}
				//count the number of times items appears in B
				for (int i = 0; i < list2.size(); i++)
				{
					E otherItem = list2.get(i);
					if(item.equals(otherItem))
						count2++;
				}	
				
				if(count1 != count2)
				{
					return false;
				}
			}*/
		 
		 
		 
		 
		 
		 while(listA.size() > 0 && listB.size() > 0)
		 {
			 if (listA.get(0) < listB.get(0))//constant time because linked list adding to head
			 {
				 out.add(listA.remove(0));
			 }
			 else
			 {
				 out.add(listB.remove(0));
			 }
		 }
		 out.addAll(listA);//minimal time
		 out.addAll(listB);
		 
		 
		 return out;
		
		
		
	}		 
			 
			 /*for (int i = 0; i < list.size() - 1; i++)
				{
					for (int j= i + 1; j < list.size(); j++)
					{
						if (list.get(i).equals(list.get(j)))
						{
							list.remove
						}
					}
				}
				retur*/n true;	
		 }
		
			
			
		 
		 
		 
		 
	 }
//Will be on Exam! ArrayLists vs LinkedLists
	/*
	ArrayLists (Add/Remove):  In general, it takes O(n) to add or remove something in an ArrayList. This is always the case
	if you add or remove something from the front or middle of a size
	n list, as you need to shift n or n/2. The exception to this is adding
	to the end of an ArrayList, which takes O(1) time, except in the
	rare case that the ArrayList is full, in which case we need to reallocate, which takes O(n) time.
	
	LinkedLists (Add/Remove): If you are adding or removing to a location you have a reference
	to, such as the head or tail of an LinkedList, this takes O(1) time. In all other cases, you need
	to search for a node at the location you are interested in, which takes O(n) time.
	 
	ArrayLists (Get/Set): It takes constant time O(1)
	
	LinkedLists(Get/Set): It takes O(n) time unless you have a pointer/reference,
	in which case it would take O(1)/constant time. 
	
	ArrayLists (Memory Usage): Can waste memory by reallocating. If you are clever, you
	can prevent memory wasting, but generally reallocating wastes the memory.
	
	LinkedLists (Memory Usage): You are using a lot more overhead than an arraylist,
	but the size expands to your desire, unlike ArrayLists. You use this when you
	know you are going to be splitting up the list or shuffling items around in the 
	list or adding to the head or tail. Otherwise, use an arraylist. 
	 */


	 public static void main (String [] args)
	 {
		 doubledValue();
		 
		 List<Integer> test = new ArrayList<>(Arrays.asList(6,5,3,1,3,5,6,1,9,14));
		 System.out.println(minPlusMax(test));
		 
	 }





}
