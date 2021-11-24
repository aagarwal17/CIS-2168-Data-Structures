import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Iterator;

public class TreeExamQuestions 
{
	private static class Node<E>
	{
		E item;
		Node<E> left;
		Node<E> right;
		public Node(E item)
		{
			this.item = item;
		}
	}
	
	
	public static <E> int size(Node<E> root)
	{
		if (root == null)
		{
			return 0;
		}
		int mySize = 1;
		int leftSize = size(root.left);
		int rightSize = size(root.right);
		return leftSize + mySize + rightSize;
	}
	
	
	public static <E> boolean isFull(Node<E> root)
	{
		if (root == null)
		{
			return true;
		}
		if(root.left == null && root.right == null)
		{
			return true;
		}
		else if(root.left == null && root.right != null)
		{
			return false;
		}
		else if(root.left != null && root.right == null)
		{
			return false;
		}
		else
		{
			return isFull(root.left) && isFull(root.right);
		}
	}
	
	public static <E> boolean equals(Node<E> treeA, Node<E> treeB)
	{
		if (treeA == null && treeB == null)
		{
			return true;
		}
		else if (treeA != null && treeB == null)
		{
			return false;
		}
		else if (treeA == null && treeB != null)
		{
			return false;
		}
		else
		{
			if(!treeA.item.equals(treeB.item))
			{
				return false;
			}
			
			return equals(treeA.left, treeB.left) && equals(treeA.right,treeB.right);
		}
	}
	
	
	public static Map<Character, Integer> count(String word)
	{
		Map<Character, Integer> map = new HashMap<>();
		for( char letter: word.toCharArray())
		{
			if(!map.containsKey(letter))
			{
				map.put(letter, 1);
			}
			else
			{
				int previous = map.get(letter);
				map.put(letter, previous+1);
			}
		}
		
		return map;
	}
	
	
	public static <E> int getHeight(Node<E> root) 
	{
		//Base Case:
		if (root == null) 
		{
			return 0;
		}
		//Otherwise return 1 + the height of the larger subtree
		return 1 + Math.max(getHeight(root.left), getHeight(root.right));
	}
	
	
	public static <E> boolean isBalanced(Node<E> root) 
	{
		//Base Case:
		if (root == null)
		{
			return true;
		}
		else if (root.left == null && root.right == null)
		{
			return true;
		}
		else
		{
			int leftTreeHeight = getHeight(root.left);
			int rightTreeHeight = getHeight(root.right);
			
			//If the difference in heights between the two subtrees is no larger than one and the subtrees are balanced, then we return true. 
			//I probably should have split up this if statement into multiple but oh well.
			if ((leftTreeHeight - rightTreeHeight >= -1) && (leftTreeHeight - rightTreeHeight <= 1) && isBalanced(root.left) && isBalanced(root.right))
			{
				return true;
			}
			return false;
		}
	}
	
	
	//Write a method which, given a List of strings, returns what Strings appeared in the array and how many times each word appeared. You may assume that each String holds a single word.


	// INPUT -> OUTPUT
	// ["foo", "bar", "foo", "bar", "baz"] -> {"foo":2 , "bar":2, "baz":1}
	// ["a", "b", "a","c", "a", "d"] -> {"a":3, "b":1, "c":1,"d":1}
	public static Map<String,Integer> wordCount(List<String> words)
	{
		Map<String, Integer> map = new HashMap<>();
		//for each word in our list:
		for(String word: words)
		{
			//if the word is not in our map, add it to the map
			if(!map.containsKey(word))
			{
				map.put(word, 1);
			}
			//since the word is already in the map, add one to its count
			else
			{
				int previous = map.get(word);
				map.put(word, previous+1);
			}
		}
		
		return map;
	}
	public static HashMap<String, Integer> reduce(HashMap<String, Integer> A, HashMap<String, Integer> B)
	{
		//Given HashMap that will hold the key value pairs combined from A and B
		HashMap<String, Integer> both =  new HashMap<String, Integer>();
		
		//I have always used iterators to traverse through a HashMap. I had to import this (I wasn't sure if I needed to include the import line)
        Iterator<String> itr= A.keySet().iterator();
        
        //I am using this to hold keys as I go along
	    String thing = "";
	    
	    //While there is still another thing in the first HashMap, I will add it to the "both" hashMap
	    //Everything can be first put into "both" since it is empty
	    while(itr.hasNext())
	    {
	           thing = itr.next();
	           both.put(thing, A.get(thing));
	    }
	    //Setting the iterator to go through the second hashMap now
	    itr = B.keySet().iterator();
	
	    while(itr.hasNext())
	    {
	           thing = itr.next();
	
	           //If "thing" or the key in B is already in "both", 
	           //the value for that key is changed to the sum of what's already in there and the value in B
	           if(both.containsKey(thing))
	           {
	        	   both.put(thing, both.get(thing) + B.get(thing));
	           } 
	           //Otherwise, the key in B is not yet in "both", so just add it to "both"
	           else
	           {
	        	   both.put(thing, B.get(thing));
	           }
	    }
	    //Just returning the combined hashMap
	    return both;
	}
	
