package com.cursosapp.cursosapi.service;

import com.cursosapp.cursosapi.domain.Curso;
import com.cursosapp.cursosapi.domain.Estudiante;
import com.cursosapp.cursosapi.repository.CursoRepository;
import com.cursosapp.cursosapi.repository.EstudianteRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EstudianteService {

    private final EstudianteRepository estudianteRepository;
    private final CursoRepository cursoRepository;

    public EstudianteService(EstudianteRepository estudianteRepository,
                             CursoRepository cursoRepository) {
        this.estudianteRepository = estudianteRepository;
        this.cursoRepository = cursoRepository;
    }

    // CREATE
    public Estudiante crearEstudiante(Estudiante estudiante) {
        // Validar que el curso existe
        if (estudiante.getCurso() != null && estudiante.getCurso().getId() != null) {
            Curso curso = cursoRepository.findById(estudiante.getCurso().getId())
                    .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
            estudiante.setCurso(curso);
        }
        return estudianteRepository.save(estudiante);
    }

    // READ - obtener todos
    public List<Estudiante> obtenerTodos() {
        return estudianteRepository.findAll();
    }

    // READ - obtener por ID
    public Estudiante obtenerPorId(Long id) {
        return estudianteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con ID: " + id));
    }

    // READ - obtener estudiantes de un curso
    public List<Estudiante> obtenerPorCursoId(Long cursoId) {
        return estudianteRepository.findByCursoId(cursoId);
    }

    // UPDATE
    public Estudiante actualizarEstudiante(Long id, Estudiante estudianteActualizado) {
        Estudiante estudiante = obtenerPorId(id);

        estudiante.setNombre(estudianteActualizado.getNombre());
        estudiante.setEmail(estudianteActualizado.getEmail());
        estudiante.setTelefono(estudianteActualizado.getTelefono());
        estudiante.setFechaInscripcion(estudianteActualizado.getFechaInscripcion());
        estudiante.setEstado(estudianteActualizado.getEstado());

        return estudianteRepository.save(estudiante);
    }

    // DELETE
    public void eliminarEstudiante(Long id) {
        Estudiante estudiante = obtenerPorId(id);
        estudianteRepository.delete(estudiante);
    }
}