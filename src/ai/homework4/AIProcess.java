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
    
    
    public void doAdvancedMove(GridPane gridBoard, BoardStateSpace workingStateSpace, String playerMark, int depth){
        System.out.println("Advanced Turn");
        BoardStateSpace original = workingStateSpace;
        BoardStateSpace workingChildrenStateSpace = new BoardStateSpace();
        BoardStateSpace chosenMove1st = new BoardStateSpace();
        int chosenLocation = -1;
        workingChildrenStateSpace.cloneBoard(workingStateSpace);
        
        
        workingChildrenStateSpace.generateChildrenStateSpaces(playerMark);
        List<BoardStateSpace> children = new ArrayList<BoardStateSpace>();
        List<BoardStateSpace> children2ndMove = new ArrayList<BoardStateSpace>();
        List<BoardStateSpace> chosenMoves = new ArrayList<BoardStateSpace>();
        children = workingChildrenStateSpace.getChildren();
        System.out.println("Number of Children is: " + children.size());
          for(int i = 0; i < children.size(); i++) {
              children.get(i).generateChildrenStateSpaces(swapPlayerMark(playerMark));
              children2ndMove = children.get(i).getChildren();
              chosenMoves.add(min(children2ndMove, playerMark));
              children2ndMove.clear();
          }
          chosenMove1st = max(chosenMoves, playerMark);
          chosenLocation = chosenMoves.indexOf(chosenMove1st);
          System.out.println("ChosenLocation is :" + chosenLocation);
          workingStateSpace = children.get(chosenLocation);

    }
    
    public BoardStateSpace min(List<BoardStateSpace> children, String playerMark){
      BoardStateSpace minChoice = new BoardStateSpace();
      minChoice = children.get(0);
      for(int i = 0; i < children.size(); i++){
          if(playerMark.equals("O")){
              if(boardUpdater.countPlayer1PotentialWin(minChoice) > boardUpdater.countPlayer1PotentialWin(children.get(i))){
                      minChoice = children.get(i);
              }
          }
          else{
              if(boardUpdater.countPlayer2PotentialWin(minChoice) > boardUpdater.countPlayer2PotentialWin(children.get(i))){
                      minChoice = children.get(i);
              }
          }   
      }
      return minChoice;    
    }
    
    public BoardStateSpace max(List<BoardStateSpace> children, String playerMark){
        BoardStateSpace minChoice = new BoardStateSpace();
        minChoice = children.get(0);
        for(int i = 0; i < children.size(); i++){
            if(playerMark.equals("O")){
                if(boardUpdater.countPlayer2PotentialWin(minChoice) < boardUpdater.countPlayer2PotentialWin(children.get(i))){
                        minChoice = children.get(i);
                }
            }
            else{
                if(boardUpdater.countPlayer1PotentialWin(minChoice) < boardUpdater.countPlayer1PotentialWin(children.get(i))){
                        minChoice = children.get(i);
                }
            }   
        }
        return minChoice;   
    }
    
    
    
    
