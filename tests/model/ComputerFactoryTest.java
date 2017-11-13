package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ComputerFactoryTest
{
    @Test
    public void getMediumComputer()
    {
        ComputerFactory sut = new ComputerFactory();
        
        MediumComputer mediumComputer = sut.getMediumComputer();
        assertNotNull(mediumComputer);
    }
    
    
}