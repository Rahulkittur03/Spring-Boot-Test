package com.digest.journalApp.services;

import com.digest.journalApp.Entity.JournlEntry;
import com.digest.journalApp.Entity.User;
import com.digest.journalApp.Repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class JournalService {

    @Autowired
    private JournalEntryRepo journalEntryRepo;

    @Autowired
    private UserService userService;


    @Transactional
    public void saveJournalEntry(JournlEntry journlEntry, String userName)
    {
        User user = userService.findByUserName(userName);
        JournlEntry saved = journalEntryRepo.save(journlEntry);
        user.getJournalEntries().add(saved);
        userService.saveUserEntry(user);
    }
    public void saveJournalEntry(JournlEntry journlEntry)
    {
        journalEntryRepo.save(journlEntry);
    }


    public List<JournlEntry> getAll()
    {
        return journalEntryRepo.findAll();
    }
    public Optional<JournlEntry> findByID(ObjectId id)
    {
        return journalEntryRepo.findById(id);
    }
    @Transactional
    public boolean deletedById(ObjectId id, String userName)
    {
        try {
            boolean removed=false;
            User user = userService.findByUserName(userName);
            removed=user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if(removed)
            {
                userService.saveUserEntry(user);
                journalEntryRepo.deleteById(id);
                return removed;
            }
            else {
                return removed;
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
            throw new RuntimeException(e.getMessage());
        }

    }
}
