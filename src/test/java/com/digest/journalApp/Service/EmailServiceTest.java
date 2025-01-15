package com.digest.journalApp.Service;

import com.digest.journalApp.services.EmailService;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {

    @Autowired
    private EmailService emailService;
    @Test
    void testMail()
    {
        System.out.println("print");
        emailService.sendMail("rahulkittur3@gmail.com","testSendMail","Checking if mail is sent");
    }



}
