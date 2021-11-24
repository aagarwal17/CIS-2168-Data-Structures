//IndexNode.java
import java.util.ArrayList;
import java.util.List;

public class NodeTest 
{

       // The word for this entry

       String word;

       // The number of occurrences for this word

       int occurrences;

       // A list of line numbers for this word

       List<Integer> list;

      

       IndexNode left;

       IndexNode right;

      

       // Constructors

       // Constructor should take in a word and a line number

       // it should initialize the list and set occurrences to 1

       public NodeTest(String word, int line_number)

       {

             this.word = word;

             this.occurrences = 1;

             list = new ArrayList<Integer>();

             list.add(line_number);

             left = null;

             right = null;

       }

      

       // return the word, the number of occurrences and the lines it appears on

       public String toString()

       {

             return("Word : "+word+" Occurrences : "+occurrences+" Line numbers : "+list.toString());

       }

}