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
@Entity(name = "airports")
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "city_id")
    @JsonIgnore
    private City city;

    @OneToMany(mappedBy = "departureAirport",
            cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnore
    private List<Tour> tours1;

    @OneToMany(mappedBy = "arrivalAirport",
            cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnore
    private List<Tour> tours2;
}
