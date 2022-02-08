package com.travelAppSpringBoot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.travelAppSpringBoot.enums.TypeMeals;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.awt.*;
import java.sql.Blob;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "hotels")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int standard;
    private String description;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @OneToMany(mappedBy = "hotel",
            cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnore
    private List<Tour> tours;

    @Enumerated(EnumType.STRING)
    private TypeMeals typeMeals;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Hotel hotel = (Hotel) o;
        return id != null && Objects.equals(id, hotel.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
