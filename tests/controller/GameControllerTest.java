package controller;

import model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.ConsoleView;

import static org.mockito.Mockito.*;
import static view.UserInteraction.*;


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
    public void start_getUserCommand_ShouldEvokecollectEvent()
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
    
    private void exchangeGameControllerToSpyThatDoesntExit()
    {
        sut = spy(new GameController(view,game));
        doReturn(true).when(sut).exitApplication();
    }
}

