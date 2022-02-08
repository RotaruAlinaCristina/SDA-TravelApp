package com.travelAppSpringBoot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.travelAppSpringBoot.enums.Promoted;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity(name = "tours")
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate departureDate;
    private LocalDate dateOfReturn;
    private Integer numberOfDays;
    private Double priceAdult;
    private Double priceChildren;
    private Integer numberSeatAdult;
    private Integer numberSeatChildren;

    @ManyToOne
    @JoinColumn(name = "departure_city_id")
    private City departureCity;

    @ManyToOne
    @JoinColumn(name = "arrival_city_id")
    private City arrivalCity;

    @ManyToOne
    @JoinColumn(name = "departure_airport_id")
    private Airport departureAirport;

    @ManyToOne
    @JoinColumn(name = "arrival_airport_id")
    private Airport arrivalAirport;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;


    @Enumerated(EnumType.STRING)
    private Promoted promoted;


    //    @OneToOne(mappedBy = "tour",
//            cascade = CascadeType.ALL)
//    private PurchasedTour purchasedTour;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Tour tour = (Tour) o;
        return id != null && Objects.equals(id, tour.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
