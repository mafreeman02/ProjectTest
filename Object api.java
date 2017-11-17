@GetMapping("/items")
    public Item getItem(@RequestParam(value="id") int id)
    {
        String url = "jdbc:postgresql://localhost:5433/Game_Database";
        String user = "postgres";
        String password = "freeman.mark";

        Connection connect = null;
        PreparedStatement prep = null;
        ResultSet result = null;

        String output = null;
        Item item = null;

        try
        {
            connect = DriverManager.getConnection(url, user, password);
            prep = connect.prepareStatement("SELECT item_id, name, description, weight  FROM public.Items Where item_id = " + id);
            result = prep.executeQuery();

//            if (result.next()) {
//                output = "Item ID: " + result.getInt(1) +
//                        "<br> Name: " + result.getString(2) +
//                        "<br> Description: " + result.getString(3)+
//                        "<br> Weight: " + result.getInt(4);
//            } else
//                output = "The item id doesn't exist";

            if(result.next())
            {
                item = new Item(result.getString(2), result.getInt(4));
                item.addDescription(result.getString(3));
            }
            else
                System.out.println("Item Not found");


        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(ZorkController.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }



        return item;

    }

    @GetMapping("/rooms")
    public Room getRoom(@RequestParam(value="id") int id)
    {
        String url = "jdbc:postgresql://localhost:5433/Game_Database";
        String user = "postgres";
        String password = "freeman.mark";

        Connection connect = null;
        PreparedStatement prep = null;
        ResultSet result = null;
        PreparedStatement prep2 = null;
        ResultSet result2 = null;

        String output = null;
        Room room = null;
        Item Item1 = null;
        Item Item2 = null;
        Item Item3 = null;
        Item Item4 = null;


        try
        {
            connect = DriverManager.getConnection(url, user, password);
            prep = connect.prepareStatement("SELECT room_id, name, description, item[1], item[2], item[3], item[4] FROM public.Rooms Where room_id = " + id);
            result = prep.executeQuery();

//            if (result.next()) {
//                output = "Room ID: " + result.getInt(1) +
//                        "<br>Name: " + result.getString(2) +
//                        "<br> Description: " + result.getString(3)+
//                        "<br> Item1: " + result.getString(4) +
//                        "<br> Item2: " + result.getString(5) +
//                        "<br> Item3: " + result.getString(6) +
//                        "<br> Item4: " + result.getString(7);
//            } else
//                output = "The item id doesn't exist";
            if(result.next()) {
                room = new Room(result.getString(3));

                prep2 = connect.prepareStatement("Select name, weight, description FROM public.Items WHERE name = '" + result.getString(4)
                        + "' OR name = '" + result.getString(5) + "' OR name = '" + result.getString(6) + "' OR name = '" + result.getString(7) + "'");
                result2 = prep2.executeQuery();
                if (result2.next()) {

                    if (result2.getString(1) != null) {
                        Item1 = new Item(result2.getString(1), result2.getInt(2));
                        Item1.addDescription(result2.getString(3));
                    } else
                        Item1 = null;
                }


                if (result2.next()) {
                    if (result2.getString(2) != null) {
                        Item2 = new Item(result2.getString(1), result2.getInt(2));
                        Item2.addDescription(result2.getString(3));
                    } else
                        Item2 = null;
                }

                if (result2.next()) {
                    if (result2.getString(3) != null) {
                        Item3 = new Item(result2.getString(1), result2.getInt(2));
                        Item3.addDescription(result2.getString(3));
                    } else
                        Item3 = null;
                }
                if (result2.next()) {
                    Item4 = new Item(result2.getString(1), result2.getInt(2));
                    Item4.addDescription(result2.getString(3));
                } else
                    Item4 = null;


                if (Item1 != null)
                    room.addItem(Item1);

                if (Item2 != null)
                    room.addItem(Item2);

                if (Item3 != null)
                    room.addItem(Item3);

                if (Item4 != null)
                    room.addItem(Item4);
            }


        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(ZorkController.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }

        return room;

    }