/**
 * Arun Agarwal Cheater's Hangman Assignment
 */
import java.util.*;

public class Hangman extends Driver //key thing: I am extending my hangman class
{
	 //Variables:
	
	 //Our dictionary of words
     protected Set<String> dict;
     //Our word, which may change as the program runs because we are cheaters
     private String word;
     //HashSet holding the words without the guessed character (aka cheating)
     private HashSet<String> wordsWithoutGuessedChar;
     //HashSet holding the words with the guessed character
     private HashSet<String> wordsWithGuessedChar;
     //my specific hashSet with the user-specified length
     HashSet<String> mySetWithLength;
     //the previous hashSet
     HashSet<String> previousSet;
     //counter I will use for the program
     private int counter;
     //position variable
     int position;

     //Constructor
     public Hangman(Set<String> dict, int length, int guesses)
     {
    	  //Calling the super classes's constructor to initialize length and guesses
          super(length, guesses);
          //Initializing everything
          this.dict = dict;
          mySetWithLength = getWordsOfLength();
          wordsWithGuessedChar = new HashSet<String>();
          previousSet = new HashSet<String>();
          counter = 0;
          position = 0;
     }
     //We are going to need to count the instances of the specific character
     //Will be used in following methods
     private int countChar(String str, char c)
     {
          int count = 0;
          for (int i = 0; i < str.length(); i++)
          {
               if (str.charAt(i) == c)
               {
            	   count++;
               }
          }
          return count;
     }
     
     //We are using our set with all the words with the guessed char and making a new set out of that with only the single char words
     private void getWordsWithSingleCharMaxSet(HashSet<String> wordsWithGuessedChar, char c)
     {
    	  //Creating a Map!!! Tieing an int to a arraylist of strings
          HashMap<Integer, ArrayList<String>> myhashMap = new HashMap<Integer, ArrayList<String>>();
          //arraylist holding the required words
          ArrayList<String> requiredList = new ArrayList<String>();

          for (int i = 0; i < length; i++)
          {
              //Creating an Iterator 
        	  Iterator<String> hashSetItr = mySetWithLength.iterator();

              ArrayList<String> values = new ArrayList<String>();

               while (hashSetItr.hasNext())
               {
                    String currentWord = hashSetItr.next();
                    boolean isPresent = currentWord.contains(String.valueOf(c));

                    if (isPresent && currentWord.indexOf(c) == i)
                    {
                         values.add(currentWord);
                    }
               }
               myhashMap.put(i, values);
          }
          int max = 0;
          //find the largest amount in hashmap and set that num equal to max, and position to i
          for (int i = 0; i < length; i++)
          {
               if (myhashMap.get(i).size() > max)
               {
                    max = myhashMap.get(i).size();
                    requiredList = myhashMap.get(i);
                    position = i;
               }
          }
          mySetWithLength = new HashSet<String>(requiredList);
     }
     
     
     
     //Getting words with the character into a hash set; needed in the above method
     private HashSet<String> getWordsWithChar(char c)
     {
          HashSet<String> hashSet = new HashSet<String>();
          //Creating new iterator
          Iterator<String> hashSetItr = mySetWithLength.iterator();

          while (hashSetItr.hasNext())
          {

               String currentWord = hashSetItr.next();
               boolean isPresent = currentWord.contains(String.valueOf(c));
               //if the character is present in the word and there is only one of that character
               //in the word, then add the word to the hashSet
               if (isPresent && countChar(currentWord, c) == 1)
               {
                    hashSet.add(currentWord);
               }
          }
          mySetWithLength = hashSet;
          return hashSet;
     }
     //Overriding method in superclass for makeNewGuess. We will update the status of the given character (if it is present or not)
     //We will update the state with the given character if it is present
     //We need different procedures if our set of words has words in it or not. 
     protected boolean makeNewGuess(char c)
     {
          boolean isGuessedCharPresent = false;

          if (counter == 0)
          {
               wordsWithGuessedChar = getWordsWithChar(c);
          }

          if (mySetWithLength.size() != 0)
          {
               previousSet = mySetWithLength;
               getWordsWithSingleCharMaxSet(mySetWithLength, c);
               if (mySetWithLength.size() != 0)

                    getState()[position] = c;

               isGuessedCharPresent = true;

          }
          //Either we are just starting, or the player has made invalid types of guesses
          if (mySetWithLength.size() == 0)
          {
        	  //I am just grabbing a random word from our previous set of words with a certain length because I don't know which word to start with
               if (mySetWithLength.size() == 0 && previousSet.size() != 0)
               {
                    word = getRandomElement(previousSet);
               }

               isGuessedCharPresent = word.contains(String.valueOf(c));
               
               //doing same thing as above--updating state of character if the guessed char is present
               if (isGuessedCharPresent)
               {
                    for (int i = 0; i < word.length(); i++)
                    {
                         if (word.charAt(i) == c)
                         {
                              getState()[i] = c;
                         }
                    }
               }
          }
          return isGuessedCharPresent;
     }
     ////I'm using this method for the getWord method to grab a random word from our hashset of words of a certain length
     private String getRandomElement(HashSet<String> set)
     {
          if (set.size() > 1)
          {
        	   //Generate a random number using nextInt method of the Random class.
               Random random = new Random();
               //Random number between 0 and HashSet.size - 1
               int randomNumber = random.nextInt(set.size());
               //Create an Iterator for our set
               Iterator<String> iterator = set.iterator();
               int currentIndex = 0;
               //The randomstring from the set I will return
               String randomElement = null;
               
              //We have generated a random number, and we are finding our random element that coincides with our random element
              //by iterating through our set until the index of the random number matches with the index of an element in the set
               while (iterator.hasNext())
               {
                    randomElement = iterator.next();
                    //if the current index is equal to the random number we got, return that random word
                    if (currentIndex == randomNumber)
                    {
                    	return randomElement;
                    }
                    currentIndex++;
               }
               return randomElement;
          }
          else //set size is 1
          {
        	  return set.toString();
          }
     }
     //Grabbing all words from our dictionary of that specified length
     private HashSet<String> getWordsOfLength()
     {
          HashSet<String> hashSet = new HashSet<String>();
          Iterator<String> hashSetItr = dict.iterator();

          while (hashSetItr.hasNext())
          {
               String currentword = String.valueOf(hashSetItr.next());
               if (currentword.length() == length)
               {
                    hashSet.add(currentword);
               }
          }
          return hashSet;
     }
     //the getWord method seen from the superclass!
     public String getWord()
     {
          if (counter == 0)
          {
               word = getRandomElement(mySetWithLength);
               return word;
          }
          else
          {
               return word;
          }
     }
}