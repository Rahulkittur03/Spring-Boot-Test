package com.digest.journalApp.Controller;

import com.digest.journalApp.Entity.User;
import com.digest.journalApp.Repository.UserEntryRepo;
import com.digest.journalApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserEntryController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserEntryRepo userEntryRepo;

    @PutMapping()
    public ResponseEntity<?> updateUser(@RequestBody User user)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User userNDB = userService.findByUserName(authentication.getName());
            userNDB.setUserName(user.getUserName());
            userNDB.setPassword(user.getPassword());
            userService.saveNewUserEntry(userNDB);
        return new ResponseEntity<>(user,HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userEntryRepo.deleteByuserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
