package com.ctd.proyectointegrador.service.impl;

import com.ctd.proyectointegrador.persistance.dto.ImagenDTO;
import com.ctd.proyectointegrador.persistance.model.Imagen;
import com.ctd.proyectointegrador.persistance.model.Producto;
import com.ctd.proyectointegrador.persistance.repository.ImagenRepository;
import com.ctd.proyectointegrador.persistance.repository.ProductoRepository;
import com.ctd.proyectointegrador.service.IService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ImagenService implements IService<ImagenDTO> {

    @Autowired
    ImagenRepository imagenRepository;
    @Autowired
    ProductoRepository productoRepository;

    @Autowired
    ObjectMapper mapper;

    private Map<String, Object> buildResponse(Object dto, String message, Integer code) {
        Map<String, Object> response = new HashMap<>();
        response.put("imagenes", dto);
        response.put("message", message);
        response.put("codigo", code);
        return response;
    }


    @Override
        public Map<String, Object> guardar(ImagenDTO object) {
            Imagen imagen = mapper.convertValue(object, Imagen.class);
            Producto productoDB = productoRepository.findById(imagen.getProducto().getId()).orElse(null);
            imagen.setProducto(productoDB);
            Imagen imgRespuesta = imagenRepository.save(imagen);
            return buildResponse(mapper.convertValue(imgRespuesta, ImagenDTO.class), "Imagen guardada", 201);
        }



    @Override
    public Map<String, Object> buscar(Long id) {
        Imagen imagenes = imagenRepository.findById(id).get();
        return buildResponse(mapper.convertValue(imagenes, ImagenDTO.class),"Imagen encontrada",201);
    }


    @Override
    public Map<String, Object> actualizar(Long id, ImagenDTO object) {
        Imagen actualizar = mapper.convertValue(object, Imagen.class);
        Imagen imagenenBD = imagenRepository.findById(id).orElse(null);
        if (imagenenBD == null) {
            return buildResponse(new ImagenDTO(), "No existe la imagen con id" + id, 404);
        }
        imagenenBD.setUrl(actualizar.getUrl());
        Imagen imagenr = imagenRepository.save(imagenenBD);
        return buildResponse(mapper.convertValue(imagenr, ImagenDTO.class),"Actualizacion exitosa",201);

    }


    @Override
    public Map<String, Object> eliminar(Long id) {
        if (imagenRepository.findById(id).isPresent()) {
            imagenRepository.deleteById(id);
            return buildResponse(new ImagenDTO(), "Imagen id " + id + "eliminada", 201);
        } else {
            return buildResponse(new ImagenDTO(), "Imagen id "+ id + "no existe", 404);
        }
    }


    @Override
    public Map<String, Object> listarTodos() {
        List<Imagen> listaImagenes = imagenRepository.findAll();

        List<ImagenDTO> listaImageDTO = new ArrayList<>();

        for(Imagen i : listaImagenes){
            ImagenDTO imageDTO =mapper.convertValue(i, ImagenDTO.class);
            listaImageDTO.add(imageDTO);
        }
       return buildResponse(listaImageDTO, "Lista creada",200);
    }


}
