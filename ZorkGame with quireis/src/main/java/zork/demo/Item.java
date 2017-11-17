package zork.demo;

/**
 *
 * @author jonbrown
 */
public class Item
{
    String name;
    String description;
    int weight;
    boolean canPickUp;
    
    
   Item(String name, int weight, String description)
   {
        this.name = name;
        this.weight = weight;
        this.description =  description;
        this.canPickUp = true;
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

    public String getDescription(){
        return description;
    }
    
    public void setPickUpAble(boolean pickUp){
        this.canPickUp = pickUp;
    }
    
    public boolean getPickUpAble(){
        return canPickUp;
    }
}
