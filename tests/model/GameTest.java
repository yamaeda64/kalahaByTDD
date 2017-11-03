package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GameTest
{
    
    private BoardFactory bf;
    private Game sut;
    
    @BeforeEach
    void setUp()
    {
        bf = mock(BoardFactory.class);
        sut = new Game(bf);
    }
    
    @Test
    void GameTest_startNewGame_shouldCreateBoard()
    {
        sut.startNewGame();
        Mockito.verify(bf).getKalahaBoard();
    }
    
    @Test
    void GameTest_getBoard_shouldReturnBoard()
    {
        when(bf.getKalahaBoard()).thenReturn(new Board());
        sut.startNewGame();
        
        Board actual = sut.getBoard();
        
        assertEquals(actual.getClass(), actual.getClass());
    }
}