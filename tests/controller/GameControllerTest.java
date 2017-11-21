package controller;

import model.ComputerFactory;
import model.Game;
import model.MediumComputer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import view.ConsoleView;
import view.UserInteraction;

import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static view.UserInteraction.PLAY;
import static view.UserInteraction.QUIT;


class GameControllerTest
{
    private GameController sut;
    private ConsoleView view;
    private Game game;
    private MediumComputer mediumComputer;
    private ComputerFactory computerFactory;
    
    @BeforeEach
    public void setUp()
    {
        view = mock(ConsoleView.class);
        game = mock(Game.class);
        sut = new GameController(view, game);
        mediumComputer = mock(MediumComputer.class);
        when(game.getMediumComputer()).thenReturn(mediumComputer);
        computerFactory = mock(ComputerFactory.class);
        
        
    }
    
    @Test
    public void shouldShowMenu_whenStart_shouldShowMenu()
    {
        sut.start();
        verify(view).showMenu();
    }
    
    @Test
    public void quit_whenQuitCommandRecieved_shouldSendQuitMesssage()
    {
        exchangeGameControllerToSpyThatDoesntExit();
        sut.quit();
        verify(view).showQuitMessage();
    }
    
    @Test
    public void quit_whenQuitCommandRecieved_ShouldQuitApplication()
    {
        exchangeGameControllerToSpyThatDoesntExit();
        sut.quit();
        verify(sut).exitApplication();
    }
    
    @Test
    public void shouldStartNewGame()
    {
        sut.play();
        verify(game).startNewGame();
    }
    
    @Test
    public void takeAction_whenPlay_ShouldStartNewGame()
    {
        sut = spy(new GameController(view, game));
        sut.takeAction(PLAY);
        verify(sut).play();
    }
    
    
    @Test
    public void takeAction_whenPlay_shouldCallPrintBoard()
    {
        sut.play();
        ArrayList<Integer> houses = new ArrayList<>();
        for(int i = 0; i < 6; i++)
        {
            houses.add(6);
        }
        InOrder rightOrder = inOrder(view);
        
        rightOrder.verify(view).clearScreen();
        rightOrder.verify(view).drawBoard(anyInt(), anyInt(), anyObject(), anyObject());
        
        
    }
    
    @Test
    public void start_getUserCommand_ShouldCallCollectEvent()
    {
        sut.start();
        verify(view).collectEvent();
    }
    
    @Test
    public void takeAction_whenQuit_ShouldQuit()
    {
        exchangeGameControllerToSpyThatDoesntExit();
        sut.takeAction(QUIT);
        verify(sut).exitApplication();
    }
    
    
    @Test
    public void takeAction_whenStartGame_ShouldCallTakeAction()
    {
        exchangeGameControllerToSpyThatDoesntExit();
        when(view.collectEvent()).thenReturn(QUIT);
        sut.start();
        
        verify(sut).takeAction(QUIT);
    }
    
    @Test
    public void whenShowWelcomeScreen_ShouldClearScreen()
    {
        sut.start();
        
        InOrder rightOrder = inOrder(view);
        
        rightOrder.verify(view).clearScreen();
        rightOrder.verify(view).showMenu();
        
    }
    
    @Test
    public void GameController_whenPlay_ShouldAskForUserInput()
    {
        when(game.isPlayersTurn()).thenReturn(true);
        howManyRoundsGameShouldBeActive(1);
        sut.play();
        verify(view).collectEvent();
    }
    
    @Test
    public void GameController_whenHouse1IsChosenDuringPlay_shouldCallTakeBallsFromHouse1()
    {
        when(game.isPlayersTurn()).thenReturn(true);
        when(view.collectEvent()).thenReturn(UserInteraction.PICK_BALLS_FROM_HOUSE);
        when(view.getNumberAfterInput()).thenReturn(1);
        howManyRoundsGameShouldBeActive(1);
        sut.play();
        verify(game).playerTakesBallsFrom(1);
        
    }
    
