package view;

import java.io.PrintStream;

/**
 * The base view Class of the application
 */
public class ConsoleView
{
    private PrintStream out;
    
    public ConsoleView(PrintStream printStream)
    {
        this.out = printStream;
    }
    
    public void showMenu()
    {
       out.println("Welcome to Kalaha, press 'N' for new game or 'Q' to Quit");
    }
}
