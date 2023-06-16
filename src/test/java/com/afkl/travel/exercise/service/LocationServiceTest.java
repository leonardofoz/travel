package com.afkl.travel.exercise.service;

import com.afkl.travel.exercise.domain.Location;
import com.afkl.travel.exercise.enums.LocationType;
import com.afkl.travel.exercise.repository.LocationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@RunWith(SpringRunner.class)
@DataJpaTest
class LocationServiceTest {

    @Autowired
    private LocationRepository locationRepository;
    private LocationService locationService;

    @BeforeEach
    void setUp() {
        locationService = new LocationService(locationRepository);
    }

    @Test
    void shouldGetAllLocations() {
        List<Location> locations = locationService.getAllLocations();
        assertFalse(locations.isEmpty());

        assertEquals(locations.size(), 2234);
    }

    @Test
    void shouldGetAllLocationsByTypeAndCode() {
        List<Location> locations = locationService.getAllLocationsByTypeAndCode(LocationType.city, "NYC");
        assertFalse(locations.isEmpty());

        assertEquals(locations.size(), 3);
    }
}