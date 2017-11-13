package controller;

import model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import view.ConsoleView;

import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static view.UserInteraction.PLAY;
import static view.UserInteraction.QUIT;


class GameControllerTest
{
    private GameController sut;
    private ConsoleView view;
    private Game game;
    
    
    @BeforeEach
    public void setUp()
    {
        view = mock(ConsoleView.class);
        game = mock(Game.class);
        sut = new GameController(view, game);
    }
    
    @Test
    public void shouldShowMenu_whenStart_shouldShowMenu()
    {
        sut.start();
        verify(view).showMenu();
    }
    
    @Test
    public void quit_whenQuitCommandRecieved_shouldSendQuitMesssage()
    {
        exchangeGameControllerToSpyThatDoesntExit();
        sut.quit();
        verify(view).showQuitMessage();
    }
    
    @Test
    public void quit_whenQuitCommandRecieved_ShouldQuitApplication()
    {
        exchangeGameControllerToSpyThatDoesntExit();
        sut.quit();
        verify(sut).exitApplication();
    }
    
    @Test
    public void shouldStartNewGame()
    {
        sut.play();
        verify(game).startNewGame();
    }
    
    @Test
    public void takeAction_whenPlay_ShouldStartNewGame()
    {
        sut = spy(new GameController(view, game));
        sut.takeAction(PLAY);
        verify(sut).play();
    }
    
    
    @Test
    public void takeAction_whenPlay_shouldCallPrintBoard()
    {
        sut.play();
        ArrayList<Integer> houses =new ArrayList<>();
        for(int i= 0; i<6; i++)
        {
            houses.add(6);
        }
        
        verify(view).drawBoard(anyInt(),anyInt(),anyObject(),anyObject());
        
    }
    
    @Test
    public void start_getUserCommand_ShouldCallCollectEvent()
    {
        sut.start();
        verify(view).collectEvent();
    }
    
    @Test
    public void takeAction_whenQuit_ShouldQuit()
    {
        exchangeGameControllerToSpyThatDoesntExit();
        sut.takeAction(QUIT);
        verify(sut).exitApplication();
    }
    
    
    @Test
    public void takeAction_whenStartGame_ShouldCallTakeAction()
    {
        exchangeGameControllerToSpyThatDoesntExit();
        when(view.collectEvent()).thenReturn(QUIT);
        sut.start();
        
        verify(sut).takeAction(QUIT);
    }
    
    @Test
    public void whenShowWelcomeScreen_ShouldClearScreen()
    {
        sut.start();
        
        InOrder rightOrder = inOrder(view);
    
        rightOrder.verify(view).clearScreen();
        rightOrder.verify(view).showMenu();
        
    }
    
    @Test
    public void GameController_whenPlay_ShouldAskForUserInput()
    {
        sut.play();
        verify(view).collectEvent();
    }
    private void exchangeGameControllerToSpyThatDoesntExit()
    {
        sut = spy(new GameController(view,game));
        doReturn(true).when(sut).exitApplication();
    }

}

