package com.cursosapp.cursosapi.service;

import com.cursosapp.cursosapi.domain.Curso;
import com.cursosapp.cursosapi.repository.CursoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    // CREATE
    public Curso crearCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    // READ - obtener todos
    public List<Curso> obtenerTodos() {
        return cursoRepository.findAll();
    }

    // READ - obtener por ID
    public Curso obtenerPorId(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado con ID: " + id));
    }

    // UPDATE
    public Curso actualizarCurso(Long id, Curso cursoActualizado) {
        Curso curso = obtenerPorId(id);

        curso.setNombre(cursoActualizado.getNombre());
        curso.setDescripcion(cursoActualizado.getDescripcion());
        curso.setDuracionHoras(cursoActualizado.getDuracionHoras());
        curso.setPrecio(cursoActualizado.getPrecio());
        curso.setNivel(cursoActualizado.getNivel());
        curso.setInstructor(cursoActualizado.getInstructor());

        return cursoRepository.save(curso);
    }

    // DELETE
    public void eliminarCurso(Long id) {
        Curso curso = obtenerPorId(id);
        cursoRepository.delete(curso);
    }
}