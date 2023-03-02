package learn.mvc.controller;

import learn.mvc.models.UserNumber;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Random;
import java.util.ArrayList;

@RestController
@RequestMapping("/random")
public class Controller {
    int int_Random;
    private ArrayList<UserNumber> userGuesses = new ArrayList<UserNumber>();
    private String array[] = new String[userGuesses.size()];
    Random randomNumber = new Random();
    // randomly generate number between 1 - 100
    @PostMapping("/start")
    public int randomNumber() {
        int MaxRange = 100;
        int_Random = randomNumber.nextInt(MaxRange);
        return int_Random;
    }

    public String getArrayString(){
        String string = "";
        for (int i = 0; i < userGuesses.size(); i++){
            if (i > 0) {
                string += ", ";
            }
        string += userGuesses.get(i).getUserNumber();
        }
        return string;
    }

    // have user pick number 1 - 100
    @PostMapping("/userNumber")
    public ResponseEntity<Object> add(@RequestBody UserNumber userNumber){
        userGuesses.add(userNumber);
//        String message = result(userNumber) + "\n Your current guesses are:" +
//                Arrays.toString(userGuesses.stream().map(userGuess -> userGuess.getUserNumber()).toArray());
        String message = result(userNumber) + "\n Your current guesses are: " + getArrayString();
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }


    //compare
    public String result(UserNumber userNumber) {
        String result = "";
        if (userNumber.getUserNumber() == int_Random) {
            result = "You Won!";
        } else if (userNumber.getUserNumber() > int_Random) {
            result = "Too high. Try again.";
        } else if (userNumber.getUserNumber() < int_Random) {
            result = "Too low. Try again.";
        }

        return result;
    }

}
