package com.daniil1380.coursedatabase;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class WebSecuritySetting extends WebSecurityConfigurerAdapter{

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().
                withUser("Tkachenko1380").password(encoder().encode("Daniil1380")).roles("USER")
                .and().
                withUser("root").password(encoder().encode("root")).roles("CHIEF")
                .and()
                .withUser("user").password(encoder().encode("Nizhnevartovsk")).roles("USER")
                .and()
                .withUser("admin").password(encoder().encode("dutyFr33!")).roles("CHIEF");

    }


    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/operations/").hasRole("CHIEF")
                .antMatchers(HttpMethod.DELETE, "/operations/").hasRole("CHIEF")
                .antMatchers("/brokers/").hasRole("CHIEF")
                .antMatchers("/clients/").hasRole("CHIEF")
                .antMatchers("/holidays/").hasRole("CHIEF")
                .antMatchers("/holidaysStockExchange/").hasRole("CHIEF")
                .antMatchers("/rates/").hasRole("CHIEF")
                .antMatchers("/shares/").hasRole("CHIEF")
                .antMatchers("/stockExchanges/").hasRole("CHIEF")
                .antMatchers(HttpMethod.GET, "/accounts/").hasRole("CHIEF")
                .antMatchers(HttpMethod.DELETE, "/accounts/").hasRole("CHIEF")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .logout();
    }
}
