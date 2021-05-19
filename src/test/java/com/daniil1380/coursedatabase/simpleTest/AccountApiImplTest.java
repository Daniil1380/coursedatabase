package com.daniil1380.coursedatabase.simpleTest;

import com.daniil1380.coursedatabase.WebSecuritySetting;
import com.daniil1380.coursedatabase.api.AccountApiImpl;
import com.daniil1380.coursedatabase.entity.AccountEntity;
import com.daniil1380.coursedatabase.repository.AccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountApiImpl.class)
@Import({TestConfig.class, WebSecuritySetting.class})
public class AccountApiImplTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private AccountRepository repo;


    @Test
    @WithMockUser(username = "root", password = "root", roles = "CHIEF")
    public void getAllAccounts() throws Exception {
        when(repo.findAll()).thenReturn(Arrays.asList(
                new AccountEntity(2, 100.23, true, 3, 4),
                new AccountEntity(4, 10.43, false, 7, 8)
        ));


        mockMvc.perform(get("/accounts/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[*].id", containsInAnyOrder(1, 2)))
        ;

    }

    @Test
    @WithMockUser(username = "Tkachenko1380", password = "Daniil1380", roles = "USER")
    public void NotGetAllAccounts() throws Exception {
        when(repo.findAll()).thenReturn(Arrays.asList(
                new AccountEntity(2, 100.23, true, 3, 4),
                new AccountEntity(4, 10.43, false, 7, 8)
        ));

        mockMvc.perform(get("/accounts/"))
                .andExpect(status().isForbidden());
    }

}
