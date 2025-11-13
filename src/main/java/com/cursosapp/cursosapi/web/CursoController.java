package com.cursosapp.cursosapi.web;

import com.cursosapp.cursosapi.domain.Curso;
import com.cursosapp.cursosapi.service.CursoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cursos")
@CrossOrigin(origins = "*")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Curso> crearCurso(@RequestBody Curso curso) {
        Curso cursoDB = cursoService.crearCurso(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoDB);
    }

    // READ - obtener todos
    @GetMapping
    public ResponseEntity<List<Curso>> obtenerTodos() {
        List<Curso> cursos = cursoService.obtenerTodos();
        return ResponseEntity.ok(cursos);
    }

    // READ - obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<Curso> obtenerPorId(@PathVariable Long id) {
        Curso curso = cursoService.obtenerPorId(id);
        return ResponseEntity.ok(curso);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Curso> actualizarCurso(
            @PathVariable Long id,
            @RequestBody Curso cursoActualizado) {
        Curso curso = cursoService.actualizarCurso(id, cursoActualizado);
        return ResponseEntity.ok(curso);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCurso(@PathVariable Long id) {
        cursoService.eliminarCurso(id);
        return ResponseEntity.noContent().build();
    }
}