package com._hackaton._back.repository;

import com._hackaton._back.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


//CRUD 함수를 기본적으로 JpaRepository가 들고 있음
// @Repository 없어도 됨. JpaRepository가 들고 있기 때문
public interface UserRepository extends JpaRepository<User,Integer> {

    //findBy 규칙 -> Username 문법
    // select * from user where username = :username
    public User findByUsername(String username); //JPA Query Method

}
