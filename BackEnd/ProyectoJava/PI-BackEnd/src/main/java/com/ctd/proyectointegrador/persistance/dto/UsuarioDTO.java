package com.ctd.proyectointegrador.persistance.dto;

import com.ctd.proyectointegrador.enums.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
/*@JsonInclude(JsonInclude.Include.NON_NULL)*/
public class UsuarioDTO {

    private Long id;

    private String nombre;

    private String apellido;

    private String email;

    private String password;

    private String ciudad;
}
