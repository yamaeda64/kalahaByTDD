package view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
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
    
    @Test
    public void shouldClearScreen()
    {
        sut.clearScreen();
        verify(printStream, times(50)).println();
    }
    
    @Test
    public void consoleView_getInput_ShouldGetQ()
    {
        String input = "Q";
        setFakeInputStream(input);
        char actual = sut.getInput();
        assertEquals('Q', actual);
    }
    
    @Test
    public void consoleView_getInput_ShouldGetA()
    {
        String input = "aAT\n";
        setFakeInputStream(input);
        char actual = sut.getInput();
        assertEquals('A', actual);
    }
    
    @Test
    public void consoleView_getInput_ShouldGet5()
    {
        String input = "\n5AT\n";
        setFakeInputStream(input);
        char actual = sut.getInput();
        assertEquals('5', actual);
    }
    private void setFakeInputStream(String input)
    {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }
    
    @Test
    public void consoleView_collectEvent_ShouldReturnQuit()
    {
        char input = 'Q';
        setFakeInputStream("" + input);
        UserInteraction actual = sut.collectEvent();
        
        assertEquals(UserInteraction.QUIT, actual);
    }
    
    @Test
    public void consoleView_collectEvent_ShouldReturnPlay()
    {
        char input = 'n';
        setFakeInputStream("" + input);
        UserInteraction actual = sut.collectEvent();
        assertEquals(UserInteraction.PLAY, actual);
    }
    
    @Test
    public void consoleView_collectEvent_ShouldReturnPickBallsFromHouse()
    {
        char input = '4';
        setFakeInputStream("" +input);
        UserInteraction actual = sut.collectEvent();
        assertEquals(UserInteraction.PICK_BALLS_FROM_HOUSE, actual);
    }
    
    
    
}