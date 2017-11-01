package textbaisedgame;/** *  This class is the main class of the application. Zork is a very *  simple, text based adventure game.  Users can walk around some scenery. *  That's all. It should really be extended to make it more interesting! * *  To play this game, create an instance of this class and call the "play" *  routine. * *  This main class creates and initialises all the others: it creates all *  rooms, creates the parser and starts the game.  It also evaluates the *  commands that the parser returns. */import java.sql.PreparedStatement;import java.sql.Connection;import java.sql.DriverManager;import java.sql.ResultSet;import java.sql.SQLException;import java.util.logging.Level;import java.util.logging.Logger;class Game{    private Parser parser;    private Room currentRoom;    private Item[] inventory;    private Room lastRoom;    private int numInven;    private int maxItems;    private int maxWeight;    /**     * Create the game and initialise its internal map.     */    public Game()    {        createRooms();        parser = new Parser();        inventory = new Item[10];        numInven = 0;        maxItems = 10;        maxWeight = 50;    }    /**     * Create all the rooms and link their exits together.     */    private void createRooms()    {        // rooms for map implementation        //Room center = null, left = null, right = null, up = null, down  = null, upRight = null, upLeft = null, downRight = null, downLeft = null;        //lockedRoom lockedLeft, lockedRight, lockedUp, lockedDown;        Room room_list[] = new Room[9];        lockedRoom locked_room_list[] = new lockedRoom[4];        Connection con = null;        PreparedStatement pst1 = null;        PreparedStatement pst2 = null;        PreparedStatement pst2a = null;        PreparedStatement pst1a = null;        PreparedStatement pst3 = null;        PreparedStatement pst3a = null;        PreparedStatement pst4 = null;        ResultSet rs1 = null;        ResultSet rs2 = null;        ResultSet rs2a = null;        ResultSet rs1a = null;        ResultSet rs3 = null;        ResultSet rs3a = null;        ResultSet rs4 = null;        String url = "jdbc:postgresql://localhost:5433/Game_Database";        String user = "postgres";        String password = "freeman.mark";        // create rooms for map implementation        try {            con = DriverManager.getConnection(url, user, password);            pst1 = con.prepareStatement("SELECT description FROM public.Rooms ORDER BY room_id");            pst1a = con.prepareStatement("SELECT description FROM public.lockedrooms ORDER BY room_id");            rs1 = pst1.executeQuery();            rs1a = pst1a.executeQuery();            for(int i = 0; i < 9; i++) {                rs1.next();                room_list[i] = new Room(rs1.getString(1));            };            for(int i = 0; i < 4; i++) {                rs1a.next();                locked_room_list[i] = new lockedRoom(rs1a.getString(1));            };            System.out.println(room_list[0].shortDescription());            System.out.println(room_list[1].shortDescription());        } catch (SQLException ex) {            Logger lgr = Logger.getLogger(Game.class.getName());            lgr.log(Level.SEVERE, ex.getMessage(), ex);        } finally {            try {                if (rs1 != null) {                    rs1.close();                }                if (pst1 != null) {                    pst1.close();                }            } catch (SQLException ex) {                Logger lgr = Logger.getLogger(Game.class.getName());                lgr.log(Level.WARNING, ex.getMessage(), ex);            }        }        // initialize room exits for the map implementaion        room_list[0].setExits(room_list[4], room_list[2], room_list[1] , room_list[1]);        room_list[1].setExits(room_list[6], room_list[0], room_list[8], locked_room_list[0]);       locked_room_list[0].setExits(null, room_list[1], null, null);        room_list[2].setExits(room_list[5], locked_room_list[1], room_list[7], room_list[0]);        locked_room_list[1].setExits(null, null, null, room_list[2]);        room_list[3].setExits(room_list[0], room_list[7], locked_room_list[3], room_list[8]);        locked_room_list[2].setExits(room_list[3], null, null, null);       room_list[4].setExits(locked_room_list[2], room_list[5], room_list[0], room_list[6]);        locked_room_list[2].setExits(null, null, room_list[4], null);        room_list[5].setExits(null, null, room_list[2], room_list[4]);        room_list[6].setExits(null, room_list[4], room_list[1], null);        room_list[7].setExits(room_list[3], room_list[4], null, null);        room_list[8].setExits(room_list[1], null, null, room_list[4]);        // create items        Item Item1 = null, Item2 = null, Item3 = null, Item4 = null;        try {            pst2 = con.prepareStatement("SELECT item[1], item[2], item [3], item[4] FROM public.Rooms ORDER BY room_id");            rs2 = pst2.executeQuery();            int i = 0;            while(rs2.next()) {                System.out.println("Select name, weight, description FROM public.Items WHERE name = '" + rs2.getString(1)                        + "' OR name = '" + rs2.getString(2) + "' OR name = '" + rs2.getString(3) + "' OR name = '" + rs2.getString(4) + "'");                pst2a = con.prepareStatement("Select name, weight, description FROM public.Items WHERE name = '" + rs2.getString(1)                        + "' OR name = '" + rs2.getString(2) + "' OR name = '" + rs2.getString(3) + "' OR name = '" + rs2.getString(4) + "'");                rs2a = pst2a.executeQuery();                if (rs2a.next()) {                    System.out.println(rs2a.getString(1) + rs2a.getString(2) + rs2a.getString(3));                    if (rs2.getString(1) != null)                        Item1 = new Item(rs2a.getString(1), rs2a.getInt(2), rs2a.getString(3));                    else                        Item1 = null;                }                if (rs2a.next()) {                    if (rs2.getString(2) != null)                        Item2 = new Item(rs2a.getString(1), rs2a.getInt(2), rs2a.getString(3));                    else                        Item2 = null;                }                if (rs2a.next()) {                    if (rs2.getString(3) != null)                        Item3 = new Item(rs2a.getString(1), rs2a.getInt(2), rs2a.getString(3));                    else                        Item3 = null;                }                if (rs2a.next()) {                    if (rs2.getString(4) != null)                        Item4 = new Item(rs2a.getString(1), rs2a.getInt(2), rs2a.getString(3));                    else                        Item4 = null;                }                if (Item1 != null)                    room_list[i].addItem(Item1);                if (Item2 != null)                    room_list[i].addItem(Item2);                if (Item3 != null)                    room_list[i].addItem(Item3);                if (Item4 != null)                    room_list[i].addItem(Item4);                Item1 = null;                Item2 = null;                Item3 = null;                Item4 = null;                i++;            }            Item1 = null;            Item2 = null;            Item3 = null;            Item4 = null;            pst3 = con.prepareStatement("SELECT item1, item2, item3, item4 FROM public.lockedRooms ORDER BY room_id");            rs3 = pst3.executeQuery();            i = 0;            while(rs3.next()) {                System.out.println("Select name, weight, description FROM public.Items WHERE name = '" + rs3.getString(1)                        + "' OR name = '" + rs3.getString(2) + "' OR name = '" + rs3.getString(3) + "' OR name = '" + rs3.getString(4) + "'");                pst3a = con.prepareStatement("Select name, weight, description FROM public.Items WHERE name = '" + rs3.getString(1)                        + "' OR name = '" + rs3.getString(2) + "' OR name = '" + rs3.getString(3) + "' OR name = '" + rs3.getString(4) + "'");                rs3a = pst3a.executeQuery();                if (rs3a.next()) {                    if (rs3.getString(1) != null)                        Item1 = new Item(rs3a.getString(1), rs3a.getInt(2), rs3a.getString(3));                    else                        Item1 = null;                }                if (rs3a.next()) {                    if (rs3.getString(2) != null)                        Item2 = new Item(rs3a.getString(1), rs3a.getInt(2), rs3a.getString(3));                    else                        Item2 = null;                }                if (rs3a.next()) {                    if (rs3.getString(3) != null)                        Item3 = new Item(rs3a.getString(1), rs3a.getInt(2), rs3a.getString(3));                    else                        Item3 = null;                }                if (rs3a.next()) {                    if (rs3.getString(4) != null)                        Item4 = new Item(rs3a.getString(1), rs3a.getInt(2), rs3a.getString(3));                    else                        Item4 = null;                }                if (Item1 != null)                    locked_room_list[i].addItem(Item1);                if (Item2 != null)                    locked_room_list[i].addItem(Item2);                if (Item3 != null)                    locked_room_list[i].addItem(Item3);                if (Item4 != null)                    locked_room_list[i].addItem(Item4);                Item1 = null;                Item2 = null;                Item3 = null;                Item4 = null;                i++;            }            pst4 = con.prepareStatement("SELECT key FROM public.lockedrooms ORDER BY room_id");            rs4 = pst4.executeQuery();            for(i = 0; i < 4; i++) {                rs4.next();                System.out.println(rs4.getString(1));                locked_room_list[i].setKey(rs4.getString(1));            }        } catch (SQLException ex) {            Logger lgr = Logger.getLogger(Game.class.getName());            lgr.log(Level.SEVERE, ex.getMessage(), ex);        } finally {            try {                if (rs2 != null) {                    rs2.close();                }                if (pst2 != null) {                    pst2.close();                }                if (con != null) {                    con.close();                }            } catch (SQLException ex) {                Logger lgr = Logger.getLogger(Game.class.getName());                lgr.log(Level.WARNING, ex.getMessage(), ex);            }        }        currentRoom = room_list[0];  // start game outside        lastRoom = room_list[0];    // temp    }    /**     *  Main play routine.  Loops until end of play.     */      public void play()    {        printWelcome();        // Enter the main command loop.  Here we repeatedly read commands and        // execute them until the game is over.        boolean finished = false;        while (! finished)        {            Command command = parser.getCommand();            finished = processCommand(command);        }        System.out.println("Thank you for playing.  Good bye.");    }    /**     * Print out the opening message for the player.     */    private void printWelcome()    {        System.out.println();        System.out.println("Welcome to Zork!");        System.out.println("Zork is a new, incredibly boring adventure game.");        System.out.println("Type 'help' if you need help.");        System.out.println();        System.out.println(currentRoom.longDescription());    }    /**     * Given a command, process (that is: execute) the command.     * If this command ends the game, true is returned, otherwise false is     * returned.     */    public boolean processCommand(Command command)    {        if(command.isUnknown())        {            System.out.println("I don't know what you mean...");            return false;        }        String commandWord = command.getCommandWord();        if (commandWord.equals("help"))            printHelp();        else if (commandWord.equals("go"))            goRoom(command);        else if(commandWord.equals("look"))            System.out.println(look(command));        else if(commandWord.equals("grab"))            grabItem(command);        else if(commandWord.equals("drop"))            dropItem(command);        else if(commandWord.equals("back"))            back();        else if(commandWord.equals("win"))        {            if(command.getSecondWord().equals("game"))            {                System.out.println("Congradulations you have won!!");                return true;            }        }        else if (commandWord.equals("quit"))        {            if(command.hasSecondWord())                System.out.println("Quit what?");            else                return true;  // signal that we want to quit        }        else if(commandWord.equals("unlock")){            unlock(command.getSecondWord());        }        return false;    }    // implementations of user commands:    /**     * Print out some help information.     * Here we print some stupid, cryptic message and a list of the     * command words.     */    private void printHelp()    {        System.out.println("You are lost. You are alone. You wander");        System.out.println("around at the Stocker Center.");        System.out.println();        System.out.println("Your command words are:");        parser.showCommands();    }    /**     * Try to go to one direction. If there is an exit, enter the new     * room, otherwise print an error message.     */    private void goRoom(Command command)    {        if(!command.hasSecondWord())        {            // if there is no second word, we don't know where to go...            System.out.println("Go where?");            return;        }        String direction = command.getSecondWord();        // Try to leave current room.        Room nextRoom = currentRoom.nextRoom(direction);        if (nextRoom == null)            System.out.println("There is no door!");        else if(nextRoom.getLocked()){            System.out.println("The door is locked!");        }        else        {            lastRoom = currentRoom;            currentRoom = nextRoom;            System.out.println(currentRoom.longDescription());        }    }    /**     * command to grab item, removes item from room then adds it to inventory     */    private void grabItem(Command command){        if(!command.hasSecondWord())        {            // if there is no second word, we don't know what to grab..            System.out.println("Grab what?");            return;        }        if(currentRoom.hasItem(command.getSecondWord())){            if(!currentRoom.getItem(command.getSecondWord()).getPickUpAble()){                System.out.print("Sorry you cannot pick up ");                System.out.println(command.getSecondWord());                return;            }            else if(maxWeight < this.getWeight() + currentRoom.getItem(command.getSecondWord()).getWeight()){                System.out.println("Your carrying too much to pick up this item.");                return;            }            this.getItem(command.getSecondWord());            currentRoom.removeItem(command.getSecondWord());            System.out.println("You have picked up the ");            System.out.println(command.getSecondWord());        }else{            System.out.print("There isn't an item called ");            System.out.println(command.getSecondWord());        }    }    /**     * command to drop item, removes from inventory then adds to room     */    private void dropItem(Command command){        if(!command.hasSecondWord())        {            // if there is no second word, we don't know what to drop..            System.out.println("Drop what?");            return;        }        if(findItem(command.getSecondWord()) == -1){            System.out.println("You aren't carrying an item called that. ");        }else{            removeItem(command.getSecondWord());            System.out.println("You have dropped the ");            System.out.println(command.getSecondWord());        }    }    /**     * adds item to inventory, removes it from room     */    private void getItem(String item){        if((numInven - 1) < maxItems ){            inventory[numInven] = currentRoom.getItem(item);            numInven++;        }else{            System.out.println("You don't have enough room for that item");        }    }    /**     * removes item from inventory, removes first instance, places in room     * ^does not remove items properly if item is in front of the list     */    private void removeItem(String item){        int place = findItem(item);        if(place != -1){            if(currentRoom.isFull()){                System.out.println("There's no room to put the item down");                return;            }            currentRoom.addItem(inventory[place]);            inventory[place] = inventory[(numInven - 1)];            numInven--;        }    }    /**     * finds item in inventory, gets first instance. Return value is index of item, value of -1 means item isn't in inventory.     */    private int findItem(String item){        int place = 0;        while(place <= (numInven - 1)){            if(item.equals(inventory[place].getName())){                return place;            }            place++;        }        return -1;    }    /**     * gets description of room, if inventory is second word lists items in inventory.     * If the second word appears as an item in either a room or the players inventory     * it gives a description of the item if the item has a description.     */    private String look(Command command){        String response ="";        if(!command.hasSecondWord()){            response = currentRoom.longDescription();        }else if(numInven == 0){            response = "There are no items in your inventory ";        }else if(command.getSecondWord().equals("inventory") || command.getCommandWord().equals("Inventory")){            int place = 0;            while(place <= (numInven - 1)){                response += " ";                response += inventory[place].getName();                place++;            }        }else if(findItem(command.getSecondWord()) != -1){            response = inventory[findItem(command.getSecondWord())].getDescription();        }else if(currentRoom.hasItem(command.getSecondWord())){            response = currentRoom.getItem(command.getSecondWord()).getDescription();        }        else{            response = "I don't know what you want me to look at.";        }        return response;    }    /**     * gives back total weight of inventory     */    public int getWeight(){        int place = 0;        int total = 0;        while(place < (numInven - 1)){            total = inventory[place].getWeight();            place++;        }        return total;    }    /**     * sends player back to room they were previously in     */    public void back(){        Room temp;        temp = currentRoom;        currentRoom = lastRoom;        lastRoom = temp;        System.out.println(currentRoom.longDescription());    }    public void unlock(String direction){        if(currentRoom.nextRoom(direction) == null){            System.out.println("There is no door in that direction!");        }        else if(!currentRoom.nextRoom(direction).getLocked()){            System.out.println("The door is not locked!");        }        else{            lockedRoom temp = currentRoom.nextLockedRoom(direction);            if(findItem(temp.getKey()) == -1){                System.out.println("You do not have the key to unlock this door!");            }else{                temp.setLocked(false);                System.out.println("The door has been unlocked!");            }        }    }    //Returns the current room's short discription    public String getCurrentRoomDesc()    {        return currentRoom.shortDescription();    }    public Room getCurrentRoom() {        return currentRoom;    }}