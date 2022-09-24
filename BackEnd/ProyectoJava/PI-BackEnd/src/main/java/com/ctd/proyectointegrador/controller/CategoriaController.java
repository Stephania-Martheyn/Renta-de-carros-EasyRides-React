package com.ctd.proyectointegrador.controller;

import com.ctd.proyectointegrador.persistance.dto.CategoriaDTO;
import com.ctd.proyectointegrador.service.impl.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    @Autowired
    CategoriaService categoriaService;

    private Integer codigo;


    @PostMapping()
    public ResponseEntity<Map<String, Object>> guardar(@RequestBody CategoriaDTO categoria){
        return ResponseEntity.created(URI.create("/categorias")).body(categoriaService.guardar(categoria));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> buscar(@PathVariable Long id) {
        Map<String, Object> response = categoriaService.buscar(id);
        codigo = (Integer) response.get("codigo");
        return ResponseEntity.status(codigo).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> actualizar(@PathVariable Long id, @RequestBody CategoriaDTO c) {
        Map<String, Object> response = categoriaService.actualizar(id, c);
        codigo = (Integer) response.get("codigo");
        return ResponseEntity.status(codigo).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> eliminar(@PathVariable Long id) {
        Map<String, Object> response = categoriaService.eliminar(id);
        codigo = (Integer) response.get("codigo");
        return ResponseEntity.status(codigo).body(response);
    }

    @GetMapping()
    public ResponseEntity<Map<String, Object>> listarTodos(){
        Map<String, Object> response = categoriaService.listarTodos();
        codigo = (Integer) response.get("codigo");
        return ResponseEntity.status(codigo).body(response);
    }
}