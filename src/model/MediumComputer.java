package model;

import java.util.Iterator;

/**
 * Created by joakimbergqvist on 2017-11-13.
 */
public class MediumComputer
{
    private Board board;
    private Game game;
    
    public MediumComputer(Game game)
    {
        this.game = game;
    }
    
    public void setBoard(Board board)
    {
        this.board = board;
    }
    
    public Board getBoard()
    {
        return board;
    }
    
    public void chooseNextHouse()
    {
        Iterator<Integer> iterator = game.getComputerHouses();
        int chooser = 6;
        int chosenNumber = -1;
        boolean hasChoosen = false;
        while(iterator.hasNext())
        {
            int currentHouse = iterator.next();
            if(currentHouse == chooser)
            {
                chosenNumber = currentHouse;
                hasChoosen = true;
            }
            chooser--;
        }
        
        if(hasChoosen)
        {
            board.computerTakesBallsFrom(chosenNumber);
        }
        
    }
}
