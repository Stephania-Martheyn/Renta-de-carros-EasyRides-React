package com.ctd.proyectointegrador.controller;

import com.ctd.proyectointegrador.exceptions.ResourceNotFoundException;
import com.ctd.proyectointegrador.persistance.dto.ProductoDTO;
import com.ctd.proyectointegrador.service.impl.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.text.ParseException;
import java.util.Map;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    @Autowired
    ProductoService productoService;

    private Integer codigo;

    @PostMapping()
    public ResponseEntity<Map<String, Object>> guardar(@RequestBody ProductoDTO producto){
        Map<String, Object> response = productoService.guardar(producto);
        return ResponseEntity.created(URI.create("/productos")).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> buscar(@PathVariable Long id) throws ResourceNotFoundException {
        Map<String, Object> response = productoService.buscar(id);
        codigo = (Integer) response.get("codigo");
        return ResponseEntity.status(codigo).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> actualizar(@PathVariable Long id, @RequestBody ProductoDTO p) {
        Map<String, Object> response = productoService.actualizar(id, p);
        codigo = (Integer) response.get("codigo");
        return ResponseEntity.status(codigo).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> eliminar(@PathVariable Long id) {
        Map<String, Object> response = productoService.eliminar(id);
        codigo = (Integer) response.get("codigo");
        return ResponseEntity.status(codigo).body(response);
    }

    @GetMapping()
    public ResponseEntity<Map<String, Object>> listarTodos(){
        Map<String, Object> response = productoService.listarTodos();
        codigo = (Integer) response.get("codigo");
        return ResponseEntity.status(codigo).body(response);
    }

    @GetMapping("/ciudad/{id}")
    public ResponseEntity<Map<String, Object>> listarPorCiudad(@PathVariable Long id){
        Map<String, Object> response = productoService.listarPorCiudad(id);
        codigo=(Integer) response.get("codigo");
        return ResponseEntity.status(codigo).body(response);
    }

    @GetMapping("/{ciudad_id}/{checkIn}/{checkOut}")
    public ResponseEntity<Map<String, Object>> filtroCiudadYFecha(@PathVariable Long ciudad_id, @PathVariable String checkIn, @PathVariable String checkOut) throws ParseException {

        Map<String, Object> response = productoService.filtroCiudadYFechas(ciudad_id, checkIn, checkOut);
        codigo=(Integer) response.get("codigo");
        return ResponseEntity.status(codigo).body(response);
    }

    @GetMapping("/{id}/reservas")
    public ResponseEntity<Map<String, Object>> reservaPorId(@PathVariable Long id){
        Map<String, Object> response = productoService.reservasPorId(id);
        codigo = (Integer) response.get("codigo");
        return ResponseEntity.status(codigo).body(response);
    }
}
