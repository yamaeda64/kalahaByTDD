package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class ComputerFactoryTest
{
    @Test
    public void getMediumComputer()
    {
        Board board = mock(Board.class);
        ComputerFactory sut = new ComputerFactory();
        
        MediumComputer mediumComputer = sut.getMediumComputer();
        assertNotNull(mediumComputer);
    }
    
}