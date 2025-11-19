package com.SistemaFacturacion.entitites;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "comprobante")
public class Comprobante {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoComprobante tipo; // FACTURA o BOLETA

    private LocalDateTime fechaEmision = LocalDateTime.now();

    private String clienteNombre;
    private String clienteDocumento; // DNI o RUC

    @Column(precision = 12, scale = 2)
    private BigDecimal subtotal = BigDecimal.ZERO;

    @Column(precision = 12, scale = 2)
    private BigDecimal igv = BigDecimal.ZERO;

    @Column(precision = 12, scale = 2)
    private BigDecimal total = BigDecimal.ZERO;

    @OneToMany(mappedBy = "comprobante", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleComprobante> detalles = new ArrayList<>();
}
