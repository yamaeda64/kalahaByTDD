package view;

import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.Scanner;

import static view.UserInteraction.*;

/**
 * The base view Class of the application
 */
public class ConsoleView
{
    public static final String menuText = "Welcome to Kalaha, press 'N' for new game or 'Q' to Quit";
    public static final String quitText = "Thank you for playing Kalaha.";
    private int houseToPickFrom = -1;
    
    private PrintStream out;
    
    
    public ConsoleView(PrintStream printStream)
    {
        this.out = printStream;
    }
    
    public void showMenu()
    {
       out.println(menuText);
    }
    
    public void showQuitMessage()
    {
        out.println(quitText);
    }
    
    public void clearScreen()
    {
        for(int i = 0; i<70; i++)
        {
            out.println();
        }
    }
    
    protected char getInput()
    {
        Scanner scanner = new Scanner(System.in);
        return scanner.next().toUpperCase().charAt(0);
    }
    
    public UserInteraction collectEvent()
    {
        char input = getInput();
        if(input == 'Q')
        {
            return QUIT;
        }
        else if(input == 'N')
        {
            return PLAY;
        }
        else if(input >= '0' && input <= '9')
        {
            houseToPickFrom = Integer.parseInt(""+input);
            return PICK_BALLS_FROM_HOUSE;
        }
        else
        {
            throw new IllegalArgumentException("Wrong input");
        }
        
    }
    
    public int getNumberAfterInput()
    {
        int returnValue = houseToPickFrom;
        houseToPickFrom = -1;           // reset it to -1 to give error if someone calls it when shouldn't
        return returnValue;
    }
    
    
    public String drawBoard(int playerStore, int computerStore, Iterator<Integer> playerHouses, Iterator<Integer> computerHouses)
    {
        DecimalFormat df = new DecimalFormat("00");
        StringBuilder board = new StringBuilder();
        board.append("             ");
        
        while(computerHouses.hasNext())
        {
            String  house = "(" + df.format(computerHouses.next().intValue()) + ")";
            board.insert(13,house);
           
        }
        board.append("\n");
        
        board.append("computer ");
        board.append("(");
        board.append(df.format(computerStore));
        board.append(")");
        board.append("                        ");
        board.append("(");
        board.append(df.format(playerStore));
        board.append(")");
        board.append(" player\n");
    
        board.append("             ");
        while(playerHouses.hasNext())
        {
            board.append("(");
            board.append(df.format(playerHouses.next()));
            board.append(")");
        }
        board.append("\n");
        
        board.append("               1   2   3   4   5   6");
        out.println(board.toString());
        return board.toString();
    }
    
    public void showChooseHouseText()
    {
        out.print("\nChoose what house to take balls from: ");
    }
}
