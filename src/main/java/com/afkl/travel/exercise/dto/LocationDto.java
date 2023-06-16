package com.afkl.travel.exercise.dto;

import com.afkl.travel.exercise.enums.LocationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) class representing a location.
 * It contains information such as code, name, latitude, longitude, description,
 * parent code, and parent type.
 *
 * @author leonardofoz
 * @since 0.1.0
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
public class LocationDto {
    private String code;
    private String name;
    private Double latitude;
    private Double longitude;
    private String description;
    private String parentCode;
    private LocationType parentType;
}
