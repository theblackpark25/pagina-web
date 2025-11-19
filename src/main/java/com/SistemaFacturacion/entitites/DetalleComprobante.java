package com.SistemaFacturacion.entitites;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "detalle_documento")
public class DetalleComprobante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "documento_id")
    private Comprobante comprobante;

    private String descripcion;

    @Column(precision = 12, scale = 2)
    private BigDecimal cantidad;

    @Column(precision = 12, scale = 2)
    private BigDecimal precioUnitario;

    @Column(precision = 12, scale = 2)
    private BigDecimal valorVenta;

    @Column(precision = 12, scale = 2)
    private BigDecimal igvLinea;

    @Column(precision = 12, scale = 2)
    private BigDecimal totalLinea;
}
