/**
 * Created by joakimbergqvist on 2017-11-01.
 */
public class Game
{
    ConsoleView view;
    
    public Game(ConsoleView consoleView)
    {
        this.view = consoleView;
    }
    public void start()
    {
        view.showMenu();
    }
}
