import java.io.*; 
import java.lang.*; 
import java.util.*;

/**
 * 
 * @author Arun Agarwal, Solitaire Encryption Assignment, September 22nd, 2020
 *
 */
public class SolitaireEncryption 
{

    public static char encryptChar(char letter, int key) 
    {
        int value =   letter - 'a';
        int encryptedValue =  (value + key) % 26;
        char encryptedChar = (char) (encryptedValue+'a');

        return encryptedChar;
    }


    public static char decryptChar(char letter, int key) 
    {
        int value =   letter - 'a';
        int decryptedValue =  (value + (26-key)) % 26;
        char decryptedChar = (char) (decryptedValue+'a');

        return decryptedChar;
    }

    public static int getKey(CircularLinkedList<Integer> deck)
    { 
		step1(deck);
		step2(deck);
		step3(deck);
		step4(deck);
		int key = step5(deck);
    	return key;
    }
    //Find the A joker (27). Swap Joker A with the card beneath (after) it in
    //the deck. This will effectively move the card down one position. (What if
    //the joker is the last card in the deck? Imagine that the deck of cards is
    //continuous; the card following the bottom card is the top card of the deck,
    //and you’d just exchange them.)
    //MAY NEED TO DO THIS. DO MULTIPLE TESTS!! make special case for if joker is last card
    private static void step1(CircularLinkedList<Integer> deck)
    {
    	int location = 0;
    	while (deck.get(location) != 27)
    	{
    		location++;
    	}
	    		if(location == deck.size - 1)
	    		{
	    			deck.add(1,27);
	    			deck.remove(location+1);
	    		}
	    		else
	    		{
		    		deck.remove(location);
	    			deck.add(location+1,27);
	    		}
	        	System.out.println("Deck after Step 1: " + deck); 
    }

    //Find the B joker (28). Move Joker B two cards down by performing two exchanges.
   //MAY NEED TO DO THIS. DO MULTIPLE TESTS!! make special case for if joker is last card or one card before last card
    private static void step2(CircularLinkedList<Integer> deck)
    {
    	int location = 0;
    	while (deck.get(location) !=28)
    	{	
    		location++;
    	}
	    		if (location == deck.size - 1)
	    		{
	    			deck.add(2,28);
	    			deck.remove(location+1);
	    		}
	    		else if (location == deck.size - 2)
	    		{
	    			deck.add(1,28);
	    			deck.remove(location+1);
	    		}
	    		else
	    		{
		    		deck.remove(location);
		    		deck.add(location+2,28);
	    		}
	        	System.out.println("Deck after Step 2: " + deck);
    }
    
    
    //Perform the triple cut. To do this, take all the cards above the first joker
    //(the one closest to the top of the deck, A and B don’t matter in this step)
    //and swap it with all the cards below the second joker
    private static void step3(CircularLinkedList<Integer> deck)
    {
    	//int AJoker = 0;
    	//int BJoker = 0;
    	CircularLinkedList<Integer> topdeck = new CircularLinkedList<>();
    	CircularLinkedList<Integer> bottomdeck = new CircularLinkedList<>();
    	
    	while (deck.get(0) != 27 && deck.get(0) != 28)
    	{
    		topdeck.add(deck.get(0));
    		deck.remove(0);
    	}
    	while (deck.get(deck.size-1) != 27 && deck.get(deck.size-1) != 28)
    	{
    		bottomdeck.add((0),deck.get(deck.size-1));
    		deck.remove(deck.size - 1);
    		//System.out.println(deck);
    	}

    	//System.out.println("top deck: " + topdeck + "\nbottom deck: " + bottomdeck + "\ndeck: " + deck);

    	while (bottomdeck.size > 0)
		{	
			deck.add(0,bottomdeck.get(bottomdeck.size -1));
			bottomdeck.remove(bottomdeck.size -1);
		}
		while (topdeck.size > 0)
		{	
			deck.add(deck.size,topdeck.get(0));
			topdeck.remove(0);
		}
		System.out.println("Deck after Step 3: " + deck);
    }
    
    //Remove the bottom card from the deck. Count down from the top card by
    //a quantity of cards equal to the value of that bottom card. (If the bottom
    //card is a joker, let its value be 27, regardless of which joker it is.) Take
    //that group of cards and move them to the bottom of the deck. Return the
    //bottom card to the bottom of the deck.
    private static void step4(CircularLinkedList<Integer> deck)
    {
    	CircularLinkedList<Integer> topdeck = new CircularLinkedList<>();
    	int location = 0;
    	int valueOfEndPoint = 0;
    	int bottomCard = deck.get(deck.size - 1);
    	deck.remove(deck.size - 1);
    	
    	if (bottomCard == 27 || bottomCard == 28)
    	{
    		bottomCard = 27;
    		location = 27;
    		valueOfEndPoint = deck.get(location);
    		while (deck.get(0) != valueOfEndPoint)
    		{
    			topdeck.add(deck.get(0));
        		deck.remove(0);
    		}
    		while (topdeck.size > 0)
    		{	
    			deck.add(deck.size,topdeck.get(0));
    			topdeck.remove(0);
    		}
    		deck.add(bottomCard);
    	}
    	else 
    	{
    		for (int i = 0; i < bottomCard; i++)
    		{
    			location++;
    		}
    		valueOfEndPoint = deck.get(location);
    		while (deck.get(0) != valueOfEndPoint)
    		{
    			topdeck.add(deck.get(0));
        		deck.remove(0);
    		}
    		while (topdeck.size > 0)
    		{	
    			deck.add(deck.size,topdeck.get(0));
    			topdeck.remove(0);
    		}
    		deck.add(bottomCard);
    	}
    	System.out.println("Deck after Step 4: " + deck);
    }
    
