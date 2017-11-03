package controller;

import model.Game;
import view.ConsoleView;

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
    }
    
    public void quit()
    {
        view.showQuitMessage();
    }
    
    public void play()
    {
        game.startNewGame();
    }
}
