/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoerevamped;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Mike
 */
public class PlayerBeginner {
    
    String myMark;
    String opponentsMark;
    
    public PlayerBeginner(String myMark){
        this.myMark = myMark;
        if(this.myMark.equals("X")){this.opponentsMark = "O";}
        else{this.opponentsMark = "X";}
        
    }
    
    public BoardStateSpace doMove(BoardStateSpace startingStateSpace){
        System.out.println("AI's Move");
        BoardStateSpace workingStateSpace = new BoardStateSpace();
        workingStateSpace.cloneStateSpace(startingStateSpace);
        
        BoardProcessor processBoard = new BoardProcessor();
        
        if(this.myMark.equals("X")){
            //Check
            Tile tileCheck1 = processBoard.checkPlayerXPotentialWin(workingStateSpace);
            Tile tileCheck2 = processBoard.checkPlayerOPotentialWin(workingStateSpace);
            Tile tileCheck3 = processBoard.findNextEmpty(workingStateSpace);
            
            if (tileCheck1 != null){tileCheck1.setTileMark("X");}
            else if(tileCheck2 != null){tileCheck2.setTileMark("X");}
            else if(tileCheck3 != null){tileCheck3.setTileMark("X");}
            else{System.out.println("CATS GAME! LOL");}
        
        }
        else{
            //Check
            Tile tileCheck1 = processBoard.checkPlayerOPotentialWin(workingStateSpace);
            Tile tileCheck2 = processBoard.checkPlayerXPotentialWin(workingStateSpace);
            Tile tileCheck3 = processBoard.findNextEmpty(workingStateSpace);
            
            if (tileCheck1 != null){tileCheck1.setTileMark("O");}
            else if(tileCheck2 != null){tileCheck1.setTileMark("O");}
            else if(tileCheck3 != null){tileCheck3.setTileMark("O");}
            else{System.out.println("CATS GAME! LOL");}
        }
        
        workingStateSpace.printBoard();
        return workingStateSpace;
        
        
        
    }
    
    public void doMoveGUIFriendly(GridPane gridBoard, BoardStateSpace workingStateSpace, String playerMark){
        System.out.println("AI's Move");
        
        BoardProcessor processBoard = new BoardProcessor();
        
        Tile foundTile;
        ImageView mark = new ImageView();
        
        
        if(playerMark.equals("X")){
            //Check
            Tile tileCheck1 = processBoard.checkPlayerXPotentialWin(workingStateSpace);
            Tile tileCheck2 = processBoard.checkPlayerOPotentialWin(workingStateSpace);
            Tile tileCheck3 = processBoard.findNextEmpty(workingStateSpace);
            
            if (tileCheck1 != null){
                tileCheck1.setTileMark("X");
                foundTile = tileCheck1;
            }
            else if(tileCheck2 != null){
                tileCheck2.setTileMark("X");
                foundTile = tileCheck2;
            }
            else if(tileCheck3 != null){
                tileCheck3.setTileMark("X");
                foundTile = tileCheck3;
            }
            else{
                System.out.println("CATS GAME! LOL");
                foundTile = null;
            }
            mark.setImage(new Image("X.png"));
            mark.setStyle("-fx-background-color: whitesmoke; -fx-padding: 2;");
        
            //workingStateSpace.setPlayer1Turn(false);
        }
        else{
            //Check
            Tile tileCheck1 = processBoard.checkPlayerOPotentialWin(workingStateSpace);
            Tile tileCheck2 = processBoard.checkPlayerXPotentialWin(workingStateSpace);
            Tile tileCheck3 = processBoard.findNextEmpty(workingStateSpace);
            
            if (tileCheck1 != null){
                tileCheck1.setTileMark("O");
                foundTile = tileCheck1;
            }
            else if(tileCheck2 != null){
                tileCheck1.setTileMark("O");
                foundTile = tileCheck2;
            }
            else if(tileCheck3 != null){
                tileCheck3.setTileMark("O");
                foundTile = tileCheck3;
            }
            else{
                System.out.println("CATS GAME! LOL");
                foundTile = null;
            }
            mark.setImage(new Image("O.png"));
            mark.setStyle("-fx-background-color: whitesmoke; -fx-padding: 2;");
        }
        
        
        
     
        if(foundTile != null){
            GridPane.setConstraints(mark, foundTile.column, foundTile.row);
            gridBoard.getChildren().add(mark);
        }
        else{
             gridBoard.getChildren().clear();
        }
        
        
        
    }
    
}
