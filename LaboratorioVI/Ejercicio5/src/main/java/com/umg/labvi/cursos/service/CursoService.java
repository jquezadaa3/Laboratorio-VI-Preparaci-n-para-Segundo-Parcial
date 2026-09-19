package com.umg.labvi.cursos.service;

import com.umg.labvi.cursos.model.Curso;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class CursoService {

    private final List<Curso> cursos = new ArrayList<>();
    private final AtomicLong contadorId = new AtomicLong(1);

    public Curso crear(Curso curso) {
        curso.setId(contadorId.getAndIncrement());
        cursos.add(curso);
        return curso;
    }

    public List<Curso> listarTodos() {
        return cursos;
    }

    public Optional<Curso> buscarPorCodigo(String codigo) {
        return cursos.stream()
                .filter(c -> c.getCodigo().equalsIgnoreCase(codigo))
                .findFirst();
    }

    public Optional<Curso> buscarPorId(Long id) {
        return cursos.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
    }

    public Optional<Curso> actualizar(Long id, Curso datosActualizados) {
        return buscarPorId(id).map(curso -> {
            curso.setNombre(datosActualizados.getNombre());
            curso.setCodigo(datosActualizados.getCodigo());
            curso.setCreditos(datosActualizados.getCreditos());
            curso.setEstado(datosActualizados.getEstado());
            return curso;
        });
    }

    public boolean eliminar(Long id) {
        return cursos.removeIf(c -> c.getId().equals(id));
    }
}
