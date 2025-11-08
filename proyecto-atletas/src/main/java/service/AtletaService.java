package com.example.proyectoatletas.proyectoatletas.service;

import com.example.proyectoatletas.proyectoatletas.entity.Atleta;
import com.example.proyectoatletas.proyectoatletas.repository.AtletaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AtletaService {

    @Autowired
    private AtletaRepository atletaRepository;

    // CRUD: CREATE (Registrar)
    public Atleta registrarAtleta(Atleta atleta) {
        return atletaRepository.save(atleta);
    }

    // CRUD: READ (Obtener todos)
    public List<Atleta> obtenerTodosLosAtletas() {
        return atletaRepository.findAll();
    }

    // CRUD: READ (Obtener por ID)
    public Optional<Atleta> obtenerAtletaPorId(Integer id) {
        return atletaRepository.findById(id);
    }

    // 4. BUSCAR POR NOMBRE (Método que causaba el error)
    public Atleta buscarPorNombre(String nombre) {
        // La implementación de JpaRepository usa findByNombre.
        return atletaRepository.findByNombre(nombre);
    }

    // CRUD: DELETE
    public void eliminarAtleta(Integer id) {
        atletaRepository.deleteById(id);
    }
}