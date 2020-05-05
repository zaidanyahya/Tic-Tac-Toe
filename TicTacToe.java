import java.util.Random;
import java.util.Scanner;
import java.io.IOException;

/**
 * TicTacToe
 */
public class TicTacToe {
    static Scanner scanner = new Scanner(System.in);
    static boolean[] playerList = new boolean[10];
    static boolean[] cpuList = new boolean[10];
    public static void main(String[] args) {
        String[][] gameBoard = { {"+", "-", "-", "-+-", "-", "-+-", "-", "-", "+"},
                {"|", " ", "1", " | ", "2", " | ", "3", " ", "|"}, {"+", "-", "-", "-+-", "-", "-+-", "-", "-", "+"},
                {"|", " ", "4", " | ", "5", " | ", "6", " ", "|"}, {"+", "-", "-", "-+-", "-", "-+-", "-", "-", "+"},
                {"|", " ", "7", " | ", "8", " | ", "9", " ", "|"}, {"+", "-", "-", "-+-", "-", "-+-", "-", "-", "+"}, };
        int[][] boardPos = {{0, 0}, {1, 2}, {1, 4}, {1, 6}, {3, 2}, {3, 4}, {3, 6}, {5, 2}, {5, 4}, {5, 6}};
        
        for (int i = 1; i < 10; i++) {
            playerList[i] = false;
            cpuList[i] = false;
        }
        
        // Game Title
        clrscr();
        System.out.println("==============================");
        System.out.println("======== TIC TAC TOE =========");
        System.out.println("==============================");
        
        String player = chooseHuman();
        String cpu = (player == "X") ? "O"  : "X";
        boolean finish = false;
        int turn = 0;
        if(player == "O"){
            printBoard(gameBoard);
            while (!finish && turn < 9){
                choosePos(gameBoard, boardPos, player, playerList, "player");
                printBoard(gameBoard);
                turn++;
                finish = checkWinning("player");
                if(finish) {
                    System.out.println("   Congratulations! You Won!");
                    continue;
                }
                if(turn < 9) {
                    choosePos(gameBoard, boardPos, cpu, cpuList, "cpu");
                    printBoard(gameBoard);
                    turn++;
                    finish = checkWinning("cpu");
                    if(finish) System.out.println("      Sorry, you lost :(");
                }
            }
        }else{ 
            while (!finish && turn < 9){
                choosePos(gameBoard, boardPos, cpu, cpuList, "cpu");
                printBoard(gameBoard);
                turn++;
                finish = checkWinning("cpu");
                
                if(finish) {
                    if(finish) System.out.println("      Sorry, you lost :(");
                    continue;
                }
                if(turn < 9) {
                    choosePos(gameBoard, boardPos, player, playerList, "player");
                    printBoard(gameBoard);
                    turn++;
                    finish = checkWinning("player");
                    if(finish) System.out.println("   Congratulations! You Won!");
                }
            }
        }
        if(!finish) System.out.println("            Draw!");
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
            System.out.print("        ");
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
            System.out.println("   Your choice is incorrect!");
            System.out.print("Choose your sign (Type O/X)? ");
            player = scanner.next().charAt(0);
        }
        
        String res;
        if(player == 'o' || player=='O'){
            res = "O";
            System.out.println("  You choose " + res + "! You go first!" + "\n");
        } else {
            res = "X";
            System.out.println(" You choose " + res + "! You go second!" + "\n");
        }
      
        return res;
    }

    public static void choosePos(String[][] gameBoard, int[][] boardPos, String sign, boolean[] list, String player) {
        String slot;
        int pos=0;   

        if(player == "player"){
            System.out.println("          Your turn");
            do{
                System.out.print("   Choose positions (1-9)! ");
                do{
                    pos = scanner.nextInt();
                    if(pos < 1 || pos > 9) {
                        System.out.println("   Your choice is incorrect!");
                        System.out.print("   Choose positions (1-9)! ");
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
            System.out.println("          CPU turn");
        }
        list[pos]  = true;
        gameBoard[boardPos[pos][0]][boardPos[pos][1]] = sign;
    }
    public static boolean checkWinning(String player) {
        boolean result = false;
        int[][] win = {{1,2,3}, {4,5,6}, {7,8,9}, {1,4,7}, {2,5,8}, {3,6,9}, {1,5,9}, {3,5,7}} ;
        boolean[] list;
        list = (player == "player") ? playerList : cpuList;

        for(int[] row: win){
            if(list[row[0]] && list[row[1]] && list[row[2]]) result = true;
            if(result) break;
        }

        return result;
    }
}