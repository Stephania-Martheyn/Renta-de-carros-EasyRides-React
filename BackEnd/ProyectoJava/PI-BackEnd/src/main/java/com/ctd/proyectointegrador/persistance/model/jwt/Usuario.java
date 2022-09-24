package com.ctd.proyectointegrador.persistance.model.jwt;

import com.ctd.proyectointegrador.enums.Role;
import com.ctd.proyectointegrador.persistance.model.Reserva;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="nombre")
    private String nombre;

    @Column(name="apellido")
    private String apellido;

    @Column(name="email")
    private String email;

    @Column(name="password")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(name="ciudad")
    private String ciudad;

    //Relacion reservas
    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    private Set<Reserva> reservas = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "rol")
    @JsonIgnore
    private Role rol;
 /*
  @ManyToOne En caso de necesitar relacio a rol - no enum roles
    @JoinColumn(name = "roles_id", referencedColumnName="id", nullable = false)
    private Rol rol;
  */
    @Transient
    private String token;

    public Usuario(String nombre, String apellido, String email, String password, String ciudad, Role rol) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.ciudad = ciudad;
        this.rol = rol;
    }

}
