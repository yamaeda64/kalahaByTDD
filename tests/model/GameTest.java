package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;


class GameTest
{
    
    @BeforeEach
    void setUp()
    {
        
    }
    
    @Test
    void GameTest_startNewGame_shouldCreateBoard()
    {
        Game sut = spy(new Game());
        BoardFactory bf = mock(BoardFactory.class);
        Board board = new Board();
        sut.startNewGame();
        
        verify(bf).getKalahaBoard();
    }
    
    
}