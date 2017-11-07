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
        
        assertEquals(new Board().getClass(), actual.getClass());
    }
    
    
    @Test
    void GameTest_takeBallsFrom3_house3ShouldBeEmpty()
    {
        when(bf.getKalahaBoard()).thenReturn(mock(Board.class));
        sut.startNewGame();
        sut.playerTakesBallsFrom(3);
        Mockito.verify(sut.getBoard()).playerTakesBallsFrom(3);
    }
    
    @Test
    void GameTest_getPlayerStore()
    {
        Board board = mock(Board.class);
        when(bf.getKalahaBoard()).thenReturn(board);
        when(board.getPlayerStore()).thenReturn(12);
        sut.startNewGame();
        int actual = sut.getPlayerStore();
        assertEquals(12, actual);
    }
    
    @Test
    void GameTest_getComputerStore()
    {
        Board board = mock(Board.class);
        when(bf.getKalahaBoard()).thenReturn(board);
        when(board.getComputerStore()).thenReturn(10);
        sut.startNewGame();
        int actual = sut.getComputerStore();
        assertEquals(10, actual);
    }
}