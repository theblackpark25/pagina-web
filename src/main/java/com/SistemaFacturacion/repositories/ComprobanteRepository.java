package com.SistemaFacturacion.repositories;

import com.SistemaFacturacion.entitites.Comprobante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComprobanteRepository extends JpaRepository<Comprobante, Long> {
}
