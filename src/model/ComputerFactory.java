package model;


public class ComputerFactory
{
    private Game game;
    
    public ComputerFactory()
    {
        
    }
    
    
    
    public MediumComputer getMediumComputer()
    {
        return new MediumComputer(game);
    }
}
