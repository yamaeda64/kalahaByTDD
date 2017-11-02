package view;

import java.io.PrintStream;

/**
 * The base view Class of the application
 */
public class ConsoleView
{
    public static final String menuText = "Welcome to Kalaha, press 'N' for new game or 'Q' to Quit";
    public static final String quitText = "Thank you for playing Kalaha.";
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
}
