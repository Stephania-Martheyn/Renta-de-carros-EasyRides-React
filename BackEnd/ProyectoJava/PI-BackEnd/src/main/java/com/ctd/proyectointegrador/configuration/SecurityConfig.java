package com.ctd.proyectointegrador.configuration;

import com.ctd.proyectointegrador.configuration.jwt.JwtAuthorizationFilter;
import com.ctd.proyectointegrador.enums.Role;
import com.ctd.proyectointegrador.service.impl.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManagerBean() throws Exception { //ni idea
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors(); //cors origin resource sha
        http.csrf().disable(); //cors side request forgery
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //ni idea

        http.authorizeRequests()
                .antMatchers(HttpMethod.POST,"/auth/**").permitAll()
                .antMatchers(HttpMethod.GET,"/**").permitAll()
                .antMatchers(HttpMethod.GET, "/reservas", "/reservas/**").hasAnyRole(Role.USER.name(), Role.ADMIN.name())
                .antMatchers(HttpMethod.POST, "/productos/**", "/categorias/**"
                        , "/ciudades/**", "/caracteristicas/**",
                        "/imagenes/**").hasAnyRole(Role.ADMIN.name())
                .antMatchers(HttpMethod.PUT, "/productos/**", "/categorias/**"
                        , "/ciudades/**", "/caracteristicas/**",
                        "/imagenes/**", "/reservas/**").hasAnyRole(Role.ADMIN.name())
                .antMatchers(HttpMethod.DELETE, "/productos/**", "/categorias/**"
                        , "/ciudades/**", "/caracteristicas/**",
                        "/imagenes/**", "/reservas/**").hasAnyRole(Role.ADMIN.name())
                .anyRequest().authenticated();
        http.addFilterBefore(jwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter() {
        return  new JwtAuthorizationFilter();
    } //ignorar
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("*")
                        .allowedHeaders("Access-Control-Allow-Origin", "*");
            }
        };
    }

    /*agregar al metodo post aparte de auth/** , el endpoint de usuarioController y agrega referencia a endpoint
del metodo buscar por ciudad y por fechas ..
n: .antMatchers(HttpMethod.POST, "/auth/**", "/usuarios/**","/api/v1/search-filter/**").permitAll()

 hace un permit all de producto, categoria, ciudad politicas etc
tenemos

metodo post de producto, categoria, rol a hasanyrol("ADMIN")
 .antMatchers(HttpMethod.POST, "productos/**", "categorias/**"
                        , "/ciudades/**", "/caracteristicas/**",
                        , "/api/v1/role/**").hasAnyRole("ADMIN")
metodo put  de todo a admin
.antMatchers(HttpMethod.PUT, "/productos**", "/categorias/**"
                        , "/ciudades/**", "/caracteristicas/**",
                        , "/api/v1/role/**", "/usuarios/**").hasAnyRole("ADMIN")

metodo delete de todo a admin

metodo get de usuario y rol a  admin

metodo post de reserva y favorito a admin y usuario

metodo put de reserva y favorito a admin y user
*/


}
