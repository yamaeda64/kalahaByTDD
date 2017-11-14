package model;

import java.util.ArrayList;
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
                chosenNumber = 7-chooser; // convert to actual houseNumber
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
                    chosenNumber = 7- chooser;      // convert to actual house number
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
                ArrayList<Integer> oneLessHouses = new ArrayList<>();
                ArrayList<Integer> numberOfHits = new ArrayList<>();
                iterator = game.getComputerHouses();
                chooser = 6;
                while(iterator.hasNext())
                {
                    int currentHouse = iterator.next();
        
                    if(currentHouse == (chooser -1))
                    {
                        oneLessHouses.add(7-chooser);
                    }
                    chooser--;
                }
                iterator = game.getComputerHouses();
                chooser = 6;
                while(iterator.hasNext())
                {
                    int high = chooser - 1;
                    int low = chooser - iterator.next();
                    int hitCounter = 0;
                    for(int number :oneLessHouses)
                    {
                        if(number >= low && number <= high)
                        {
                            hitCounter++;
                        }
                    }
                    numberOfHits.add(hitCounter);
                }
                chooser = 1;
                int maxHits = 0;
                for(int number : numberOfHits)
                {
                    if(number > maxHits)
                    {
                        maxHits = number;
                        chosenNumber = chooser;
                        hasChoosen = true;
                    }
                    chooser++;
                }
                if(hasChoosen)
                {
                    board.computerTakesBallsFrom(chosenNumber);
                }
            }
            
        }
        
    }
}
