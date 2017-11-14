import controller.GameController;
import model.BoardFactory;
import model.ComputerFactory;
import model.Game;
import view.ConsoleView;

import java.io.PrintStream;


public class MancalaMain
{
    public static void main(String[] args)
    {
       
        GameController controller = new GameController(new ConsoleView(new PrintStream(System.out)), new Game(new BoardFactory(), new ComputerFactory()));
        
        controller.start();
        
    }
}
