package com.ideahub.my_pay.Repository;

import com.ideahub.my_pay.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
   // @Query(value ="SELECT * FROM customer  WHERE customer.phone_number =:num",nativeQuery = true)
 // Optional<Customer> findbyphone(@Param("num") Integer num);
    Customer findByPhone(String phone );


}
