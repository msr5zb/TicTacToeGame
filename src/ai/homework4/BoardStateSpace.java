/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai.homework4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

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
    String player1Mark = "X";
    String player2Mark = "O";
    boolean player1Turn = true;
    int nodeID = 0;
    int parentID = 0;
    int lookAhead = 0;
    int potentialValue = 0;
    BoardStateSpace parent;
    
    
    List<BoardStateSpace> children = new ArrayList<BoardStateSpace>();
    
    public List<BoardStateSpace> getChildren(){return this.children;}
    public String getPlayer1Mark(){return this.player1Mark;}
    public String getPlayer2Mark(){return this.player2Mark;}
    public boolean getPlayer1Turn(){return this.player1Turn;}
    public void setPlayer1Turn(boolean value){this.player1Turn = value;}
    public BoardStateSpace(){
            for(int i = 0; i < ROWS; i++){
                for(int j = 0; j < COLUMNS; j++){
                    this.board[i][j] = new Tile(i,j);
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
                    childBoard.parent = this;
                    this.children.add(childBoard);
                    childBoard.getTile(i, j).setTileMark("empty");
                }
            }
        }
    }
    
    public void cloneBoard(BoardStateSpace boardToClone){
        //Loop Through All Rooms, Cloning them one by one.
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                this.board[i][j] = boardToClone.getTile(i,j);
            }
        }
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
    
    
    
    public void updateStateSpace(GridPane gridBoard){
        System.out.println("Updating Display...");
        
       gridBoard.getChildren().clear();
       gridBoard.setStyle("-fx-background-color: blue; -fx-padding: 2;");
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                ImageView mark = new ImageView();
                mark.setImage(new Image(this.board[i][j].getTileMark() + ".png"));
                mark.setStyle("-fx-background-color: whitesmoke; -fx-padding: 2;");
                GridPane.setConstraints(mark, j, i);
                gridBoard.getChildren().add(mark);
            }
        }
           
    }
    
    public void flipOdd(BoardStateSpace updatedStateSpace, String flip){
            for(int i = 0; i < 4; i++){
                for(int j = 0; j < 4; j++){
                    if(!this.board[i][j].getTileMark().equals(updatedStateSpace.getTile(i, j).getTileMark())){
                        this.board[i][j].setTileMark(flip);
                    }
                }
            }
            
    }
}
