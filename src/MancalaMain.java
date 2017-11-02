import controller.GameController;
import view.ConsoleView;

import java.io.PrintStream;


public class MancalaMain
{
    public static void main(String[] args)
    {
        
        GameController controller = new GameController(new ConsoleView(new PrintStream(System.out)));
        
        controller.start();
    }
}
