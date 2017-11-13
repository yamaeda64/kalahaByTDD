package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
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
        Board board = mock(Board.class);
        when(bf.getKalahaBoard()).thenReturn(board);
        sut.startNewGame();
        Mockito.verify(bf).getKalahaBoard();
    }
    
    @Test
    void GameTest_startNewGame_shouldCallCreateHouseAndStores()
    {
        Board board = mock(Board.class);
        when(bf.getKalahaBoard()).thenReturn(board);
        sut.startNewGame();
        
        Mockito.verify(board).createHouseAndStores();
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
        Board board = mock(Board.class);
        when(bf.getKalahaBoard()).thenReturn(board);
        sut.startNewGame();
        
        ArrayList<Integer> startPos = new ArrayList<>();
        startPos.add(6);
        startPos.add(6);
        startPos.add(6);
        startPos.add(6);
        startPos.add(6);
        startPos.add(6);
        when(board.getPlayerHouses()).thenReturn(startPos.iterator());
    
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
    
    @Test
    void GameTest_getPlayerHouses()
    {
        Board board = mock(Board.class);
        when(bf.getKalahaBoard()).thenReturn(board);
        ArrayList<Integer> numberList = new ArrayList<>();
        numberList.add(1);
        numberList.add(2);
        numberList.add(3);
    
        when(board.getPlayerHouses()).thenReturn(numberList.iterator());
        sut.startNewGame();
        Iterator<Integer> actual = sut.getPlayerHouses();
    
    
        for(int i = 1; i <= 3; i++)
        {
            assertEquals(i, actual.next().intValue());
        }
    }
    
    @Test
    public void GameTest_getComputerHouses()
    {
        Board board = mock(Board.class);
        when(bf.getKalahaBoard()).thenReturn(board);
        ArrayList<Integer> numberList = new ArrayList<>();
        numberList.add(4);
        numberList.add(5);
        numberList.add(6);
    
        when(board.getComputerHouses()).thenReturn(numberList.iterator());
        sut.startNewGame();
        Iterator<Integer> actual = sut.getComputerHouses();
    
        for(int i = 4; i <= 6; i++)
        {
            assertEquals(i, actual.next().intValue());
        }
    }
    
    @Test
    public void GameTest_isGameActive_shouldReturnFalseBeforeStart()
    {
        boolean actual = sut.isGameActive();
        
        assertFalse(actual);
    }
    
    @Test
    public void GameTest_isGameActiveAfterStartingNewGame_shouldBeTrue()
    {
        Board board = mock(Board.class);
        when(bf.getKalahaBoard()).thenReturn(board);
        sut.startNewGame();
        boolean actual = sut.isGameActive();
        assertTrue(actual);
    }
    
    @Test
    public void GameTest_isGameActiveWhenHousesIsEmpty_shouldBeFalse()
    {
        Board board = mock(Board.class);
        when(bf.getKalahaBoard()).thenReturn(board);
        
        ArrayList<Integer> playerHouses = new ArrayList<>();
        playerHouses.add(0);
        playerHouses.add(0);
        playerHouses.add(0);
        playerHouses.add(0);
        playerHouses.add(0);
        playerHouses.add(0);
        when(board.getPlayerHouses()).thenReturn(playerHouses.iterator());
        
        sut.startNewGame();
        sut.playerTakesBallsFrom(6);
        boolean actual = sut.isGameActive();
        
        assertEquals(false,actual);
        
        
    }
    @Test
    public void GameTest_whenStartGame_shouldCallGetMediumComputer()
    {
        ComputerFactory factory =mock(ComputerFactory.class);
        
        Board board = mock(Board.class);
        when(bf.getKalahaBoard()).thenReturn(board);
        
        sut.startNewGame();
        verify(factory).getMediumComputer();
        
    }
}