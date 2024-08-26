package com.usermicroservice.main.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.usermicroservice.main.Components.JwtGenerator;
import com.usermicroservice.main.DAO.UserCreatedDAO;
import com.usermicroservice.main.Entities.User;
import com.usermicroservice.main.ServiceImpl.ServiceHandleImpl;

import jakarta.validation.Valid;

import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Map;
import java.net.http.HttpHeaders;
import java.util.HashMap;


@RestController
@RequestMapping("/api/v1/mx")
public class UserCredentials {

    public Logger log = org.slf4j.LoggerFactory.getLogger(getClass());

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    ServiceHandleImpl userServiceHandleImpl;

    @Autowired
    private JwtGenerator jwtTokenProvider;
    
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody Map<String, Object> requestBody){
        try {
            
            log.info(requestBody.toString());
             org.springframework.security.core.Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(requestBody.get("email"), requestBody.get("contra"))
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String jwt = jwtTokenProvider.generateToken(userDetails); 

            // Retornar el JWT

            return ResponseEntity.ok()
            .header(org.springframework.http.HttpHeaders.AUTHORIZATION, "Bearer " + jwt)
            .build();
        } catch (BadCredentialsException ex) {
            log.error(null, ex);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario o contraseña incorrectos");
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> postMethodName(@Valid @RequestBody User userPost) {
        User user = userServiceHandleImpl.signInUser(userPost);
        if(user!=null){
            return ResponseEntity.ok().body(new UserCreatedDAO(user.getNombre(),user.getEmail(),"SE HA CREADO SU USUARIO CON ÉXITO"));
        }else{
            return ResponseEntity.badRequest().body("Hubo un error al crear el usuario");
        }
    }

}
