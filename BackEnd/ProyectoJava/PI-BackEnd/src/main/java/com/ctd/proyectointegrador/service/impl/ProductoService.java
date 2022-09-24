package com.ctd.proyectointegrador.service.impl;

import com.ctd.proyectointegrador.exceptions.ResourceNotFoundException;
import com.ctd.proyectointegrador.persistance.dto.ImagenDTO;
import com.ctd.proyectointegrador.persistance.dto.ProductoDTO;
import com.ctd.proyectointegrador.persistance.dto.ReservaDTO;
import com.ctd.proyectointegrador.persistance.model.*;
import com.ctd.proyectointegrador.persistance.repository.*;
import com.ctd.proyectointegrador.service.IService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Transactional
@Service
public class ProductoService implements IService<ProductoDTO> {
    @Autowired
    ProductoRepository productoRepository;
    @Autowired
    CiudadRepository ciudadRepository;
    @Autowired
    CategoriaRepository categoriaRepository;
    @Autowired
    ImagenRepository imgRepository;

    @Autowired
    ReservaRepository reservaRepository;

    @Autowired
    CaracteristicaRepository caracteristicaRepository;
    @Autowired
    ObjectMapper mapper;

    private Map<String, Object> buildResponse(Object dto, String message, Integer code) {
        Map<String, Object> response = new HashMap<>();
        response.put("productos", dto);
        response.put("message", message);
        response.put("codigo", code);
        return response;
    }

    public Map<String, Object> guardar(ProductoDTO p) {

        Producto producto = mapper.convertValue(p, Producto.class);

        List<Caracteristica> nuevasCarac = new ArrayList<>();
        for (Caracteristica cat :
                producto.getCaracteristicas()) {
            nuevasCarac.add(caracteristicaRepository.findById(cat.getId()).get());
        }
        producto.setCaracteristicas(nuevasCarac);

        Ciudad ciudadBD = ciudadRepository.findById(producto.getCiudad().getId()).get();
        producto.setCiudad(ciudadBD);

        Categoria categoriaDB = categoriaRepository.findById(producto.getCategoria().getId()).get();
        producto.setCategoria(categoriaDB);

        Producto prodRespuesta = productoRepository.save(producto);

        System.out.println(prodRespuesta);

        for (Imagen img :
                producto.getImagenes()) {
            img.setProducto(prodRespuesta);
            imgRepository.save(img);
        }

        return buildResponse(mapper.convertValue(prodRespuesta, ProductoDTO.class), "producto creado", 201);
    }

    public Map<String, Object> buscar(Long id) throws ResourceNotFoundException {
        Producto prodRespuesta = productoRepository.findById(id).orElse(null);
        if (prodRespuesta == null){
            throw new ResourceNotFoundException("No se encuentra producto con id " + id);
        }
        return buildResponse(mapper.convertValue(prodRespuesta, ProductoDTO.class), "producto encontrado", 201);
    }

    public Map<String, Object> actualizar(Long id, ProductoDTO object) {
        Producto actualizar = mapper.convertValue(object, Producto.class);
        Producto productoEnBD = productoRepository.findById(id).orElse(null);
        if (productoEnBD == null) {
            // error
            return buildResponse(new ProductoDTO(), "No existe id " + id, 404);
        }
        productoEnBD.setTitulo(actualizar.getTitulo());
        productoEnBD.setDescripcion(actualizar.getDescripcion());
        productoEnBD.setImagenes(actualizar.getImagenes());
        productoEnBD.setCaracteristicas(actualizar.getCaracteristicas());
        productoEnBD.setCiudad(ciudadRepository.findById(actualizar.getCiudad().getId()).get());
        productoEnBD.setCategoria(actualizar.getCategoria());
        Producto prodRespuesta = productoRepository.save(productoEnBD);

        return buildResponse(mapper.convertValue(prodRespuesta, ProductoDTO.class), "cambio Exitoso", 201);
    }

    public Map<String, Object> eliminar(Long id) {
        if (productoRepository.findById(id).isPresent()) {
            productoRepository.removeImagenes(id);
            productoRepository.removeCarac(id);
            productoRepository.removeReservas(id);
            productoRepository.deleteById(id);
            return buildResponse(new ProductoDTO(), "Producto id " + id + " eliminado", 200);
        } else {
            return buildResponse(new ProductoDTO(), "Producto id " + id + " no existe", 404);
        }
    }

    public Map<String, Object> listarTodos() {

        List<Producto> listaProd = productoRepository.findAll();

        List<ProductoDTO> listaDTO = new ArrayList<>();

        for (Producto p : listaProd) {
            ProductoDTO prodDTO = mapper.convertValue(p, ProductoDTO.class);
            listaDTO.add(prodDTO);
        }
        Collections.shuffle(listaDTO);
        return buildResponse(listaDTO, "lista creada", 200);
    }

    public Map<String,Object> listarPorCiudad(Long id) {
        List<Producto> productos = productoRepository.listarPorCiudad(id);
        System.out.println(productos);
        List<ProductoDTO> productosPorCiudad = new ArrayList<>();
        for (Producto producto : productos) {
            productosPorCiudad.add(mapper.convertValue(producto, ProductoDTO.class));
        }
        return buildResponse(productosPorCiudad, "Lista por ciudad", 200);
    }

    public Map<String, Object> filtroCiudadYFechas(Long ciudad_id, String checkIn, String checkOut) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date checkInD = formatter.parse(checkIn);
        Date checkOutD = formatter.parse(checkOut);

        List<Producto> listaProd = productoRepository.listarPorFechaYCiudad(ciudad_id, checkInD, checkOutD);

        List<ProductoDTO> listaDTO = new ArrayList<>();

        for (Producto p : listaProd) {
            ProductoDTO prodDTO = mapper.convertValue(p, ProductoDTO.class);
            listaDTO.add(prodDTO);
        }
        return buildResponse(listaDTO, "lista creada", 200);
    }

    public Map<String, Object> reservasPorId(Long id) {
        List<Reserva> listaReservas = reservaRepository.reservasPorIdProducto(id);
        List<ReservaDTO> listaDTO = new ArrayList<>();

        for(Reserva r : listaReservas){
            ReservaDTO reservaDTO= mapper.convertValue(r, ReservaDTO.class);
            listaDTO.add(reservaDTO);
        }
        return buildResponse(listaDTO, "Lista creada", 200);
    }
}



