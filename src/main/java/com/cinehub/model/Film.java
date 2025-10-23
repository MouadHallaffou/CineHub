package com.cinehub.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "films")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id", nullable = false)
    private Long filmID;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(name = "release_year", nullable = false)
    private Integer releaseYear;

    @Column(name = "duration_minutes", nullable = false)
    private Integer durationMinutes;

    @Column(length = 1000, columnDefinition = "TEXT", name = "synopsis")
    private String synopsis;

    @Column(name = "rating")
    private Double rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "director_id", nullable = false)
    private Director director;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

}
