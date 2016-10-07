/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai.homework4;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Mike
 */
public class AIProcess {
        private BoardUpdater boardUpdater= new BoardUpdater();

    
    public AIProcess(){}
    
    public void doBeginnerMove(GridPane gridBoard, BoardStateSpace workingStateSpace, String playerMark){
        System.out.println("AI's Move");
        
        BoardUpdater checkWinner = new BoardUpdater();
        
        Tile foundTile;
        ImageView mark = new ImageView();
        
        
        if(playerMark.equals("X")){
            //Check
            Tile tileCheck1 = checkWinner.checkPlayer1PotentialWin(workingStateSpace);
            Tile tileCheck2 = checkWinner.checkPlayer2PotentialWin(workingStateSpace);
            Tile tileCheck3 = checkWinner.findNextEmpty(workingStateSpace);
            
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
        
            workingStateSpace.setPlayer1Turn(false);
        }
        else{
            //Check
            Tile tileCheck1 = checkWinner.checkPlayer1PotentialWin(workingStateSpace);
            Tile tileCheck2 = checkWinner.checkPlayer2PotentialWin(workingStateSpace);
            Tile tileCheck3 = checkWinner.findNextEmpty(workingStateSpace);
            
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
            workingStateSpace.setPlayer1Turn(true);
        }
        
        
        
     
        if(foundTile != null){
            GridPane.setConstraints(mark, foundTile.getColumn(), foundTile.getRow());
            gridBoard.getChildren().add(mark);
        }
        else{
             gridBoard.getChildren().clear();
        }
        
        
        
    }
//    
   public void doAdvancedMove(GridPane gridBoard, BoardStateSpace workingStateSpace, String playerMark, int depth){
        System.out.println("AI's Move");
        
//        BoardUpdater checkWinner = new BoardUpdater();
//        
//        Tile foundTile;
//        BoardStateSpace foundBoard;
//        ImageView mark = new ImageView();
//        
//        
//        if(playerMark.equals("X")){
//            //Check
//            Tile tileCheck1 = checkWinner.checkPlayer1PotentialWin(workingStateSpace);
//            Tile tileCheck2 = checkWinner.checkPlayer2PotentialWin(workingStateSpace);
//            foundBoard = miniMax(gridBoard, workingStateSpace, playerMark, depth);
//            
//            if (tileCheck1 != null){
//                tileCheck1.setTileMark("X");
//                foundTile = tileCheck1;
//            }
//            else if(tileCheck2 != null){
//                tileCheck2.setTileMark("X");
//                foundTile = tileCheck2;
//            }
//            else if(foundBoard != null){
//                System.out.println("here?");
//                workingStateSpace = foundBoard;
//            }
//            else{
//                System.out.println("CATS GAME! LOL");
//                foundTile = null;
//            }
//            mark.setImage(new Image("X.png"));
//            mark.setStyle("-fx-background-color: whitesmoke; -fx-padding: 2;");
//        
//            workingStateSpace.setPlayer1Turn(false);
//        }
//        else{
//            //Check
//            Tile tileCheck1 = checkWinner.checkPlayer1PotentialWin(workingStateSpace);
//            Tile tileCheck2 = checkWinner.checkPlayer2PotentialWin(workingStateSpace);
//            foundBoard = miniMax(gridBoard, workingStateSpace, playerMark, depth);
//            
//            if (tileCheck1 != null){
//                tileCheck1.setTileMark("O");
//                foundTile = tileCheck1;
//            }
//            else if(tileCheck2 != null){
//                tileCheck1.setTileMark("O");
//                foundTile = tileCheck2;
//            }
//            else if(foundBoard != null){
//                System.out.println("here?");
//                workingStateSpace = foundBoard;
//            }            
//            else{
//                System.out.println("CATS GAME! LOL");
//                foundTile = null;
//            }
//            mark.setImage(new Image("O.png"));
//            mark.setStyle("-fx-background-color: whitesmoke; -fx-padding: 2;");
//            workingStateSpace.setPlayer1Turn(true);       
//        }
//   }
//   
//   
//   
//   
   
        
        if(playerMark.equals("X")){
            //Create Our Fringe
            List<BoardStateSpace> fringeCalculation = new ArrayList<BoardStateSpace>();
            List<BoardStateSpace> fringeGeneration = new ArrayList<BoardStateSpace>();
            

            
            int nodeID = 0;
            int parentID = 0;
            int lookAhead = 0;
            int potentialValue = 0;
            
            workingStateSpace.nodeID = nodeID;
            workingStateSpace.parentID = parentID;
            workingStateSpace.lookAhead = lookAhead;
            workingStateSpace.potentialValue = boardUpdater.countPlayer1PotentialWin(workingStateSpace);
            
             //Add First Node to Fringe
            fringeCalculation.add(workingStateSpace);
            fringeGeneration.add(workingStateSpace);           
            
            //Look Ahead 2 Children's worth!
            while(lookAhead <= 2){

                //Work with Best H-Value
                workingStateSpace = fringeGeneration.get(nodeID);
                fringeGeneration.remove(nodeID);
     
                
                //Expand Node's Children  
                if(lookAhead%2 == 0){
          
                    workingStateSpace.generateChildrenStateSpaces("X");
                    List<BoardStateSpace> children = workingStateSpace.getChildren();
                    for(int i = 0; i < children.size(); i++) {
                        children.get(i).nodeID = ++nodeID;
                        children.get(i).parentID = workingStateSpace.parentID;
                        children.get(i).lookAhead = 1+lookAhead;
                        children.get(i).potentialValue = boardUpdater.countPlayer1PotentialWin(children.get(i));
                        fringeGeneration.add(children.get(i));
                        fringeCalculation.add(children.get(i));
                    }
    
                }
                else{
                    workingStateSpace.generateChildrenStateSpaces("O");
                    List<BoardStateSpace> children = workingStateSpace.getChildren();
                    for(int i = 0; i < children.size(); i++) {
                        children.get(i).nodeID = ++nodeID;
                        children.get(i).parentID = workingStateSpace.parentID;
                        children.get(i).lookAhead = 1+lookAhead;
                        children.get(i).potentialValue = boardUpdater.countPlayer2PotentialWin(children.get(i));
                        fringeGeneration.add(children.get(i));
                        fringeCalculation.add(children.get(i));
                    }        
                }
           
        }
        
        
        workingStateSpace = calculateMiniMax(workingStateSpace, fringeCalculation);
        
        
        }
        
        
        
    }

//    
//    
//    

//    
//    public void doMasterMove(GridPane gridBoard, BoardStateSpace workingStateSpace, String playerMark){
//        
//    }    
//    
//    
// 
//    
//    
//    public BoardStateSpace miniMax(GridPane gridBoard, BoardStateSpace workingStateSpace, String playerMark, int wantedDepth){
//        System.out.print("We Made it\n");
//        workingStateSpace.generateChildrenStateSpaces(playerMark);
//        List<BoardStateSpace> children = workingStateSpace.getChildren();
//        int StateToPick = -1;
//        int i;
//        int smallestChildHueristic = 100;
//        int LargestChildHueristic = -100;
//        List<BoardStateSpace> childrensChoice = new ArrayList<BoardStateSpace>();
//        System.out.print(wantedDepth + "\n");
//        //set children in array
//           
//            if (wantedDepth == 1){
//                for (i = 0; i < (children.size()-1); i++){
//                    
//                                System.out.print("/nFor loop" + StateToPick + "\n");
//
//                    if(playerMark.equals("X")){
//                        if (boardUpdater.countPlayer1PotentialWin(children.get(i)) < smallestChildHueristic){
//                            smallestChildHueristic = boardUpdater.countPlayer1PotentialWin(children.get(i));
//                            StateToPick = i;
//                                    
//                        }
//                    }
//                    else{
//                        if (boardUpdater.countPlayer2PotentialWin(children.get(i)) < smallestChildHueristic){
//                            smallestChildHueristic = boardUpdater.countPlayer2PotentialWin(children.get(i));
//                            StateToPick = i;
//                                    
//                        }
//                    }
//                }
//            }
//            else if (wantedDepth % 2 == 0){
//                for (i = 0; i < (children.size()-1); i++){
//                    childrensChoice.clear();
//                    if(playerMark.equals("X")){
//                        childrensChoice.add(miniMax(gridBoard, children.get(i), "O", wantedDepth-1));
//                        for (i=0; i<(childrensChoice.size()-1); i++){
//                            if(playerMark.equals("X")){
//                                if (boardUpdater.countPlayer1PotentialWin(children.get(i)) > LargestChildHueristic){
//                                    LargestChildHueristic = boardUpdater.countPlayer1PotentialWin(childrensChoice.get(i));
//                                    StateToPick = i;
//
//                                }
//                            }
//                            else{
//                                if (boardUpdater.countPlayer2PotentialWin(children.get(i)) > LargestChildHueristic){
//                                    LargestChildHueristic = boardUpdater.countPlayer2PotentialWin(childrensChoice.get(i));
//                                    StateToPick = i;
//
//                                }
//                            }                    
//                        }                        
//                    }
//                    else{
//                        childrensChoice.add(miniMax(gridBoard, children.get(i), "X", wantedDepth-1));
//                        for (i=0; i<(childrensChoice.size()-1); i++){
//                            if(playerMark.equals("X")){
//                                if (boardUpdater.countPlayer1PotentialWin(children.get(i)) > LargestChildHueristic){
//                                    LargestChildHueristic = boardUpdater.countPlayer1PotentialWin(childrensChoice.get(i));
//                                    StateToPick = i;
//
//                                }
//                            }
//                            else{
//                                if (boardUpdater.countPlayer2PotentialWin(children.get(i)) > LargestChildHueristic){
//                                    LargestChildHueristic = boardUpdater.countPlayer2PotentialWin(childrensChoice.get(i));
//                                    StateToPick = i;
//
//                                }
//                            }                    
//                        }
//                    }
//                }
//
//            }
//            else {
//                for (i = 0; i < (children.size()-1); i++){
//                    childrensChoice.clear();
//                    if(playerMark.equals("X")){
//                        childrensChoice.add(miniMax(gridBoard, children.get(i), "O", wantedDepth-1));
//                        for (i=0; i<(childrensChoice.size()-1); i++){
//                            if(playerMark.equals("X")){
//                                if (boardUpdater.countPlayer1PotentialWin(children.get(i)) < smallestChildHueristic){
//                                    smallestChildHueristic = boardUpdater.countPlayer1PotentialWin(childrensChoice.get(i));
//                                    StateToPick = i;
//
//                                }
//                            }
//                            else{
//                                if (boardUpdater.countPlayer2PotentialWin(children.get(i)) < smallestChildHueristic){
//                                    smallestChildHueristic = boardUpdater.countPlayer2PotentialWin(childrensChoice.get(i));
//                                    StateToPick = i;
//
//                                }
//                            }                        
//                        }                    
//                    }
//                    else{
//                        childrensChoice.add(miniMax(gridBoard, children.get(i), "X", wantedDepth-1));
//                        for (i=0; i<(childrensChoice.size()-1); i++){
//                            if(playerMark.equals("X")){
//                                if (boardUpdater.countPlayer1PotentialWin(children.get(i)) < smallestChildHueristic){
//                                    smallestChildHueristic = boardUpdater.countPlayer1PotentialWin(childrensChoice.get(i));
//                                    StateToPick = i;
//
//                                }
//                            }
//                            else{
//                                if (boardUpdater.countPlayer2PotentialWin(children.get(i)) < smallestChildHueristic){
//                                    smallestChildHueristic = boardUpdater.countPlayer2PotentialWin(childrensChoice.get(i));
//                                    StateToPick = i;
//
//                                }
//                            }                        
//                        }                        
//                    }
//                }
//
//            }
//            System.out.print("State to Pick =" + StateToPick + "\n");
//        return children.get(i); 
//    }
//    
}


