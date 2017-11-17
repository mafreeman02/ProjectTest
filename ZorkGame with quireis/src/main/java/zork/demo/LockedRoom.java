package zork.demo;

/**
 *
 * @author jonbrown
 */
public class LockedRoom extends Room
{
    private String Key;
    
    public LockedRoom(String description)
    {
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
