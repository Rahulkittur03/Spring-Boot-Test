package com.digest.journalApp.Controller;

import com.digest.journalApp.Entity.User;
import com.digest.journalApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class Public_Controller {

    @Autowired
    private UserService userService;


    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody User user)
    {
        try
        {
            userService.saveUserEntry(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/health_Check")
    public String health_Check()
    {
        return "health 100%";
    }
}
