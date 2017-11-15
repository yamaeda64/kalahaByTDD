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
    
    public void setGame(Game game)
    {
        this.game = game;
    }
}
