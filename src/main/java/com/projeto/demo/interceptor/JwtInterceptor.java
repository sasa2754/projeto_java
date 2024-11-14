package com.projeto.demo.interceptor;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.projeto.demo.services.JWTService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtInterceptor extends OncePerRequestFilter{

    final JWTService JwtTokenService;

    public JwtInterceptor(JWTService JwtTokenService) {
        this.JwtTokenService = JwtTokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
      var jwt = getJwt(request);

      if (jwt == null)
        {
            filterChain.doFilter(request, response);
            return;
        }

        var token = JwtTokenService.validateJWT(jwt).getClaims();
        if (token == null)
        {
            filterChain.doFilter(request, response);
            return;
        }
        
        var authentication = new UsernamePasswordAuthenticationToken("jao", null, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        request.setAttribute("token", token.get("id"));
        filterChain.doFilter(request, response);
    }

    String getJwt(HttpServletRequest request) {
      String bearerToken = request.getHeader("Authorization");
      if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
          return bearerToken.substring(7);
      }
      return null;
  }
}
