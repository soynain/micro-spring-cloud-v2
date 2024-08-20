package com.usermicroservice.main.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usermicroservice.main.DTO.UserLoginDTO;
import com.usermicroservice.main.Entities.User;
import com.usermicroservice.main.Repositories.UserRepository;
import com.usermicroservice.main.Services.UserServiceInterface;

@Service
public class ServiceHandleImpl implements UserServiceInterface{

    @Autowired
    UserRepository userRepository;

    @Override
    public User signInUser(User userDTO) {
        return userRepository.save(userDTO);
    }

    @Override
    public User login(UserLoginDTO userBody) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }
    
}
