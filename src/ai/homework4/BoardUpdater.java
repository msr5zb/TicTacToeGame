/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai.homework4;

import javafx.collections.ObservableList;
import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Mike
 */
public class BoardUpdater {
 
    public BoardUpdater(){}
    
    public void resetBoard(GridPane board, BoardStateSpace workingBoardStateSpace){
        //Reset The StateSpace
        for(int i= 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                workingBoardStateSpace.getTile(i, j).setTileMark("empty");
            }
        }
        
        
    
        
//        for (Node component : board.getChildren()) {
//            ImageView workingImage = (ImageView)component;   
//            workingImage.setImage(new Image("empty.png"));
//        }
//        
        
         
    }
    
    public void synchBoard(GridPane board, BoardStateSpace workingBoardStateSpace){
        
    }
    
    
    public boolean checkIfWinner(BoardStateSpace board){
     
        //Check for three in a row
        for(int row = 0; row < 4; row++){
            for(int col = 0; col < 4; col++){


                //Placements to Check

                //2 Left
                if(col-2>=0){
                    if(!board.getTile(row,col).getTileMark().equals("empty") &&
                       board.getTile(row,col).getTileMark().equals(board.getTile(row,col-1).getTileMark()) && 
                       board.getTile(row,col-1).getTileMark().equals(board.getTile(row,col-2).getTileMark())){
                        return true;
                    }
                }
                //2 Right
                if(col+2<4){
                    if(!board.getTile(row,col).getTileMark().equals("empty") &&
                       board.getTile(row,col).getTileMark().equals(board.getTile(row,col+1).getTileMark()) &&
                       board.getTile(row,col+1).getTileMark().equals(board.getTile(row,col+2).getTileMark())){
                        return true;
                    }
                }

                //1 Left and 1 Right
                if(col-1>=0 && col+1<4){
                    if(!board.getTile(row,col).getTileMark().equals("empty") &&
                       board.getTile(row,col).getTileMark().equals(board.getTile(row,col-1).getTileMark())&&
                       board.getTile(row,col-1).getTileMark().equals(board.getTile(row,col+1).getTileMark())){
                        return true;
                    }   
                }

                //2 Up
                if(row-2>=0){
                    if(!board.getTile(row,col).getTileMark().equals("empty") &&
                       board.getTile(row,col).getTileMark().equals(board.getTile(row-1,col).getTileMark())&&
                       board.getTile(row-1,col).getTileMark().equals(board.getTile(row-2,col).getTileMark())){
                        return true;
                    }
                }

                //2 Down
                if(row+2<4){
                    if(!board.getTile(row,col).getTileMark().equals("empty") &&
                       board.getTile(row,col).getTileMark().equals(board.getTile(row+1,col).getTileMark())&&
                       board.getTile(row+1,col).getTileMark().equals(board.getTile(row+2,col).getTileMark())){
                        return true;
                    }
                }

                //1 Up 1 Down
                if(row-1>=0 && row+1<4){
                    if(!board.getTile(row,col).getTileMark().equals("empty") &&
                       board.getTile(row,col).getTileMark().equals(board.getTile(row-1,col).getTileMark())&&
                       board.getTile(row-1,col).getTileMark().equals(board.getTile(row+1,col).getTileMark())){
                        return true;
                    }    
                }

                //2 Diagonal Up-Left
                if(row-2>=0 && col-2>=0){
                    if(!board.getTile(row,col).getTileMark().equals("empty") &&
                       board.getTile(row,col).getTileMark().equals(board.getTile(row-1,col-1).getTileMark())&&
                       board.getTile(row-1,col-1).getTileMark().equals(board.getTile(row-2,col-2).getTileMark())){
                        return true;
                    }    
                }

                //2 Diagonal Up-Right
                if(row-2>=0 && col+2<4){
                    if(!board.getTile(row,col).getTileMark().equals("empty") &&
                       board.getTile(row,col).getTileMark().equals(board.getTile(row-1,col+1).getTileMark())&&
                       board.getTile(row-1,col+1).getTileMark().equals(board.getTile(row-2,col+2).getTileMark())){
                        return true;
                    }
                }

                //2 Diagonal Down-Left
                if(row+2<4 && col-2>=0){
                    if(!board.getTile(row,col).getTileMark().equals("empty") &&
                       board.getTile(row,col).getTileMark().equals(board.getTile(row+1,col-1).getTileMark())&&
                       board.getTile(row+1,col-1).getTileMark().equals(board.getTile(row+2,col-2).getTileMark())){
                        return true;
                    }
                }

                //2 Diagonal Down-Right
                if(row+2<4 && col+2<4){
                    if(!board.getTile(row,col).getTileMark().equals("empty") &&
                       board.getTile(row,col).getTileMark().equals(board.getTile(row+1,col+1).getTileMark())&&
                       board.getTile(row+1,col+1).getTileMark().equals(board.getTile(row+2,col+2).getTileMark())){
                        return true;
                    }
                }


                //1 Diagonal Up-Left, 1 Diagonal Down-Right
                if(row-1>=0 && row+1<4 && col-1>=0 && col+1<4){
                    if(!board.getTile(row,col).getTileMark().equals("empty") &&
                       board.getTile(row,col).getTileMark().equals(board.getTile(row-1,col-1).getTileMark())&&
                       board.getTile(row-1,col-1).getTileMark().equals(board.getTile(row+1,col+1).getTileMark())){
                        return true;
                    }
                }

                //1 Diagonal Up-Right, 1 Diagonal Down-Left
                if(row-1>=0 && row+1<4 && col-1>=0 && col+1<4){
                    if(!board.getTile(row,col).getTileMark().equals("empty") &&
                       board.getTile(row,col).getTileMark().equals(board.getTile(row-1,col+1).getTileMark())&&
                       board.getTile(row-1,col+1).getTileMark().equals(board.getTile(row+1,col-1).getTileMark())){
                        return true;
                    }
                }

            }
        }
        return false;
        
    }
    
    
    
}
