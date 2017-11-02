import controller.GameController;
import model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.ConsoleView;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


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
        sut.quit();
        verify(view).showQuitMessage();
    }
    
    @Test
    public void shouldStartNewGame()
    {
        sut.play();
        verify(game).startNewGame();
    }
}