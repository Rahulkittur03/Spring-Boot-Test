package com.digest.journalApp.Service;


import com.digest.journalApp.Entity.User;
import com.digest.journalApp.Repository.UserEntryRepo;
import com.digest.journalApp.services.UserDetailsServiceImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import org.springframework.data.mongodb.core.aggregation.ConditionalOperators;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.ArrayList;
import static org.mockito.Mockito.*;


public class UserDetailsServiceImpTest {


    @InjectMocks
    private UserDetailsServiceImp userDetailsService;

    @Mock
    private UserEntryRepo userEntryRepo;

    @BeforeEach
    void  setup(){
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void LoadUserByUserName()
    {
        when(userEntryRepo.findByuserName("Rahul")).thenReturn(User.builder().userName("Rahul").password("12345").roles(new ArrayList<>()).build());
        UserDetails user = userDetailsService.loadUserByUsername("Rahul");
        Assertions.assertNotNull(user);
    }

}
