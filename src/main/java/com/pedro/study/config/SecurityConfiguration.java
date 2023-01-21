package com.pedro.study.config;

import com.pedro.study.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//
//@EnableWebSecurity
//@AllArgsConstructor
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//    private UserService userService;
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .userDetailsService(userService)
//                .passwordEncoder(passwordEncoder());
////                .inMemoryAuthentication()
////                .withUser("fulano")
////                .password("123")
////                .roles("USER");
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
////    @Override
////    protected void configure(HttpSecurity http) throws Exception {
////        http
////                .csrf().disable()
////                .cors()
////                .and()
////                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
////    }
//
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }
//}
