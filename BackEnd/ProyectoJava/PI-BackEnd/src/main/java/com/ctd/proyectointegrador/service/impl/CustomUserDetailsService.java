package com.ctd.proyectointegrador.service.impl;
import com.ctd.proyectointegrador.configuration.SecurityUtils;
import com.ctd.proyectointegrador.persistance.model.jwt.UserPrinciple;
import com.ctd.proyectointegrador.persistance.model.jwt.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UsuarioService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario user = userService.findByUserEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));
        Set<GrantedAuthority> authorities =Set.of(SecurityUtils.convertToAuthority(user.getRol().name()));

        return UserPrinciple.builder()
                .user(user)
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .authorities(authorities)
                .build();
    }
}