/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoerevamped;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Mike
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private AnchorPane boardPane;
    @FXML
    private GridPane board;
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
    
    BoardStateSpace currentStateSpace;
    BoardProcessor processBoard = new BoardProcessor();
         
    boolean aiTurn = true;
         boolean humanTurn = false;   
    
         public boolean getAiTurn(){return this.aiTurn;}
         public void setAiTurn(boolean value){this.aiTurn = value;}
         public boolean getHumanTurn(){return this.humanTurn;}
         public void setHumanTurn(boolean value){this.humanTurn = value;}
         
         
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        BoardStateSpace workingStateSpace = new BoardStateSpace();
        setCurrentStateSpace(workingStateSpace);
                
    }    

    @FXML
    private void resetButtonClick(MouseEvent event) {
        
    }

    @FXML
    private void beginnerVsMeClick(MouseEvent event) {
        System.out.println("Setting Beginner vs. Me!");
        
        for(int i= 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                getCurrentStateSpace().board[i][j].setTileMark("empty");
            }
        }
        
        PlayerBeginner playerBeginner = new PlayerBeginner("X");
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
                        if(getHumanTurn()){
                            
                           //Check if Mark Available to Play
                           if(getCurrentStateSpace().board[row][column].tileMark.equals("empty")){
                                System.out.println("Adding...");             

                                //Update Board StateSpace
                                getCurrentStateSpace().board[row][column].setTileMark("O");

                                //Update GUI - Add Image                                              
                                ImageView workingImage = (ImageView)event.getSource();   
                                workingImage.setImage(new Image("O.png"));


                                //Check if Win
                                if(processBoard.checkIfWinner(getCurrentStateSpace())){
                                    System.out.println("WINNER");
                                    //End Game 
                                    setAiTurn(false);
                                    setHumanTurn(false);
                                }
                                else{
                                    //Do Opponent's Move
                                    playerBeginner.doMoveGUIFriendly(board, getCurrentStateSpace(), "X");
                                    setHumanTurn(true);
                                    if(processBoard.checkIfWinner(getCurrentStateSpace())){
                                        System.out.println("WINNER");
                                        //End Game 
                                        setAiTurn(false);
                                        setHumanTurn(false);
                                        
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
        //Do Opponent's Move
        playerBeginner.doMoveGUIFriendly(board, getCurrentStateSpace(), "X");
        setHumanTurn(true);
        if(processBoard.checkIfWinner(getCurrentStateSpace())){
            System.out.println("WINNER");
            //End Game 
            setAiTurn(false);
            setHumanTurn(false);

        }
        
    }
    
    

    @FXML
    private void beginnerVsAdvancedClick(MouseEvent event) {
        boolean gameOver = false;
        PlayerBeginner playerBeginner = new PlayerBeginner("X");
        PlayerAdvanced playerAdvanced = new PlayerAdvanced("O");
        
        while(!gameOver){
            
            try {Thread.sleep(1000);} catch (InterruptedException ex) {Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);}    
            
            //Beginner Does Move
            setCurrentStateSpace(playerBeginner.doMove(getCurrentStateSpace()));
            getCurrentStateSpace().updateDisplay(board);
            
            //Check if Won
            if(processBoard.checkIfWinner(getCurrentStateSpace())){
                System.out.println("WINNER");
                gameOver = true;
            }
            
            try {Thread.sleep(1000);} catch (InterruptedException ex) {Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);}    
            
            //Advanced Does Move
            setCurrentStateSpace(playerAdvanced.doMove(getCurrentStateSpace()));
            getCurrentStateSpace().updateDisplay(board);
               
            //Check if Won
            if(processBoard.checkIfWinner(getCurrentStateSpace())){
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
    
    
    
    private void setCurrentStateSpace(BoardStateSpace newStateSpace){this.currentStateSpace = newStateSpace;}
    private BoardStateSpace getCurrentStateSpace(){return this.currentStateSpace;}
    
     
 
    
    
    
}
