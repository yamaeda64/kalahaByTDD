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
        view.clearScreen();
        view.showMenu();
        try
        {
            UserInteraction currentInteraction = view.collectEvent();
            takeAction(currentInteraction);
        }
        catch(IllegalArgumentException e)
        {
            view.showWrongInputMessage();
        }
        
    }
    
    public void quit()
    {
        view.showQuitMessage();
        exitApplication();
    }
    
    public void play()
    {
        view.clearScreen();
        
        game.startNewGame();
        askViewToDrawBoard();
        while(game.isGameActive())
        {
            if(game.isPlayersTurn())
            {
                view.showChooseHouseText();
                takeActionWhenPlay(view.collectEvent());
                view.clearScreen();
                askViewToDrawBoard();
            }
            else
            {
                game.getMediumComputer().chooseNextHouse();
            }
        }
        
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
    
    private void takeActionWhenPlay(UserInteraction userInteraction)
    {
        int houseNumberToPickFrom;
        if(userInteraction == UserInteraction.PICK_BALLS_FROM_HOUSE)
        {
            houseNumberToPickFrom = view.getNumberAfterInput();
            game.playerTakesBallsFrom(houseNumberToPickFrom);
        }
    }
    
    private void askViewToDrawBoard()
    {
        view.drawBoard(game.getPlayerStore(),game.getComputerStore(),game.getPlayerHouses(),game.getComputerHouses());
    }
}
