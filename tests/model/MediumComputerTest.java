package model;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class MediumComputerTest
{
    @Test
    public void mediumComputer_whenSetBoard_boardShouldNotBeNULL()
    {
        Game game = mock(Game.class);
        Board board = mock(Board.class);
        MediumComputer sut = new MediumComputer(game);
        when(game.getBoard()).thenReturn(board);
                
        sut.setBoard(game.getBoard());
        
        assertNotNull(sut.getBoard());
    }
    
    
    @Test
    public void chooseNextHouse()
    {
        Game game = mock(Game.class);
        Board board = mock(Board.class);
        when(game.getBoard()).thenReturn(board);
        MediumComputer sut = new MediumComputer(game);
        sut.setBoard(game.getBoard());
        ArrayList<Integer> mockedInput = new ArrayList<>();
        mockedInput.add(6);
        mockedInput.add(5);
        mockedInput.add(4);
        mockedInput.add(3);
        mockedInput.add(2);
        mockedInput.add(1);
        when(game.getComputerHouses()).thenReturn(mockedInput.iterator());
        sut.chooseNextHouse();
        verify(board).computerTakesBallsFrom(1);
    }
    
    
    
    
}