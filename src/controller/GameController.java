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
    private boolean shouldPrintErrorMessage;
    private int errorMessageID;
    private boolean gameLoop = true;
    
    public GameController(ConsoleView consoleView, Game game)
    {
        this.view = consoleView;
        this.game = game;
    }
    
    public void start()
    {
        view.clearScreen();
        while(gameLoop)
        {
            view.showMenu();
            gameLoop = false;
            try
            {
                UserInteraction currentInteraction = view.collectEvent();
                takeAction(currentInteraction);
                
            }
            catch(IllegalArgumentException e)
            {
                view.showWrongInputMessage();
                gameLoop = true;
            }
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
                try
                {
                    takeActionWhenPlay(view.collectEvent());
                }
                catch(IllegalArgumentException e)
                {
                    shouldPrintErrorMessage = true;
                    errorMessageID = 2;
                }
                view.clearScreen();
                askViewToDrawBoard();
                if(shouldPrintErrorMessage)
                {
                    printErrorMessage();
                    gameLoop = true;
                }
            } else
            {
                game.getMediumComputer().chooseNextHouse();
                view.clearScreen();
                askViewToDrawBoard();
            }
        }
        view.presentFinalScore(game.getPlayerScoreWhenGameIsOver(), game.getComputerScoreWhenGameIsOver());
        view.pressAnyKeyToContrinue();
        view.waitForKeyPress();
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
        } else if(userInteraction == PLAY)
        {
            play();
        } else
        {
            view.showWrongInputMessage();
            gameLoop = true;
        }
    }
    
    private void takeActionWhenPlay(UserInteraction userInteraction)
    {
        int houseNumberToPickFrom;
        if(userInteraction == UserInteraction.PICK_BALLS_FROM_HOUSE)
        {
            houseNumberToPickFrom = view.getNumberAfterInput();
            
            try
            {
                game.playerTakesBallsFrom(houseNumberToPickFrom);
            }
            catch(NullPointerException e)
            {
                shouldPrintErrorMessage = true;
                errorMessageID = 1;
            }
            
        }
        else
        {
            shouldPrintErrorMessage = true;
            errorMessageID = 2;
        }
    }
    
    public void setGameLoop(boolean gameLoop)   // used make exit loops in testing
    {
        this.gameLoop = gameLoop;
    }
    
    private void askViewToDrawBoard()
    {
        view.drawBoard(game.getPlayerStore(),game.getComputerStore(),game.getPlayerHouses(),game.getComputerHouses());
    }
    
    private void printErrorMessage()
    {
        if(errorMessageID == 1)
        {
            view.userChoseEmptyHouse();
            shouldPrintErrorMessage = false;
            errorMessageID = 0;
        } else if(errorMessageID == 2)
        {
            view.showWrongInputMessage();
            shouldPrintErrorMessage = false;
            errorMessageID = 0;
        }
    }
}
