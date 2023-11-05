package com.example.londonAPI.service;

import com.example.londonAPI.models.User;
import com.example.londonAPI.utils.CityLocationEnum;
import com.example.londonAPI.utils.HaversineDistanceCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Value("${usersApiUrl}")
    private String usersApiUrl;

    @Value("${rangeInMiles}")
    private double rangeInMiles;

    @Autowired
    private RestTemplate restTemplate;

    public List<User> getUsersFromLondonWithinRange(){
        List<User> londonUsersWithinRange = new ArrayList<User>();

        List<User> usersList = getAllUsers();

        //Should be replaced with proper log statements
        System.out.println("List size of all the users:"+usersList.size());

        CityLocationEnum cityLocationEnum = CityLocationEnum.getByCityName("London");

        double originLatitude = cityLocationEnum.getLatitude();
        double originLongitude = cityLocationEnum.getLongitude();

        londonUsersWithinRange = usersList.stream().filter(user->
            HaversineDistanceCalculator.calculateDistance(originLatitude, originLongitude, user.getLatitude(), user.getLongitude()) <= rangeInMiles
        ).collect(Collectors.toList());

        //Should be replaced with proper log statements
        System.out.println("List size of all the users withing range:"+londonUsersWithinRange.size());
        return londonUsersWithinRange;
    }

    public List<User> getAllUsers(){
        ResponseEntity<User[]> responseEntity = restTemplate.getForEntity(usersApiUrl,User[].class);
        User[] users = responseEntity.getBody();
        List<User> usersList = List.of(users);
        return usersList;
    }
}
