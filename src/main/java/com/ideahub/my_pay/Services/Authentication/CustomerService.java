package com.ideahub.my_pay.Services.Authentication;

import com.ideahub.my_pay.Entity.Customer;
import com.ideahub.my_pay.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository custom_repo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public void saveCustomer(Customer customer){
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        custom_repo.save(customer);
    }


    public Customer updatetokan(Integer customer_id, String token) {
       Customer cus = custom_repo.findById(customer_id).orElse(null);
        cus.setToken(token);
        return custom_repo.save(cus);
    }
}
