package com.cherryj.rentbill.domain;


import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {

    UserAccount findByUserName(String userName);
}