    @Test
    public void GameController_whenHouse4IsChosenDuringPlay_shouldUpdateView()
    {
        when(view.collectEvent()).thenReturn(UserInteraction.PICK_BALLS_FROM_HOUSE);
        when(view.getNumberAfterInput()).thenReturn(4);
        sut.play();
        InOrder rightOrder = inOrder(view);
        
        rightOrder.verify(view).clearScreen();
        rightOrder.verify(view).drawBoard(game.getPlayerStore(), game.getComputerStore(), game.getPlayerHouses(), game.getComputerHouses());
    }
    
    @Test
    public void GameController_whenComputersTurn_ShouldClearScreenAndDrawNewBoard()
    {
        when(game.isPlayersTurn()).thenReturn(false);
        when(game.isGameActive()).thenReturn(true).thenReturn(false);
    
        sut.play();
        InOrder rightOrder = inOrder(view);
        
        rightOrder.verify(view).clearScreen();
        rightOrder.verify(view).drawBoard(game.getPlayerStore(), game.getComputerStore(), game.getPlayerHouses(), game.getComputerHouses());
        rightOrder.verify(view).clearScreen();
        rightOrder.verify(view).drawBoard(game.getPlayerStore(), game.getComputerStore(), game.getPlayerHouses(), game.getComputerHouses());
    }
    
    @Test
    public void GameController_playerTakeBallAndGameIsNotOver_computerShouldTakeBalls()
    {
        howManyRoundsGameShouldBeActive(1);
        when(view.collectEvent()).thenReturn(UserInteraction.PICK_BALLS_FROM_HOUSE);
        when(view.getNumberAfterInput()).thenReturn(3);
        
        sut.play();
        
        verify(mediumComputer).chooseNextHouse();
    }
    
    @Test
    public void GameController_startNewGame_shouldClearScreenAndShowInstructions()
    {
        when(game.isPlayersTurn()).thenReturn(true);
        howManyRoundsGameShouldBeActive(1);
        when(view.collectEvent()).thenReturn(UserInteraction.PLAY);
        sut.start();
        InOrder rightOrder = inOrder(view);
        
        rightOrder.verify(view).clearScreen();
        rightOrder.verify(view).drawBoard(game.getPlayerStore(), game.getComputerStore(), game.getPlayerHouses(), game.getComputerHouses());
        rightOrder.verify(view).showChooseHouseText();
    }
    
    @Test
    public void GameController_startNewGame_shouldShowWrongInputText()
    {
        when(view.collectEvent()).thenThrow(new IllegalArgumentException("Wrong Input"));
        sut.start();
        verify(view).showWrongInputMessage();
    }
    
    @Test
    public void GameController_whenPlaying_ShouldTakeEachSecondTurn()
    {
        when(game.isPlayersTurn()).thenReturn(true).thenReturn(false).thenReturn(true).thenReturn(false).thenReturn(true).thenReturn(false);
        when(view.collectEvent()).thenReturn(UserInteraction.PICK_BALLS_FROM_HOUSE);
        when(view.getNumberAfterInput()).thenReturn(1).thenReturn(2).thenReturn(3);
        howManyRoundsGameShouldBeActive(6);
        sut.play();
        verify(view, times(3)).collectEvent();
    }
    
    @Test
    public void GameController_whenGameOver_ShouldCallPresentFinalScore()
    {
        when(game.isGameActive()).thenReturn(false);
        when(game.getPlayerScoreWhenGameIsOver()).thenReturn(35);
        when(game.getComputerScoreWhenGameIsOver()).thenReturn(37);
        sut.play();
        verify(view).presentFinalScore(35,37);
    }
    
    @Test
    public void GameController_whenGameOver_ShouldCallPressAnyKey()
    {
        when(game.isGameActive()).thenReturn(false);
        when(game.getPlayerScoreWhenGameIsOver()).thenReturn(36);
        when(game.getComputerScoreWhenGameIsOver()).thenReturn(36);
        sut.play();
        verify(view).pressAnyKeyToContrinue();
                
    }
    
    private void exchangeGameControllerToSpyThatDoesntExit()
    {
        sut = spy(new GameController(view,game));
        doReturn(true).when(sut).exitApplication();
    }
    
    private void howManyRoundsGameShouldBeActive(int rounds)
    {
        when(game.isGameActive()).thenAnswer(new Answer()
        {
            private int counter = 0;
            
            public Boolean answer(InvocationOnMock invocation) throws Throwable
            {
                if(counter++ < rounds)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
        });
    }
}

