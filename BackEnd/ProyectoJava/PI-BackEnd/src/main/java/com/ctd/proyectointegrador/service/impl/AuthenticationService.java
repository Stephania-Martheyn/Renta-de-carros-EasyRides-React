package com.ctd.proyectointegrador.service.impl;

import com.ctd.proyectointegrador.persistance.model.jwt.UserPrinciple;
import com.ctd.proyectointegrador.configuration.jwt.JwtProvider;
import com.ctd.proyectointegrador.persistance.model.jwt.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    public Usuario signInAndReturnJWT(Usuario signInRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword())
        );

        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        String jwt = jwtProvider.generateToken(userPrinciple);

        Usuario signInUser = userPrinciple.getUser();
        signInUser.setToken(jwt);

        return signInUser;
    }
}