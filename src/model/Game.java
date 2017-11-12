package model;


import java.util.Iterator;

public class Game
{
    private Board board;
    private BoardFactory boardFactory;
    private boolean isGameActive;
    
    public Game (BoardFactory boardFactory)
    {
        this.boardFactory = boardFactory;
        isGameActive = false;
    }
    
    public void startNewGame()
    {
        board = boardFactory.getKalahaBoard();
        isGameActive = true;
        board.createHouseAndStores();
    }
    
    public Board getBoard()
{
    return board;
}
    
    public void playerTakesBallsFrom(int i)
    {
        board.playerTakesBallsFrom(i);
        boolean isGameActiveChanger = false;
        Iterator<Integer> iterator = board.getPlayerHouses();
        while(board.getPlayerHouses().hasNext())
        {
            if(board.getPlayerHouses().next() != 0)
            {
                isGameActiveChanger = true;
            }
        }
        isGameActive = isGameActiveChanger;
        
    }
    
    public int getPlayerStore()
    {
        return board.getPlayerStore();
    }
    
    public int getComputerStore()
    {
        return board.getComputerStore();
    }
    
    public Iterator<Integer> getPlayerHouses()
    {
        return board.getPlayerHouses();
    }
    
    public Iterator<Integer> getComputerHouses()
    {
        return board.getComputerHouses();
    }
    
    public boolean isGameActive()
    {
        return isGameActive;
    }
}
