package com.example.eatandgo.interfaces;

import com.example.eatandgo.domain.Restaurant;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RestaurantController {

    @GetMapping("/restaurants")
    public List<Restaurant> list(){
        List<Restaurant> restaurants = new ArrayList<>();

        Restaurant restaurant = new Restaurant("McDonald", "Seoul");

        restaurants.add(restaurant);

        return restaurants;
    }
}
