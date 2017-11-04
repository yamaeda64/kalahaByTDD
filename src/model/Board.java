package model;


import java.util.ArrayList;
import java.util.Iterator;

public class Board
{
    private final int BOARD_ROWS = 2;
    private final int BOARD_HOUSES = 6;
    private final int BOARD_STORES = 2;
    private ArrayList<Integer> ballHolder;
    
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
        ballHolder = new ArrayList<>();
        for(int i = 0; i<BOARD_HOUSES*BOARD_ROWS + BOARD_STORES; i++)
        {
            ballHolder.add(0);
        }
    }
    
    public Iterator<Integer> getHousesAndStores()
    {
        return ballHolder.iterator();
    }
}
