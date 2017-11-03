package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


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
    
    @Test
    public void testBoardHousesPerSideSize()
    {
        Board sut = new Board();
        int actual = sut.getBoardHousesPerSide();
        assertEquals(6, actual);
    }
    
    @Test
    public void testBoardStoresSize()
    {
        Board sut = new Board();
        int actual = sut.getStoreSize();
        assertEquals(2, actual);
    }
}