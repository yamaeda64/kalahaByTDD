package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


class MediumComputerTest
{
    private Game game;
    private Board board;
    private MediumComputer sut;
    
    @BeforeEach
    void setup()
    {
        game = mock(Game.class);
        board = mock(Board.class);
        sut = new MediumComputer(game);
        when(game.getBoard()).thenReturn(board);
        sut.setBoard(game.getBoard());
    }
    
    @Test
    public void mediumComputer_whenSetBoard_boardShouldNotBeNULL()
    {
        
        assertNotNull(sut.getBoard());
    }
    
    
    @Test
    public void mediumComputer_whenLastHouseHasOne_shouldChooseOne()
    {
        
        ArrayList<Integer> mockedInput = new ArrayList<>();
        mockedInput.add(6);
        mockedInput.add(5);
        mockedInput.add(4);
        mockedInput.add(3);
        mockedInput.add(2);
        mockedInput.add(1);
        when(game.getComputerHouses()).thenReturn(mockedInput.iterator());
        sut.chooseNextHouse();
        verify(game).computerTakesBallsFrom(6);
    }
    
    @Test
    public void mediumComputer_whenFirstHouseHas6AndnoSmallerLeadsToStore_shouldChooseSix()
    {
       
        ArrayList<Integer> mockedInput = new ArrayList<>();
        mockedInput.add(6);
        mockedInput.add(4);
        mockedInput.add(8);
        mockedInput.add(4);
        mockedInput.add(3);
        mockedInput.add(5);
        when(game.getComputerHouses()).thenReturn(mockedInput.iterator());
        sut.chooseNextHouse();
        verify(board).computerTakesBallsFrom(1);
    }
    
    @Test
    public void mediumComputer_when4thHouseGoesEvenRoundBoard_shouldChoose4()
    {
    
        ArrayList<Integer> mockedInput = new ArrayList<>();
        mockedInput.add(8);
        mockedInput.add(4);
        mockedInput.add(8);
        mockedInput.add(16);
        mockedInput.add(3);
        mockedInput.add(5);
        
       
        when(game.getComputerHouses()).thenAnswer(new Answer<Iterator<Integer>>() {
            @Override
            public Iterator<Integer> answer(final InvocationOnMock invocation) throws Throwable {
                return mockedInput.iterator();
            }
        });
        
        
        sut.chooseNextHouse();
        verify(board).computerTakesBallsFrom(4);
    }
    
    @Test
    public void mediumComputer_whenNoOptionGoToStore_takeOneThatGoToStoreNextTurn()
    {
        ArrayList<Integer> mockedInput = new ArrayList<>();
        mockedInput.add(8);
        mockedInput.add(4);
        mockedInput.add(1);
        mockedInput.add(2);
        mockedInput.add(4);
        mockedInput.add(0);
    
    
        when(game.getComputerHouses()).thenAnswer(new Answer<Iterator<Integer>>() {
            @Override
            public Iterator<Integer> answer(final InvocationOnMock invocation) throws Throwable {
                return mockedInput.iterator();
            }
        });
        
        sut.chooseNextHouse();
        verify(board).computerTakesBallsFrom(1);
        
    }
    
    @Test
    public void mediumComputer_pickLowestHouseNumberWhenNoOtherGoodOption()
    {
        ArrayList<Integer> mockedInput = new ArrayList<>();
        mockedInput.add(1);
        mockedInput.add(8);
        mockedInput.add(5);
        mockedInput.add(8);
        mockedInput.add(4);
        mockedInput.add(2);
    
    
        when(game.getComputerHouses()).thenAnswer(new Answer<Iterator<Integer>>() {
            @Override
            public Iterator<Integer> answer(final InvocationOnMock invocation) throws Throwable {
                return mockedInput.iterator();
            }
        });
    
        sut.chooseNextHouse();
        verify(board).computerTakesBallsFrom(2);
    }
}