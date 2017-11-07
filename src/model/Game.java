package model;


import java.util.Iterator;

public class Game
{
    private Board board;
    private BoardFactory boardFactory;
    
    public Game (BoardFactory boardFactory)
    {
        this.boardFactory = boardFactory;
    }
    
    public void startNewGame()
    {
        board = boardFactory.getKalahaBoard();
    }
    
    public Board getBoard()
    {
        return board;
    }
    
    public void playerTakesBallsFrom(int i)
    {
        board.playerTakesBallsFrom(i);
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
    
}
