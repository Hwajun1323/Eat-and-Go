package com.example.eatandgo.interfaces;

import com.example.eatandgo.application.RestaurantService;
import com.example.eatandgo.domain.MenuItem;
import com.example.eatandgo.domain.MenuItemRepository;
import com.example.eatandgo.domain.Restaurant;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    //@Autowired
    //private MenuItemRepository menuItemRepository;

    @GetMapping("/restaurants")
    public List<Restaurant> list(){
        List<Restaurant> restaurants = restaurantService.getRestaurants();

        return restaurants;
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant detail(@PathVariable("id") Long id){
        Restaurant restaurant = restaurantService.getRestaurant(id);

        //List<MenuItem> menuItems = menuItemRepository.findAllByRestaurantId(id);
        //restaurant.setMenuItems(menuItems);

        return restaurant;
    }

    @PostMapping("/restaurants")
    public ResponseEntity<?> create( @RequestBody Restaurant resource) throws URISyntaxException {
        String name = resource.getName();
        String address = resource.getAddress();

        Restaurant restaurant = new Restaurant( name, address);
        restaurantService.addRestaurant(restaurant);

        URI location = new URI("/restaurants/" + restaurant.getId());
        return ResponseEntity.created(location).body("{}");
    }
}
