package model;

import java.util.Iterator;


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
                chosenNumber = chooser;
                hasChoosen = true;
            }
            chooser--;
        }
        
        if(hasChoosen)
        {
            board.computerTakesBallsFrom(chosenNumber);
        }
        else
        {
            iterator = game.getComputerHouses();
            chooser = 6;
            while(iterator.hasNext())
            {
                int currentHouse = iterator.next();
                
                if(currentHouse == (chooser + 13))
                {
                    chosenNumber = chooser;
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
}
