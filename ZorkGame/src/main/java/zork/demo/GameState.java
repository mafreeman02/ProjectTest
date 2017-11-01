package zork.demo;

public class GameState {

    private Command currentCommand;
    private String currentRoom;
    private Boolean hasItems;
    //private Item[] roomItems;
    public GameState(Command command, Game game) {
        Room temp = game.getCurrentRoom();

        currentCommand = command;
        currentRoom = game.getCurrentRoom().shortDescription();

        hasItems = (temp.hasAnyItem());
    }

    public Command getCurrentCommand() { return currentCommand; }

    public String getCurrentRoom() { return currentRoom; }

    public Boolean getHasItems() { return hasItems; }
}

//public class GameState{
//
//     private String messages;
//     private String gameId;
//
//     public GameState(){}
//
//
//    public String getMessages() {
//        return messages;
//    }
//
//    public void setMessages(String messages) {
//        this.messages = messages;
//    }
//
//    public String getGameId() {
//        return gameId;
//    }
//
//    public void setGameId(String gameId) {
//        this.gameId = gameId;
//    }
//
//}
