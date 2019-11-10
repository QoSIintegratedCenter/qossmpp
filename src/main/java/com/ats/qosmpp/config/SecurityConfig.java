package com.ats.qosmpp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Value("${app.auth_password:QosICats21}")
    String authPassword;
    @Autowired
    @Qualifier("datasource")
    private DataSource dataSource;


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests().antMatchers("/bootstrap/**", "/dist/**", "/plugins/**", "/admin/**", "/request/**", "/responses/**", "/logfile/download", "/fontAwesome/**", "/webjars/**").permitAll()
                .and().authorizeRequests()
                .antMatchers("/api/**")
                .authenticated()
                .and()
                .httpBasic().and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                /*authorizeRequests()
                        .antMatchers("/api/**").hasRole("USER")
                        .antMatchers("/admin").hasRole("ADMIN")*/
                .and()

                .csrf().disable();
        http.csrf().disable();
        http.headers().frameOptions().disable();
        ;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
//        auth.authenticationProvider(authProvider);
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username,password,1 as enable from qos_smpp_users where username=?").authoritiesByUsernameQuery(
                "select username,password, status from qos_smpp_users where username=?");

    }

    /* @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.inMemoryAuthentication()
                .withUser("qos_smpp").password(authPassword).roles("USER")
                .and()
                .withUser("superadmin").password("ats").roles("USER", "ADMIN");
    }*/

}
