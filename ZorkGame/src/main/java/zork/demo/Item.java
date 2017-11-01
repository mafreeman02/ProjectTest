/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zork.demo;

/**
 *
 * @author jonbrown
 */
public class Item {
    String name;
    String description;
    int weight;
    boolean pickUpAble;
    
    
   Item(String name, int weight){
        this.name = name;
        this.weight = weight;
        this.description = "No description available for this item";
        this.pickUpAble = true;
        
    }
    
    public void addDescription(String desc){
        this.description = desc;
    }
    
    public String getName(){
        return name;
    }
    
    public int getWeight(){
        return weight;
    }
    
    public void setPickUpAble(boolean pickUp){
        this.pickUpAble = pickUp;
    }
    
    public boolean getPickUpAble(){
        return pickUpAble;
    }
    
    public String getDescription(){
        return description;
    }
}
