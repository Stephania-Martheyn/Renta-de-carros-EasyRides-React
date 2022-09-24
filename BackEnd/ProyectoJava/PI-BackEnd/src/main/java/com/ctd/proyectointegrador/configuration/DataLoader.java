package com.ctd.proyectointegrador.configuration;

import com.ctd.proyectointegrador.enums.Role;
import com.ctd.proyectointegrador.persistance.model.jwt.Usuario;
import com.ctd.proyectointegrador.persistance.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    UsuarioRepository repository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pwd = encoder.encode("grupo11");

        repository.save(new Usuario("Belen", "Risso", "grupo11@digital.com", pwd, "San Miguel del Monte", Role.ADMIN));
    }
}
