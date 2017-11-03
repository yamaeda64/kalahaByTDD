package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by joakimbergqvist on 2017-11-03.
 */
class BoardTest
{
    
   @BeforeEach
   public void setUp()
   {
       
   }
   
    @Test
    public void testBoardRows()
    {
        Board sut = new Board();
        int actual = sut.getBoardRows();
        assertEquals(2, actual);
    }
    
}