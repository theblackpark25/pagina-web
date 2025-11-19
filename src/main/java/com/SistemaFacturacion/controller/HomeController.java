package com.SistemaFacturacion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping({"/", "/index"})
    public String index() { return "index"; }

    @GetMapping("/registro")
    public String registro() { return "registro"; }

    @GetMapping("/comprobantes")
    public String comprobantes() { return "comprobantes";
    }
}
