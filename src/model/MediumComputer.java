package model;

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
}
