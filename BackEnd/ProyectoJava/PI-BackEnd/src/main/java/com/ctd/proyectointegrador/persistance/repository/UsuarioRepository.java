package com.ctd.proyectointegrador.persistance.repository;

import com.ctd.proyectointegrador.enums.Role;
import com.ctd.proyectointegrador.persistance.model.Reserva;
import com.ctd.proyectointegrador.persistance.model.jwt.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
    @Modifying
    @Query("update Usuario set role = :role where email= :email")
    void updateUserRole(@Param("email") String email, @Param("role") Role role);

}