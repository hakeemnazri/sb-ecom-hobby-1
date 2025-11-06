package com.sb_hobby.ecom.auth.providers;

import com.sb_hobby.ecom.auth.services.AuthService;
import com.sb_hobby.ecom.auth.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationProvider {
    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    HashingProvider hashingProvider;

    public DaoAuthenticationProvider provideDaoAuth(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailsService);
        authProvider.setPasswordEncoder(hashingProvider.getBcryptPasswordEncoder());

        return authProvider;
    }
}
