package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;


class BoardFactoryTest
{
    @BeforeEach
    void setUp()
    {
        
    }
    
    @Test
    void getKalahaBoard_shouldNotBeNull()
    {
        BoardFactory sut = new BoardFactory();
        Board board = sut.getKalahaBoard();
        
        assertNotNull(board);
    }
    
}