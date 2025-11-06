package com.sb_hobby.ecom.auth.services;

import com.sb_hobby.ecom.auth.providers.AuthenticationProvider;
import com.sb_hobby.ecom.auth.providers.HashingProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    AuthenticationProvider authenticationProvider;

    @Autowired
    @Qualifier("jwtAuthEntryPoint")
    AuthenticationEntryPoint authenticationEntryPoint;

    public DaoAuthenticationProvider getAuthProvider(){
        return authenticationProvider.provideDaoAuth();
    }

    public AuthenticationEntryPoint getAuthEntryPoint(){
        return authenticationEntryPoint;
    }

}
