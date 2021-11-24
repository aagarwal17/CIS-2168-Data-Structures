/**
 * Arun Agarwal Data Structures Cheater's Hangman Assignment
 */
import java.util.Set;
import java.util.HashSet;

public abstract class Hangman
{
     //instance variables to keep track of game
     public static final char BLANK = '-';

     //the number of guesses the player has left
     protected int guessesRemaining;

     //the length of the secret word
     protected int length;

     //the current state of the game. There will be a blank at each unrevealed location and a character at each revealed location
     protected char[] state;

     //stores the guesses made by the user so far
     protected Set guesses;

     //Constructor
     public Hangman(int length, int guessCount)
     {

          //initializing two variables that will be provided values by the user
          this.guessesRemaining = guessCount;
          this.length = length;

          // initialize the state with all blanks
          state = new char[length];
          for (int i = 0; i < state.length; i++)
          {
               state[i] = BLANK;
          }
          // initialize the guesses set to an empty set
          guesses = new HashSet<>();
     }

     //Checks if player won game
     public final boolean isGameWon()
     {
          return (getBlanksRemaining() <= 0);
     }
     //Checks if player lost game
     public final boolean isGameLost()
     {
          return (getGuessesRemaining() <= 0);
     }
     //Checks if the game has finished
     public boolean isGameOver()
     {
          return isGameWon() || isGameLost();
     }

     //Gets the number of guesses remaining
     public final int getGuessesRemaining()
     {
          return guessesRemaining;
     }
     //Gets the number of blanks remaining
     public final int getBlanksRemaining()
     {
          int blankCount = 0;
          for (int i = 0; i < state.length; i++)
          {
               if (state[i] == BLANK)
               {
                    blankCount++;
               }
          }
          return blankCount;
     }
     //Method that allows player to make a guess with a char 'c'
     public final boolean makeGuess(char c)
     {
    	  //I wanted to make guess lowercase and insure that it is a letter using the methods below
          c = Character.toLowerCase(c);

          //Returning false if the char is not alphabetical, if it has already been guessed, or if the game is over
          if (!Character.isAlphabetic(c) || guesses.contains(c) || isGameOver())
          {
               return false;
          }

          //If we have made it pass the above if statement, we can then add the character to our set of guesses
          guesses.add(c);

          //Testing outcome of guess, having already shown that it meets the things above
          if (makeNewGuess(c))
          {
               //guess was successful, return true
               return true;
          }
          else //guess was unsuccessful, decrease the amount of guesses by one and return false
          {
               guessesRemaining--;
               return false;
          }
     }

     //My toStrng method (just printing out the state bc that's all we need, I hope)
     public String toString()
     {
          return new String(state);
     }
     
     //Updates the state with the given character
     protected abstract boolean makeNewGuess(char c);
     //implemented in subclasses!

     public abstract String getWord();
     //implemented in subclasses!
     
     //returns state
     public char[] getState()
     {
          return state;
     }
     //returns number of guesses
     public Set getGuesses()
     {
          return guesses;
     }

}