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
    boolean isLocked;
    
    public lockedRoom(String description){
        super(description);
        isLocked = true;
    }
    
    
    public boolean isLocked(){
        return isLocked;
    }
    
}
