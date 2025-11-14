package com.cursosapp.cursosapi.web;

import com.cursosapp.cursosapi.domain.Curso;
import com.cursosapp.cursosapi.domain.Estudiante;
import com.cursosapp.cursosapi.dto.EstudianteDTO;
import com.cursosapp.cursosapi.repository.CursoRepository;
import com.cursosapp.cursosapi.service.EstudianteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
@CrossOrigin(origins = "*")
public class EstudianteController {

    private final EstudianteService estudianteService;
    private final CursoRepository cursoRepository;

    public EstudianteController(EstudianteService estudianteService, CursoRepository cursoRepository) {
        this.estudianteService = estudianteService;
        this.cursoRepository = cursoRepository;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Estudiante> crearEstudiante(@RequestBody EstudianteDTO dto) {
        Curso curso = cursoRepository.findById(dto.cursoId)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
        Estudiante estudiante = new Estudiante();
        estudiante.setNombre(dto.nombre);
        estudiante.setEmail(dto.email);
        estudiante.setTelefono(dto.telefono);
        estudiante.setFechaInscripcion(LocalDate.parse(dto.fechaInscripcion));
        estudiante.setEstado(dto.estado);
        estudiante.setCurso(curso);

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