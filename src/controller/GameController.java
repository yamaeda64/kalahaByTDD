package controller;

import view.ConsoleView;

/**
 * the base controller class
 */
public class GameController
{
    private ConsoleView view;
    
    public GameController(ConsoleView consoleView)
    {
        this.view = consoleView;
    }
    
    public void start()
    {
        view.showMenu();
    }
    
    public void quit()
    {
        
        
    }
}
