package model;


public class Game
{
    Board board;
    BoardFactory boardFactory;
    
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
}
