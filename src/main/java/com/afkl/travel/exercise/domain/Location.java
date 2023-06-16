package com.afkl.travel.exercise.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Entity class tha represents the <b>LOCATION</b> table
 *
 * @author leonardofoz
 * @since 0.1.0
 */
@Getter
@Entity
public class Location {

    @Id
    private Long id;

    private String code;

    private String type;

    private Double longitude;

    private Double latitude;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    @JsonBackReference
    private Location parent;

    @OneToMany(mappedBy = "location", fetch = FetchType.LAZY)
    private List<Translation> translations;
}