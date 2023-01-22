package com.pedro.study.config;

import com.pedro.study.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.server.authorization.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenCustomizer;

@Configuration
public class JwtTokenCustomizerConfig {

    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext> jwtEncodingContextOAuth2TokenCustomizer(UserRepository userRepository){
        return (context -> {
            Authentication authentication = context.getPrincipal();

                final User user = (User) authentication.getPrincipal();

                final com.pedro.study.model.User userEntity = userRepository.findByLogin(user.getUsername()).orElseThrow(
                        ()->{
                            return new RuntimeException("Usuario não encontrado");
                        }
                );

//                Set<String> authorities = new HashSet<>();
//                for (GrantedAuthority authority : user.getAuthorities()) {
//                    authorities.add(authority.toString());
//                }
                context.getClaims().claim("user_id", userEntity.getId().toString());
//                context.getClaims().claim("user_fullname", userEntity.getName());
//                context.getClaims().claim("authorities", authorities);


        });
    }
}


