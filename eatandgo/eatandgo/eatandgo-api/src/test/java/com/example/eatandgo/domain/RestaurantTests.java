package com.example.eatandgo.domain;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class RestaurantTests {

    @Test
    public void creation(){
        Restaurant restaurant = new Restaurant("McDonald", "Seoul");
        assertThat(restaurant.getName(), is("McDonald"));
        assertThat(restaurant.getAddress(), is("Seoul"));
    }

    @Test
    public void information(){
        Restaurant restaurant = new Restaurant("McDonald", "Seoul");

        assertThat(restaurant.getInformation(), is("McDonald in Seoul"));

    }

}