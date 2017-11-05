package model;


import java.util.ArrayList;
import java.util.Iterator;

public class Board
{
    private final int BOARD_ROWS = 2;
    private final int BOARD_HOUSES = 6;
    private final int BOARD_STORES = 2;
    private final int STARTING_BALLS_PER_HOUSE = 6;
    
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
            if(i == BOARD_HOUSES || i == BOARD_HOUSES+1+BOARD_HOUSES)
            {
                ballHolder.add(0);
            }
            else
            {
                ballHolder.add(STARTING_BALLS_PER_HOUSE);
            }
        }
    }
    
    public Iterator<Integer> getHousesAndStores()
    {
        return ballHolder.iterator();
    }
    
    public int getSTARTING_BALLS_PER_HOUSE()
    {
        return STARTING_BALLS_PER_HOUSE;
    }
    
    public Iterator<Integer> getPlayerHouses()
    {
        Iterator<Integer> iterator = new Iterator<Integer>()
        {
            int i = 0;
            @Override
            public boolean hasNext()
            {
                if(i < BOARD_HOUSES)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
    
            @Override
            public Integer next()
            {
                return ballHolder.get(i++);
            }
        };
        return iterator;
    }
    
    public int getPlayerStore()
    {
        return ballHolder.get(BOARD_HOUSES);
    }
}
