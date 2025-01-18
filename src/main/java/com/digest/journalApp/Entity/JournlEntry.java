package com.digest.journalApp.Entity;

import com.digest.journalApp.enums.Sentiment;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "Journal_Entries")
@Data
public class JournlEntry {
    @Getter
    @Id
    private ObjectId id;

    private String title;

    private String content;
    private LocalDateTime date;
    private Sentiment sentiment;

}
