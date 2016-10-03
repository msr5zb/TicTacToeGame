/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai.homework4;

import javafx.scene.layout.GridPane;

/**
 *
 * @author Mike
 */
public class BoardUpdater {
 
    public BoardUpdater(){}
    
    public void resetBoard(BoardStateSpace board, GridPane grid){
        for(int i= 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                board.getTile(i, j).setTileMark("empty");
            }
        }
    }
    
    public boolean checkIfWinner(BoardStateSpace board){
        //Note, There is 10 Possible Ways to Win...
        if(     board.getTile(0, 0).getTileMark().equals("X") && 
                board.getTile(0, 1).getTileMark().equals("X") &&
                board.getTile(0, 2).getTileMark().equals("X") &&
                board.getTile(0, 3).getTileMark().equals("X")   ){
        }
        if(     board.getTile(1, 0).getTileMark().equals("X") && 
                board.getTile(1, 1).getTileMark().equals("X") &&
                board.getTile(1, 2).getTileMark().equals("X") &&
                board.getTile(1, 3).getTileMark().equals("X")   ){
        }  
        if(     board.getTile(2, 0).getTileMark().equals("X") && 
                board.getTile(2, 1).getTileMark().equals("X") &&
                board.getTile(2, 2).getTileMark().equals("X") &&
                board.getTile(2, 3).getTileMark().equals("X")   ){
        }                 
        if(     board.getTile(3, 0).getTileMark().equals("X") && 
                board.getTile(3, 1).getTileMark().equals("X") &&
                board.getTile(3, 2).getTileMark().equals("X") &&
                board.getTile(3, 3).getTileMark().equals("X")   ){
        }         
        if(     board.getTile(0, 0).getTileMark().equals("X") && 
                board.getTile(1, 0).getTileMark().equals("X") &&
                board.getTile(2, 0).getTileMark().equals("X") &&
                board.getTile(3, 0).getTileMark().equals("X")   ){
        }    
        if(     board.getTile(0, 1).getTileMark().equals("X") && 
                board.getTile(1, 1).getTileMark().equals("X") &&
                board.getTile(2, 1).getTileMark().equals("X") &&
                board.getTile(3, 1).getTileMark().equals("X")   ){
        } 
        if(     board.getTile(0, 2).getTileMark().equals("X") && 
                board.getTile(1, 2).getTileMark().equals("X") &&
                board.getTile(2, 2).getTileMark().equals("X") &&
                board.getTile(3, 2).getTileMark().equals("X")   ){
        } 
        if(     board.getTile(0, 3).getTileMark().equals("X") && 
                board.getTile(1, 3).getTileMark().equals("X") &&
                board.getTile(2, 3).getTileMark().equals("X") &&
                board.getTile(3, 3).getTileMark().equals("X")   ){
        } 
        if(     board.getTile(0, 0).getTileMark().equals("X") && 
                board.getTile(1, 1).getTileMark().equals("X") &&
                board.getTile(2, 2).getTileMark().equals("X") &&
                board.getTile(3, 3).getTileMark().equals("X")   ){
        } 
        if(     board.getTile(0, 3).getTileMark().equals("X") && 
                board.getTile(1, 2).getTileMark().equals("X") &&
                board.getTile(2, 1).getTileMark().equals("X") &&
                board.getTile(3, 0).getTileMark().equals("X")   ){
        }
        return false;
    }
    
    
    
}
