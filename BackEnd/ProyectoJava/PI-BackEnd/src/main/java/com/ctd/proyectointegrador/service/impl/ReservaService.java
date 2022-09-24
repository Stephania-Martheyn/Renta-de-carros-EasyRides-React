package com.ctd.proyectointegrador.service.impl;

import com.ctd.proyectointegrador.exceptions.BadRequestException;
import com.ctd.proyectointegrador.persistance.dto.ReservaDTO;
import com.ctd.proyectointegrador.persistance.model.Producto;
import com.ctd.proyectointegrador.persistance.model.Reserva;
import com.ctd.proyectointegrador.persistance.model.jwt.Usuario;
import com.ctd.proyectointegrador.persistance.repository.ProductoRepository;
import com.ctd.proyectointegrador.persistance.repository.ReservaRepository;
import com.ctd.proyectointegrador.persistance.repository.UsuarioRepository;
import com.ctd.proyectointegrador.service.IService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReservaService implements IService<ReservaDTO> {

    @Autowired
    ReservaRepository reservaRepository;

    @Autowired
    ProductoRepository productoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ObjectMapper mapper;

    private Map<String, Object> buildResponse(Object dto, String message, Integer code) {
        Map<String, Object> response = new HashMap<>();
        response.put("reservas", dto);
        response.put("message", message);
        response.put("codigo", code);
        return response;
    }

    @Override
    public Map<String, Object> guardar(ReservaDTO object) throws BadRequestException {
        Reserva reserva= mapper.convertValue(object, Reserva.class);
        Producto productoBD = productoRepository.findById(reserva.getProducto().getId()).orElse(null);
        Usuario usuarioBD = usuarioRepository.findById(reserva.getUsuario().getId()).orElse(null);
        if (productoBD == null || usuarioBD == null) {
            throw new BadRequestException("Id de usuario y/o producto no existe");
        }
        reserva.setProducto(productoBD);
        reserva.setUsuario(usuarioBD);
        Reserva reservaRespuesta = reservaRepository.save(reserva);
        return buildResponse(mapper.convertValue(reservaRespuesta, ReservaDTO.class), "Reserva guardada",201);
    }


    @Override
    public Map<String, Object> buscar(Long id) {
        Reserva reservas = reservaRepository.findById(id).get();
        return buildResponse(mapper.convertValue(reservas, ReservaDTO.class), "Reserva encontrada",200);
    }


    @Override
    public Map<String, Object> actualizar(Long id, ReservaDTO object) {
        Reserva actualizar = mapper.convertValue(object, Reserva.class);
        Reserva reservaBD = reservaRepository.findById(id).orElse(null);
        if(reservaBD == null){
            return buildResponse(new ReservaDTO(),"No existe reserva con id"+ id,404);
    }
        reservaBD.setHoraInicio(actualizar.getHoraInicio());
        reservaBD.setFechaInicial(actualizar.getFechaInicial()); // mod
        reservaBD.setFechaFinal(actualizar.getFechaFinal());
        Reserva reservaAct = reservaRepository.save(reservaBD);
        return buildResponse(mapper.convertValue(reservaAct, ReservaDTO.class), "Actualizacion exitosa", 201);
}


    @Override
    public Map<String, Object> eliminar(Long id) {
        if (reservaRepository.findById(id).isPresent()) {
            reservaRepository.deleteById(id);
            return buildResponse(new ReservaDTO(), "Reserva id "+ id + " eliminada", 200);
        }else
            return buildResponse(new ReservaDTO(), "Reserva id "+ id +" no existe",404);
    }

    @Override
    public Map<String, Object> listarTodos() {
        List<Reserva> listaReserva =  reservaRepository.findAll();
        List<ReservaDTO> listaReservaDTO= new ArrayList<>();
        for(Reserva r: listaReserva){
            ReservaDTO reservaDTO = mapper.convertValue(r, ReservaDTO.class);
            listaReservaDTO.add(reservaDTO);
        }
        return buildResponse(listaReservaDTO, "Lista creada",200);
    }
}
