package com.projeto.demo.services;

public interface  BcryptService {
  public String encode(String password);
  public Boolean  math(String password,String encodePassword);
}
