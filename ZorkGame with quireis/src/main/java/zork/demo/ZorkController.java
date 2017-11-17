package zork.demo;


import com.sun.jmx.remote.internal.ClientCommunicatorAdmin;
import org.jooq.tools.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import org.springframework.web.bind.annotation.PostMapping;

import org.jooq.*;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



@RestController
@RequestMapping("/game")
public class ZorkController {


    Game ZorkGame = new Game();
    Command command;

    @GetMapping("/")
    public void index() {
        ZorkGame.play();
    }

    @GetMapping("/go/{dir}")
    public GameState goEast(@PathVariable String dir)
    {
        command = new Command("go", dir);
        ZorkGame.processCommand(command);

        return new GameState(command, ZorkGame);
    }

    @GetMapping("/help")
    public GameState Help()
    {
        command = new Command("help", "east");
        ZorkGame.processCommand(command);

        return new GameState(command, ZorkGame);
    }

    @GetMapping("/unlock/{id}")
    public GameState unlock(@PathVariable String id)
    {
        command = new Command("unlock", id);
        ZorkGame.processCommand(command);

        return new GameState(command, ZorkGame);
    }

    @GetMapping("/grab/{item}")
    public GameState grab(@PathVariable String item)
    {
        command = new Command("grab", item);
        ZorkGame.processCommand(command);

        return new GameState(command, ZorkGame);
    }

    @GetMapping("/drop/{item}")
    public GameState drop(@PathVariable String item)
    {
        command = new Command("drop", item);
        ZorkGame.processCommand(command);

        return new GameState(command, ZorkGame);
    }

    @GetMapping("/winGame")
    public GameState winGame()
    {
        command = new Command("win", "game");
        ZorkGame.processCommand(command);

        return new GameState(command, ZorkGame);
    }

    @GetMapping("/look")
    public GameState look()
    {
        command = new Command("look", null);
        ZorkGame.processCommand(command);

        return new GameState(command, ZorkGame);
    }

    @GetMapping("/look/{item}")
    public GameState lookAt(@PathVariable String item)
    {
        command = new Command("look", item);
        ZorkGame.processCommand(command);

        return new GameState(command, ZorkGame);
    }

    @GetMapping("/quit")
    public GameState quit()
    {
        command = new Command("quit", null);
        ZorkGame.processCommand(command);

        return new GameState(command, ZorkGame);
    }


    @GetMapping("/game/input")
    public GameState readGameState(String input)
    {
        String firstHalf, secondHalf;
        int index = input.indexOf(' ');

        firstHalf = input.substring(0, index);
        secondHalf = input.substring(index + 1, input.length());
        command = new Command(firstHalf, secondHalf);

        ZorkGame.processCommand(command);
        // need to make game work with the api

        // create a GameState object to return
        return new GameState(command, ZorkGame);

        // return gs;
    }

    @GetMapping("/items")
    public String getItem(@RequestParam(value="id") int id)
    {
        String url = "jdbc:postgresql://localhost:5433/Game_Database";
        String user = "postgres";
        String password = "freeman.mark";

        Connection connect = null;
        PreparedStatement prep = null;
        ResultSet result = null;

        String output = null;
        Item item = null;

        try {
            connect = DriverManager.getConnection(url, user, password);
            prep = connect.prepareStatement("SELECT item_id, name, description, weight  FROM public.Items Where item_id = " + id);
            result = prep.executeQuery();

            if (result.next()) {
                output = "Item ID: " + result.getInt(1) +
                        "<br> Name: " + result.getString(2) +
                        "<br> Description: " + result.getString(3)+
                        "<br> Weight: " + result.getInt(4);
            } else {
                output = "The item id doesn't exist";
            }
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(ZorkController.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }

        return output;
    }

    @GetMapping("/rooms")
    public String getRoom(@RequestParam(value="id") int id)
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

        try {
            connect = DriverManager.getConnection(url, user, password);
            prep = connect.prepareStatement("SELECT room_id, name, description, item[1], item[2], item[3], item[4] FROM public.Rooms Where room_id = " + id);
            result = prep.executeQuery();

            if (result.next()) {
                output = "Room ID: " + result.getInt(1) +
                        "<br>Name: " + result.getString(2) +
                        "<br> Description: " + result.getString(3)+
                        "<br> Item1: " + result.getString(4) +
                        "<br> Item2: " + result.getString(5) +
                        "<br> Item3: " + result.getString(6) +
                        "<br> Item4: " + result.getString(7);
            } else {
                output = "The item id doesn't exist";
            }
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(ZorkController.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }

        return output;

    }

    @RequestMapping(value = "/lockedrooms", method = RequestMethod.GET)
    public String getLockedRoom(@RequestParam(value="id") int id)
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

        try {
            connect = DriverManager.getConnection(url, user, password);
            prep = connect.prepareStatement("SELECT room_id, name, description, item1, item2, item3, item4, key FROM public.LockedRooms Where room_id = " + id);
            result = prep.executeQuery();

            if (result.next()) {
                output = "Room ID: " + result.getInt(1) +
                        "<br>Name: " + result.getString(2) +
                        "<br> Description: " + result.getString(3)+
                        "<br> Item1: " + result.getString(4) +
                        "<br> Item2: " + result.getString(5) +
                        "<br> Item3: " + result.getString(6) +
                        "<br> Item4: " + result.getString(7) +
                        "<br> key: " + result.getString(8);
            } else {
                output = "The item id doesn't exist";
            }
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(ZorkController.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return output;
    }
}
