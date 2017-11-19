package model;


import java.util.Iterator;

public class Game
{
    private Board board;
    private BoardFactory boardFactory;
    private boolean isGameActive;
    private MediumComputer mediumComputer;
    private ComputerFactory computerFactory;
    private boolean playerTurn;
    
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
        if(board.getSwitchTurn())
        {
            playerTurn = false;
        }
        else
        {
            playerTurn = true;
        }
        isGameActive = isGameActiveChanger;
    }
    
    public void isGameActiveAfterComputerMove()
    {
        boolean isGameActiveChanger = false;
        Iterator<Integer> iterator = board.getComputerHouses();
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
    
    
    public boolean isPlayersTurn()
    {
        return playerTurn;
    }
    
    public void computerTakesBallsFrom(int i)
    {
        board.computerTakesBallsFrom(i);
        if(board.getSwitchTurn())
        {
            playerTurn = true;
        }
        else
        {
            playerTurn = false;
        }
        isGameActiveAfterComputerMove();
    }
    
    public boolean getIsGameActive()
    {
        return isGameActive;
    }
    
    public int getPlayerScoreWhenGameIsOver()
    {
        int score = getPlayerStore();
        Iterator<Integer> computerHouses = getComputerHouses();
        while(computerHouses.hasNext())
        {
            score += computerHouses.next();
        }
        return score;
    }
    
    public int geComputerScoreWhenGameIsOver()
    {
        int score = getComputerStore();
        Iterator<Integer> playerHouses = getPlayerHouses();
        while(playerHouses.hasNext())
        {
            score += playerHouses.next();
        }
        return score;
    }
}
