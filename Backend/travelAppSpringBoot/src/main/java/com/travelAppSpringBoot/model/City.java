package com.travelAppSpringBoot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "cities")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id")
    @ToString.Exclude
    private Country country;

    @OneToMany(mappedBy = "city")
    @ToString.Exclude
    @JsonIgnore
    private List<Airport> airports;

    @OneToMany(mappedBy = "departureCity")
    @ToString.Exclude
    @JsonIgnore
    private List<Tour> tours1;

    @OneToMany(mappedBy = "arrivalCity")
    @ToString.Exclude
    @JsonIgnore
    private List<Tour> tours2;

    @OneToMany(mappedBy = "city")
    @ToString.Exclude
    @JsonIgnore
    private List<Hotel> hotels;

}
