package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;


class GameTest
{
    @BeforeEach
    void setUp()
    {
        
    }
    
    @Test
    void startNewGame()
    {
        Game sut = new Game();
        Board board = mock(Board.class);
        sut.startNewGame();
        Mockito.verify(board).Board();
        
        
    }
    
}