package com.example.proyectoatletas.proyectoatletas.repository;

import com.example.proyectoatletas.proyectoatletas.entity.Atleta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtletaRepository extends JpaRepository<Atleta, Integer> {

    // JPA genera la consulta SQL automáticamente al ver "findBy" + el nombre del campo.
    Atleta findByNombre(String nombre);
}