/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai.homework4;

/**
 *
 * @author Mike
 */
public class Tile {
   //Tile Mark can be Empty, X, or O
    String tileMark = "empty";
    int row;
    int column;
    int getRow(){return this.row;}
    int getColumn(){return this.column;}
    public Tile(int row, int column){
        this.row = row;
        this.column = column;
    }
    public Tile(String mark){this.tileMark = mark;}
    public void setTileMark(String mark){this.tileMark = mark;}  
    public String getTileMark(){return this.tileMark;}
    
}
