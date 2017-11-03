package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GameTest
{
    
    @BeforeEach
    void setUp()
    {
        
    }
    
    @Test
    void GameTest_startNewGame_shouldCreateBoard()
    {
        BoardFactory bf = mock(BoardFactory.class);
        Game sut = new Game(bf);
        
        sut.startNewGame();
        
        Mockito.verify(bf).getKalahaBoard();
    }
    
    @Test
    void GameTest_getBoard_shouldReturnBoard()
    {
      
        BoardFactory bf = mock(BoardFactory.class);
        when(bf.getKalahaBoard()).thenReturn(new Board());
        Game sut = new Game(bf);
        sut.startNewGame();
        
        Board actual = sut.getBoard();
        
        assertEquals(actual.getClass(), actual.getClass());
    }
    
    
    
    
}