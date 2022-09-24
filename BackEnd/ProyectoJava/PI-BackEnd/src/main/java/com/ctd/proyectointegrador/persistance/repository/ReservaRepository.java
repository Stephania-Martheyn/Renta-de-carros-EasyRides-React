package com.ctd.proyectointegrador.persistance.repository;


import com.ctd.proyectointegrador.persistance.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    @Query(value = "SELECT r.* FROM reservas r WHERE r.usuario_id = ?1", nativeQuery = true)
    List<Reserva> reservasPorIdUsuario(Long id);

    @Query(value = "SELECT r.* FROM reservas r WHERE r.producto_id = ?1", nativeQuery = true)
    List<Reserva> reservasPorIdProducto(Long id);
}
