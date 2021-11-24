/**
 * 
 * @author Arun Agarwal
 *Fox and Rabbit Project
 */
public class Rabbit extends Animal 
{
	// instance variables
    private boolean haveSeenFox = false;
    private boolean canSeeFoxNow = false;
    private int timesSeenFox = 0;
    private int distanceToFox = 0;
    private int directionToFox;
    private int currentDirection;
    
    /**
     * Constructs a rabbit in the given model, at the given position
     * in the field.
     * 
     * @param model  the model that controls this rabbit.
     * @param row    the row of the field containing this rabbit.
     * @param column the column of the field containing this rabbit.
     */
    public Rabbit(Model model, int row, int column) 
    {
        super(model, row, column);
    }
    
    int decideMove() 
    {
    	int edgeCount = 0;
    	int moveableArea;
    	int possibleDirection = -1;
    	int previousDirection;
    	
    	//looks all around for fox
    	 canSeeFoxNow = false;
         for (int i = Model.MIN_DIRECTION; i <= Model.MAX_DIRECTION; i++) 
         {
             if (look(i) == Model.FOX) 
             {
                 canSeeFoxNow = haveSeenFox = true;
                 timesSeenFox++;
                 directionToFox = i;
                 distanceToFox = distance(i);
             }
             // looks to see if the rabbit is close to the edges
             else if (look(i) == Model.EDGE && distance(i) < 4) 
             {
                 edgeCount++;
             }
         }
         
         //if the rabbit has not seen the fox and is close to the edges, move away from the edges
         moveableArea = 0;
         if (!haveSeenFox)
         {
        	 if (edgeCount > 0) 
             {
                 for (int i = Model.MIN_DIRECTION; i < Model.MAX_DIRECTION; i++) 
                 {
                     if (canMove(i) && distance(i) > moveableArea) 
                     {
                         moveableArea = distance(i);
                         possibleDirection = i;
                     }
                 }
             }
             if (moveableArea == 0) 
            	 {
            	 return Model.STAY;
            	 }
             else 
            	 {
            	 return possibleDirection;
            	 }
         }
         
         //if the rabbit can see the fox currently, and if it has seen fox at least twice recently, decide a move
         if (canSeeFoxNow && (timesSeenFox >= 2))
         {
        	 //decrease timesSeenFox because we are making a move
        	 timesSeenFox--;
        	
        	 /**I tried to do something with making specific scenarios to increase survival
        	 if (distanceToFox < 2)
        		 startingPosition = 3;
        	 else if (distanceToFox < 5)
        		 startingPosition = 2;
        	 else
        		 startingPosition = 1;
        	 **/
        	 
        	 //store the current direction in the last direction
        	 previousDirection = currentDirection;
        	 possibleDirection = Model.turn(directionToFox, 3);
        	 currentDirection = possibleDirection;
        	 
        	 //if the last direction that was stored is the same as the possible one, there must
        	 //be no movable area
        	 if (previousDirection == possibleDirection)
        	 {
        		 moveableArea = 0;
        	 }
        	 else
        	 {
        		 moveableArea = distance(possibleDirection);
        	 }
        	 
        	 //if all of the below conditions are met, reevaluate the moveableArea and change the currentDirection
        	 // to the new possibleDirection made
        	 for (int i = 0; i < 3; i++)
        	 {
        		 possibleDirection = Model.turn(possibleDirection, 1);                    
                 if (canMove(possibleDirection) && possibleDirection != previousDirection) 
                 {
                     if ((distance(possibleDirection) > moveableArea) && (possibleDirection != Model.turn(directionToFox, 4))) 
                     {
                         moveableArea = distance(possibleDirection);
                         currentDirection = possibleDirection;
                     }
                 }    
        	 }
        	 return currentDirection; 
         }
         
         // if the rabbit can see the fox now, decide a move
         if (canSeeFoxNow) 
         { 
        	 /**I tried to do something with making specific scenarios to increase survival
             if (distanceToFox < 2) 
             {
                 startingPosition = 3;
             }
             else if (distanceToFox < 5) 
             {
                 startingPosition = 2;
             }
             else 
             {
                 startingPosition = 1;
             }
             **/
             possibleDirection = Model.turn(directionToFox, 3);
         	 currentDirection = possibleDirection;
             moveableArea = distance(possibleDirection);
             for (int i = 0; i < 3; i++) 
             {
                 possibleDirection = Model.turn(possibleDirection, 1);                    
                 if (canMove(possibleDirection)) 
                 {
                     if ((distance(possibleDirection) > moveableArea) && (possibleDirection != Model.turn(directionToFox, 4))) 
                     {
                    	 moveableArea = distance(possibleDirection);
                         currentDirection = possibleDirection;
                     }
                 }
             }
             return currentDirection;
         }
         
         //if you do not see the fox, or something else goes wrong, just stay
         return Model.STAY;
    }
}
