package model;


import java.util.Iterator;

public class Board
{
    private final int BOARD_ROWS = 2;
    private final int BOARD_HOUSES = 6;
    private final int BOARD_STORES = 2;
    
    public void Board()
    {
        
    }
    
    public int getBoardRows()
    {
        return BOARD_ROWS;
    }
    
    public int getBoardHousesPerSide()
    {
        return BOARD_HOUSES;
    }
    
    public int getStoreSize()
    {
        return BOARD_STORES;
    }
    
    public void createHouseAndStores()
    {
        
    }
    
    public Iterator<Integer> getHousesAndStores()
    {
        return null;
    }
}
