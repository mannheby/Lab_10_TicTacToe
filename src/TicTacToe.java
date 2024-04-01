import java.util.Scanner;

public class TicTacToe
{
    private static final int ROW = 3; // matrix row amount
    private static final int COL = 3; // matrix column amount
    private static String board [][] = new String [ROW][COL]; // creates matrix

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        int xCoord = 0;
        int yCoord = 0;
        boolean done = false;
        String player = "";
        do
        {
            int moveCount = 0;
            boolean gameOver = false;
            clearBoard();
            do
            {
                xCoord = SafeInput.getRangedInt(in, "Which row do you want your next move to be in?", 1, 3); // gets ranged int for coords
                yCoord = SafeInput.getRangedInt(in, "Which column do you want your next move to be in?", 1, 3);
                xCoord--; // subtract 1 to go from human language to matrix language
                yCoord--;
                moveCount ++;
                if (moveCount % 2 == 0) // alternates the player that goes starting with X
                {
                    player = "O";
                }
                else
                {
                    player = "X";
                }
                if (isValidMove(xCoord,yCoord))
                {
                    board[xCoord][yCoord] = player;
                    display();
                }
                else
                {
                    System.out.println("You can't play there. Try again."); // out put if move is invalid
                    moveCount --;
                }
                if(moveCount >= 5)
                {
                    if (isWin("X")) // if x wins
                    {
                        gameOver = true;
                        System.out.println("Player 1 wins!");
                    }
                    else if (isWin("O")) // of o wins
                    {
                        gameOver = true;
                        System.out.println("Player 2 wins!");
                    }
                }
                if (moveCount >= 7 && isTie()) //f there is a tie
                {
                    gameOver = true;
                    System.out.println("It is a tie.");
                }

            }while(!gameOver);
            done = SafeInput.getYNConfirm(in, "Are you done playing?");
        }while(!done);
    }

    private static void clearBoard() // clears the board to blank
    {
        for(int x = 0; x < ROW; x++) // loops through all rows
        {
            for(int y = 0; y < COL; y++) // loops through all columns
            {
                board[x][y] = " "; // sets the cell at each coord to a space
            }
        }
    }

    private static void display() // displays the board
    {
        for(int x = 0; x < ROW; x++) // loops through all rows
        {
            for(int y = 0; y < COL; y++) // loops through all columns
            {
                System.out.print(board[x][y] + " | "); // prints board
            }
            System.out.println();
        }
    }

    private static boolean isValidMove(int x, int y)
    {
        return board[x][y].equals(" "); // return true if there is a space in the cell
    }

    private static boolean isWin(String player)
    {
        if(isRowWin(player) || isColWin(player) || isDiaWin(player)) // player wins if a column, row, diagonal is filled
        {
            return true;
        }
        return false;
    }
    private static boolean isRowWin(String player)
    {
        for(int x = 0; x < ROW; x++) // loops through all rows
        {
            if(board[x][0].equals(player) && board[x][1].equals(player) && board[x][2].equals(player)) // checks if each cell has the same player
            {
                return true;
            }
        }
        return false;
    }
    private static boolean isColWin(String player)
    {
        for(int y = 0; y < COL; y++) // loops through all columns
        {
            if(board[0][y].equals(player) && board[1][y].equals(player) && board[2][y].equals(player)) // checks if each cell has the same player
            {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiaWin(String player)
    {
        if (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) // checks if each cell has the same player
        {
            return true;
        }
        else if (board[2][0].equals(player) && board[1][1].equals(player) && board[0][2].equals(player))
        {
            return true;
        }
        return false;
    }

    private static boolean isTie()
    {
        boolean checkRowX = false;
        boolean checkRowO = false;
        boolean checkColX = false;
        boolean checkColO = false;
        boolean checkDiaX = false;
        boolean checkDiaO = false;
        if((board[0][0].equals("X") || board[0][1].equals("X") || board[0][2].equals("X")) && (board[1][0].equals("X") || board[1][1].equals("X") || board[1][2].equals("X")) && (board[2][0].equals("X") || board[2][1].equals("X") || board[2][2].equals("X"))) // checks if there is one X in each row
        {
            checkRowX = true;
        }
        if((board[0][0].equals("O") || board[0][1].equals("O") || board[0][2].equals("O")) && (board[1][0].equals("O") || board[1][1].equals("O") || board[1][2].equals("O")) && (board[2][0].equals("O") || board[2][1].equals("O") || board[2][2].equals("O"))) // checks if there is one O in each row
        {
            checkRowO = true;
        }

        if((board[0][0].equals("X") || board[1][0].equals("X") || board[2][0].equals("X")) && (board[0][1].equals("X") || board[1][1].equals("X") || board[2][1].equals("X")) && (board[0][2].equals("X") || board[1][2].equals("X") || board[2][2].equals("X"))) // checks if there is one X in each column
        {
            checkColX = true;
        }
        if((board[0][0].equals("O") || board[1][0].equals("O") || board[2][0].equals("O")) && (board[0][1].equals("O") || board[1][1].equals("O") || board[2][1].equals("O")) && (board[0][2].equals("O") || board[1][2].equals("O") || board[2][2].equals("O"))) // checks if there is one O in each column
        {
            checkColO = true;
        }


        if ((board[0][0].equals("X") || board[1][1].equals("X") || board[2][2].equals("X")) && (board[0][2].equals("X") || board[1][1].equals("X") || board[2][0].equals("X"))) // checks if there is an X in each diagonal
        {
            checkDiaX = true;
        }
        if ((board[0][0].equals("O") || board[1][1].equals("O") || board[2][2].equals("O")) && (board[2][0].equals("O") || board[1][1].equals("O") || board[0][2].equals("O"))) // checks if there is an O in each diagonal
        {
            checkDiaO = true;
        }
        if(checkRowX && checkRowO && checkColX && checkColO && checkDiaX && checkDiaO) // if all the above are true it is a tie
        {
            return true;
        }
        else
        {
            return false;
        }
    }

}