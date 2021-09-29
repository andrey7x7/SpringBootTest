package com.example.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password("admin").roles("ADMIN");
                //.and()

        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(
                        "select name as username, password, 1 from user " +
                                "where name=?")
                .authoritiesByUsernameQuery(
                        "select name as username, role as authority from user " +
                                "where name=?")
                .rolePrefix("ROLE_");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                    //.antMatchers("/**").permitAll()
                    .antMatchers("/").permitAll()
                    .antMatchers("/admin/**").hasAnyRole("ADMIN")
                    .antMatchers("/user").hasAnyRole("USER", "ADMIN")
                    .antMatchers("/user/**").hasRole("ADMIN")
                .and()
                    .formLogin()
                    .permitAll()
                .and()
                    .logout()
                    .permitAll();
    }

    @Bean
    public PasswordEncoder encoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}