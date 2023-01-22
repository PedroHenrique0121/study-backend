package com.pedro.study.config;


import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableResourceServer
@AllArgsConstructor
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    private TokenStore tokenStore;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(tokenStore);

    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()

                .antMatchers("/api/users/**").permitAll()
                .antMatchers(HttpMethod.POST,"/api/disciplinas/cadastrar").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.DELETE,"/api/disciplinas/**").hasAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.PUT,"/api/disciplinas/**").hasAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.GET,"/api/disciplinas/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")

                .antMatchers("/api/authorization/**").permitAll()
                .antMatchers("/api/roles/**").permitAll()
                .anyRequest().denyAll();

    }
}
