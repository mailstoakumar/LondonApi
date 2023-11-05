package com.example.londonAPI.service;

import com.example.londonAPI.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    private final String usersApiUrl = "https://london-api.onrender.com/users";

    @InjectMocks
    private UserService userService;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    public void setup() {
        ReflectionTestUtils.setField(userService, "usersApiUrl", usersApiUrl);
        // Add any setup or initialization code here
    }

    @Test
    public void testGetUsersFromCityWithinRange() {
        String city = "London";
        double range = 100.0;

        User[] testUsers = createTestUsers().toArray(new User[2]);

        // Create a ResponseEntity with the test data
        ResponseEntity<User[]> responseEntity = new ResponseEntity<>(testUsers, HttpStatus.OK);

        // Mock the restTemplate.getForEntity method to return the ResponseEntity
        Mockito.when(restTemplate.getForEntity(eq(usersApiUrl), eq(User[].class)))
                .thenReturn(responseEntity);

        // Test the service method
        List<User> result = userService.getUsersFromLondonWithinRange();

        // Add assertions to validate the result
        assertEquals(1, result.size());
    }

    @Test
    public void testGetAllUsers() {
        User[] testUsers = createTestUsers().toArray(new User[2]);

        // Create a ResponseEntity with the test data
        ResponseEntity<User[]> responseEntity = new ResponseEntity<>(testUsers, HttpStatus.OK);

        // Mock the restTemplate.getForEntity method to return the ResponseEntity
        Mockito.when(restTemplate.getForEntity(eq(usersApiUrl), eq(User[].class)))
                .thenReturn(responseEntity);

        // Test the getAllUsers method
        List<User> result = userService.getAllUsers();

        // Add assertions to validate the result
        assertEquals(2, result.size());
    }

    private List<User> createTestUsers() {
        // Create and return a list of test User objects for your test case
        List<User> users = new ArrayList<>();

        User user = new User(135L, "Mechelle", "Boam", "mboam3q@thetimes.co.uk", "113.71.242.187", 51.5074, -0.1278);
        users.add(user);

        user = new User(396L, "Terry", "Stowgill", "tstowgillaz@webeden.co.uk", "143.190.50.240", -6.7098551, 111.652983);
        users.add(user);

        return users;
    }
}