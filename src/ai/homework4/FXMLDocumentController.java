/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai.homework4;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;



/**
 *
 * @author Mike
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private AnchorPane boardPane;
    @FXML
    private GridPane board;
    
    private BoardStateSpace workingBoardStateSpace = new BoardStateSpace();
    private BoardUpdater boardUpdater= new BoardUpdater();
    boolean playerTurn = true;
    String playerMark = "X";
    String aiMark = "O";
    
    public BoardStateSpace getBoardStateSpace(){return this.workingBoardStateSpace;}
    public void setBoardStateSpace(BoardStateSpace newBoardStateSpace){this.workingBoardStateSpace = newBoardStateSpace;}
    
    public BoardUpdater getBoardUpdater(){return this.boardUpdater;}
    public GridPane getBoard(){return this.board;}
    
    
    public boolean getPlayerTurn(){return this.playerTurn;}
    public void setPlayerTurn(boolean value){this.playerTurn = value;}
    
    public String getPlayerMark(){return this.playerMark;}
    public void setPlayerMark(String value){this.playerMark = value;}
    
    public String getAiMark(){return this.aiMark;}
    public void setAiMark(String value){this.aiMark = value;}    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        initializeNewGame(getBoard(), getBoardStateSpace());               
    }    


  
    
    public void initializeNewGame(GridPane board, BoardStateSpace workingBoardStateSpace){
        
        for(int i= 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                workingBoardStateSpace.getTile(i, j).setTileMark("empty");
            }
        }
        
        
       board.getChildren().clear();
       board.setStyle("-fx-background-color: blue; -fx-padding: 2;");
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                ImageView mark = new ImageView(new Image("empty.png"));
                                        
                //Add in the current working Room, note parameters are (object, col, row);
                mark.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        int row = GridPane.getRowIndex((Node)event.getSource());
                        int column = GridPane.getColumnIndex((Node)event.getSource());
                        //Check if it's the Player's Turn...
                        if(getPlayerTurn()){
                           //Check if Mark Available to Play
                           if(getBoardStateSpace().getTile(row, column).getTileMark().equals("empty")){
                                System.out.println("Adding...");             

                                //Update Board StateSpace
                                getBoardStateSpace().getTile(row, column).setTileMark(getPlayerMark());

                                //Update GUI - Add Image                                              
                                ImageView workingImage = (ImageView)event.getSource();   
                                workingImage.setImage(new Image("X.png"));


                                //Check if Win
                                if(getBoardUpdater().checkIfWinner(workingBoardStateSpace)){
                                    System.out.println("WINNER");
                                    //End Game - Clear Board
                                    initializeNewGame(getBoard(), getBoardStateSpace());

                                }
                                else{
                                    //Do Opponent's Move
                                    
                                }

                           }
                           else{
                               System.out.println("You Cannot Place a Mark Here!");
                           }           
                        }
                        else{
                            System.out.println("You Must Wait Your Turn!");
                        }
                    }
                });
                mark.setStyle("-fx-background-color: whitesmoke; -fx-padding: 2;");
                GridPane.setConstraints(mark, j, i);
                board.getChildren().add(mark);
            }
        }  
    }
         

    
    
}
