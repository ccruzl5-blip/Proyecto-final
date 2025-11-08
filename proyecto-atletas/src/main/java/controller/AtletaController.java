package com.example.proyectoatletas.proyectoatletas.controller;

import com.example.proyectoatletas.proyectoatletas.entity.Atleta;
import com.example.proyectoatletas.proyectoatletas.service.AtletaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/atletas")
// 💡 CORRECCIÓN: Permite peticiones desde cualquier origen (*).
// Esto soluciona el error de conexión si accedes desde un puerto diferente.
@CrossOrigin(origins = "*")
public class AtletaController {

    @Autowired
    private AtletaService atletaService;

    // 1. REGISTRAR ATLETA (POST)
    @PostMapping
    public Atleta registrarAtleta(@RequestBody Atleta atleta) {
        return atletaService.registrarAtleta(atleta);
    }

    // 2. OBTENER TODOS LOS ATLETAS (GET)
    @GetMapping
    public List<Atleta> obtenerTodosLosAtletas() {
        return atletaService.obtenerTodosLosAtletas();
    }

    // 3. OBTENER ATLETA POR ID (GET con Path Variable)
    @GetMapping("/{id}")
    public ResponseEntity<Atleta> obtenerAtletaPorId(@PathVariable Integer id) {
        return atletaService.obtenerAtletaPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 4. BUSCAR POR NOMBRE (GET con Query Parameter)
    @GetMapping("/buscar")
    public Atleta buscarPorNombre(@RequestParam String nombre) {
        return atletaService.buscarPorNombre(nombre);
    }
}