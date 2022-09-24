package com.ctd.proyectointegrador.controller;

import com.ctd.proyectointegrador.service.impl.PoliticaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/politicas")
public class PoliticaController {
    @Autowired
    PoliticaService politicaService;
    private Integer codigo;

    @GetMapping()
    public ResponseEntity<Map<String, Object>> listarTodos(){
        Map<String, Object> response = politicaService.listarTodos();
        codigo=(Integer) response.get("codigo");
        return ResponseEntity.status(codigo).body(response);
    }



}
