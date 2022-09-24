package com.ctd.proyectointegrador.persistance.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@Table(name = "ciudades")
public class Ciudad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "provincia")
    private String provincia;
    @Column(name = "pais")
    private String pais;

    @OneToMany(mappedBy = "ciudad")
    @JsonIgnore
    private List<Producto> productos;

    public Ciudad() {
    }

    public Ciudad(String nombre, String provincia, String pais ) {
        this.nombre = nombre;
        this.provincia = provincia;
        this.pais = pais;
        this.productos = new ArrayList<>();
    }
}
