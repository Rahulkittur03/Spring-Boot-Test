package com.digest.journalApp.Cache;

import com.digest.journalApp.Entity.ConfigJournalAppEntity;
import com.digest.journalApp.Repository.Config_Journal_app_Repo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    @Autowired
    private Config_Journal_app_Repo configJournalApp;
    public Map<String,String> APPCACHE;

    @PostConstruct
    public void init()
    {
        APPCACHE=new HashMap<>();
        List<ConfigJournalAppEntity> all = configJournalApp.findAll();

        for(ConfigJournalAppEntity configJournalAppEntity:all){
            APPCACHE.put(configJournalAppEntity.getKey(),configJournalAppEntity.getValue());
        }
    }
}
