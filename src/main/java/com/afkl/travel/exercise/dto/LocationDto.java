package com.afkl.travel.exercise.dto;

import com.afkl.travel.exercise.enums.LocationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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
