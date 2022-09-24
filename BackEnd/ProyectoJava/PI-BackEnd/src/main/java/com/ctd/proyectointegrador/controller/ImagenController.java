package com.ctd.proyectointegrador.controller;

import com.ctd.proyectointegrador.persistance.dto.ImagenDTO;
import com.ctd.proyectointegrador.service.impl.ImagenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/imagenes")
public class ImagenController {
    @Autowired
    ImagenService imagenService;

    private Integer codigo;

    @PostMapping()
    public ResponseEntity<Map<String, Object>> guardar(@RequestBody ImagenDTO imagenes){
        Map<String, Object> response = imagenService.guardar(imagenes);
        return ResponseEntity.created(URI.create("/imagenes")).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> buscar(@PathVariable Long id) {
        Map<String, Object> response = imagenService.buscar(id);
        codigo = (Integer) response.get("codigo");
        return ResponseEntity.status(codigo).body(response);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> actualizar(@PathVariable Long id, @RequestBody ImagenDTO i) {
        Map<String, Object> response = imagenService.actualizar(id, i);
        codigo = (Integer) response.get("codigo");
        return ResponseEntity.status(codigo).body(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> eliminar(@PathVariable Long id) {
        Map<String, Object> response = imagenService.eliminar(id);
        codigo = (Integer) response.get("codigo");
        return ResponseEntity.status(codigo).body(response);
    }
    @GetMapping()
    public ResponseEntity<Map<String, Object>> listarTodos(){
        Map<String, Object> response = imagenService.listarTodos();
        codigo = (Integer) response.get("codigo");
        return ResponseEntity.status(codigo).body(response);
    }

}
