package com.ideahub.my_pay.Controller.Authentication;


import com.ideahub.my_pay.Configuration.JwtTokenUtil;
import com.ideahub.my_pay.Entity.Authentication.Jwttokan;
import com.ideahub.my_pay.Entity.Customer;
import com.ideahub.my_pay.Entity.Test;
import com.ideahub.my_pay.Repository.CustomerRepository;
import com.ideahub.my_pay.Services.Authentication.CustomerService;
import com.ideahub.my_pay.Setting.CodeGenerator;
import com.ideahub.my_pay.Setting.Constant;
import com.ideahub.my_pay.Setting.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class AuthenticationController {
    @Autowired
    private CustomerRepository custom_repo;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomerService customerservice;
    @Autowired
    private JwtTokenUtil jwttokanutil;

    @GetMapping("/addphone")
    public GenericResponse addphone(@RequestParam String num, HttpServletRequest request) {


        Customer cus = custom_repo.findByPhone(num);
       //Customer cus=  custom_repo.findByPhone_number(bb);

        if (cus!=null) {
            Customer ust2 = cus;
            System.out.println(ust2.getCustomer_id());
            return GenericResponse.builder().status(Constant.HTTP_RESULT_FAILED)
                    .msg("number already exists in the database").statusCode(Constant.HTTP_BAD_REQUEST_CODE)
                    .isSuccess(Constant.HTTP_RESULT_FAILED_BOOL).error("Phone number already exists ").build();
        } else {
            System.out.println("keerthy");
            request.getSession().setAttribute("Phone_Number", num);
            String b = request.getSession().getAttribute("Phone_Number").toString();
            System.out.println(b);
            CodeGenerator codegen = new CodeGenerator();
            String code = codegen.createSMSCode();
            request.getSession().setAttribute("code", code);

            return GenericResponse.builder().status(Constant.HTTP_RESULT_SUCCESS)
                    .msg("add new number").statusCode(Constant.HTTP_SUCCESS_CODE)
                    .isSuccess(Constant.HTTP_RESULT_SUCCESS_BOOL).error("Enter the Verification code").build();
        }


    }

    @GetMapping("/verification")
    public GenericResponse codeverification(@RequestParam String code, HttpServletRequest request) {
        try {
            String ab = request.getSession().getAttribute("code").toString();
            System.out.println(ab);
            System.out.println(code);
            if (ab.equals(code)) {
                System.out.println("Code is corect");

                return GenericResponse.builder().status(Constant.HTTP_RESULT_SUCCESS)
                        .msg("Enter Your Registration Details").statusCode(Constant.HTTP_SUCCESS_CODE)
                        .isSuccess(Constant.HTTP_RESULT_SUCCESS_BOOL).build();
            } else {
                System.out.println("Code is wrong");
                System.out.println(ab);
                System.out.println(code);
                return GenericResponse.builder().status(Constant.HTTP_RESULT_FAILED)
                        .msg("Wrong Code").statusCode(Constant.HTTP_BAD_REQUEST_CODE)
                        .isSuccess(Constant.HTTP_RESULT_FAILED_BOOL).error("Code is Wrong Try Again ").build();
            }
        } catch (Exception e) {
            System.out.println("badrequest");
            return GenericResponse.builder().status(Constant.HTTP_RESULT_FAILED)
                    .msg("Bad Request").statusCode(Constant.HTTP_BAD_REQUEST_CODE)
                    .isSuccess(Constant.HTTP_RESULT_FAILED_BOOL).error("Bad Request try again").build();
        }
    }
    @PostMapping("/login")
    public GenericResponse  login(@RequestBody Jwttokan jwtokan) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtokan.getPhone(), jwtokan.getPassword()));
            System.out.println("login success");
        } catch (Exception e) {
            System.out.println("hello jhj");
            return GenericResponse.builder().status(Constant.HTTP_RESULT_FAILED)
                    .msg("Invalid Username or password").statusCode(Constant.HTTP_BAD_REQUEST_CODE)
                    .isSuccess(Constant.HTTP_RESULT_FAILED_BOOL).error("Invalid Username or password").build();
        }
        Customer cus = custom_repo.findByPhone(jwtokan.getPhone());
        String token = jwttokanutil.generateToken(cus);
      Customer toancus =  customerservice.updatetokan(cus.getCustomer_id(),token);
    System.out.println(toancus.getToken());

        return GenericResponse.builder().status(Constant.HTTP_RESULT_SUCCESS)
                .msg("Enter Your Registration Details").statusCode(Constant.HTTP_SUCCESS_CODE)
                .isSuccess(Constant.HTTP_RESULT_SUCCESS_BOOL).data(toancus).build();

    }

    @PostMapping("/register")
    public GenericResponse register(@RequestBody @Valid  Customer customer, @RequestParam String code, HttpServletRequest request) {
       try {
      String ab = request.getSession().getAttribute("code").toString();
     String b =  request.getSession().getAttribute("Phone_Number").toString();

           if(ab.equals(code)){
               System.out.println("customer");
               customerservice.saveCustomer(customer);

               return GenericResponse.builder().status(Constant.HTTP_RESULT_SUCCESS)
                       .msg("Registration Sucessfull").statusCode(Constant.HTTP_SUCCESS_CODE)
                       .isSuccess(Constant.HTTP_RESULT_SUCCESS_BOOL).build();
           }else{
               return GenericResponse.builder().status(Constant.HTTP_RESULT_FAILED)
                       .msg("Code is wrong").statusCode(Constant.HTTP_BAD_REQUEST_CODE)
                       .isSuccess(Constant.HTTP_RESULT_FAILED_BOOL).error("code is wrong").build();
           }


    }catch (Exception e){
           return GenericResponse.builder().status(Constant.HTTP_RESULT_FAILED)
                   .msg(e.getMessage()).statusCode(Constant.HTTP_BAD_REQUEST_CODE)
                   .isSuccess(Constant.HTTP_RESULT_FAILED_BOOL).error("Bad request").build();
       }
    }

    @GetMapping("/sample")
    public String getSample() {
        return "hiiiiii";
    }
}