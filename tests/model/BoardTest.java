package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class BoardTest
{
    
    private Board sut;
    
    @BeforeEach
   public void setUp()
   {
       sut = new Board();
   }
   
    @Test
    public void testBoardRows()
    {
        int actual = sut.getBoardRows();
        assertEquals(2, actual);
    }
    
    @Test
    public void testBoardHousesPerSideSize()
    {
        int actual = sut.getBoardHousesPerSide();
        assertEquals(6, actual);
    }
    
    @Test
    public void testBoardStoresSize()
    {
        int actual = sut.getStoreSize();
        assertEquals(2, actual);
    }
}