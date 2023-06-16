package com.afkl.travel.exercise.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Entity class tha represents the <b>TRANSLATION</b> table
 *
 * @author leonardofoz
 * @since 0.1.0
 */
@Getter
@Entity
public class Translation {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "location")
    private Location location;

    @Column(nullable = false)
    private String language;

    private String name;

    private String description;


}
