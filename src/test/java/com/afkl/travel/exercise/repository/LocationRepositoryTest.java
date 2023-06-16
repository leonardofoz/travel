package com.afkl.travel.exercise.repository;

import com.afkl.travel.exercise.domain.Location;
import com.afkl.travel.exercise.enums.LocationType;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
class LocationRepositoryTest {

    @Autowired
    private LocationRepository repository;

    @Test
    void shouldFindByTypeAndCode() {
        List<Location> list = repository.findByTypeAndCode(LocationType.airport.name(), "GRU");
        assertFalse(list.isEmpty());
    }

    @Test
    void shouldFindByTypeCountry() {
        List<Location> list = repository.findByType(LocationType.country.name());
        assertEquals(list.size(), 172);
    }
}