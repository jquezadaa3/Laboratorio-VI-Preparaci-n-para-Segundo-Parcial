package com.umg.labvi.biblioteca.service;

import com.umg.labvi.biblioteca.model.Libro;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class LibroService {

    private final List<Libro> libros = new ArrayList<>();
    private final AtomicLong contadorId = new AtomicLong(1);

    public Libro registrar(Libro libro) {
        libro.setId(contadorId.getAndIncrement());
        libros.add(libro);
        return libro;
    }

    public List<Libro> listarTodos() {
        return libros;
    }

    public Optional<Libro> buscarPorTitulo(String titulo) {
        return libros.stream()
                .filter(l -> l.getTitulo().equalsIgnoreCase(titulo))
                .findFirst();
    }

    public Optional<Libro> buscarPorId(Long id) {
        return libros.stream()
                .filter(l -> l.getId().equals(id))
                .findFirst();
    }

    public Optional<Libro> actualizar(Long id, Libro datosActualizados) {
        return buscarPorId(id).map(libro -> {
            libro.setTitulo(datosActualizados.getTitulo());
            libro.setAutor(datosActualizados.getAutor());
            libro.setIsbn(datosActualizados.getIsbn());
            libro.setAnioPublicacion(datosActualizados.getAnioPublicacion());
            libro.setEstado(datosActualizados.getEstado());
            return libro;
        });
    }

    public boolean eliminar(Long id) {
        return libros.removeIf(l -> l.getId().equals(id));
    }
}
