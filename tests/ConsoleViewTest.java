import org.junit.jupiter.api.Test;
import view.ConsoleView;

import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static view.ConsoleView.menuText;


class ConsoleViewTest
{
    private ConsoleView sut;
    
    @Test
    public void shouldShowMenu()
    {
        PrintStream printStream = mock(PrintStream.class);
        sut = new ConsoleView(printStream);
        
        sut.showMenu();
        
        verify(printStream).println(menuText);
        
    }
    
    @Test
    public void shouldShowMenu_assertRightMenuLine()
    {
        String actual = menuText;
        String expected = "Welcome to Kalaha, press 'N' for new game or 'Q' to Quit";
        assertEquals(actual, expected);
    }
}