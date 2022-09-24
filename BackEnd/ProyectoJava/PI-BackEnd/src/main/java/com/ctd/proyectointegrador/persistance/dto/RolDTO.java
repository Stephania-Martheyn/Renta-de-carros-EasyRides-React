package com.ctd.proyectointegrador.persistance.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RolDTO {
    private Integer id;

    private String nombre;

    //private Set<UsuarioDTO> usuarios;
}
