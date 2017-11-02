import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.ConsoleView;

import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static view.ConsoleView.menuText;
import static view.ConsoleView.quitText;


class ConsoleViewTest
{
    private ConsoleView sut;
    private PrintStream printStream;
    
    @BeforeEach
    public void setUp()
    {
        printStream = mock(PrintStream.class);
        sut = new ConsoleView(printStream);
    }
    
    @Test
    public void shouldShowMenu()
    {
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
    
    
    @Test
    public void shouldShowQuitMessage()
    {
        sut.showQuitMessage();
        verify(printStream).println(quitText);
    }
    
    @Test
    public void shouldShowQuitMessage_assertCorrectQuitMessage()
    {
        String actual = quitText;
        String expected = "Thank you for playing Kalaha.";
        assertEquals(actual, expected);
    }
}