package com.SistemaFacturacion.entitites;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "comprobante")
public class Comprobante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipo;//boleta o factura
    private String serie;
    private Integer numero;
    private LocalDate fechaEmision;
    private BigDecimal montoTotal;
    private String moneda;
    private String estado;
}
