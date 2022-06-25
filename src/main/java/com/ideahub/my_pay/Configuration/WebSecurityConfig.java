package com.ideahub.my_pay.Configuration;


import com.ideahub.my_pay.Services.Authentication.InfoUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private InfoUserDetailsService infoUserDetailsService;
//    @Autowired
//    private JwtTokenUtil jwttokanutil;
//@Override
//public void configure(WebSecurity web) throws Exception {
//    web.ignoring().antMatchers("login");
//}

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().authorizeRequests()

                .antMatchers(HttpMethod.POST,"/register", "/sample","/login").permitAll()
                .antMatchers(HttpMethod.GET,"/", "/home","/addphone","/hello","/verification")

                .permitAll()
                .anyRequest().authenticated()



                .and()
                .logout()
                .permitAll();
//        http.addFilterBefore(new JwtAuthenticationFilter(authenticationManagerBean(), JwtTokenUtil(),
//                infoUserDetailsService), UsernamePasswordAuthenticationFilter.class).antMatcher("login");
    }
    @Bean
    public PasswordEncoder ff() {
        return new BCryptPasswordEncoder();
    }
    PasswordEncoder pass = new BCryptPasswordEncoder();
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(infoUserDetailsService).passwordEncoder(pass);
    }
    @Bean
    public JwtTokenUtil JwtTokenUtil() {
        return new JwtTokenUtil();
    }






}