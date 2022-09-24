package com.ctd.proyectointegrador.service.impl;


import com.ctd.proyectointegrador.persistance.dto.CiudadDTO;
import com.ctd.proyectointegrador.persistance.model.Ciudad;
import com.ctd.proyectointegrador.persistance.repository.CiudadRepository;
import com.ctd.proyectointegrador.service.IService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CiudadService implements IService<CiudadDTO> {
    @Autowired
    CiudadRepository ciudadRespository;

    @Autowired
    ObjectMapper mapper;

    private Map<String, Object> buildResponse(Object dto, String message, Integer code){
        Map<String, Object> response = new HashMap<>();
        response.put("ciudad", dto);
        response.put("message", message);
        response.put("codigo", code);
        return response;
    }

    public Map<String, Object> guardar(CiudadDTO object) {
        Ciudad ciudad = mapper.convertValue(object, Ciudad.class);
        Ciudad ciudadRespuesta= ciudadRespository.save(ciudad);
        return buildResponse(mapper.convertValue(ciudadRespuesta, CiudadDTO.class), "ciudad creada",201);
    }

    public Map<String, Object> buscar(Long id) {
        if(ciudadRespository.findById(id).isPresent()){
            Ciudad ciudadRespuesta = ciudadRespository.findById(id).get();
            return buildResponse(mapper.convertValue(ciudadRespuesta, CiudadDTO.class), "ciudad encontrada",200);
        }else{
            return buildResponse(new CiudadDTO(), "no existe ciudad id " + id,404);
        }
    }

    public Map<String, Object> actualizar(Long id, CiudadDTO object) {
        Ciudad actualizar = mapper.convertValue(object, Ciudad.class);
        Ciudad ciudadBD = ciudadRespository.findById(id).orElse(null);
        if(ciudadBD == null){
            return buildResponse(new CiudadDTO(), "no existe ciudad id " + id, 404);
        }
        ciudadBD.setNombre(actualizar.getNombre());
        ciudadBD.setProvincia(actualizar.getProvincia());
        ciudadBD.setPais(actualizar.getPais());
        Ciudad ciudad = ciudadRespository.save(ciudadBD);
        return buildResponse(mapper.convertValue(ciudad, CiudadDTO.class), "ciudad actualizada", 201);
    }

    public Map<String, Object> eliminar(Long id) {
        if(ciudadRespository.findById(id).isPresent()) {
            ciudadRespository.deleteById(id);
            return buildResponse(new CiudadDTO(), "ciudad id"+id+" eliminada", 200);
        }
        return buildResponse(new CiudadDTO(), "no existe ciudad id " + id, 200);
    }

    public Map<String, Object> listarTodos() {
        List<Ciudad> listaCiudades = ciudadRespository.findAll();
        List<CiudadDTO> listaCiudadesDTO = new ArrayList<>();
        for(Ciudad c : listaCiudades){
            CiudadDTO CiuDTO= mapper.convertValue(c, CiudadDTO.class);
            listaCiudadesDTO.add(CiuDTO);
        }
        return buildResponse(listaCiudadesDTO, "lista creada", 200);
    }
}
