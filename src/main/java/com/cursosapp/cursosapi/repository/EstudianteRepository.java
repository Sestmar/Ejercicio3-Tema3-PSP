package com.cursosapp.cursosapi.repository;

import com.cursosapp.cursosapi.domain.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    List<Estudiante> findByCursoId(Long cursoId);
    Estudiante findByEmail(String email);
}