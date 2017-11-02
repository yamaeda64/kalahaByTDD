import controller.GameController;
import org.junit.jupiter.api.Test;
import view.ConsoleView;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


class GameControllerTest
{
    private GameController sut;
    
    
    @Test
    public void shouldShowMenu_whenStart_shouldShowMenu()
    {
        ConsoleView view = mock(ConsoleView.class);
        sut = new GameController(view);
        
        sut.start();
        verify(view).showMenu();
    }
    
    @Test
    public void quit_whenQuitCommandRecieved_shouldSendQuitMesssage()
    {
        ConsoleView view = mock(ConsoleView.class);
        sut = new GameController(view);
        
        sut.quit();
        
        verify(view).showQuitMessage();
        
    }
    
}