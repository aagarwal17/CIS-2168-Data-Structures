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
        //ASK WHY YOU DO NOT WRITE CURRENT DIRECTION = MATH.RANDOM... HERE BUT YOU DO IN THE FOX CLASS?
    }
    
    /**
     * Controls the movement of the rabbit
     * 
     * @return the direction in which the rabbit wishes to move.
     */
    int decideMove() 
    {

        int possibleDirection = -1;
        int edgeCount = 0;
        int openArea;
        int startingPosition;
        int maxTurn;
        int lastDirection;

        // look all around for fox
        canSeeFoxNow = false;
        for (int i = Model.MIN_DIRECTION; i <= Model.MAX_DIRECTION; i++) {
            if (look(i) == Model.FOX) {
                canSeeFoxNow = haveSeenFox = true;
                timesSeenFox++;
                directionToFox = i;
                distanceToFox = distance(i);
            }
            // looks to see if the rabbit is close to the edges
            else if (look(i) == Model.EDGE && distance(i) < 4) {
                edgeCount++;
            }
        }

        // if the rabbit has not seen fox and is close to the edges,
        // move away from the edges
        openArea = 0;
        //FIGURE OUT WHAT OPENAREA VARIABLE REPRESENTS
        if (!haveSeenFox) 
        {
            if (edgeCount > 0) 
            {
                for (int i = Model.MIN_DIRECTION; i < Model.MAX_DIRECTION; i++) {
                    if (canMove(i) && distance(i) > openArea) 
                    {
                        openArea = distance(i);
                        possibleDirection = i;
                    }
                }
            }
            if (openArea == 0) return Model.STAY;
            else return possibleDirection;
        }

        // if the rabbit can see fox now, and if has seen fox
        // at least twice recently, decide a move
        if (canSeeFoxNow && (timesSeenFox >= 3)) 
        {
            timesSeenFox--;
            if (distanceToFox < 2) 
            {
                startingPosition = 3;
                maxTurn = 2;
            }
            else if (distanceToFox < 5) 
            {
                startingPosition = 2;
                maxTurn = 4;
            }
            else 
            {
                startingPosition = 1;
                maxTurn = 6;
            }
            lastDirection = currentDirection;
            currentDirection = possibleDirection = Model.turn(directionToFox, startingPosition);
            if (lastDirection == possibleDirection) {
                openArea = 0;
            }
            else openArea = distance(possibleDirection);
            for (int i = 0; i < maxTurn; i++) {
                possibleDirection = Model.turn(possibleDirection, 1);                    
                if (canMove(possibleDirection) && 
                    possibleDirection != lastDirection) {
                    if ((distance(possibleDirection) > openArea) && 
                        (possibleDirection != Model.turn(directionToFox, 4))) {
                        openArea = distance(possibleDirection);
                        currentDirection = possibleDirection;
                    }
                }
            }
            return currentDirection;
        }

        // if the rabbit can see the fox now, decide a move
        if (canSeeFoxNow) 
        {
            if (distanceToFox < 2) 
            {
                startingPosition = 3;
                maxTurn = 2;
            }
            else if (distanceToFox < 5) 
            {
                startingPosition = 2;
                maxTurn = 4;
            }
            else 
            {
                startingPosition = 1;
                maxTurn = 6;
            }
            currentDirection = possibleDirection = 
                               Model.turn(directionToFox, startingPosition);
            openArea = distance(possibleDirection);
            for (int i = 0; i < maxTurn; i++) 
            {
                possibleDirection = Model.turn(possibleDirection, 1);                    
                if (canMove(possibleDirection)) 
                {
                    if ((distance(possibleDirection) > openArea) && 
                            (possibleDirection != Model.turn(directionToFox, 4))) 
                    {
                        openArea = distance(possibleDirection);
                        currentDirection = possibleDirection;
                    }
                }
            }
            return currentDirection;
        }
        // stay at current position if do not see fox
        else return Model.STAY;
    }
}