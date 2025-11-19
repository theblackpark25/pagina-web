package com.SistemaFacturacion.dtos;

import java.math.BigDecimal;

public record DetailRequestDTO(String descripcion, BigDecimal cantidad, BigDecimal precioUnitario) {

}
