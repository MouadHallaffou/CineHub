package com.cinehub.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.jpa.repository.EntityGraph;

import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
@Table(name = "directors")
public class Director {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "director_id", nullable = false)
    private Long directorID;

    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "nationality", length = 100)
    private String nationality;

    @Column(name = "biography", length = 2000, columnDefinition = "TEXT")
    private String biography;

    @OneToMany(mappedBy = "director", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Film> films;
}
