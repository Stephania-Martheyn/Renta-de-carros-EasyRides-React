package com.ctd.proyectointegrador.service.impl;

import com.ctd.proyectointegrador.persistance.dto.CaracteristicaDTO;
import com.ctd.proyectointegrador.persistance.model.Caracteristica;
import com.ctd.proyectointegrador.persistance.repository.CaracteristicaRepository;
import com.ctd.proyectointegrador.service.IService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CaracteristicaService implements IService<CaracteristicaDTO> {

    @Autowired
    CaracteristicaRepository caracteristicaRepository;

    @Autowired
    ObjectMapper mapper;

    private Map<String, Object> buildResponse(Object dto, String message, Integer code) {
        Map<String, Object> response = new HashMap<>();
        response.put("caracteristicas", dto);
        response.put("message", message);
        response.put("codigo", code);
        return response;
    }


    @Override
    public Map<String, Object> guardar(CaracteristicaDTO object) {
        Caracteristica caracteristica = mapper.convertValue(object, Caracteristica.class);
        Caracteristica nuevaCaracteristica= caracteristicaRepository.save(caracteristica);
        return buildResponse(mapper.convertValue(nuevaCaracteristica, CaracteristicaDTO.class), "Caracteristica guardada", 201);
    }


    @Override
    public Map<String, Object> buscar(Long id) {
        Caracteristica caracteristica = caracteristicaRepository.findById(id).get();
        return buildResponse(mapper.convertValue(caracteristica, CaracteristicaDTO.class),"Caracteristica id "+id+" encontrada", 201);
    }

    @Override
    public Map<String, Object> actualizar(Long id, CaracteristicaDTO object) {
        Caracteristica actualizar = mapper.convertValue(object, Caracteristica.class);
        Caracteristica caracteristicaenBD = caracteristicaRepository.findById(id).get();
        if(caracteristicaenBD == null){
            return buildResponse(new CaracteristicaDTO(), "No existe la caracteristica con id "+ id ,404);
        }
        caracteristicaenBD.setTitulo(actualizar.getTitulo());
        Caracteristica caracteristicar = caracteristicaRepository.save(caracteristicaenBD);
        return buildResponse(mapper.convertValue(caracteristicar, CaracteristicaDTO.class),"Actualizacion exitosa",201);
    }



    @Override
    public Map<String, Object> eliminar(Long id) {
        if(caracteristicaRepository.findById(id).isPresent()){
            caracteristicaRepository.deleteById(id);
            return buildResponse(new CaracteristicaDTO(), "Caracteristica id " + id + "eliminada", 201);
        }else {
            return buildResponse(new CaracteristicaDTO(), "Caracteristica id "+ id + "no existe", 404);
        }

    }


    @Override
    public Map<String, Object> listarTodos() {
        List<Caracteristica> listaCaracteristicas = caracteristicaRepository.findAll();
        List<CaracteristicaDTO> listaCaractDTO = new ArrayList<>();
        for(Caracteristica c : listaCaracteristicas){
            CaracteristicaDTO CaractDTO =mapper.convertValue(c, CaracteristicaDTO.class);
            listaCaractDTO.add(CaractDTO);
        }
        return buildResponse(listaCaractDTO, "Lista creada",200);
    }

}
