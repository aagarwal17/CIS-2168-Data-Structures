/**
 * Arun Agarwal Cheater's Hangman
 */
import java.util.*;
import java.io.*;

public class Main
{

     static Set<String> dictionary = new TreeSet<String>();

     //Method to read in our words.txt file
     private static void loadWordsFromTextFile()
     {
          try
          {
               File f = new File("words.txt");
               BufferedReader b = new BufferedReader(new FileReader(f));
               String readLine = "";
               while ((readLine = b.readLine()) != null)
               {
                    dictionary.add(readLine);
               }
          }
          catch (Exception e)
          {
               e.printStackTrace();
          }
     }
     //Main Method
     public static void main(String args[])
     {
          //Need a scanner for user input
          Scanner sc = new Scanner(System.in);
          //Loading in words from textfile
          loadWordsFromTextFile();
          
          System.out.println("Enter word length: ");
          int wordLength = sc.nextInt();

          System.out.println("Enter number of guesses: ");
          int numberOfGuesses = sc.nextInt();

          Driver hangman = new Hangman(dictionary, wordLength, numberOfGuesses);
         
          //getting word
          String wordSelectedFromDictionary = hangman.getWord();
          if (wordSelectedFromDictionary == null)
          {
               System.out.println("No word of this length " + wordLength + " in the dictionary ");
          }
          System.out.println(hangman.getGuessesRemaining() + " guesses left:\t " + new String(hangman.getState()));
          
          while (!hangman.isGameOver())
          {
               System.out.println("Enter a Guess: ");
               char guessedChar = sc.next().trim().charAt(0);
               hangman.makeGuess(guessedChar);
               System.out.println(hangman.getGuessesRemaining() + " guesses left:\t " + new String(hangman.getState()));
               System.out.println(hangman.getWord());
          }

          if (hangman.isGameWon())
          {
               System.out.println("You win!");
          }

          else
          {
               System.out.println("You lost!");
               System.out.println("The word was: " + hangman.getWord());
          }
     }
}