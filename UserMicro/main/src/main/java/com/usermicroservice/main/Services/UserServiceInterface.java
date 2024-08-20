package com.usermicroservice.main.Services;

import com.usermicroservice.main.DTO.UserLoginDTO;
import com.usermicroservice.main.Entities.User;

public interface UserServiceInterface {
    User signInUser(User userDTO);
    User login(UserLoginDTO userBody);
}
