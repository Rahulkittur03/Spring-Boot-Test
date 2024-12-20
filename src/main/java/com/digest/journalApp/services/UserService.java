package com.digest.journalApp.services;

import com.digest.journalApp.Entity.User;
import com.digest.journalApp.Repository.UserEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserEntryRepo userEntryRepo;

    private static final PasswordEncoder password =new BCryptPasswordEncoder();

    public void saveNewUserEntry(User user)
    {
        user.setPassword(password.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        userEntryRepo.save(user);
    }

    public void saveUserEntry(User user)
    {
        userEntryRepo.save(user);
    }

    public void saveAdmin(User user)
    {
        user.setPassword(password.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        userEntryRepo.save(user);
    }
    public List<User> getAll()
    {
        return userEntryRepo.findAll();
    }
    public Optional<User> findByID(ObjectId id)
    {
        return userEntryRepo.findById(id);
    }
    public void deletedById(ObjectId id)
    {
        userEntryRepo.deleteById(id);
    }
    public User findByUserName(String name)
    {
        return userEntryRepo.findByuserName(name);
    }
}
