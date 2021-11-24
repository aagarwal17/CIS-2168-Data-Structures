import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * Arun Agarwal Binary Search Algorithm Assignment
 * 10/19/2020
 */

// Your class. Notice how it has no generics.
// This is because we use generics when we have no idea what kind of data we are getting
// Here we know we are getting two pieces of data:  a string and a line number
public class IndexTree 
{

	// This is your root 
	// again, your root does not use generics because you know your nodes
	// hold strings, an int, and a list of integers
	private IndexNode root;
	
	// Make your constructor
	// It doesn't need to do anything
	public IndexTree()
	{
		root = null;
	}
	// complete the methods below
	
	// this is your wrapper method
	// it takes in two pieces of data rather than one
	// call your recursive add method
	public void add(String word, int lineNumber)
	{
		root = add(root, word, lineNumber);
	}
	
	
	
	// your recursive method for add
	// Think about how this is slightly different the the regular add method
	// When you add the word to the index, if it already exists, 
	// you want to  add it to the IndexNode that already exists
	// otherwise make a new indexNode
	private IndexNode add(IndexNode root, String word, int lineNumber)
	{
		//Base Case:
		if (root == null)
		{
			root = new IndexNode(word, lineNumber);
		}
		else
		{
			if(word.compareTo(root.word) < 0)
			{
				root.left = add(root.left, word, lineNumber);
			}
			else if(word.compareTo(root.word) > 0)
			{
				root.right = add(root.right, word, lineNumber);
			}
			else
			{
				if(!root.list.contains(lineNumber))
				{
					root.occurrences++;
					root.list.add(lineNumber);
				}
			}
		}
		return root;
	}
	
	
	
	
	// returns true if the word is in the index
	public boolean contains(String word)
	{
		//Base Case:
		if (root == null)
		{
			return false;
		}
		IndexNode current = root;
		
		while(current != null)
		{
			if (current.word == word)
			{
				return true;
			}
			else if (word.compareTo(current.word) < 0)
			{
				current = current.left;
			}
			else //word.compareTo(node.word) > 0
			{
				current = current.right;
			}
		}
		
		return false;
	}
	
	// call your recursive method
	// use book as guide
	public void delete(String word)
	{
		//I ADDED PRINT STATEMENTS FOR ORGANIZATION AND TESTING
		if(root == null)
		{
			System.out.println("Tree Empty");
		}
		//For some reason, delete won't work if I include this case
		/*else if ((!contains(word)))
		{
			System.out.println(word + " was not found in tree");
		}*/
		else //word is there and needs to be deleted
		{
			root = delete(root, word);
			System.out.println(word + " was deleted form the tree");
		}
	}
	
	// your recursive case
	// remove the word and all the entries for the word
	// This should be no different than the regular technique.
	private IndexNode delete(IndexNode root, String word)
	{
		IndexNode temp1;
		IndexNode temp2;
		
		//This was the last case in his video
		//I just did it first because it was on my mind first
		//Deals with removing root
		if (word.equals(root.word))
		{
			IndexNode left = root.left;
			IndexNode right = root.right;
			
			if (left == null && right == null)
			{
				return null;
			}
			else if (left == null)
			{
				return right;
			}
			else if (right == null)
			{
				return left;
			}
			else //In his video, he took the left side. I am gonna take the right side to try it out :)
			{
				temp1 = right;
				while (temp1.left != null)
				{
					temp1 = temp1.left;
				}
				temp1.left = left;
				return right;
			}
		}
		
		else if (word.compareTo(root.word) < 0)
		{
			temp2 = delete(root.left, word);
			root.left = temp2;
		}
		else // word.compareTo(root.word) > 0
		{
			temp2 = delete(root.right, word);
			root.right = temp2;
		}
		
		
		return root;
	}
	
	
	// prints all the words in the index in inorder order
	// To successfully print it out
	// this should print out each word followed by the number of occurrences and the list of all occurrences
	// each word and its data gets its own line
	public void printIndex()
	{
		printIndex(root);
	}
	
	//I created a recursive method to print the words in inorder traversal
	private void printIndex(IndexNode root)
	{
		//Base Case:
		if (root == null)
		{
			return;
		}
		
		//The Inorder Traversal Print Algorithm
		printIndex(root.left);
		System.out.println(root);
		printIndex(root.right);
	}
	
	public static void main(String[] args)
	{
		IndexTree index = new IndexTree();
		
		// add all the words to the tree
		String fileName = "pg100.txt";
		int linenum = 0;
		
		try 
		{
			Scanner scanner = new Scanner(new File(fileName));
			while(scanner.hasNextLine())
			{
				String line = scanner.nextLine();
				//System.out.println(line);
				String[] words = line.split("\\s+");
				for(String word : words)
				{
					word = word.replaceAll("[^a-zA-Z]", "");
					word = word.replaceAll(":", "");
					word = word.replaceAll(",", "");
					index.add(word, linenum);
					//System.out.println(word);
				}
				linenum++;
			}
			scanner.close();
			
		} 
		catch (FileNotFoundException e1) 
		{
			e1.printStackTrace();
		}
		
		
		// print out the index
		//index.printIndex();
		
		// test removing a word from the index
		
		//index.printIndex();
		//index.delete("zounds");
		index.printIndex();
		
	}
}
