package com.digest.journalApp.Service;

import com.digest.journalApp.Entity.User;
import com.digest.journalApp.Repository.UserEntryRepo;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserEntryRepo userEntryRepo;

    @Disabled
    @Test
    public void testAdd()
    {
        User user=userEntryRepo.findByuserName("Rahul");

        assertEquals(4,2+2);
        assertNotNull(user);
        assertTrue(user.getRoles().contains("ADMIN"));
        assertFalse(user.getJournalEntries().isEmpty());
    }


    @ParameterizedTest
    @CsvSource({
            "Rahul",
            "Pooja",
            "Ram"
    })
    public void Test(String name)
    {
        User user = userEntryRepo.findByuserName(name);
        assertNotNull(user,"Failed"+user.getUserName());
    }

}
