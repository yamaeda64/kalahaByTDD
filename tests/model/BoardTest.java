package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

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
    
    // Tests if number of elements in iterator is same as (row*houses + stores)
    @Test
    public void Board_creteHouseAndStoreArray_shouldCreateArrayList()
    {
        sut.createHouseAndStores();
        int actual = 0;
        Iterator<Integer> iterator = sut.getHousesAndStores();
        int expected = sut.getBoardRows()*sut.getBoardHousesPerSide() + sut.getStoreSize();
        while(iterator.hasNext())
        {
            iterator.next();
            actual++;
        }
        assertEquals(expected,actual);
    }
    
    @Test
    public void Board_fillArrayWithBalls_sumOfBallsShouldBeEqual()
    {
        sut.createHouseAndStores();
        int actual = 0;
        Iterator<Integer> iterator = sut.getHousesAndStores();
        while(iterator.hasNext())
        {
            actual += iterator.next();
        }
        int expected = sut.getBoardRows()*sut.getBoardHousesPerSide()* sut.getSTARTING_BALLS_PER_HOUSE();
        assertEquals(expected,actual);
    }
    
    @Test
    public void boardTest_getPlayerHouses_shouldBe36()  // TODO, need more testing when move balls functionality is added
    {
        sut.createHouseAndStores();
        int actual = 0;
        Iterator<Integer> iterator = sut.getPlayerHouses();
        while(iterator.hasNext())
        {
            actual += iterator.next();
        }
        int expected = sut.getBoardHousesPerSide()*sut.getSTARTING_BALLS_PER_HOUSE();
        assertEquals(expected,actual);
        
    }
    
    @Test
    public void boardTest_getPlayerStore_shouldBe0()       // TODO, need more testing when move balls functionality added
    {
        sut.createHouseAndStores();
        int actual = sut.getPlayerStore();
        assertEquals(0,actual);
    }
    
    @Test
    public void boardTest_getComputerHouses_shouldBe36()  // TODO, need more testing when move balls functionality is added
    {
        sut.createHouseAndStores();
        int actual = 0;
        Iterator<Integer> iterator = sut.getComputerHouses();
        while(iterator.hasNext())
        {
            actual += iterator.next();
        }
        int expected = sut.getBoardHousesPerSide()*sut.getSTARTING_BALLS_PER_HOUSE();
        assertEquals(expected,actual);
    }
    
    
    @Test
    public void boardTest_getComputerStore_shouldBe0()       // TODO, need more testing when move balls functionality added
    {
        sut.createHouseAndStores();
        int actual = sut.getComputerStore();
        assertEquals(0,actual);
    }
    
    
    @Test
    public void boardTest_TakeAndDistribute_House1ShouldBeEmpty()
    {
        sut.createHouseAndStores();
        int inputHouse = 1;
        sut.playerTakesBallsFrom(inputHouse);
        int actual;
        Iterator<Integer> iterator = sut.getPlayerHouses();
        actual = iterator.next();
        
        assertEquals(0,actual);
    }
    
    @Test
    public void boardTest_TakeAndDistribute_House2ShouldBe7()
    {
        sut.createHouseAndStores();
        int inputHouse = 1;
        sut.playerTakesBallsFrom(inputHouse);
        int actual = -1;
        Iterator<Integer> iterator = sut.getPlayerHouses();
        while(iterator.hasNext())
        {
            actual = iterator.next();
        }
        assertEquals(7,actual);
    }
    
    @Test
    public void boardTest_TakeAndDistributeOverComputerStore_computerStoreShouldBe0()
    {
        sut.createHouseAndStores();
        sut.playerTakesBallsFrom(1);
        sut.playerTakesBallsFrom(3);
        sut.playerTakesBallsFrom(6);
        int actual = sut.getComputerStore();
        
        assertEquals(0,actual);
    }
}