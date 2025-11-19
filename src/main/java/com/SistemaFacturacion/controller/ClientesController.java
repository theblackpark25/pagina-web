package com.SistemaFacturacion.controller;

import com.SistemaFacturacion.entitites.Clientes;
import com.SistemaFacturacion.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
public class ClientesController {

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping
    public Clientes registrarCLientes(@RequestBody Clientes cliente) {
        return clienteRepository.save(cliente);
    }
}
