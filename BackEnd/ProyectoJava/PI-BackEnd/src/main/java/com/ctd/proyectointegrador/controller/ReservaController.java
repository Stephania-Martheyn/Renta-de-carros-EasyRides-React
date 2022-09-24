package com.ctd.proyectointegrador.controller;

import com.ctd.proyectointegrador.exceptions.BadRequestException;
import com.ctd.proyectointegrador.persistance.dto.ReservaDTO;
import com.ctd.proyectointegrador.service.impl.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    ReservaService reservaService;

    private Integer codigo;

    @PostMapping()
    public ResponseEntity<Map<String, Object>> guardar(@RequestBody ReservaDTO reserva) throws BadRequestException {
        Map<String, Object> response = reservaService.guardar(reserva);
        return ResponseEntity.created(URI.create("/reservas")).body(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> buscar(@PathVariable Long id) {
        Map<String, Object> response = reservaService.buscar(id);
        codigo = (Integer) response.get("codigo");
        return ResponseEntity.status(codigo).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> actualizar(@PathVariable Long id, @RequestBody ReservaDTO r) {
        Map<String, Object> response = reservaService.actualizar(id, r);
        codigo = (Integer) response.get("codigo");
        return ResponseEntity.status(codigo).body(response);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> eliminar(@PathVariable Long id) {
        Map<String, Object> response = reservaService.eliminar(id);
        codigo = (Integer) response.get("codigo");
        return ResponseEntity.status(codigo).body(response);
    }


    @GetMapping()
    public ResponseEntity<Map<String, Object>> listarTodos(){
        Map<String, Object> response = reservaService.listarTodos();
        codigo = (Integer) response.get("codigo");
        return ResponseEntity.status(codigo).body(response);
}

}