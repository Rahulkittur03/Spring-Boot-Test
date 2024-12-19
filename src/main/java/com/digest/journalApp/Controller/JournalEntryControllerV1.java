package com.digest.journalApp.Controller;

import com.digest.journalApp.Entity.JournlEntry;
import com.digest.journalApp.Entity.User;
import com.digest.journalApp.services.JournalService;
import com.digest.journalApp.services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Journal")
public class JournalEntryControllerV1 {

    @Autowired
    private JournalService journalService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllJournalEntriesOfUser()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);
        List<JournlEntry> all = user.getJournalEntries();
        if(all!=null && !all.isEmpty())
        {
            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<JournlEntry> createEntry(@RequestBody JournlEntry MyEntry)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        try {
            MyEntry.setDate(LocalDateTime.now());
            journalService.saveJournalEntry(MyEntry,userName);
            return new ResponseEntity<>(MyEntry, HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<JournlEntry> getJournalId(@PathVariable ObjectId myId)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user=userService.findByUserName(userName);
        List<JournlEntry> collect=user.getJournalEntries().stream().filter(x -> x.getId().equals(myId)).toList();

        if(!collect.isEmpty())
        {
            Optional<JournlEntry> journlEntry = journalService.findByID(myId);
            if(journlEntry.isPresent())
            {
                return new ResponseEntity<>(journlEntry.get(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/id/{myId}")
    public ResponseEntity<?> deleteById(@PathVariable ObjectId myId)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        boolean rm=journalService.deletedById(myId,userName);
        if(rm){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PutMapping("/id/{myId}")
    public ResponseEntity<JournlEntry> updateById(@PathVariable ObjectId myId,@RequestBody JournlEntry NewEntry)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user=userService.findByUserName(userName);
        List<JournlEntry> collect=user.getJournalEntries().stream().filter(x -> x.getId().equals(myId)).toList();

        if(!collect.isEmpty())
        {
            Optional<JournlEntry> journlEntry = journalService.findByID(myId);
            if(journlEntry.isPresent())
            {
                JournlEntry OldEntry = journlEntry.get();
                OldEntry.setTitle(NewEntry.getTitle()!=null && !NewEntry.getTitle().isEmpty() ? NewEntry.getTitle() : OldEntry.getTitle());
                OldEntry.setContent(NewEntry.getContent()!=null && !NewEntry.getContent().isEmpty() ? NewEntry.getContent() : OldEntry.getContent());
                journalService.saveJournalEntry(OldEntry);
                return new ResponseEntity<>(OldEntry,HttpStatus.OK);
            }
        }

        
        JournlEntry OldEntry=journalService.findByID(myId).orElse(null);
        if(OldEntry!=null){
            OldEntry.setTitle(NewEntry.getTitle()!=null && !NewEntry.getTitle().isEmpty() ? NewEntry.getTitle() : OldEntry.getTitle());
            OldEntry.setContent(NewEntry.getContent()!=null && !NewEntry.getContent().isEmpty() ? NewEntry.getContent() : OldEntry.getContent());
            journalService.saveJournalEntry(OldEntry);
            return new ResponseEntity<>(OldEntry,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
