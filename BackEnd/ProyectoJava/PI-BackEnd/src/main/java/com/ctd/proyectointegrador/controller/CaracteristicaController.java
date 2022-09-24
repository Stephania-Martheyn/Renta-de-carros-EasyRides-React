package com.ctd.proyectointegrador.controller;

import com.ctd.proyectointegrador.service.impl.CaracteristicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/caracteristicas")
public class CaracteristicaController {

    @Autowired
    CaracteristicaService caracteristicaService;

    private Integer codigo;

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> buscar(@PathVariable Long id) {
        Map<String, Object> response = caracteristicaService.buscar(id);
        codigo=(Integer) response.get("codigo");
        return ResponseEntity.status(codigo).body(response);
    }

    @GetMapping()
    public ResponseEntity<Map<String, Object>> listarTodos(){
        Map<String, Object> response = caracteristicaService.listarTodos();
        codigo=(Integer) response.get("codigo");
        return ResponseEntity.status(codigo).body(response);
    }

}
