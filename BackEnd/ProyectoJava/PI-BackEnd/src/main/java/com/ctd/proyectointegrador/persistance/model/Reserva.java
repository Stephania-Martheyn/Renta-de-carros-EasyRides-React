package com.ctd.proyectointegrador.persistance.model;

import com.ctd.proyectointegrador.persistance.model.jwt.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Comparator;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "reservas")
public class Reserva implements Comparator<Reserva> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column (name = "hora_inicio")
    private String horaInicio;

    @Column(name = "fecha_inicial")
    private String fechaInicial;

    @Column(name = "fecha_final")
    private String fechaFinal;

    //Relación Muchos a Uno con la tabla “productos”
    @ManyToOne
    @JoinColumn(name ="producto_id")
    private Producto producto;

    //Relacion reservas a usuario
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Override
    public int compare(Reserva o1, Reserva o2) {
        return o1.getId().compareTo(o2.getId());
    }
}
