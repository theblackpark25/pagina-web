package com.SistemaFacturacion.entitites;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "clientes",
        uniqueConstraints = @UniqueConstraint(columnNames = {"dni"}))
public class Clientes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dni;
    private String nombres;
    private String apellidos;
    private String correo;
    private String password;
}
