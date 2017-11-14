package model;


import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
    
    /*
    @Test
    public void chooseNextHouse()
    {
        Board board = mock(Board.class);
        MediumComputer sut = new MediumComputer();
        
        ArrayList<Integer> mockedInput = new ArrayList<>();
        mockedInput.add(1);
        mockedInput.add(2);
        mockedInput.add(3);
        mockedInput.add(4);
        mockedInput.add(5);
        mockedInput.add(6);
        when(board.getComputerHouses()).thenReturn(mockedInput.iterator());
        
        verify(board).computerTakesBallsFrom(1);
    }
    */
    
}