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
import javafx.scene.control.Button;
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
    boolean player1Turn = true;
    String player1Mark = "X";
    boolean player2Turn = false;
    String player2Mark = "O";
    
    String aiMark = "O";
    String difficulty;
    @FXML
    private Button resetButton;
    @FXML
    private Button beginnerVsMe;
    @FXML
    private Button beginnerVsAdvanced;
    @FXML
    private Button advancedVsBeginner;
    @FXML
    private Button advancedVsMaster;
    @FXML
    private Button masterVsAdvanced;
    
    public BoardStateSpace getBoardStateSpace(){return this.workingBoardStateSpace;}
    public void setBoardStateSpace(BoardStateSpace newBoardStateSpace){this.workingBoardStateSpace = newBoardStateSpace;}
    
    public BoardUpdater getBoardUpdater(){return this.boardUpdater;}
    public GridPane getBoard(){return this.board;}
    
    
    public boolean getPlayer1Turn(){return this.player1Turn;}
    public boolean getPlayer2Turn(){return this.player2Turn;}
    
    public void setPlayer1Turn(boolean value){this.player1Turn = value;}
    public void setPlayer2Turn(boolean value){this.player2Turn = value;}

    public String getAiMark(){return this.aiMark;}
    public void setAiMark(String value){this.aiMark = value;}    
    
    public void setDifficulty(String value){this.difficulty = value;}
    public String getDifficulty(){return this.difficulty;}
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {      
      
        initializeNewGame(getBoard(), getBoardStateSpace());               
    }    


  
    
    public void initializeNewGame(GridPane board, BoardStateSpace workingBoardStateSpace){
        
       
    }
         

    public void aiTurn(String difficulty, String playerMark){
        
        AIProcess newAI = new AIProcess();
        
        switch(difficulty){
            case "Beginner": newAI.doBeginnerMove(this.board, this.getBoardStateSpace(), playerMark); break;
            case "Advanced": newAI.doAdvancedMove(this.board, this.getBoardStateSpace(), playerMark, 2); break;
            //case "Master": newAI.doMasterMove(this.board, this.getBoardStateSpace(), playerMark); break;
        }
    }

    @FXML
    private void resetButtonClick(MouseEvent event) {
        
    }

    @FXML
    private void beginnerVsMeClick(MouseEvent event) {
        System.out.println("Setting Beginner vs. Me!");

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
                        if(!getBoardStateSpace().getPlayer1Turn()){
                            
                           //Check if Mark Available to Play
                           if(getBoardStateSpace().getTile(row, column).getTileMark().equals("empty")){
                                System.out.println("Adding...");             

                                //Update Board StateSpace
                                getBoardStateSpace().getTile(row, column).setTileMark(getBoardStateSpace().getPlayer2Mark());

                                //Update GUI - Add Image                                              
                                ImageView workingImage = (ImageView)event.getSource();   
                                workingImage.setImage(new Image("O.png"));


                                //Check if Win
                                if(getBoardUpdater().checkIfWinner(workingBoardStateSpace)){
                                    System.out.println("WINNER");
                                    //End Game - Clear Board
                                    //initializeNewGame(getBoard(), getBoardStateSpace());
                                     getBoardStateSpace().setPlayer1Turn(true);
                                }
                                else{
                                    //Do Opponent's Move
                                    getBoardStateSpace().setPlayer1Turn(true);
                                    aiTurn("Beginner", "X");
                                    getBoardStateSpace().setPlayer1Turn(false);
                                    if(getBoardUpdater().checkIfWinner(workingBoardStateSpace)){
                                        System.out.println("WINNER");
                                         getBoardStateSpace().setPlayer1Turn(true);
                                    }
                                    
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
        aiTurn("Beginner", "X");
        if(getBoardUpdater().checkIfWinner(workingBoardStateSpace)){
            System.out.println("WINNER");
             getBoardStateSpace().setPlayer1Turn(false);
        }
        
        setPlayer1Turn(false);
    }

    @FXML
    private void beginnerVsAdvancedClick(MouseEvent event) {
        boolean gameOver = false;
        while(!gameOver){
            aiTurn("Beginner", "X");
            getBoardStateSpace().updateStateSpace(board);
            if(getBoardUpdater().checkIfWinner(workingBoardStateSpace)){
                System.out.println("WINNER");
                gameOver = true;
            }
            aiTurn("Advanced", "O");
            getBoardStateSpace().updateStateSpace(board);
            
            
            
            if(getBoardUpdater().checkIfWinner(workingBoardStateSpace)){
                System.out.println("WINNER");
                gameOver = true;
            }
        }
        
        
        
        
    }

    @FXML
    private void advancedVsBeginnerClick(MouseEvent event) {
    }

    @FXML
    private void advancedVsMasterClick(MouseEvent event) {
    }

    @FXML
    private void masterVsAdvancedClick(MouseEvent event) {
    }
    
    
    
    
    
    
}
