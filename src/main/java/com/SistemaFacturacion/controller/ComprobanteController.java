package com.SistemaFacturacion.controller;

import com.SistemaFacturacion.dtos.DocumentRequestDTO;
import com.SistemaFacturacion.entitites.Comprobante;
import com.SistemaFacturacion.service.ComprobanteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/documentos")
public class ComprobanteController {
    private final ComprobanteService svc;
    public ComprobanteController(ComprobanteService svc ) {
        this.svc = svc;
    }

    @PostMapping
    public ResponseEntity<Comprobante> crear(@RequestBody DocumentRequestDTO req) {
        Comprobante doc = svc.crearComprobante(req);
        return ResponseEntity.status(201).body(doc);
    }

    @GetMapping
    public ResponseEntity<List<Comprobante>> listar() {
        return ResponseEntity.ok(svc.listarTodos());
    }
}
