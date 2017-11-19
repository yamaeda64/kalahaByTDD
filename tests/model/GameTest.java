package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameTest
{
    
    private BoardFactory bf;
    private ComputerFactory computerFactory;
    private Game sut;
    private Board board;
    private MediumComputer mediumComputer;
    
    @BeforeEach
    void setUp()
    {
        bf = mock(BoardFactory.class);
        computerFactory = mock(ComputerFactory.class);
        board = mock(Board.class);
        when(bf.getKalahaBoard()).thenReturn(board);
        
        mediumComputer = mock(MediumComputer.class);
        when(computerFactory.getMediumComputer()).thenReturn(mediumComputer);
        
        sut = new Game(bf, computerFactory);
        
    }
    
    @Test
    void GameTest_startNewGame_shouldCreateBoard()
    {
        
        sut.startNewGame();
        Mockito.verify(bf).getKalahaBoard();
    }
    
    @Test
    void GameTest_startNewGame_shouldCallCreateHouseAndStores()
    {
       
        sut.startNewGame();
        
        Mockito.verify(board).createHouseAndStores();
    }
    
   
    @Test
    void GameTest_getBoard_shouldReturnBoard()
    {
        
        sut.startNewGame();
        
        Board actual = sut.getBoard();
        Board expected = mock(Board.class);
        assertEquals(expected.getClass(), actual.getClass());
    }
    

    @Test
    void GameTest_takeBallsFrom3_house3ShouldBeEmpty()
    {
        
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
        when(board.getPlayerStore()).thenReturn(12);
        sut.startNewGame();
        int actual = sut.getPlayerStore();
        assertEquals(12, actual);
    }
    
    @Test
    void GameTest_getComputerStore()
    {
       
        when(board.getComputerStore()).thenReturn(10);
        sut.startNewGame();
        int actual = sut.getComputerStore();
        assertEquals(10, actual);
    }
    
    @Test
    void GameTest_getPlayerHouses()
    {
        
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
        
        sut.startNewGame();
        verify(computerFactory).getMediumComputer();
        
    }
    
    @Test
    public void GameTest_computerCheckIfGameIsActive_shouldReturnTrue()
    {
        ArrayList<Integer> computerHouses = new ArrayList<>();
        computerHouses.add(2);
        computerHouses.add(1);
        computerHouses.add(0);
        computerHouses.add(0);
        computerHouses.add(0);
        computerHouses.add(0);
        when(board.getComputerHouses()).thenReturn(computerHouses.iterator());
        sut.computerTakesBallsFrom(2);
        boolean actual = sut.getIsGameActive();
        assertTrue(actual);
    }
    
    @Test
    public void GameTest_computerCheckIfGameIsActive_shouldReturnFalse()
    {
        ArrayList<Integer> computerHouses = new ArrayList<>();
        computerHouses.add(0);
        computerHouses.add(0);
        computerHouses.add(0);
        computerHouses.add(0);
        computerHouses.add(0);
        computerHouses.add(0);
        when(board.getComputerHouses()).thenReturn(computerHouses.iterator());
        
        boolean actual = sut.getIsGameActive();
        assertFalse(actual);
    }
    
    @Test
    public void GameTest_makeMove_playersTurnShouldBeFalse()
    {
        ArrayList<Integer> playerHouses = new ArrayList<>();
        playerHouses.add(5);
        playerHouses.add(0);
        playerHouses.add(0);
        playerHouses.add(0);
        playerHouses.add(0);
        playerHouses.add(0);
        when(board.getPlayerHouses()).thenReturn(playerHouses.iterator());
        sut.startNewGame();
        when(board.getSwitchTurn()).thenReturn(true);
        sut.playerTakesBallsFrom(6);
        boolean actual = sut.isPlayersTurn();
        assertFalse(actual);
    }
    
    @Test
    public void GameTest_makeMove_playersTurnShouldBeTrue()
    {
        ArrayList<Integer> playerHouses = new ArrayList<>();
        playerHouses.add(6);
        playerHouses.add(0);
        playerHouses.add(0);
        playerHouses.add(0);
        playerHouses.add(0);
        playerHouses.add(0);
        when(board.getPlayerHouses()).thenReturn(playerHouses.iterator());
        sut.startNewGame();
        when(board.getSwitchTurn()).thenReturn(false);
        sut.playerTakesBallsFrom(6);
        boolean actual = sut.isPlayersTurn();
        assertTrue(actual);
    }
    
    @Test
    public void GameTest_computerTakeBalls_shouldCallBoardComputerTakesBall()
    {
        ArrayList<Integer> computerHouses = new ArrayList<>();
        computerHouses.add(0);
        computerHouses.add(0);
        computerHouses.add(0);
        computerHouses.add(2);
        computerHouses.add(0);
        computerHouses.add(0);
        when(board.getComputerHouses()).thenReturn(computerHouses.iterator());
        sut.computerTakesBallsFrom(4);
        verify(board).computerTakesBallsFrom(4);
    }
    
    @Test
    public void GameTest_compuerMakesMove_computersTurnShouldBeTrue()
    {
        ArrayList<Integer> computerHouses = new ArrayList<>();
        computerHouses.add(0);
        computerHouses.add(0);
        computerHouses.add(0);
        computerHouses.add(2);
        computerHouses.add(0);
        computerHouses.add(0);
        when(board.getComputerHouses()).thenReturn(computerHouses.iterator());
        when(board.getSwitchTurn()).thenReturn(true);
        sut.computerTakesBallsFrom(4);
        boolean actual = sut.isPlayersTurn();
        assertTrue(actual);
    }
    
    @Test
    public void GameTest_compuerMakesMove_computersTurnShouldBeFalse()
    {
        ArrayList<Integer> computerHouses = new ArrayList<>();
        computerHouses.add(0);
        computerHouses.add(0);
        computerHouses.add(0);
        computerHouses.add(3);
        computerHouses.add(0);
        computerHouses.add(0);
        when(board.getComputerHouses()).thenReturn(computerHouses.iterator());
        when(board.getSwitchTurn()).thenReturn(false);
        sut.computerTakesBallsFrom(4);
        boolean actual = sut.isPlayersTurn();
        assertFalse(actual);
    }
    
    @Test
    public void GameTest_computerMakeMove_isGameOverShouldBeFalse()
    {
        ArrayList<Integer> computerHouses = new ArrayList<>();
        computerHouses.add(1);
        computerHouses.add(0);
        computerHouses.add(0);
        computerHouses.add(0);
        computerHouses.add(0);
        computerHouses.add(0);
        when(board.getComputerHouses()).thenReturn(computerHouses.iterator());
        sut.computerTakesBallsFrom(1);
        
        boolean actual = sut.getIsGameActive();
        assertTrue(actual);
    }
    
    @Test
    public void GameTest_computerMakeMove_isGameActiveShouldBeFalse()
    {
        ArrayList<Integer> computerHouses = new ArrayList<>();
        computerHouses.add(0);
        computerHouses.add(0);
        computerHouses.add(0);
        computerHouses.add(0);
        computerHouses.add(0);
        computerHouses.add(0);
        when(board.getComputerHouses()).thenReturn(computerHouses.iterator());
        sut.computerTakesBallsFrom(6);
        
        boolean actual = sut.isGameActive();
        assertFalse(actual);
    }
    
    @Test
    public void GameTest_isGameActiveWhenFirstHouseIsEmpty_shouldBeTrue()
    {
        ArrayList<Integer> computerHouses = new ArrayList<>();
        computerHouses.add(0);
        computerHouses.add(1);
        computerHouses.add(2);
        computerHouses.add(0);
        computerHouses.add(0);
        computerHouses.add(0);
        when(board.getComputerHouses()).thenReturn(computerHouses.iterator());
        
        sut.startNewGame();
        sut.computerTakesBallsFrom(3);
        boolean actual = sut.getIsGameActive();
        
        assertTrue(actual);
    }
    
    @Test
    public void GameTest_whenGameOver_playerScoreShouldBe41()
    {
        ArrayList<Integer> computerHouses = new ArrayList<>();
        computerHouses.add(4);
        computerHouses.add(3);
        computerHouses.add(2);
        computerHouses.add(1);
        computerHouses.add(6);
        computerHouses.add(5);
        when(board.getComputerHouses()).thenReturn(computerHouses.iterator());
        
        when(board.getPlayerStore()).thenReturn(20);
        int actual = sut.getPlayerScoreWhenGameIsOver();
        
    
        assertEquals(41,actual);
    }
}