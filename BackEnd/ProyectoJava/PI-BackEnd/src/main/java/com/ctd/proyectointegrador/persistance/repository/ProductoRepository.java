package com.ctd.proyectointegrador.persistance.repository;
import com.ctd.proyectointegrador.persistance.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;


public interface ProductoRepository extends JpaRepository<Producto, Long> {

    @Query(
            value = "SELECT * FROM productos p WHERE p.ciudad_id = ?1",
            nativeQuery = true)
    List<Producto> listarPorCiudad(Long id);

    @Query(value = "SELECT p.* FROM productos p WHERE p.id NOT IN (SELECT r.producto_id FROM reservas r WHERE r.fecha_inicial < ?3 AND r.fecha_final >  ?2 ) AND p.ciudad_id = ?1", nativeQuery = true)
    List<Producto> listarPorFechaYCiudad(Long ciudad_id, Date checkInD, Date checkOut);

    @Modifying
    @Query(value = "DELETE FROM imagenes i WHERE i.producto_id = ?1", nativeQuery = true)
    void removeImagenes(Long id);

    @Modifying
    @Query(value = "DELETE FROM producto_caracteristica i WHERE i.producto_id = ?1", nativeQuery = true)
    void removeCarac(Long id);

    @Modifying
    @Query(value = "DELETE FROM imagenes i WHERE i.producto_id = ?1", nativeQuery = true)
    void removeReservas(Long id);

}
