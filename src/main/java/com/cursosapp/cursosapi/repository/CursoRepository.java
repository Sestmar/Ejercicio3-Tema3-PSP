package com.cursosapp.cursosapi.repository;

import com.cursosapp.cursosapi.domain.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    Curso findByNombre(String nombre);
}
