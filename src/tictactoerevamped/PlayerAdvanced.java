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
public class PlayerAdvanced {
    String myMark;
    String opponentsMark;
    BoardProcessor boardProcessor = new BoardProcessor();
    
    public PlayerAdvanced(String myMark){
        this.myMark = myMark;
        if(this.myMark.equals("X")){this.opponentsMark = "O";}
        else{this.opponentsMark = "X";}
        
    }
    
    public BoardStateSpace doMove(BoardStateSpace StartingStateSpace){
        
        
        BoardStateSpace parentStateSpace = new BoardStateSpace();
        parentStateSpace.cloneStateSpace(StartingStateSpace);
        
        //We will only be looking two steps ahead!
        int lookAheadCounter;
        //Create a Fringe to Generate all StateSpaces
        List<BoardStateSpace> stateSpaceGenerationFringe = new ArrayList<BoardStateSpace>(); 
        //Create a Fringe To Hold all StateSpaces... We Will Use This to Find Mins/Maxes
        List<BoardStateSpace> allStateSpaceHolder = new ArrayList<BoardStateSpace>(); 
       
        int parentID = -1;
        int nodeID = 0;
        int lookAhead = 0;
        
        parentStateSpace.parentID = parentID;
        parentStateSpace.nodeID = nodeID;
        parentStateSpace.lookAhead = lookAhead;
                
        //Add Our Beginning Node to Fringes
        stateSpaceGenerationFringe.add(parentStateSpace);
        allStateSpaceHolder.add(parentStateSpace);
        
        //While We have Something in our Fringe...
        while(!stateSpaceGenerationFringe.isEmpty()){
            
            //Get First Thing in Fringe
            BoardStateSpace workingStateSpace = stateSpaceGenerationFringe.get(0);
            //Remove it From Fringe after we Get it
            stateSpaceGenerationFringe.remove(0);
            
            if(workingStateSpace.lookAhead > 2){break;}
            
            //Generate Children Based off this Fringe
            if(workingStateSpace.lookAhead%2 == 0){
                //Generate Children StateSpaces where We can Make Our Move
                workingStateSpace.generateChildrenStateSpaces(this.myMark);
                List<BoardStateSpace> children = new ArrayList<BoardStateSpace>();
                children = workingStateSpace.children;
                for(int i = 0; i < children.size(); i++) {
                    children.get(i).generateHValue(this.myMark);
                    children.get(i).nodeID = ++nodeID;
                    children.get(i).parentID = workingStateSpace.parentID;
                    children.get(i).lookAhead = workingStateSpace.lookAhead+1;
                    stateSpaceGenerationFringe.add(children.get(i));
                    allStateSpaceHolder.add(children.get(i));
                }
                
                
            }
            
            if(workingStateSpace.lookAhead%2 == 1){
                //Based off Our Initial Move, Let's Generate Children on Where the Opponent Will Make His Move
                workingStateSpace.generateChildrenStateSpaces(this.opponentsMark);
                List<BoardStateSpace> children = new ArrayList<BoardStateSpace>();
                children = workingStateSpace.children;
                for(int i = 0; i < children.size(); i++) {
                    children.get(i).nodeID = ++nodeID;
                    workingStateSpace.generateChildrenStateSpaces(this.myMark);
                    children.get(i).parentID = workingStateSpace.parentID;
                    children.get(i).lookAhead = workingStateSpace.lookAhead+1;
                    stateSpaceGenerationFringe.add(children.get(i));
                    allStateSpaceHolder.add(children.get(i));
                }            
            }
            

            
            System.out.println("At Level " + workingStateSpace.lookAhead + ", We have a total of: " + stateSpaceGenerationFringe.size() + " children.");
        }
                
        //Now, We Should be Left with a Fringe that Contains All StateSpaces with Their information in them - allStateSpaceHolder 
        //We Will Use This to Determine Which StateSpace We Want!
        //BoardStateSpace bestMove = miniMax(allStateSpaceHolder);
        
        return StartingStateSpace;
    }   
    
//    
//    public BoardStateSpace miniMax(List<BoardStateSpace> fringe){
//        System.out.println("In the Min Max");
//        int lookAheadCounter = 0;
//        BoardStateSpace bestChoice;
//        
//        while(lookAheadCounter <= 2){
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
//            //While There is Still Children to Process at This Level
//            while(!lookAheadChildren.isEmpty()){
//
//                 //Create Group of Children Node of Same Parent, Starting with First in Queue
//                 List<BoardStateSpace> lookAheadChildrenGroup = new ArrayList<BoardStateSpace>();
//                 lookAheadChildrenGroup.add(lookAheadChildren.get(0));
//                 lookAheadChildren.remove(0);
//
//                 //Find all Node with Same Parent and add them to Group.
//                 for(int j = 0; j < lookAheadChildren.size(); j++){
//                    if(lookAheadChildrenGroup.get(0).parentID == lookAheadChildren.get(j).parentID){
//                            lookAheadChildrenGroup.add(lookAheadChildren.get(j));
//                            lookAheadChildren.remove(j);
//                    }
//                 }
//
//                 //Now We have a group of Children With the Same Parent
//                 //Find Min/Max, This is our Deepest Case, as Such we need to Generate the Values for them as well!
//                 if(lookAheadChildrenGroup.get(0).lookAhead%2 == 0){
//                    BoardStateSpace minChoice = lookAheadChildrenGroup.get(0);
//                    for(int z = 0; z < lookAheadChildrenGroup.size(); z++){
//                        //If I'm O's, Search Through Board for Potential Winning O's
//                        if(minChoice.hValue > lookAheadChildrenGroup.get(z).hValue){
//                            minChoice = lookAheadChildrenGroup.get(z);
//                        }
//                    }
//                    
//
//                    //Set the Groups Paren's Value to The Biggest Number!
//                    maxChoice.parent.potentialValue = maxChoice.potentialValue;
//                 }
//                 
//                //If 1, It's the Opponents Move, we should find the Min Value of Potential Wins we can get! 
//                if(lookAheadChildrenGroup.get(0).lookAhead == 1){
//                 
//                 else{
//                         BoardStateSpace maxChoice = lookAheadChildrenGroup.get(0);
//                         for(int z = 0; z < lookAheadChildrenGroup.size(); z++){
//                             if(playerMark.equals("X")){
//                                 if(boardUpdater.countPlayer1PotentialWin(maxChoice) <= boardUpdater.countPlayer1PotentialWin(lookAheadChildrenGroup.get(z))){
//                                         maxChoice = lookAheadChildrenGroup.get(z);
//                                 }
//                             }
//                             else{
//                                 if(boardUpdater.countPlayer2PotentialWin(maxChoice) <= boardUpdater.countPlayer2PotentialWin(lookAheadChildrenGroup.get(z))){
//                                         maxChoice = lookAheadChildrenGroup.get(z);
//                                 }
//                             }
//
//                         }
//
//                         maxChoice.parent.potentialValue = maxChoice.potentialValue;
//                         if(lookAheadCounter==1){
//                             System.out.println("**********playerMark IS: " + playerMark);
//
//                            return maxChoice;
//                         }
//                 }
//        }
//        lookAheadCounter--;
//        
//    }
//        return null;
//    }
//



}










