package com.ats.qosmpp.config;

import com.ats.qosmpp.domain.User;
import com.ats.qosmpp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String userName = authentication.getName();
        String password = authentication.getCredentials().toString();

        User user = userService.findByUserName(userName);

        List<GrantedAuthority> authorities = new ArrayList<>();
        System.out.println(user);
        try {
            user.getRoles().stream().forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(role.getType()));
            });
        } catch (Exception e){
//            new AuthenticationException("Nom ");
        }

//        authorities.add(new SimpleGrantedAuthority(user.getRoles())); // description is a string
       /* if (shouldAuthenticateAgainstThirdPartySystem()) {

            // use the credentials
            // and authenticate against the third-party system
            return new UsernamePasswordAuthenticationToken(
                    name, password, new ArrayList<>());
        } else {
            return null;
        }*/
        return new UsernamePasswordAuthenticationToken(userName, password, new ArrayList<>());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}