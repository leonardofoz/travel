package com.afkl.travel.exercise.service;

import com.afkl.travel.exercise.enums.LocationType;
import com.afkl.travel.exercise.domain.Location;
import com.afkl.travel.exercise.repository.LocationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public List<Location> getAllLocationsByTypeAndCode(LocationType type, String code) {
        if (type == LocationType.airport)
            return locationRepository.findByTypeAndCode(type.name(), code);
        else if (type == LocationType.city) {
            List<Location> airports = locationRepository.findByType(LocationType.airport.name());
            List<Location> cities = airports.stream()
                    .filter(location -> Objects.equals(location.getParent().getType(), LocationType.city.name()))
                    .filter(location -> Objects.equals(location.getParent().getCode(), code))
                    .collect(Collectors.toList());
            return cities;
        } else {
            List<Location> airports = locationRepository.findByType(LocationType.airport.name());
            List<Location> cities = airports.stream()
                    .filter(location -> Objects.equals(location.getParent().getParent().getType(), LocationType.country.name()))
                    .filter(location -> Objects.equals(location.getParent().getParent().getCode(), code))
                    .collect(Collectors.toList());

            return cities;
        }
    }
}
