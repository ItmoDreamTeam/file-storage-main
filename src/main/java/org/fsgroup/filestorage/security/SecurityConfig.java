package org.fsgroup.filestorage.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().and()

                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/signup").permitAll()

                .antMatchers("/user/{username}/file/{fileId}")
                .access("@userAuthService.isAuthorized(authentication, #username, #fileId)")

                .antMatchers("/user/{username}/**")
                .access("@userAuthService.isAuthorized(authentication, #username)")

                .anyRequest().denyAll()
                .and().httpBasic()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}