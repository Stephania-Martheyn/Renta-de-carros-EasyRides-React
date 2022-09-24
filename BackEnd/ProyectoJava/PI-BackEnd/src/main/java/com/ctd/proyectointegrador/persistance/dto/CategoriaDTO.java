package com.ctd.proyectointegrador.persistance.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoriaDTO {

    @NotEmpty(message = "no debe estar vacio")
    @NotNull(message = "no debe ser nulo")
    private Long id;

    @NotEmpty(message = "no debe estar vacio")
    @NotNull(message = "no debe ser nulo")
    private String titulo;

    @NotEmpty(message = "no debe estar vacio")
    @NotNull(message = "no debe ser nulo")
    private String descripcion;

    @NotEmpty(message = "no debe estar vacio")
    @NotNull(message = "no debe ser nulo")
    private String url;

    @NotEmpty(message = "no debe estar vacio")
    @NotNull(message = "no debe ser nulo")
    private List<ProductoDTO> productos;
}
