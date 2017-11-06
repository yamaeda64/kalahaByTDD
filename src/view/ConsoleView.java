package view;

import java.io.PrintStream;
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
        for(int i = 0; i<50; i++)
        {
            out.println();
        }
    }
    
    protected char getInput()
    {
        Scanner scanner = new Scanner(System.in);
        char input = scanner.next().toUpperCase().charAt(0);
        return input;
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
        else if(input >= '0' || input <= '9')
        {
            houseToPickFrom = Integer.parseInt(""+input);
            return PICK_BALLS_FROM_HOUSE;
        }
        return null;
    }
    
    public int getNumberAfterInput()
    {
        int returnValue = houseToPickFrom;
        houseToPickFrom = -1;           // reset it to -1 to give error if someone calls it when shouldn't
        return returnValue;
    }
}
