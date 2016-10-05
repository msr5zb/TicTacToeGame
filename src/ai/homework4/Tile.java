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
    
    public Tile(){}
    public Tile(String mark){this.tileMark = mark;}
    public void setTileMark(String mark){this.tileMark = mark;}  
    public String getTileMark(){return this.tileMark;}
    
}
