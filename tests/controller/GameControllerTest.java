package controller;

import model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.ConsoleView;

import static org.mockito.Mockito.*;
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
        sut = spy(new GameController(view,game));
        doReturn(true).when(sut).exitApplication();
        sut.quit();
        verify(view).showQuitMessage();
    }
    
    @Test
    public void quit_whenQuitCommandRecieved_ShouldQuitApplication()
    {
        sut = spy(new GameController(view,game));
        doReturn(true).when(sut).exitApplication();
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
    public void start_getUserCommand_ShouldEvokecollectEvent()
    {
        sut.start();
        verify(view).collectEvent();
    }
    
    @Test
    public void takeAction_whenQuit_ShouldQuit()
    {
        sut = spy(new GameController(view,game));
        doReturn(true).when(sut).exitApplication();
        sut.takeAction(QUIT);
        verify(sut).exitApplication();
    }
}

