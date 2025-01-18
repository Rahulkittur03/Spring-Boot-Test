package com.digest.journalApp.Entity;

import com.digest.journalApp.enums.Sentiment;
import com.mongodb.connection.ProxySettings;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "User")

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private ObjectId id;

    @Indexed(unique = true)
    @NonNull
    private String userName;
    @NonNull
    private String password;
    private String email;

    @DBRef
    private List<JournlEntry> journalEntries=new ArrayList<>();
    private List<String>roles;

}
