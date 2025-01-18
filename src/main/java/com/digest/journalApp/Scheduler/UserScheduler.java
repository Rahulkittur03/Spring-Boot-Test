package com.digest.journalApp.Scheduler;


import com.digest.journalApp.Cache.AppCache;
import com.digest.journalApp.Entity.JournlEntry;
import com.digest.journalApp.Entity.User;
import com.digest.journalApp.Repository.UserReproImpl;
import com.digest.journalApp.enums.Sentiment;
import com.digest.journalApp.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserScheduler {

    @Autowired
    private EmailService emailService;
    @Autowired
    private UserReproImpl userRepro;

    @Autowired
    private AppCache appCache;

    @Scheduled(cron = "0 0 10 ? * SUN")
//    @Scheduled(cron = "0 0/1 * 1/1 * ?")
    public void feathUserAndSendMail(){
        List<User> users = userRepro.getUserForSA();
        for(User user:users)
        {
            List<JournlEntry> journalEntries = user.getJournalEntries();
            List<Sentiment> sentiments = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x->x.getSentiment()).toList();

            Map<Sentiment,Integer> SentimentCount=new HashMap<>();
            {
                for(Sentiment sentiment : sentiments)
                {
                    if(sentiment!=null)
                    {
                        SentimentCount.put(sentiment,SentimentCount.getOrDefault(sentiment,0)+1);
                    }
                }
                Sentiment mostFrequentSentiment=null;
                int maxCount=0;
                for(Map.Entry<Sentiment,Integer> entry:SentimentCount.entrySet())
                {
                    if(entry.getValue()>maxCount)
                    {
                        maxCount=entry.getValue();
                        mostFrequentSentiment=entry.getKey();
                    }
                }
                if(mostFrequentSentiment!=null)
                {
                    emailService.sendMail(user.getEmail(),"Sentiment for last 7 days",mostFrequentSentiment.toString());
                }

            }





        }

    }
    //@Scheduled(cron = "0 0/10 * 1/1 *")
    public void AppCache()
    {
        appCache.init();
    }
}
