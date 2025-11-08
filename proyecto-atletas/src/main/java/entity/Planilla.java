package com.example.proyectoatletas.proyectoatletas.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "planilla")
public class Planilla {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_planilla;

    private LocalDate fecha_pago;
    private Double monto;
    private String estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_atleta", nullable = false)
    private Atleta atleta;
}