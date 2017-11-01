/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textbaisedgame;

/**
 *
 * @author jonbrown
 */
public class lockedRoom extends Room{
    private String Key;
    boolean isLocked;
    
    public lockedRoom(String description){
        super(description);
        this.setLocked(true);
        this.Key =  new String("");
    }
    
    public void setKey(String Key){
        this.Key = Key;
    }
    
    public String getKey(){
        return Key;
    }
    
}
