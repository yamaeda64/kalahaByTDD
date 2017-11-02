import controller.GameController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.ConsoleView;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


class GameControllerTest
{
    private GameController sut;
    private ConsoleView view;
    
    @BeforeEach
    public void setUp()
    {
        view = mock(ConsoleView.class);
        sut = new GameController(view);
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
    
}