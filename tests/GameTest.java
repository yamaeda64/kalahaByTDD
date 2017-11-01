import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by joakimbergqvist on 2017-11-01.
 */
class GameTest
{
    private Game sut;
    
  
    @Test
    public void shouldShowMenu()
    {
        ConsoleView view = mock(ConsoleView.class);
        sut = new Game();
        
        sut.start();
        verify(view).showMenu();
    }
    
}