/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoerevamped;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mike
 */
public class PlayerMaster {
    String myMark;
    String opponentsMark;
    BoardProcessor boardProcessor = new BoardProcessor();
    
    public PlayerMaster(String myMark){
        this.myMark = myMark;
        if(this.myMark.equals("X")){this.opponentsMark = "O";}
        else{this.opponentsMark = "X";}
        
    }
  
    
    public BoardStateSpace doMove(BoardStateSpace originalStateSpace){
        System.out.println("Dustin's Move");
        
        //Clone original StateSpace to work with.
        BoardStateSpace workingStateSpace = new BoardStateSpace();
        workingStateSpace.cloneStateSpace(originalStateSpace);
        
        
        BoardStateSpace workingChildrenStateSpace = new BoardStateSpace();
        
        BoardStateSpace chosenMove1st = new BoardStateSpace();
        BoardStateSpace chosenMove2nd = new BoardStateSpace();
        BoardStateSpace chosenMove3rd = new BoardStateSpace();
        int chosenLocation = -1;
        int chosenLocation2 = -1;
        int chosenLocation3 = -1;
        
        
        
        workingChildrenStateSpace.cloneStateSpace(workingStateSpace);
        
        if(this.myMark.equals("X")){
            //Check
            Tile tileCheck1 = new Tile();
            Tile tileCheck2 = new Tile();
            tileCheck1 = boardProcessor.checkPlayerXPotentialWin(workingStateSpace);
            tileCheck2 = boardProcessor.checkPlayerOPotentialWin(workingStateSpace);
            
            if (tileCheck1 != null){
                workingChildrenStateSpace.board[tileCheck1.row][tileCheck1.column].setTileMark(this.myMark);           
                return workingChildrenStateSpace;
            }
            else if(tileCheck2 != null){
                workingChildrenStateSpace.board[tileCheck2.row][tileCheck2.column].setTileMark(this.myMark);  
                return workingChildrenStateSpace;
            }
        
        }
        else{
            //Check
            Tile tileCheck1 = new Tile();
            Tile tileCheck2 = new Tile();
            tileCheck1 = boardProcessor.checkPlayerOPotentialWin(workingStateSpace);
            tileCheck2 = boardProcessor.checkPlayerXPotentialWin(workingStateSpace);
            
            if (tileCheck1 != null){
                workingChildrenStateSpace.board[tileCheck1.row][tileCheck1.column].setTileMark(this.myMark);
                return workingChildrenStateSpace;}
            else if(tileCheck2 != null){
                workingChildrenStateSpace.board[tileCheck2.row][tileCheck2.column].setTileMark(this.myMark);
                return workingChildrenStateSpace;
            }
        }        
        
        
        
        
        
        workingChildrenStateSpace.generateChildrenStateSpaces(this.myMark);
        List<BoardStateSpace> children = new ArrayList<BoardStateSpace>();
        List<BoardStateSpace> children2 = new ArrayList<BoardStateSpace>();
        List<BoardStateSpace> children3 = new ArrayList<BoardStateSpace>();
        
        List<BoardStateSpace> children2ndMove = new ArrayList<BoardStateSpace>();
        List<BoardStateSpace> children3rdMove = new ArrayList<BoardStateSpace>();
        List<BoardStateSpace> children4thMove = new ArrayList<BoardStateSpace>();
        
        List<BoardStateSpace> chosenMoves = new ArrayList<BoardStateSpace>();
        List<BoardStateSpace> chosenMoves2 = new ArrayList<BoardStateSpace>();
        List<BoardStateSpace> chosenMoves3 = new ArrayList<BoardStateSpace>();
        
        children = workingChildrenStateSpace.children;
        System.out.println("Number of Children is: " + children.size());
        
          for(int i = 0; i < children.size(); i++) {
              //Generate 2nd Gen Children
              children.get(i).generateChildrenStateSpaces(this.opponentsMark);
              children2ndMove = children.get(i).children;
                        children2 = children.get(i).children;
                        for(int j = 0; j < children2ndMove.size(); j++) {
                            
                            //Generate 3rd Gen
                            children2.get(j).generateChildrenStateSpaces(this.myMark);
                            children3rdMove = children2.get(j).children;
                                    children3 = children2.get(j).children;
                                    for(int k = 0; k < children3rdMove.size(); k++) {
                                        System.out.println("i: " + i + " j: " + j + " k: " + k);
                                        //Generate 4th Gen
                                        children3.get(k).generateChildrenStateSpaces(this.opponentsMark);
                                        children4thMove = children3.get(k).children;
                                        //
                                        chosenMoves3.add(min(children4thMove, this.myMark));
                                        children4thMove.clear();
                                    }     
                                        chosenMove3rd = max(chosenMoves3, this.myMark);
                                        chosenLocation3 = chosenMoves3.indexOf(chosenMove3rd);   
                            chosenMoves2.add(min(children3rdMove, this.myMark));
                            children3rdMove.clear();
                        }  
                            chosenMove2nd = max(chosenMoves2, this.myMark);
                            chosenLocation2 = chosenMoves2.indexOf(chosenMove2nd);
                chosenMoves.add(min(children2ndMove, this.myMark));
                children2ndMove.clear();
          }
          chosenMove1st = max(chosenMoves, this.myMark);
          chosenLocation = chosenMoves.indexOf(chosenMove1st);
          System.out.println("ChosenLocation is :" + chosenLocation);
          workingStateSpace = children.get(chosenLocation);
          
          return workingStateSpace;
    }
    
    public BoardStateSpace min(List<BoardStateSpace> children, String playerMark){
      BoardStateSpace minChoice = new BoardStateSpace();
      minChoice = children.get(0);
      for(int i = 0; i < children.size(); i++){
          if(playerMark.equals("O")){
              if(boardProcessor.countPlayerXPotentialWin(minChoice) > boardProcessor.countPlayerXPotentialWin(children.get(i))){
                      minChoice = children.get(i);
              }
          }
          else{
              if(boardProcessor.countPlayerOPotentialWin(minChoice) > boardProcessor.countPlayerOPotentialWin(children.get(i))){
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
                if(boardProcessor.countPlayerOPotentialWin(minChoice) < boardProcessor.countPlayerOPotentialWin(children.get(i))){
                        minChoice = children.get(i);
                }
            }
            else{
                if(boardProcessor.countPlayerXPotentialWin(minChoice) < boardProcessor.countPlayerXPotentialWin(children.get(i))){
                        minChoice = children.get(i);
                }
            }   
        }
        return minChoice;   
    }
    
}
