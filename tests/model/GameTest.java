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
    void GameTest_startNewGame_shouldCreateBoard()
    {
        BoardFactory bf = mock(BoardFactory.class);
        Game sut = new Game(bf);
        
        sut.startNewGame();
        
        Mockito.verify(bf).getKalahaBoard();
    }
    
    
    
    
}