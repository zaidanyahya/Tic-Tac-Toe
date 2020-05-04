import java.util.Scanner;

/**
 * TicTacToe
 */
public class TicTacToe {

    public static void main(String[] args) {
        System.out.flush();
        String[][] gameBoard = 
            {
                {"-", "-", "-+-", "-", "-+-", "-", "-"},
                {" ", "1", " | ", "2", " | ", "3", " "},
                {"-", "-", "-+-", "-", "-+-", "-", "-"},
                {" ", "4", " | ", "5", " | ", "6", " "},
                {"-", "-", "-+-", "-", "-+-", "-", "-"},
                {" ", "7", " | ", "8", " | ", "9", " "},
                {"-", "-", "-+-", "-", "-+-", "-", "-"},
            };

        printBoard(gameBoard);
    }

    public static void printBoard(String[][] gameBoard){
        for(String[] row: gameBoard){
            for(String col:row){
                System.out.print(col);
            }
            System.out.println();
        }
    }
}