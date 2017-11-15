package model;


import java.util.Iterator;

public class Game
{
    private Board board;
    private BoardFactory boardFactory;
    private boolean isGameActive;
    private MediumComputer mediumComputer;
    private ComputerFactory computerFactory;
    
    public Game (BoardFactory boardFactory, ComputerFactory computerFactory)
    {
        this.computerFactory = computerFactory;
        computerFactory.setGame(this);
        this.boardFactory = boardFactory;
        isGameActive = false;
        mediumComputer = this.computerFactory.getMediumComputer();
        board = this.boardFactory.getKalahaBoard();
       
        mediumComputer.setBoard(board);
    }
    
    public void startNewGame()
    {
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
        while(iterator.hasNext())
        {
           
            if(iterator.next() != 0)
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
    
    public MediumComputer getMediumComputer()
    {
        return mediumComputer;
    }
}
