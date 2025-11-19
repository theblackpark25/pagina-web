package com.SistemaFacturacion.service;

import com.SistemaFacturacion.dtos.DetailRequestDTO;
import com.SistemaFacturacion.dtos.DocumentRequestDTO;
import com.SistemaFacturacion.entitites.Comprobante;
import com.SistemaFacturacion.entitites.DetalleComprobante;
import com.SistemaFacturacion.entitites.TipoComprobante;
import com.SistemaFacturacion.repositories.ComprobanteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class ComprobanteService {
    private final ComprobanteRepository comprobanteRepository;
    private static final BigDecimal IGV_RATE = new BigDecimal("0.18");
    public ComprobanteService(ComprobanteRepository repo) { this.comprobanteRepository = repo; }

    @Transactional
    public Comprobante crearComprobante(DocumentRequestDTO req) {
        if (req.detalles() == null || req.detalles().isEmpty()) {
            throw new IllegalArgumentException("Detalles obligatorios");
        }

        Comprobante doc = new Comprobante();
        doc.setTipo(TipoComprobante.valueOf(req.tipoDocumento().toUpperCase()));
        doc.setClienteNombre(req.clienteNombre());
        doc.setClienteDocumento(req.clienteDocumento());

        BigDecimal subtotal = BigDecimal.ZERO;
        BigDecimal igvTotal = BigDecimal.ZERO;

        for (DetailRequestDTO dreq : req.detalles()) {
            DetalleComprobante d = new DetalleComprobante();
            d.setDescripcion(dreq.descripcion());
            d.setCantidad(dreq.cantidad());
            d.setPrecioUnitario(dreq.precioUnitario());

            BigDecimal valorVenta = dreq.precioUnitario().multiply(dreq.cantidad())
                    .setScale(2, RoundingMode.HALF_UP);
            BigDecimal igvLinea = valorVenta.multiply(IGV_RATE).setScale(2, RoundingMode.HALF_UP);
            BigDecimal totalLinea = valorVenta.add(igvLinea).setScale(2, RoundingMode.HALF_UP);

            d.setValorVenta(valorVenta);
            d.setIgvLinea(igvLinea);
            d.setTotalLinea(totalLinea);

            d.setComprobante(doc);
            doc.getDetalles().add(d);

            subtotal = subtotal.add(valorVenta);
            igvTotal = igvTotal.add(igvLinea);
        }

        doc.setSubtotal(subtotal.setScale(2, RoundingMode.HALF_UP));
        doc.setIgv(igvTotal.setScale(2, RoundingMode.HALF_UP));
        doc.setTotal(doc.getSubtotal().add(doc.getIgv()).setScale(2, RoundingMode.HALF_UP));

        return comprobanteRepository.save(doc);
    }

    public java.util.List<Comprobante> listarTodos() {
        return comprobanteRepository.findAll();
    }

}
