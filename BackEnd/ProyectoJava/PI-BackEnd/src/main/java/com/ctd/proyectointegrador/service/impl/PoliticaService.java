package com.ctd.proyectointegrador.service.impl;


import com.ctd.proyectointegrador.persistance.dto.PoliticaDTO;
import com.ctd.proyectointegrador.persistance.model.Politica;
import com.ctd.proyectointegrador.persistance.repository.PoliticaRepository;
import com.ctd.proyectointegrador.service.IPoliticaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PoliticaService implements IPoliticaService<PoliticaDTO> {
    @Autowired
    PoliticaRepository politicaRepository;

    @Autowired
    ObjectMapper mapper;


    private Map<String, Object> buildResponse(Object dto, String message, Integer code) {
        Map<String, Object> response = new HashMap<>();
        response.put("politicas", dto);
        response.put("message", message);
        response.put("codigo", code);
        return response;
    }


    @Override
    public Map<String, Object> listarTodos() {
        List<Politica> listaPoliticas = politicaRepository.findAll();
        List<PoliticaDTO> listaPoliticasDTO = new ArrayList<>();

        for(Politica p : listaPoliticas){
            PoliticaDTO politicsDTO = mapper.convertValue(p, PoliticaDTO.class);
            listaPoliticasDTO.add(politicsDTO);
        }
        return buildResponse(listaPoliticasDTO, "Lista creada",200);
    }
}

