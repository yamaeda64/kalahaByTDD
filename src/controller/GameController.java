package controller;

import model.Game;
import view.ConsoleView;
import view.UserInteraction;

import static view.UserInteraction.*;

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
        view.clearScreen();
        UserInteraction currentInteraction = view.collectEvent();
        takeAction(currentInteraction);
    }
    
    public void quit()
    {
        view.showQuitMessage();
        exitApplication();
    }
    
    public void play()
    {
        game.startNewGame();
        view.drawBoard(game.getPlayerStore(),game.getComputerStore(),game.getPlayerHouses(),game.getComputerHouses());
    }
    
    public boolean exitApplication()
    {
        System.exit(0);
        return false;        // returns false if system couldn't shut down.
    }
    
    public void takeAction(UserInteraction userInteraction)
    {
        if(userInteraction == QUIT)
        {
            quit();
        }
        else if(userInteraction == PLAY)
        {
            play();
        }
    }
}
