package com.ctd.proyectointegrador.controller;


import com.ctd.proyectointegrador.persistance.dto.CiudadDTO;
import com.ctd.proyectointegrador.service.impl.CiudadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/ciudades")
public class CiudadController {
    @Autowired
    CiudadService ciudadService;

    private Integer codigo;

    @PostMapping()
    public ResponseEntity<Map<String, Object>> guardar(@RequestBody CiudadDTO ciudad){
        Map<String, Object> response = ciudadService.guardar(ciudad);
        return ResponseEntity.created(URI.create("/ciudades")).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> buscar(@PathVariable Long id) {
        Map<String, Object> response = ciudadService.buscar(id);
        codigo = (Integer) response.get("codigo");
        return ResponseEntity.status(codigo).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> actualizar(@PathVariable Long id, @RequestBody CiudadDTO c) {
        Map<String, Object> response = ciudadService.actualizar(id, c);
        codigo = (Integer) response.get("codigo");
        return ResponseEntity.status(codigo).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> eliminar(@PathVariable Long id) {
        Map<String, Object> response = ciudadService.eliminar(id);
        codigo = (Integer) response.get("codigo");
        return ResponseEntity.status(codigo).body(response);
    }


    @GetMapping()
    public ResponseEntity<Map<String, Object>> listarTodos(){
        Map<String, Object> response = ciudadService.listarTodos();
        codigo = (Integer) response.get("codigo");
        return ResponseEntity.status(codigo).body(response);
    }
}
