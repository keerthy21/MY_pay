package com.ideahub.my_pay.Services.Authentication;

import com.ideahub.my_pay.Entity.Customer;
import com.ideahub.my_pay.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class InfoUserDetailsService implements UserDetailsService {
    @Autowired
    private CustomerRepository customerrepo;
    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        Customer customer = customerrepo.findByPhone(phone);

        if (customer == null) {
            throw new UsernameNotFoundException("Could not find customer");
        }
        return  new InfoUserDetails(customer);
    }




    }


