import java.util.Random;
import java.util.Scanner;
import java.io.IOException;

/**
 * TicTacToe
 */
public class TicTacToe {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        String[][] gameBoard = { { "-", "-", "-+-", "-", "-+-", "-", "-" },
                { " ", "1", " | ", "2", " | ", "3", " " }, { "-", "-", "-+-", "-", "-+-", "-", "-" },
                { " ", "4", " | ", "5", " | ", "6", " " }, { "-", "-", "-+-", "-", "-+-", "-", "-" },
                { " ", "7", " | ", "8", " | ", "9", " " }, { "-", "-", "-+-", "-", "-+-", "-", "-" }, };
        int[][] boardPos = {{0, 0}, {1, 1}, {1, 3}, {1, 5}, {3, 1}, {3, 3}, {3, 5}, {5, 1}, {5, 3}, {5, 5}};
        char[] boardStatus = new char[10];
        for(int i=1;i<10;i++) boardStatus[i] = (char)(48);
        
        // Game Title
        clrscr();
        System.out.println("==============================");
        System.out.println("======== TIC TAC TOE =========");
        System.out.println("==============================");
        
        String player = chooseHuman();
        String cpu = (player == "X") ? "O"  : "X";
        if(player == "O"){
            choosePos(gameBoard, boardPos, player, "player");
            printBoard(gameBoard);
            choosePos(gameBoard, boardPos, cpu, "cpu");
        }else{
            
            choosePos(gameBoard, boardPos, cpu, "cpu");
            printBoard(gameBoard);
            choosePos(gameBoard, boardPos, player, "player");
        }
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
    
    public static void printBoard(String[][] gameBoard) {
        for (String[] row : gameBoard) {
            for (String col : row) {
                System.out.print(col);
            }
            System.out.println();
        }
        System.out.println();
    }
    
    public static String chooseHuman(){
        System.out.println();
        System.out.print("Choose your sign (Type O/X)? ");
        char player = scanner.next().charAt(0);
        while(player != 'o' && player != 'x' && player != 'O' && player != 'X') {
            System.out.println("Your choice is incorrect!");
            System.out.print("Choose your sign (Type O/X)? ");
            player = scanner.next().charAt(0);
        }
        String msg;
        String res;
        if(player == 'o' || player=='O'){
            res = "O";
            msg = "You go first!";
        } else {
            res = "X";
            msg = "You go second!";
        }
        
        System.out.println("You choose " + res + "! " + msg + "\n");
    
        return res;
    }

    public static void choosePos(String[][] gameBoard, int[][] boardPos, String sign, String player) {
        String slot;
        int pos;   

        if(player == "player"){
            System.out.println("Your turn");
            do{
                System.out.print("Choose positions (1-9)! ");
                do{
                    pos = scanner.nextInt();
                    if(pos < 1 || pos > 9) {
                        System.out.println("Incorrect choice!");
                        System.out.print("Choose positions (1-9)! ");
                    } 
                }while(pos < 1 || pos > 9);
                slot = gameBoard[boardPos[pos][0]][boardPos[pos][1]];
                if(slot=="O" || slot=="X") System.out.println("Position occupied, choose other position!");
            }while(slot=="O" || slot=="X");
        }else{
            Random rand = new Random();
            do{
                do{
                    pos = rand.nextInt(9) + 1;
                }while(pos < 1 || pos > 9);
                slot = gameBoard[boardPos[pos][0]][boardPos[pos][1]];
            }while(slot=="O" || slot=="X");
            System.out.println("CPU turn");
        }
        
        gameBoard[boardPos[pos][0]][boardPos[pos][1]] = sign;
    }
}