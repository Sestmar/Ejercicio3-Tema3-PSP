package com.cursosapp.cursosapi.web;

import com.cursosapp.cursosapi.domain.Estudiante;
import com.cursosapp.cursosapi.service.EstudianteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
@CrossOrigin(origins = "*")
public class EstudianteController {

    private final EstudianteService estudianteService;

    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Estudiante> crearEstudiante(@RequestBody Estudiante estudiante) {
        Estudiante estudianteDB = estudianteService.crearEstudiante(estudiante);
        return ResponseEntity.status(HttpStatus.CREATED).body(estudianteDB);
    }

    // READ - obtener todos
    @GetMapping
    public ResponseEntity<List<Estudiante>> obtenerTodos() {
        List<Estudiante> estudiantes = estudianteService.obtenerTodos();
        return ResponseEntity.ok(estudiantes);
    }

    // READ - obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> obtenerPorId(@PathVariable Long id) {
        Estudiante estudiante = estudianteService.obtenerPorId(id);
        return ResponseEntity.ok(estudiante);
    }

    // READ - obtener estudiantes de un curso
    @GetMapping("/curso/{cursoId}")
    public ResponseEntity<List<Estudiante>> obtenerPorCursoId(@PathVariable Long cursoId) {
        List<Estudiante> estudiantes = estudianteService.obtenerPorCursoId(cursoId);
        return ResponseEntity.ok(estudiantes);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Estudiante> actualizarEstudiante(
            @PathVariable Long id,
            @RequestBody Estudiante estudianteActualizado) {
        Estudiante estudiante = estudianteService.actualizarEstudiante(id, estudianteActualizado);
        return ResponseEntity.ok(estudiante);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEstudiante(@PathVariable Long id) {
        estudianteService.eliminarEstudiante(id);
        return ResponseEntity.noContent().build();
    }
}