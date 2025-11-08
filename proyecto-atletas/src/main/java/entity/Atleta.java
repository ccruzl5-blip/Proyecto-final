package com.example.proyectoatletas.proyectoatletas.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "atletas")
public class Atleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private Integer edad;
    private String disciplina;
    private String departamento;

    @OneToMany(mappedBy = "atleta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Entrenamiento> entrenamientos;

    @OneToMany(mappedBy = "atleta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Planilla> planilla;
}