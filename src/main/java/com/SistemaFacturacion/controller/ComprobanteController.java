package com.SistemaFacturacion.controller;

import com.SistemaFacturacion.dtos.DocumentRequestDTO;
import com.SistemaFacturacion.entitites.Comprobante;
import com.SistemaFacturacion.service.ComprobanteService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// SE CAMBIA A @Controller PARA PERMITIR DEVOLVER VISTAS
@Controller
@RequestMapping
public class ComprobanteController {

    private final ComprobanteService svc;

    public ComprobanteController(ComprobanteService svc) {
        this.svc = svc;
    }

    // ==============================
    //   *** RUTAS PARA LAS VISTAS ***
    // ==============================

    // LISTA DE COMPROBANTES (vista comprobantes.html)
    @GetMapping("/comprobantes")
    public String vistaComprobantes() {
        return "comprobantes";
    }

    // FORMULARIO DE NUEVO COMPROBANTE
    @GetMapping("/comprobantes/nuevo")
    public String nuevoComprobante() {
        return "comprobante-form";
    }

    // VISTA "VER DETALLE"
    @GetMapping("/comprobantes/ver/{id}")
    public String verComprobante(@PathVariable Long id) {
        return "comprobante-ver";
    }


    // ==============================
    //   *** API REST (tu código) ***
    // ==============================

    @PostMapping("/api/documentos")
    @ResponseBody
    public ResponseEntity<Comprobante> crear(@RequestBody DocumentRequestDTO req) {
        Comprobante doc = svc.crearComprobante(req);
        return ResponseEntity.status(201).body(doc);
    }

    @GetMapping("/api/documentos")
    @ResponseBody
    public ResponseEntity<List<Comprobante>> listar() {
        return ResponseEntity.ok(svc.listarTodos());
    }
}
