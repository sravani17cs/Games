import java.util.Scanner;
import java.util.ArrayList;
import java.util.StringTokenizer;
public class Tictaetoe
{
    private static ArrayList<int[]> memo = new ArrayList<int[]>();  //placeholder for each array element
    private static char[][] grid = new char[3][3]; //creates the grid of 3 by 3
    private static Scanner input = new Scanner(System.in); //gets the input from the user command line
    private static int player;
    //gets the input for the users
    private static int[] getcell(int player){
        int[] move ;
        while (true){
            System.out.println(" ");
            System.out.println("Player " + player + " turn. Please enter your  move:" ); //prompt for the user
            String moveString = input.nextLine(); //checks if the right responds

            if (isValid(moveString)) //checks if it is valid
            {
                move = convertArray(moveString); // converts the array value int the position X or O should be placed

                if (!inputcheck(move)) //if not exist the program
                {
                    break;
                }

            } else {
                System.out.println("invalid move please type in integer space integer ( 2 2)");
            }
        }
        return move;
    }
    //initializes the bord with null values
    public static void setCell(int player, char[][] grid) {
        char hold = '\u0000'; //initializing a null object

        if (player == 1) // checking if it's player 1
        {
            hold = 'X';   //set the hold to 'O'
        }
        else if (player == 2) //checks if it's player 2
        {
            hold = 'O'; //sets the hold to 'X'
        }
        int[] store = getcell(player);   // calls the get function to get the values from the user
        int row = store[0]; //stores the values
        int col = store[1];

        grid[row][col] = hold;   //calls the grid to hold the values
        Memory(row, col);//call memory
    }
    //  memory stores values at the position can't be used again
    private static void Memory(int row, int col)
    {
        int[] savearray = new int[2];  //saves the values
        savearray[0] = row;
        savearray[1] = col;
        memo.add(savearray);
    }

    //assings the memory size
    private static boolean checkgrid()
    {
        return (memo.size() == Math.pow(2, 3));
    }

    // resets the grid and fills with - with easch restart of the game
    public static void reset()
    {
        for(int i =0 ; i < 3; i++)
        {
            for(int j = 0; j<3; j++)
            {
                grid[i][j] = '-';
            }
        }
    }
    //used to print the grid
    private static void printGrid(char[][] grid)
    {

        for (int i = 0; i < 3; i++){
            System.out.println(" ");
            for (int j = 0; j < 3; j++){
                System.out.print(grid[i][j] + " ");
            }
        }

        System.out.println(" ");

    }
    //check where that grid is taken
    private static boolean inputcheck(int[] move){
        for (int i = 0; i < memo.size(); i++){
            if (memo.get(i)[0] == move[0] && memo.get(i)[1] == move[1]){
                System.out.println("sorry that grid is taken, try another!");
                return true;
            }
        }
        return false;

    }
    // takes in the array from the user and place the X and Y in the right position
    private static int[] convertArray(String str)
    {
        StringTokenizer save = new StringTokenizer(str);
        int [] move = new int[2];
        for (int i = 0; i < 2; i++){
            move[i] = Integer.parseInt(save.nextToken());
            move[i]--;
        }
        return move;
    }
    //checks the results
    private static boolean PlaceMark()
    {
        for (int i = 0; i < 3; i++)
        {
            //checks the row
            if (grid[i][0] == grid [i][1] && grid [i][0] == grid[i][2])
            {
                if (grid[i][0] != '-')
                    return true; //because char '-' is empty
            }
            //checks the colunm
            if (grid[0][i] == grid [1][i] && grid [0][i] == grid[2][i])
            {
                if (grid[0][i] != '-')
                    return true;
            }
        }
        //checks the diagonals
        if ((grid[1][1] == grid[0][0] && grid[1][1] == grid[2][2]) ||
                (grid[1][1] == grid[0][2] && grid [1][1] == grid[2][0])){
            if (grid[1][1] != '-')
                return true;
        }

        return false;

    }
    // checks if a valid move and if the input is in range
    private static boolean isValid(String str){
        StringTokenizer token = new StringTokenizer(str);
        for (int i = 0; i < 2; i++)
        {
            int myNumber = Integer.parseInt(token.nextToken());
                if (!(myNumber >= 1 && myNumber <= 3))
                {
                    return false;
                }

        }

        if (token.hasMoreTokens())
         {
            return false;
         }
            return true;

    }

    //after playing a game asks the user to restart or exist the game.
    private static boolean wantToRestart(){

        while(true){

            System.out.println("type (restart) to play again or type (exit) to quit:");

            String str = input.nextLine();

            if (str.equals("restart")){
                return true;
            }
            else if (str.equals("exit")){
                return false;
            } else {
                System.out.println("invalid command");
            }

        }
    }
    //main function
    public static void main (String[] args)  //main program
    {
        while(true)   //game can go on until it's a draw or either of the players win
        {
            reset();    //resets before each game starts
            memo.clear(); //clears each array
            //instructions for the users
            System.out.println("Welcome to a 2-player tic tac toe game.");
            System.out.println("How to play: In turn, each player will type in 2 numbers ");
            System.out.println("(row and column) where the O or the X will be placed.");
            System.out.println("Player 1 will be an O and Player 2 will an X");
            System.out.println("all you need to do is enter a array number like 2 2 ");
            System.out.println(" ");

            //alternates through each places and calls the function
            for ( player = 1; player <=2; player++ )
            {
                setCell(player, grid);  //sets the player and gride
                printGrid(grid);
                if(PlaceMark()) break;
                if (checkgrid()){
                    player = 0;
                    break;
                }
                if (player == 2)
                {
                    player = 0;
                }
            }
            //checking if there is a winner
            if (player != 0){
                System.out.println("player  " +   player  + " won!!!");
            } else {
                System.out.println("draw");
            }
            if (!wantToRestart()){
                System.out.println("Gamer Over, GOOD JOB!!!!!");
                break;
            }
        }

    }

}