	public static void main (String [] args)
	{
		List<String> words = new ArrayList();
		words.add("foo");
		words.add("bar");
		words.add("foo");
		words.add("bar");
		words.add("foo");
		words.add("baz");
		words.add("foo");
		words.add("4");
		words.add("");
		words.add("hello");
		words.add("hello");
		words.add("hello");
		
		System.out.println(wordCount(words));
	}
	
	
	public static int search(TripleNode tree, int value) 
	{
		//I will create a recursive method for this
		
		//Base Case:
		if(tree == null)
		{
			return 0;
		}
		
		//If the value of tree (root of tree or subtree) equals the provided value, then return 1 + the counts for the subtrees
		else if (tree.value == value)
		{
			return (1 + search(tree.left,value) + search(tree.middle,value) + search(tree.right, value));
		}
		
		//If the value of tree (root of tree or subtree) does not equal the provided value, then just add counts of subtrees together
		else
		{
			return  (search(tree.left,value) + search(tree.middle,value) + search(tree.right, value));
		}
	}
	
	
	public static int search(TripleNode tree, int value)
	{
		 if(tree == null) 
		 {
		        return 0;
		 } 
		 else 
		 {
		        int c = 0;
		        if(tree.value == value) 
		        {
		            c = 1;
		        }
		        c += search(tree.left, value);
		        c += search(tree.middle, value);
		        c += search(tree.right, value);
		        return c;
		    }
	}
	
	//Write a method that takes the root of a binary search tree or subtree and returns a Linked List containing all the data stored in the tree in post-order.
	//For clarity, I will refer nodes in the tree as TNode.
	public static LinkedList<E> gudsq(TNode<E> root) 
	{
		//I will create a recursive method for this. Since the question is asking for post order,
		//we must process it left, right, then root. 
		
		//The linkedlist that will be returned
		List<Integer> out = new LinkedList<Integer>();
		
		//Base Case:
		 if(root == null) 
		 {
			 return 0;
		 }
		 
		//If the root does not have subtrees, return 0;
	    if (root.left == null && root.right == null)
	    {
	    	return 0;
	    }
	    //I am going to move everything to the right side, making the left side null, and then add the items to the right side into the
	    //tree, and then add the root
	    // If the left subtree is nonempty:
	    if (root.left != null)
	    {
	        //Recursive Call on the left subtree
	        gudsq(root.left);
	 
	        //Creating a temporary store for root.right
	        TNode temp = root.right;
	        //Assigning the values in root.left to root.right and setting left equal to null. This puts all values on the right side
	        root.right = root.left;
	        root.left = null;
	 
	        // Find the position to insert the stored value
	        TNode current = root.right;
	        while (current.right != null) 
	        {
	            current = current.right;
	        }
	 
	        // Insert the stored value
	        current.right = temp;
	    }
	 
	    //Recursively calling the function to do it for the right part of the tree
	    gudsq(root.right);
	    
	    //Creating a temporary node to store the root
	    TNode tempRoot = root;
	    
	    //With everything on the right-hand side, I only need to add the items to the linked list and then ad the root
	    while(root.right != null) 
	    {
	    	out.add(root.right);
	    	root = root.next;
	    }
	out.add(tempRoot);
	//Returning the linkedlist
	return out;
	}
 
// Function for Inorder traversal
public void inOrder(Node node)
{
     
    // Base Condition
    if (node == null)
        return;
 
    inOrder(node.left);
    System.out.print(node.data + " ");
    inOrder(node.right);
}
	//Write a method that takes the root of a binary search tree or subtree and returns a Linked List containing all the data stored in the tree in post-order.
	//For clarity, I will refer nodes in the tree as TNode.
	public static LinkedList<E> gudsq(TNode<E> root) 
	{
		//I will create a recursive method for this. Since the question is asking for post order,
		//we must process it left, right, then root. 
		
		//Base Case:
		 if(root == null) 
		 {
			 return 0;
		 }

		//Need to create linked lists for the left and right subtrees and the final list. The gudsq method will be recursively called on each
		List<Integer> left_tree = new LinkedList<Integer>(convert(root.left));
		List<Integer> right_tree = new LinkedList<Integer>(convert(root.right));
		List<Integer> out = new LinkedList<Integer>(root.data);

		if(left_tree == null)
		{
			out.next = right_tree; 
		// if left_list is null this means we only have to connect the node that we made to the right list and we return the node.

		return node;
		}
		else{

		LinkedList<Integer> temp = left_tree; // if left_list is not equal to null then we traverse to the end of left_list and connect the end of left_list to node that we made and finally we connect node.next to right_list.
		while(temp.next != null)
		{

		temp = temp.next;

		}

		temp.next = node;

		node.next = right_tree;
		
		LinkedList<Integer> out = new LinkedList<>();
		out.addAll(listA);
		out.addAll(listB);

		return left_tree;

		}
	/*What are collisions in Hash table? Why do they occur and how are they handled?
21) Collisions happen because we do not have an infinite amount of space but we have an infinite number of possible keys, so two things may try to go in the same space
Collisions in Hash tables--extend it like an arraylist; happens when you have two
 * things trying to get into the same space. Two strategies for handling it. Open addressing--if space is full, take next available space. Linear Probing would be going space by space. There is also Quadratic Probing where you would index + 1^2, index + 2^2, index + 3^2... (which spaces them more out, but it is more expensive operation because it has addition and multiplication)
 * Other way is with a linkedlist (chaining method), making the slot a list rather than just one item, and we append the item to the end of the list. 
 * 22) Worst case runtime of the insertion algorithm in a binary search tree is n. (the average run time for this would be log base 2 n). Worst case is n because if the tree is unbalanced, we have to perform operations over n elements because we are not reduing our search base by half (maybe only reducing it by one or two). Thus, a balance binary search tree would be better
 * Worst case runtime of the deletion algorithm in a binary search tree is n. 
 */
}
