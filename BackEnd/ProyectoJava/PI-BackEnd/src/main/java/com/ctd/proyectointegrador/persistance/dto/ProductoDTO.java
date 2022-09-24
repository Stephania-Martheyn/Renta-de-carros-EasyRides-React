package com.ctd.proyectointegrador.persistance.dto;

import com.ctd.proyectointegrador.persistance.model.Imagen;
import com.ctd.proyectointegrador.persistance.model.Reserva;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductoDTO {

    private Long id;

    private String titulo;

    private String descripcion;

    private List<ImagenDTO> imagenes;

    private CiudadDTO ciudad;

    private CategoriaDTO categoria;

    private List<CaracteristicaDTO> caracteristicas;

    private List<ReservaDTO> reservas;
}
