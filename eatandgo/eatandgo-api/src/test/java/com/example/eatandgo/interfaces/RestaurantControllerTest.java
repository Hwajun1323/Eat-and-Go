package com.example.eatandgo.interfaces;

import com.example.eatandgo.application.RestaurantService;
import com.example.eatandgo.domain.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.clearInvocations;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RestaurantController.class)
public class RestaurantControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RestaurantService restaurantService;

    //@SpyBean(RestaurantRepositoryImpl.class)
    //private RestaurantRepositoryImpl restaurantRepository;

//    @SpyBean(MenuItemRepositoryImpl.class)
//    private MenuItemRepositoryImpl menuItemRepository;

    @Test
    public void list() throws Exception {

        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(new Restaurant(1004L, "McDonald", "Seoul"));

        given(restaurantService.getRestaurants()).willReturn(restaurants);

        mvc.perform(get("/restaurants"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("1004")))
                .andExpect(content().string(
                        containsString("McDonald")));
        //
    }

    @Test
    public void detail() throws Exception {
        Restaurant restaurant1 = new Restaurant(1004L, "McDonald", "Seoul");
        restaurant1.addMenuItem(new MenuItem("Kimchi"));

        Restaurant restaurant2 = new Restaurant(2021L, "KFC", "Seoul");

        given(restaurantService.getRestaurant(1004L)).willReturn(restaurant1);
        given(restaurantService.getRestaurant(2021L)).willReturn(restaurant2);

        mvc.perform(get("/restaurants/1004"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("1004")))
                .andExpect(content().string(
                        containsString("McDonald")))
                .andExpect(content().string(
                        containsString("Kimchi")
                ));

        mvc.perform(get("/restaurants/2021"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("2021")))
                .andExpect(content().string(
                        containsString("KFC")));
    }


    @Test
    public void create() throws Exception {
        //Restaurant restaurant = new Restaurant(1234L, "Hwajun", "Seoul");

//        given(restaurantService.addRestaurant((any())).will(invocation -> {
//            Restaurant restaurant = invocation.getArgument(0);
//            return Restaurant.builder()
//                    .id(1234L)
//                    .name(restaurant.getName())
//                    .address(restaurant.getAddress())
//                    .build();
//                });

        mvc.perform(post("/restaurants")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Hwajun\", \"address\":\"Seoul\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/restaurants/1234"))
                .andExpect(content().string("{}"));

        verify(restaurantService).addRestaurant(any());
    }
}