package com.usermicroservice.main.ServiceImpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.usermicroservice.main.DTO.UserLoginDTO;
import com.usermicroservice.main.Entities.User;
import com.usermicroservice.main.Repositories.UserRepository;
import com.usermicroservice.main.Services.UserServiceInterface;

@Service
public class ServiceHandleImpl implements UserServiceInterface,UserDetailsService{

    @Autowired
    UserRepository userRepository;

    @Override
    public User signInUser(User userDTO) {
        return userRepository.save(userDTO);
    }

    @Override
    public User login(UserLoginDTO userBody) {
        User user = userRepository.getByEmail(userBody.getEmail());
        if (user == null) {
            throw new UsernameNotFoundException("El usuario con el correo " + userBody.getEmail() + " no existe");
        }

        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("El usuario con el correo " + username + " no existe");
        }

        return new org.springframework.security.core.userdetails.User(
            user.getEmail(),
            user.getContra(),
            new ArrayList<>()
        );
    }
    
}