//   public void doAdvancedMove(GridPane gridBoard, BoardStateSpace workingStateSpace, String playerMark, int depth){
//        System.out.println("AI's Move");
//        System.out.println("Mark is: " + playerMark);
//        if(playerMark.equals("O")){
//            //Create Our Fringe
//            List<BoardStateSpace> fringeCalculation = new ArrayList<BoardStateSpace>();
//            List<BoardStateSpace> fringeGeneration = new ArrayList<BoardStateSpace>();
//  
//            int nodeID = 0;
//            int parentID = 0;
//            int lookAhead = 0;
//            int potentialValue = 0;
//            
//            workingStateSpace.nodeID = nodeID;
//            workingStateSpace.parentID = parentID;
//            workingStateSpace.lookAhead = lookAhead;
//            workingStateSpace.potentialValue = boardUpdater.countPlayer1PotentialWin(workingStateSpace);
//            
//            BoardStateSpace workingChildrenStateSpace = new BoardStateSpace();
//            workingChildrenStateSpace.cloneBoard(workingStateSpace);
//            
//             //Add First Node to Fringe
//            fringeCalculation.add(workingChildrenStateSpace);
//            fringeGeneration.add(workingChildrenStateSpace);           
//            
//            //Look Ahead 2 Children's worth!
//            while(!fringeGeneration.isEmpty()){
//                
//                
//                
//                //Work with Best H-Value
//                System.out.println("Size of Array is: " + fringeGeneration.size());
//                workingChildrenStateSpace = fringeGeneration.get(0);
//                System.out.println("Look Ahead Level is: " + workingChildrenStateSpace.lookAhead);
//                fringeGeneration.remove(0);
//                
//                
//                //Expand Node's Children  
//                if(lookAhead%2 == 0 && workingChildrenStateSpace.lookAhead < 2){
//          
//                    workingChildrenStateSpace.generateChildrenStateSpaces(playerMark);
//                    List<BoardStateSpace> children = new ArrayList<BoardStateSpace>();
//                    children = workingChildrenStateSpace.getChildren();
//                    System.out.println("Number of Children is: " + children.size());
//                    for(int i = 0; i < children.size(); i++) {
//                        children.get(i).nodeID = nodeID++;
//                        children.get(i).parentID = workingChildrenStateSpace.parentID;
//                        children.get(i).lookAhead = 1+workingChildrenStateSpace.lookAhead;
//                        children.get(i).potentialValue = boardUpdater.countPlayer1PotentialWin(children.get(i));
//                        fringeGeneration.add(children.get(i));
//                        fringeCalculation.add(children.get(i));
//                    }
//    
//                }
//                else{
//                    if(workingChildrenStateSpace.lookAhead < 2){
//                        workingChildrenStateSpace.generateChildrenStateSpaces(swapPlayerMark(playerMark));
//                        List<BoardStateSpace> children = new ArrayList<BoardStateSpace>();
//                        children = workingChildrenStateSpace.getChildren();
//                        System.out.println("Number of Children is: " + children.size());
//                        for(int i = 0; i < children.size(); i++) {
//                            children.get(i).nodeID = nodeID++;
//                            children.get(i).parentID = workingChildrenStateSpace.parentID;
//                            children.get(i).lookAhead = 1+lookAhead;
//                            children.get(i).potentialValue = boardUpdater.countPlayer2PotentialWin(children.get(i));
//                            fringeGeneration.add(children.get(i));
//                            fringeCalculation.add(children.get(i));
//                        }    
//                    }
//                }
//                
//                System.out.println("Size of Array After is: " + fringeGeneration.size());
//                
//        }
//        
//        
//        workingStateSpace =  miniMax(fringeCalculation, playerMark, depth);
//        
//   
//        }
//        
//        
//        
//    }
//   
//
//    public BoardStateSpace miniMax(List<BoardStateSpace> fringe, String playerMark, int depth){
//        System.out.println("In the Min Max");
//        System.out.println("*****Number of Nodes to Check: " + fringe.size());
//        
//        BoardUpdater boardUpdater = new BoardUpdater();
//        int lookAheadCounter = depth;
//        while(lookAheadCounter != 0){
//            
//            //Get All Children from Fringe That are of Desired Level
//            List<BoardStateSpace> lookAheadChildren = new ArrayList<BoardStateSpace>();
//            for(int i = 0; i < fringe.size(); i++){
//                if(fringe.get(i).lookAhead == lookAheadCounter){
//                        lookAheadChildren.add(fringe.get(i));
//                        fringe.remove(i);
//                }
//             }
//
//        //While There is Still Children to Process at This Level
//        while(!lookAheadChildren.isEmpty()){
//            
//            
//             //Create Group of Children Node of Same Parent, Starting with First in Queue
//             List<BoardStateSpace> lookAheadChildrenGroup = new ArrayList<BoardStateSpace>();
//             lookAheadChildrenGroup.add(lookAheadChildren.get(0));
//             lookAheadChildren.remove(0);
//
//             //Find all Node with Same Parent and add them to Group.
//             for(int j = 0; j < lookAheadChildren.size(); j++){
//                     if(lookAheadChildrenGroup.get(0).parentID == lookAheadChildrenGroup.get(j).parentID){
//                             lookAheadChildrenGroup.add(lookAheadChildren.get(j));
//                             lookAheadChildren.remove(j);
//                     }
//             }
//
//             //Now We have a group of Children With the Same Parent
//             //Find Min/Max
//             if(lookAheadChildrenGroup.get(0).lookAhead%2==0){
//                     BoardStateSpace minChoice = lookAheadChildrenGroup.get(0);
//                     for(int z = 0; z < lookAheadChildrenGroup.size(); z++){
//                         if(playerMark.equals("O")){
//                             if(boardUpdater.countPlayer1PotentialWin(minChoice) > boardUpdater.countPlayer1PotentialWin(lookAheadChildrenGroup.get(z))){
//                                     minChoice = lookAheadChildrenGroup.get(z);
//                             }
//                         }
//                         else{
//                             if(boardUpdater.countPlayer2PotentialWin(minChoice) > boardUpdater.countPlayer2PotentialWin(lookAheadChildrenGroup.get(z))){
//                                     minChoice = lookAheadChildrenGroup.get(z);
//                             }
//                         }
//                             
//                     }
//                     
//                     minChoice.parent.potentialValue = minChoice.potentialValue;
//             }
//             else{
//                     BoardStateSpace maxChoice = lookAheadChildrenGroup.get(0);
//                     for(int z = 0; z < lookAheadChildrenGroup.size(); z++){
//                         if(playerMark.equals("X")){
//                             if(boardUpdater.countPlayer1PotentialWin(maxChoice) <= boardUpdater.countPlayer1PotentialWin(lookAheadChildrenGroup.get(z))){
//                                     maxChoice = lookAheadChildrenGroup.get(z);
//                             }
//                         }
//                         else{
//                             if(boardUpdater.countPlayer2PotentialWin(maxChoice) <= boardUpdater.countPlayer2PotentialWin(lookAheadChildrenGroup.get(z))){
//                                     maxChoice = lookAheadChildrenGroup.get(z);
//                             }
//                         }
//                             
//                     }
//                     
//                     maxChoice.parent.potentialValue = maxChoice.potentialValue;
//                     if(lookAheadCounter==1){
//                         System.out.println("**********playerMark IS: " + playerMark);
//                        
//                        return maxChoice;
//                     }
//             }
//        }
//        lookAheadCounter--;
//        
//    }
//        return null;
//    }
    
    public String swapPlayerMark(String playerMark){
        if(playerMark.equals("X")){
            return "O";
        }
        else{
            return "X";
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


