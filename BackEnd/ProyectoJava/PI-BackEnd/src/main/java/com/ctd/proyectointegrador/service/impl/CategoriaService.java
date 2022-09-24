package com.ctd.proyectointegrador.service.impl;

import com.ctd.proyectointegrador.persistance.dto.CategoriaDTO;
import com.ctd.proyectointegrador.persistance.model.Categoria;
import com.ctd.proyectointegrador.persistance.repository.CategoriaRepository;
import com.ctd.proyectointegrador.service.IService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoriaService implements IService<CategoriaDTO> {

    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    ObjectMapper mapper;

    private Map<String, Object> buildResponse(Object dto, String message, Integer code) {
        Map<String, Object> response = new HashMap<>();
        response.put("categorias", dto);
        response.put("message", message);
        response.put("codigo", code);
        return response;
    }

    public Map<String, Object> guardar(CategoriaDTO c){
        Categoria categoria = mapper.convertValue(c, Categoria.class);
        Categoria nuevaCat = categoriaRepository.save(categoria);
        return buildResponse(mapper.convertValue(nuevaCat, CategoriaDTO.class), "Categoria guardada",201);
    }

    public Map<String, Object> buscar(Long id){
        Categoria categoria = categoriaRepository.findById(id).get();
        return buildResponse(mapper.convertValue(categoria, CategoriaDTO.class),"Categoria id "+ id + "encontrada",201);
    }


    public Map<String, Object> actualizar(Long id, CategoriaDTO object){
     Categoria actualizar = mapper.convertValue(object, Categoria.class);
     Categoria categoriaenBD =categoriaRepository.findById(id).orElse(null);
     if (categoriaenBD == null){
         return buildResponse(new CategoriaDTO(), "No existe la categoria con id "+ id,404);
     }
     categoriaenBD.setTitulo(actualizar.getTitulo());
     categoriaenBD.setDescripcion(actualizar.getDescripcion());
     categoriaenBD.setUrl(actualizar.getUrl()); //lista de prod?
     Categoria categoriar = categoriaRepository.save(categoriaenBD);
     return buildResponse(mapper.convertValue(categoriar, CategoriaDTO.class), "Actualizacion exitosa", 201);
    }
    

    public Map<String, Object> eliminar(Long id) {

        if (categoriaRepository.findById(id).isPresent()) {
            categoriaRepository.deleteById(id);
            return buildResponse(new CategoriaDTO(), "Categoria id "+ id + " eliminada", 201);
        } else {
            return buildResponse(new CategoriaDTO(), "Categoria id "+ id + " no existe", 404);
        }
    }


    public Map<String, Object> listarTodos(){
        List<Categoria> listaCat = categoriaRepository.findAll();
        List<CategoriaDTO> listaDTO = new ArrayList<>();

        for (Categoria c: listaCat){
            CategoriaDTO catDTO = mapper.convertValue(c, CategoriaDTO.class);
            listaDTO.add(catDTO);
        }
        return buildResponse(listaDTO, "Lista creada",200);
    }
}
