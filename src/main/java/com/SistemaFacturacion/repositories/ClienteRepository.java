package com.SistemaFacturacion.repositories;

import com.SistemaFacturacion.entitites.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Clientes, Long> {
}
