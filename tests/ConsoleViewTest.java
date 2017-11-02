import org.junit.jupiter.api.Test;
import view.ConsoleView;

import java.io.PrintStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


class ConsoleViewTest
{
    private ConsoleView sut;
    
    @Test
    public void shouldShowMenu()
    {
        PrintStream printStream = mock(PrintStream.class);
        sut = new ConsoleView(printStream);
        
        sut.showMenu();
        
        verify(printStream).println("Welcome to Kalaha, press 'N' for new game or 'Q' to Quit");
        
    }
}