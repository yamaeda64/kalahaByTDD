package model;


public class Board
{
    private final int BOARD_ROWS = 2;
    private final int BOARD_HOUSES = 6;
    
    
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
        return 0;
    }
}
