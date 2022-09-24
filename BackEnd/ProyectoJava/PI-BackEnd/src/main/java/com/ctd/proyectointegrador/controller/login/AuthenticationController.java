package com.ctd.proyectointegrador.controller.login;

import com.ctd.proyectointegrador.persistance.dto.UsuarioDTO;
import com.ctd.proyectointegrador.persistance.model.jwt.Usuario;
import com.ctd.proyectointegrador.service.impl.AuthenticationService;
import com.ctd.proyectointegrador.service.impl.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UsuarioService userService;

    @PostMapping("register")
    public ResponseEntity<?> signUp(@RequestBody UsuarioDTO user) {
        if(userService.findByUserEmail(user.getEmail()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return  new ResponseEntity<>(userService.guardar(user), HttpStatus.CREATED);
    }

    @PostMapping("login")
    public ResponseEntity<?> signIn(@RequestBody Usuario user) {
        return  new ResponseEntity<>(authenticationService.signInAndReturnJWT(user), HttpStatus.OK);
    }
}