package com.usermicroservice.main.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.usermicroservice.main.DAO.UserCreatedDAO;
import com.usermicroservice.main.Entities.User;
import com.usermicroservice.main.ServiceImpl.ServiceHandleImpl;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/mx")
public class UserCredentials {

    @Autowired
    ServiceHandleImpl userServiceHandleImpl;
    
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(RequestBody requestBody){
        return ResponseEntity.ok().body("HAS INICIADO SESION");
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
