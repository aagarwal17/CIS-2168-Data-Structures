/**
 * Arun Agarwal Cheater's Hangman Assignment
 */
import java.util.*;

public class PartialMaliciousHangman extends Hangman
{
	//variables
     protected Set<String> dictionary;
     private String word;
     private HashSet<String> wordsWithoutGuessedChar;
     private HashSet<String> wordsWithGuessedChar;
     HashSet<String> mySetWithLength;
     private int counter;
     int randomNumber;

     public PartialMaliciousHangman(Set<String> dictionary, int length, int guesses)
     {
          super(length, guesses); //calling the super class (hangman)!
         
          //initializing everything
          this.dictionary = dictionary;
          mySetWithLength = getWordsOfLength();
          wordsWithoutGuessedChar = new HashSet<String>();
          wordsWithGuessedChar = new HashSet<String>();
          counter = 0;
          Random random = new Random();
          randomNumber = random.nextInt(2);
     }
     //we are now cheating, so we will get words without the character into a hashset
     private HashSet<String> getWordsWithoutChar(char c)
     {
          HashSet<String> hashSet = new HashSet<String>();
          Iterator<String> hashSetItr = mySetWithLength.iterator();

          while (hashSetItr.hasNext())
          {
               String currentWord = hashSetItr.next();
               boolean isPresent = currentWord.contains(String.valueOf(c));
               if (!isPresent)
               {
                    hashSet.add(currentWord);
               }
          }
          mySetWithLength = hashSet;
          return hashSet;
     }
     //Getting words with the character into a hash set
     private HashSet<String> getWordsWithChar(char c)
     {
          HashSet<String> hashSet = new HashSet<String>();
          Iterator<String> hashSetItr = mySetWithLength.iterator();

          while (hashSetItr.hasNext())
          {
               String currentWord = hashSetItr.next();
               boolean isPresent = currentWord.contains(String.valueOf(c));
               if (isPresent)
               {
                    hashSet.add(currentWord);
               }
          }
          return hashSet;
     }
     //Overriding method in superclass for makeNewGuess. We will update the status of the given character (if it is present or not)
     protected boolean makeNewGuess(char c)
     {
          boolean isGuessedCharPresent = false;
         
          if (counter == 0)
          {
               wordsWithoutGuessedChar = getWordsWithoutChar(c);
               wordsWithGuessedChar = getWordsWithChar(c);
               counter++;
          }
          //creating switch case
          switch (randomNumber)
          {
          case 0:
        //what to do for three different cases of how many letters have been guessed
               if (counter < length / 2)
               {
                    counter++;
                    isGuessedCharPresent = false;
                    wordsWithoutGuessedChar = getWordsWithoutChar(c);
               }
               if (counter == length / 2)
               {
                    word = getRandomElement(wordsWithoutGuessedChar);
                    counter++;
               }
               if (counter > length / 2)
               {
                    isGuessedCharPresent = word.contains(String.valueOf(c));
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
               break;

          case 1:
               if (counter == 1)
               {
                    word = getRandomElement(wordsWithGuessedChar);
                    counter++;
               }
               isGuessedCharPresent = word.contains(String.valueOf(c));
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
               break;
          }
          return isGuessedCharPresent;
     }
     ////I'm using this method for the getWord method to grab a random word from our hashset of words of a certain length
     private String getRandomElement(HashSet<String> set)
     {
          if (set.size() > 0)
          {
        	   //Generate a random number using nextInt method of the Random class.
               Random random = new Random();
               //Random number between 0 and HashSet.size - 1
               int randomNumber = random.nextInt(set.size());
              //Create an Iterator for our set
               Iterator<String> iterator = set.iterator();
               int currentIndex = 0;
               String randomElement = null; //what I will return
               //Iterating the HashSet
               while (iterator.hasNext())
               {
                    randomElement = iterator.next();
                    //if the current index is equal to the random number we got, return that random word
                    if (currentIndex == randomNumber)
                         return randomElement;
                    currentIndex++;
               }
               return randomElement;
          }
          else
          {
               return null;
          }
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