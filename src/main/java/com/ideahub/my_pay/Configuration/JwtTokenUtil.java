package com.ideahub.my_pay.Configuration;

import com.ideahub.my_pay.Entity.Customer;
import com.ideahub.my_pay.Repository.CustomerRepository;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.SignatureException;
import java.util.Date;
import java.util.Optional;

public class JwtTokenUtil {
    private String jwtSecret = "keerthy";


    private static final long serialVersionUID = -2550185165626007488L;

    public static final long tokenValidity = 5000* 60 * 60;
    @Autowired
    private CustomerRepository customrepo;
    public Customer getCustomer(final String token) {
        try {
            System.out.println("DSFdffggfgfdgfd"+token);
            Claims body = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
            System.out.println("Subject  "+body.getSubject());
            Customer user = customrepo.findByPhone(body.getSubject());

             System.out.println("hhhhhhhhhhhhhhh"+user.getCustomer_id());
            if(user.getToken() != null && user.getToken().equals(token)) {
                return user;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage() + " => " + e);
        }
        return null;
    }

    public String generateToken(Customer user) {
        System.out.println(user);
        Claims claims = Jwts.claims().setSubject(user.getPhone());

        long nowMillis = System.currentTimeMillis();
        long expMillis = nowMillis + tokenValidity;
        Date exp = new Date(expMillis);

        return Jwts.builder().setClaims(claims).setIssuedAt(new Date(nowMillis))
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    }

//   public void validateToken(final String token) {
//        try {
//            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
//        } catch (SignatureException ex) {
//            throw new ApptimusAuthenticationExceptionHandler("Invalid JWT signature");
//       } catch (MalformedJwtException ex) {
//            throw new ApptimusAuthenticationExceptionHandler("Invalid JWT token");
//        } catch (ExpiredJwtException ex) {
//            throw new ApptimusAuthenticationExceptionHandler("Expired JWT token");
//        } catch (UnsupportedJwtException ex) {
//            throw new ApptimusAuthenticationExceptionHandler("Unsupported JWT token");
//        } catch (IllegalArgumentException ex) {
//            throw new ApptimusAuthenticationExceptionHandler("JWT claims string is empty.");
//        }
//    }
//
//    public boolean validateTokenBoolean(final String token) {
//        try {
//            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
//            return true;
//        } catch (SignatureException ex) {
//            return false;
//        } catch (MalformedJwtException ex) {
//            return false;
//        } catch (ExpiredJwtException ex) {
//            return false;
//        } catch (UnsupportedJwtException ex) {
//            return false;
//        } catch (IllegalArgumentException ex) {
//            return false;
//      }
//    }

}
