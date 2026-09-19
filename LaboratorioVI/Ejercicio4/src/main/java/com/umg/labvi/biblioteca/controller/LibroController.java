package com.umg.labvi.biblioteca.controller;

import com.umg.labvi.biblioteca.model.Libro;
import com.umg.labvi.biblioteca.service.LibroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @PostMapping
    public ResponseEntity<Libro> registrarLibro(@RequestBody Libro libro) {
        Libro nuevoLibro = libroService.registrar(libro);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoLibro);
    }

    @GetMapping
    public ResponseEntity<List<Libro>> consultarLibros() {
        return ResponseEntity.ok(libroService.listarTodos());
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<Libro> consultarLibroPorTitulo(@PathVariable String titulo) {
        return libroService.buscarPorTitulo(titulo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> actualizarLibro(@PathVariable Long id, @RequestBody Libro libro) {
        return libroService.actualizar(id, libro)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLibro(@PathVariable Long id) {
        boolean eliminado = libroService.eliminar(id);
        return eliminado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
