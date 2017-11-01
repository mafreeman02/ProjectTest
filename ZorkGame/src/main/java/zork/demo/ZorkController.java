package zork.demo;


import com.sun.jmx.remote.internal.ClientCommunicatorAdmin;
import org.jooq.tools.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import org.springframework.web.bind.annotation.PostMapping;

import org.jooq.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;



@RestController
@RequestMapping("/game")
public class ZorkController {

    Game myGame = new Game();
    Command command;

    @GetMapping("/")
    public void index(){

        myGame.play();
    }

    @GetMapping("/go/{dir}")
    public GameState goEast(@PathVariable String dir){
        command = new Command("go", dir);
        myGame.processCommand(command);

        return new GameState(command, myGame);
    }

    @GetMapping("/help")
    public GameState Help(){
        command = new Command("help", "east");
        myGame.processCommand(command);

        return new GameState(command, myGame);
    }

    @GetMapping("/unlock/{id}")
        public GameState unlock(@PathVariable String id){
            command = new Command("unlock", id);
            myGame.processCommand(command);

            return new GameState(command, myGame);
    }

    @GetMapping("/grab/{item}")
        public GameState grab(@PathVariable String item){
        command = new Command("grab", item);
        myGame.processCommand(command);

        return new GameState(command, myGame);
    }

    @GetMapping("/drop/{item}")
        public GameState drop(@PathVariable String item){
        command = new Command("drop", item);
        myGame.processCommand(command);

        return new GameState(command, myGame);
    }

    @GetMapping("/winGame")
            public GameState winGame(){
            command = new Command("win", "game");
            myGame.processCommand(command);

            return new GameState(command, myGame);
        }

    @GetMapping("/look")
        public GameState look(){
        command = new Command("look", null);
        myGame.processCommand(command);

        return new GameState(command, myGame);
    }

    @GetMapping("/look/{item}")
        public GameState lookAt(@PathVariable String item){
        command = new Command("look", item);
        myGame.processCommand(command);

        return new GameState(command, myGame);
    }

    @GetMapping("/quit")
    public GameState quit(){
        command = new Command("quit", null);
        myGame.processCommand(command);

        return new GameState(command, myGame);
    }


    @GetMapping("/game/input")
    public GameState readGameState(String input){
        String firstHalf, secondHalf;
        int index = input.indexOf(' ');

        firstHalf = input.substring(0,index);
        secondHalf = input.substring(index + 1,input.length());
        command = new Command(firstHalf, secondHalf);

        myGame.processCommand(command);
        // need to make game work with the api

        // create a GameState object to return
        return new GameState(command, myGame);

        // return gs;
    }

    @GetMapping("/users")
    public Room getRoom(@RequestParam(value="name") String name)
    {
        Room room = new Room(name);
        Item item= new Item("game", 1);
        room.addItem(item);

        return room;
    }

}
