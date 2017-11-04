package controller;

import model.Game;
import view.ConsoleView;
import view.UserInteraction;

/**
 * the base controller class
 */
public class GameController
{
    private ConsoleView view;
    private Game game;
    
    public GameController(ConsoleView consoleView, Game game)
    {
        this.view = consoleView;
        this.game = game;
    }
    
    public void start()
    {
        view.showMenu();
        UserInteraction currentInteraction = view.collectEvent();
    }
    
    public void quit()
    {
        
        
    }
    
    public void play()
    {
        game.startNewGame();
    }
    
    public boolean exitApplication()
    {
        System.exit(0);
        return false;        // returns false if system couldn't shut down.
    }
}
