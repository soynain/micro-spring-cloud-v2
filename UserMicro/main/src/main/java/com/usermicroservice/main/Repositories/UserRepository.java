package com.usermicroservice.main.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usermicroservice.main.Entities.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

}
