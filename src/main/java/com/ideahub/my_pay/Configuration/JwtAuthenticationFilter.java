package com.ideahub.my_pay.Configuration;

import com.ideahub.my_pay.Entity.Customer;
import com.ideahub.my_pay.Services.Authentication.InfoUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter  extends BasicAuthenticationFilter {
    @Autowired
    private JwtTokenUtil jwttokanutil;
    @Autowired
    private InfoUserDetailsService infoUserDetailsService;
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager,
                                   JwtTokenUtil jwtTokenUtil, InfoUserDetailsService infoDetailsService){
        super(authenticationManager);
        this.jwttokanutil = jwtTokenUtil;
        this.infoUserDetailsService = infoDetailsService;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");
        System.out.println(header);
        System.out.println("keerthyfilter");
        if (header == null || !header.startsWith("Bearer")) {
           System.out.println("header is null");
        }

        String token = header.substring(7);
        Customer customer = jwttokanutil.getCustomer(token);


        UserDetails userDetails = infoUserDetailsService.loadUserByUsername(customer.getPhone());

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }

        filterChain.doFilter(request, response);
    }

}
