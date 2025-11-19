package com.SistemaFacturacion.dtos;

import com.SistemaFacturacion.entitites.TipoComprobante;
import lombok.Data;

import java.util.List;


public record DocumentRequestDTO(String tipoDocumento, String clienteNombre, String clienteDocumento, List<DetailRequestDTO> detalles) {

}
