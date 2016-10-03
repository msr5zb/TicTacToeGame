/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai.homework4;

/**
 *
 * @author Mike
 */

public class BoardStateSpace {
    static int ROWS = 4;
    static int COLUMNS = 4;
    Tile[][] board = new Tile[ROWS][COLUMNS];

    
    
    public BoardStateSpace(){
            for(int i = 0; i < ROWS; i++){
                for(int j = 0; j < COLUMNS; j++){
                    this.board[i][j] = new Tile();
                }
            }
    }
    
    public Tile[][] getBoard(){return this.board;}
    public Tile getTile(int row, int column){return this.board[row][column];}
    
    
    
    
    
    
}
