import java.util.Scanner;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Connectfour
{

    //initializing
    private static final char[] players = new char[] {'X', 'O'};
    private int height,width;
    private int col = -1, row = -1;
    private  char[][] grid;

    //main function takes the user input from standard in and gives the user instructions on how to play the game
    //calls the winner function to check and see and call other functions
    public static void main(String[] args)
    {
         Scanner input = new Scanner(System.in);
         int height = 6, width = 8;
         int count = height * width;
         Connectfour board = new Connectfour(height, width);
         System.out.println("This a Connect 4 game,use 0-" + (width - 1) + " to choose a column. " +
                    "The goal of the game is to get four in a row, either vertically, horizontally or diagonally. \n" +
                    "The Player X gets the first turn to start the game and then O's turn \n " +
                    "Start the game by picking a number from the range 0 to 4 and take turns, Hope you enjoy!");
         System.out.println(board);

         for (int player = 0; count-- > 0; player = 1 - player)
         {
             char symbol = players[player];
             board.choose(symbol, input);
             System.out.println(board);
             if (board.Winner())
             {
                 System.out.println("Player " + symbol + " won congratulation!");
                 return;

             }

        }
        System.out.println("Game over, NO winner :(.");
    }

    //constructor and fills the grid with empty dashes
    private Connectfour(int height, int width)
    {
        this.width = width;
        this.height = height;
        this.grid = new char[height][];

        for ( int i = 0; i < height; i++)
        {
            Arrays.fill(this.grid[i] = new char[width],    '-'); //fills the grid by _ empty values
        }
    }

    public String toString()  //conversion to string function
    {
        return IntStream.range(0, this.width)  //inserts the given value by the user and places the object in place
                .mapToObj(Integer:: toString)
                .collect(Collectors.joining() )+"\n"+
                Arrays.stream(this.grid)
                        .map(String::new)
                        .collect(Collectors.joining("\n"));
    }

    private String checkverti () //checks the vertical column of the last played position
    {
        StringBuilder bulid = new StringBuilder(this.height); //Constructs a string builder with no characters in it
        for (int i = 0; i < this.height ; i++) // i represents the height
        {
            bulid.append(this.grid[i][this.col]);
        }
        return bulid.toString();
    }

    private String checkhori()  //checks horizontally
    {
        return new String (this.grid[this.row]);
    }


    private String  checkdiag1()  //checks the diagonal from left to right \
    {
        StringBuilder build = new StringBuilder(this.height);
        for (int i = 0; i < this.height ; i++)
        {
            int  j  = this.col - this.row + i;
            if (0<=j && j < this.width)
            {
                build.append(this.grid[i][j]);
            }
        }
        return build.toString();
    }

    private String checkdiag2()  //checks the diagonal from right to left
    {
        StringBuilder bulid = new StringBuilder(this.height);
        for (int i = 0; i < this.height ; i++)
        {
            int  j  = this.col + this.row - i;
            if (0<=j && j < this.width)
            {
                bulid.append(this.grid[i][j]);
            }
        }
        return bulid.toString();

    }
//places the content in place
    private static boolean content(String array, String point)
    {

        return array.contains(point);
    }

    //handles the user input
    private void choose(char userinput, Scanner input)
    {
        while(true)
        {
            System.out.print("\nPlayer " + userinput + " turn: ");
            int save = input.nextInt();


            if (! (0 <= save && save < this.width)) {
                System.out.println("Column must be between 0 and " +
                        (this.width - 1));
                continue;
            }
            for (int i = this.height - 1; i >= 0; i--) {
                if (this.grid[i][save] == '-') {
                    this.grid[this.row=i][this.col=save] = userinput;
                    return;
                }
            }
            System.out.println("that row is full, try other one!");
        }
    }

    private boolean Winner()  //checks if the pick and see if it any combination of the winner 4
    {
        char save = this.grid[this.row][this.col];
        String streak = String.format("%c%c%c%c", save, save, save, save);
        return content(this.checkhori(), streak) || content(this.checkverti (), streak) || //check for row of 4 x's or o's
                content(this.checkdiag1(), streak) || content(this.checkdiag2(), streak);
    }
}
