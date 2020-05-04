import java.util.Scanner;
import java.io.IOException;

/**
 * TicTacToe
 */
public class TicTacToe {

    public static void main(final String[] args) {

        final String[][] gameBoard = { { "-", "-", "-+-", "-", "-+-", "-", "-" },
                { " ", "1", " | ", "2", " | ", "3", " " }, { "-", "-", "-+-", "-", "-+-", "-", "-" },
                { " ", "4", " | ", "5", " | ", "6", " " }, { "-", "-", "-+-", "-", "-+-", "-", "-" },
                { " ", "7", " | ", "8", " | ", "9", " " }, { "-", "-", "-+-", "-", "-+-", "-", "-" }, };

        // Game Title
        clrscr();
        System.out.println("==============================");
        System.out.println("======== TIC TAC TOE =========");
        System.out.println("==============================");

        char player = chooseHuman();
        printBoard(gameBoard);

    }

    public static void clrscr() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {
        }
    }
    
    public static void printBoard(final String[][] gameBoard) {
        for (final String[] row : gameBoard) {
            for (final String col : row) {
                System.out.print(col);
            }
            System.out.println();
        }
    }
    
    public static char chooseHuman(){
        Scanner scan = new Scanner(System.in);
        System.out.println();
        System.out.print("Choose your sign (Type O/X)? ");
        char player = scan.next().charAt(0);
        while(player != 'o' && player != 'x' && player != 'O' && player != 'X') {
            System.out.println("Your choice is incorrect!");
            System.out.print("Choose your sign (Type O/X)? ");
            player = scan.next().charAt(0);
        }
        String msg;
        if(player == 'o' || player=='O'){
            player = 'O';
            msg = "You go first!";
        } else {
            player = 'X';
            msg = "You go second!";
        }
        
        System.out.println("You choose "+player+"! "+msg+"\n");
        scan = null;
        return player;
    }
}