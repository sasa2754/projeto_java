package com.projeto.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class Teste {

    @Autowired
    private UserImpl UserImpl;
    
    // Tem que ter 12 caracteres, precisa ter letra maiúscula e minúscula e número :)
    @Test
    public void TestPassword() {
        // Tem que chamar a classe que você criar, onde tem a função de validar a senha, é a mesma função que você vai implementar no código, então é a função que estver no impl
        // Chama com o autowired pra poder colocar ele no DependencyConfiguration :)

        assertFalse(UserImpl.checkPass("oi"));
        assertFalse(UserImpl.checkPass("oi123A"));
        assertFalse(UserImpl.checkPass("minhaSenhaMtFodaSuperBoaQNaoPassa"));
        assertFalse(UserImpl.checkPass("123"));
        assertFalse(UserImpl.checkPass("2345678909876543355"));
        assertFalse(UserImpl.checkPass("seraqessapassa123"));

        assertTrue(UserImpl.checkPass("EssaPassaComCerteza12345"));
        assertTrue(UserImpl.checkPass("Essatbmpassa000"));
        assertTrue(UserImpl.checkPass("minhasenhamtfodaA876"));
    }

    // Vendo se tem formato de email
    @Test
    public void TestEmail() {
        assertFalse(UserImpl.checkEmail("naoEumEmail"));
        assertFalse(UserImpl.checkEmail("naoEumEmail@"));
        assertFalse(UserImpl.checkEmail("naoEumEmail@aaaaaa"));
        assertFalse(UserImpl.checkEmail("naoEumEmail123"));
        assertFalse(UserImpl.checkEmail("aaaa2email.com"));
        assertFalse(UserImpl.checkEmail("n"));
        assertFalse(UserImpl.checkEmail("@a.com"));

        assertTrue(UserImpl.checkEmail("email.email@email.com"));
        assertTrue(UserImpl.checkEmail("essePassa123@email.com"));
    }

    // Só pra conferir se ele tem só números ou não
    @Test
    public void TestEDV() {
        assertFalse(UserImpl.cheackEDV("aaaaaaaa"));
        assertFalse(UserImpl.cheackEDV("aaaaaaaa123"));

        assertTrue(UserImpl.cheackEDV("123456"));
        assertTrue(UserImpl.cheackEDV("92901234"));
    }
}
