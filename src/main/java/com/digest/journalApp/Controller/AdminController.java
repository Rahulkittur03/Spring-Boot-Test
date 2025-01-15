package com.digest.journalApp.Controller;

import com.digest.journalApp.Entity.User;
import com.digest.journalApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")


public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllUser()
    {
        List<User> all=userService.getAll();
        if(!all.isEmpty())
        {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("Add")
    public ResponseEntity<?> postUser(@RequestBody User user)
    {
        userService.saveAdmin(user);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping("/Spec/{name}")
    public ResponseEntity<?> getUserByUserName(@PathVariable String name)
    {
        List<User> customUser = userService.findCustomUser(name);
        if(customUser.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customUser,HttpStatus.OK);
    }

    @GetMapping("/Spec")
    public ResponseEntity<?>getSentiUser(){
        List<User> sentimentUser = userService.getSentimentUser();
        if (sentimentUser.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(sentimentUser,HttpStatus.OK);
    }
}
