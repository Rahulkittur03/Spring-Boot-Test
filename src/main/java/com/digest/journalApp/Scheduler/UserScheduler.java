package com.digest.journalApp.Scheduler;


import com.digest.journalApp.Cache.AppCache;
import com.digest.journalApp.Entity.JournlEntry;
import com.digest.journalApp.Entity.User;
import com.digest.journalApp.Repository.UserReproImpl;
import com.digest.journalApp.services.EmailService;
import com.digest.journalApp.services.SentimentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    private EmailService emailService;
    @Autowired
    private UserReproImpl userRepro;
    @Autowired
    private SentimentService sentimentService;
    @Autowired
    private AppCache appCache;

    @Scheduled(cron = "0 0 10 ? * SUN")
//    @Scheduled(cron = "0 0/1 * 1/1 * ?")
    public void feathUserAndSendMail(){
        List<User> users = userRepro.getUserForSA();
        for(User user:users)
        {
            List<JournlEntry> journalEntries = user.getJournalEntries();
            List<String> filterList = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x->x.getContent()).toList();
            String entry = String.join(" ", filterList);
            String sentiment = sentimentService.getSentiment(entry);
            //emailService.sendMail(user.getEmail(),"Sentimental of User",sentiment);
            System.out.println("Sentiment of the user"+sentiment+entry);
        }

    }
    @Scheduled(cron = "0 0/10 * 1/1 * ? *")
    public void AppCache()
    {
        appCache.init();
    }
}
