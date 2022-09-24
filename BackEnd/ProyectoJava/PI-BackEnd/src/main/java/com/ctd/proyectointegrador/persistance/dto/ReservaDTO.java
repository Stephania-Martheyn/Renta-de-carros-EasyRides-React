package com.ctd.proyectointegrador.persistance.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservaDTO {

    private Long id;

    private String horaInicio;

    private String fechaInicial;

    private String fechaFinal;

    private ProductoDTO producto;

    private UsuarioDTO usuario;
}
