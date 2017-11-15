package model;

import java.util.ArrayList;
import java.util.Iterator;


public class MediumComputer
{
    private Board board;
    private Game game;
    private boolean hasChosen;
    
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
        int chosenNumber = getNextHouseNumber();
        
        if(hasChosen)
        {
            board.computerTakesBallsFrom(chosenNumber);
        }
    }
    
    public int getNextHouseNumber()
    {
        hasChosen = false;
        
        int chosenNumber = getEvenToStoreHouse();
        
        if(!hasChosen)
        {
            chosenNumber = getEvenToStoreAroundTheBoard();
            
        }
        
        if(!hasChosen)
        {
            chosenNumber = getHouseThatMakeEvenForNextRound();
        }
        
        if(!hasChosen)
        {
            chosenNumber = getHouseNumberOfHighestNumberOfBalls();
        }
        
        return chosenNumber;
    }
    
    
    
    
    private int getEvenToStoreHouse()
    {
        Iterator<Integer> iterator = game.getComputerHouses();
        
        int chooser = 1;
        int chosenNumber = -1;
        while(iterator.hasNext())
        {
            int currentHouse = iterator.next();
            if(currentHouse == 7-chooser)
            {
                chosenNumber = chooser;
                hasChosen = true;
            }
            chooser++;
        }
        return chosenNumber;
    }
    
    private int getEvenToStoreAroundTheBoard()
    {
        
        Iterator<Integer> iterator = game.getComputerHouses();
        int chooser = 1;
        int chosenNumber = -1;
        while(iterator.hasNext())
        {
            int currentHouse = iterator.next();
            
            if(currentHouse == (7-chooser + 13))
            {
                chosenNumber = chooser;      // convert to actual house number
                hasChosen = true;
            }
            chooser++;
        }
        return chosenNumber;
    }
    
    private int getHouseThatMakeEvenForNextRound()
    {
        ArrayList<Integer> oneLessHouses = new ArrayList<>();
        ArrayList<Integer> numberOfHits = new ArrayList<>();
        Iterator<Integer> iterator = game.getComputerHouses();
        int chooser = 1;
        while(iterator.hasNext())
        {
            int currentHouse = iterator.next();
            
            if(currentHouse == (7-chooser -1))
            {
                oneLessHouses.add(chooser);
            }
            chooser++;
        }
        iterator = game.getComputerHouses();
        chooser = 1;
        while(iterator.hasNext())
        {
            int high = 7 - chooser - 1;
            int low = 7 - chooser - iterator.next();
            int hitCounter = 0;
            for(int number :oneLessHouses)
            {
                if(number >= low && number <= high)
                {
                    hitCounter++;
                }
            }
            numberOfHits.add(hitCounter);
            chooser++;
        }
        chooser = 1;
        int maxHits = 0;
        int chosenNumber = -1;
        for(int number : numberOfHits)
        {
            if(number > maxHits)
            {
                maxHits = number;
                chosenNumber = chooser;
                hasChosen = true;
            }
            chooser++;
        }
        return chosenNumber;
    }
    
    private int getHouseNumberOfHighestNumberOfBalls()
    {
        int maxBallCount = 0;
        int chooser = 1;
        int chosenNumber = -1;
        Iterator<Integer> iterator = game.getComputerHouses();
        while(iterator.hasNext())
        {
            int currentHouse = iterator.next();
            if(currentHouse > maxBallCount)
            {
                maxBallCount = currentHouse;
                chosenNumber = chooser;
                hasChosen = true;
            }
            chooser++;
        }
        return chosenNumber;
    }
}