    //(Last step!) Look at the top card’s value (which is again 1-27, as it was in
    //the previous step). Keep it at the top of the deck. Count down the deck
    //by that many cards. Record the value of the NEXT card in the deck, but
    //don’t remove it from the deck. If that next card happens to be a joker,
    //don’t record anything. Leave the deck the way it is, and start again from
    //the first step, repeating until that next card is not a joker
    private static int step5(CircularLinkedList<Integer> deck)
    {
    	int nextCard = 0;
    	int topCard = deck.get(0);
    	if (topCard == 27 || topCard == 28)
    	{
    		topCard = 27;
    	}
    	nextCard = deck.get(topCard);
    	if (nextCard == 27 || nextCard == 28)
    	{
    		step1(deck);
    		step2(deck);
    		step3(deck);
    		step4(deck);
    		step5(deck);
    	}
    	System.out.println("Deck after Step 5: " + deck);
    	System.out.println("Key Value: " + nextCard);
    	
    	return nextCard;
    }
    
    
    public static void main(String[] args) 
    {

    	//creates deck
        CircularLinkedList<Integer> deck = new CircularLinkedList<>();
        CircularLinkedList<Integer> deckB = new CircularLinkedList<>();
        
        for (int i = 6; i <=14; i++)
        	deck.add(i);
        deck.add(28);
        for (int j = 1; j <= 5; j++)
        	deck.add(j);
        for (int k = 27; k >= 22; k--)
        	deck.add(k);
        for (int l = 15; l <= 21; l++)
    	   deck.add(l);
        
        for (int i = 0; i < deck.size; i++)
        {
        	deckB.add(deck.get(i));
        }
        
        System.out.println("Deck after Step 0: " + deck);
        
        //creates scanner to read user input
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter a string to decrypt: ");  
        String output= sc.nextLine().toLowerCase();              //reads string
        while (output.length() == 0)
        {
        	System.out.println("You did not type anything. Enter a string to decrypt: ");  
            output= sc.nextLine().toLowerCase();              //reads string
        }
        System.out.println("You have entered: " + output + "\nNote: if you included characters not between a and z,"
        		+ " they will not be included in the decrypted message.");      
        
        //converts string to char Array, then arraylist. With the arraylist, it is 
        //made divisible by 5 and certain characters are removed.
        char[] mycharArray = output.toCharArray();
        List<Character> myCharlist = new ArrayList<Character>();
        for(char c: mycharArray) 
        {
        	if (c < 'a' || c > 'z')
        	{
        		continue;
        	}
        	else
        	{
        		myCharlist.add(c);
        	}
        }
        while (myCharlist.size() % 5 != 0)
        {
        	myCharlist.add('x');
        }
        System.out.println("Your edited phrase,separated, is " + myCharlist);
        
        String encryptedOutput = "";
        for (char c : myCharlist)
        {
        	encryptedOutput += encryptChar(c,getKey(deck));
        }
        System.out.println("The encrypted string is " + encryptedOutput);
        
        char[] myEncryptArray = encryptedOutput.toCharArray();
        List<Character> myEncryptList = new ArrayList<Character>();
        for(char c: myEncryptArray) 
        {
        	myEncryptList.add(c);
        }
        
        
        String decryption = "";
        for (char c: myEncryptList)
        {
        	decryption += decryptChar(c,getKey(deckB));
        }
        System.out.println("The decrypted string is " + decryption);
        
        
        System.out.println("Would you like to decrypt another message?: (y for yes, n for no) ");
        String answer = sc.nextLine();
        
       while (answer.equals("y") || answer.equals("Y"))
        {
        	System.out.println("Enter a string to decrypt: ");  
            output= sc.nextLine().toLowerCase();              //reads string
            while (output.length() == 0)
            {
            	System.out.println("You did not type anything. Enter a string to decrypt: ");  
                output= sc.nextLine().toLowerCase();              //reads string
            }
            System.out.println("You have entered: " + output + "\nNote: if you included characters not between a and z,"
            		+ " they will not be included in the decrypted message.");      
            
            //converts string to char Array, then arraylist. With the arraylist, it is 
            //made divisible by 5 and certain characters are removed.
            mycharArray = output.toCharArray();
            myCharlist = new ArrayList<Character>();
            for(char c: mycharArray) 
            {
            	if (c < 'a' || c > 'z')
            	{
            		continue;
            	}
            	else
            	{
            		myCharlist.add(c);
            	}
            }
            while (myCharlist.size() % 5 != 0)
            {
            	myCharlist.add('x');
            }
            System.out.println("Your edited phrase,separated, is " + myCharlist);
            
            encryptedOutput = "";
            for (char c : myCharlist)
            {
            	encryptedOutput += encryptChar(c,getKey(deck));
            }
            System.out.println("The encrypted string is " + encryptedOutput);
            
            decryption = "";
            for (char c: myCharlist)
            {
            	decryption += decryptChar(c,getKey(deck));
            }
            System.out.println("The decrypted string is " + decryption);
             
             System.out.println("Would you like to decrypt another message?: (y for yes, n for no) ");
             answer = sc.nextLine();
        }
        if (answer.equals("n") || answer.equals("N"))
		{
        	System.out.println("Okay. Program ends then!");
		}
    }
}
