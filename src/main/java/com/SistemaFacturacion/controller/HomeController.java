package com.SistemaFacturacion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // Página principal
    @GetMapping("/")
    public String home() {
        return "index";  // Renderiza templates/index.html
    }

    // Vista Login
    @GetMapping("/login")
    public String login() {
        return "login";  // Renderiza templates/login.html
    }

    // Vista registro de clientes
    @GetMapping("/registro")
    public String registro() {
        return "registro";  // Renderiza templates/registro.html
    }
}

