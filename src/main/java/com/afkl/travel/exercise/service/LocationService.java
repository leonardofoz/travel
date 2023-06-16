package com.afkl.travel.exercise.service;

import com.afkl.travel.exercise.enums.LocationType;
import com.afkl.travel.exercise.domain.Location;
import com.afkl.travel.exercise.repository.LocationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Service class that provides operations related to locations.
 *
 * @author leonardofoz
 * @since 0.1.0
 */
@Service
@AllArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;

    /**
     * Retrieves all locations.
     *
     * @return a list of all locations
     */
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    /**
     * Retrieves locations by type and code.
     *
     * @param type the type of the location
     * @param code the code of the location
     * @return a list of locations that match the given type and code
     */
    public List<Location> getAllLocationsByTypeAndCode(LocationType type, String code) {
        if (type == LocationType.airport)
            return locationRepository.findByTypeAndCode(type.name(), code);
        else if (type == LocationType.city) {
            List<Location> airports = locationRepository.findByType(LocationType.airport.name());
            return airports.stream()
                .filter(location -> Objects.equals(location.getParent().getType(), LocationType.city.name()))
                .filter(location -> Objects.equals(location.getParent().getCode(), code))
                .collect(Collectors.toList());
        } else {
            List<Location> airports = locationRepository.findByType(LocationType.airport.name());

            return airports.stream()
                .filter(location -> Objects.equals(location.getParent().getParent().getType(), LocationType.country.name()))
                .filter(location -> Objects.equals(location.getParent().getParent().getCode(), code))
                .collect(Collectors.toList());
        }
    }
}
