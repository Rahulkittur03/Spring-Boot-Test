package com.digest.journalApp.services;

import com.digest.journalApp.Entity.JournlEntry;
import com.digest.journalApp.Entity.User;
import com.digest.journalApp.Repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class JournalService {

    @Autowired
    private JournalEntryRepo journalEntryRepo;

    @Autowired
    private UserService userService;

    private static final Logger logger= LoggerFactory.getLogger(JournalService.class);

    @Transactional
    public void saveJournalEntry(JournlEntry journlEntry, String userName)
    {
        try {
            User user = userService.findByUserName(userName);
            JournlEntry saved = journalEntryRepo.save(journlEntry);
            user.getJournalEntries().add(saved);
            userService.saveUserEntry(user);
        }
        catch (Exception e)
        {
            logger.info("Problem");
            throw new RuntimeException(e.getMessage());
        }

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
            throw new RuntimeException(e.getMessage());
        }

    }
}
