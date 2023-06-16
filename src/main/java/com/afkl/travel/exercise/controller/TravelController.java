package com.afkl.travel.exercise.controller;

import com.afkl.travel.exercise.domain.Location;
import com.afkl.travel.exercise.domain.Translation;
import com.afkl.travel.exercise.dto.LocationDto;
import com.afkl.travel.exercise.enums.Language;
import com.afkl.travel.exercise.enums.LocationType;
import com.afkl.travel.exercise.service.LocationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller class that handles all requests related to travel provided in the microservice
 * The API endpoints are secured with basic authentication using the provided username and password.
 *
 * @author leonardofoz
 * @since 0.1.0
 */
@RestController
@RequestMapping("${travel.controller.path}")
@AllArgsConstructor
@Validated
@Slf4j
public class TravelController {

    private final LocationService locationService;

    /**
     * Fetches all the locations from the database
     * @return The list of locations converted in order
     * to include name and description.
     */
    @GetMapping("${travel.controller.get-locations}")
    @ResponseBody
    public List<LocationDto> getAllLocationDto() {
        log.info("Get all locations");
        List<Location> allLocations = locationService.getAllLocations();

        return allLocations.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    /**
     * Fetches the locations based on the provided type and code.
     * @param type Enum type of the location (e.g., "country", "city", "airport").
     * @param code The code of the location.
     * @return The list of locations matching the given type and code,
     * converted to include name and description.
     */
    @GetMapping("${travel.controller.get-locations}/{type}/{code}")
    public List<LocationDto> getLocations(
            @PathVariable LocationType type,
            @PathVariable String code) {
        log.info("Get all locations by type: {} and code: {}", type, code);
        List<Location> locations = locationService.getAllLocationsByTypeAndCode(type, code);
        log.info("Converting the List<Location> fetched to a List<LocationDto>");
        return locations.stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }

    private LocationDto convertToDto(Location location) {
        Translation translation = getTranslationFromLocation(location.getTranslations());

        return LocationDto.builder()
            .code(location.getCode())
            .name(translation != null ? translation.getName() : null)
            .latitude(location.getLatitude())
            .longitude(location.getLongitude())
            .description(translation != null ? translation.getDescription() : null)
            .parentCode(location.getParent() != null ? location.getParent().getCode() : null)
            .parentType(LocationType.airport).build();
    }

    private Translation getTranslationFromLocation(List<Translation> translations) {
        return translations.stream()
            .filter(t -> t.getLanguage().equals(Language.EN.name()))
            .findFirst()
            .orElseGet(() -> translations.stream()
                .filter(t -> t.getLanguage().equals(Language.NL.name()))
                .findFirst()
                .orElse(null));
    }
}
