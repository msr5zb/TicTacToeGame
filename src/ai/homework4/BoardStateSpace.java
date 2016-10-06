/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai.homework4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Mike
 */

public class BoardStateSpace {
    static int ROWS = 4;
    static int COLUMNS = 4;
    Tile[][] board = new Tile[ROWS][COLUMNS];
    int player1PotentialOf2;
    int player2PotentialOf2;
    List<BoardStateSpace> children = new ArrayList<BoardStateSpace>();
    
    
    public BoardStateSpace(){
            for(int i = 0; i < ROWS; i++){
                for(int j = 0; j < COLUMNS; j++){
                    this.board[i][j] = new Tile();
                }
            }
    }
    
    
    
    public Tile[][] getBoard(){return this.board;}
    public Tile getTile(int row, int column){return this.board[row][column];}
    
    public void generateChildrenStateSpaces(String mark){
        //Potential Children States
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(this.board[i][j].getTileMark().equals("empty")){
                    //Make it a Child State
                    BoardStateSpace childBoard = new BoardStateSpace();
                    childBoard.cloneBoard(this);
                    childBoard.getTile(i, j).setTileMark(mark);
                    this.children.add(childBoard);
                }
            }
        }
    }
    
    public void cloneBoard(BoardStateSpace boardToClone){
//Loop Through All Rooms, Cloning them one by one.
//        for(int i = 0; i < 4.length; i++){
//            for(int j = 0; j < 4[0].length; j++){
//                clonedRoomsArray[i][j] = boardToClone[i][j].cloneTile(boardToClone[i][j]);
//            }
//        }
    }
    
    public int calculatePlayer1PotentialOf2(String mark){
        //Note, a potential of two is when two makes are placed next to each other consecuatively. 
        //8 Cases: 1Up, 1Down, 1Left, 1Right, 1Up-Left, 1Up-Right, 1Down-Left, 1Down-Right
        int potentialCounter = 0;
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                
            }
        }
        
        
        
        
        return 0;
    }
    
    public int calculatePlayer2PotentialOf2(String mark){
        
        return 0;
    }
    
    public int getPlayer1PotentialOf2(){return this.player1PotentialOf2;}
    public int getPlayer2PotentialOf2(){return this.player2PotentialOf2;}
}
