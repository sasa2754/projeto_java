package com.projeto.demo.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.projeto.demo.services.BcryptService;

public class BcryptImpl implements BcryptService{

  @Override
  public String encode(String password) {
    var encoder = new BCryptPasswordEncoder();
    return encoder.encode(password);
  }

  @Override
  public Boolean math(String password, String encodePassword) {
    var bcrypt = new BCryptPasswordEncoder();
    return bcrypt.matches(password, encodePassword);
  }
  
}
