package com.ideahub.my_pay.Controller;

import com.ideahub.my_pay.Setting.GenericResponse;
import com.ideahub.my_pay.Setting.GenericResponseBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class HomeController {
    @GetMapping("/home2")
    public String home(){
        return "Spring Master Keerthy";
    }
//    @GetMapping("/login")
//    public GenericResponse homef( HttpServletRequest request){
//        String b = (String)request.getSession().getAttribute("NOTES_SESSION");
//        System.out.println(b);
//        return GenericResponse.builder().isSuccess(true).msg("ggggggg").build();
//    }




}

