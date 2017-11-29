package model;


import java.util.Iterator;
import java.util.Random;

public class Game
{
    private Board board;
    private BoardFactory boardFactory;
    private boolean isGameActive;
    private MediumComputer mediumComputer;
    private ComputerFactory computerFactory;
    private boolean playerTurn;
    private Random random;
    
    public Game(BoardFactory boardFactory, ComputerFactory computerFactory, Random random)
    {
        this.computerFactory = computerFactory;
        computerFactory.setGame(this);
        this.boardFactory = boardFactory;
        isGameActive = false;
        mediumComputer = this.computerFactory.getMediumComputer();
        board = this.boardFactory.getKalahaBoard();
       
        mediumComputer.setBoard(board);
        this.random = random;
    }
    
    public void startNewGame()
    {
        isGameActive = true;
        board.createHouseAndStores();
        playerTurn = random.nextBoolean();
    }
    
    public Board getBoard()
{
    return board;
}
    
    public boolean playerTakesBallsFrom(int i)
    {
        boolean playerTookBalls;
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
        return true;                         // need for testing purposes only
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
        return null;
       // return mediumComputer;
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
    
    public int getComputerScoreWhenGameIsOver()
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
