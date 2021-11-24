import java.util.Arrays;

public class EightQueens {

    public static boolean solve(int[][] board, int col){
        if(col >= board.length){
            return true;
        }

        for(int row = 0; row < board.length; row++){
            if(canPlace(board,row,col)){
                board[row][col] = 1;

                if(solve(board, col+1) == true){
                    return true;
                }
                board[row][col] = 0;
            }
        }
        return false;
    }



    public static boolean canPlace(int[][] board, int row, int col){
        return row == col;
    }

    public static void main(String[] args) {
        int[][] board = new int[8][8];
        solve(board,0);

        for(int[] row : board){
            System.out.println(Arrays.toString(row));
        }


    }
}
