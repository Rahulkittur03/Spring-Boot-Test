package com.digest.journalApp.Entity;

import com.mongodb.connection.ProxySettings;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection ="config_Journal_app")

@Data
public class ConfigJournalAppEntity {

    private String key;
    private String value;

}
