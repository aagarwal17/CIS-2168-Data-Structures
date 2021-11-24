/**
 * Arun Agarwal Cheater's Hangman Assignment
 */
import java.util.*;

public class StandardHangman extends Hangman //key thing: I am extending my hangman class
{
	//variables
     protected Set<String> dictionary;
     private String word;
     HashSet<String> mySetWithLength;

     //Constructor
     StandardHangman(Set<String> dictionary, int length, int guesses)
     {
          super(length, guesses);
          this.dictionary = dictionary;
          mySetWithLength = getWordsOfLength();
     }
     
     //The makeNewGuess method seen from the superclass! Will update the state with the given character
     protected boolean makeNewGuess(char c)
     {
          boolean isGuessedCharPresent = false;
          
          isGuessedCharPresent = word.contains(String.valueOf(c));
          
          //SEE WHAT THIS DOES...
          // System.out.println("index--"+index);

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
          return isGuessedCharPresent;
     }
     //I'm using this method for the getWord method to grab a random word from our hashset of words of a certain length
     private String getRandomElement(HashSet<String> set)
     {
          //Generate a random number using nextInt method of the Random class.
          Random random = new Random();

          //Random number between 0 and HashSet.size - 1
          int randomNumber = random.nextInt(set.size());

          //Create an Iterator for our set
          Iterator<String> iterator = set.iterator();
          int currentIndex = 0;
          String randomElement = null; //what I will return

          //Iterate the HashSet
          while (iterator.hasNext())
          {
               randomElement = iterator.next();
               // if current index is equal to random number
               if (currentIndex == randomNumber)
               {
            	   return randomElement;
               }
               currentIndex++;
          }
          return randomElement;
     }
     
     //Grabbing all words from our dictionary of that specified length
     private HashSet<String> getWordsOfLength()
     {
          HashSet<String> hashSet = new HashSet<String>();

          Iterator<String> hashSetItr = dictionary.iterator();

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
     //The getWord method seen from the superclass!
     public String getWord()
     {
          if (dictionary.size() > 0)
          {             
               word = getRandomElement(mySetWithLength);
               return word;
          }
          else
          {
               return null;
          }        
     }
}