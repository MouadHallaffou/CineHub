package com.cinehub.repository;

import com.cinehub.model.Film;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
    @Query("SELECT f FROM Film f WHERE LOWER(f.title) = LOWER(:title)")

    Optional<Film> findByTitle(@Param("title") String title);

    boolean existsByTitle( String title);
}
