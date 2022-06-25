package com.ideahub.my_pay.Configuration;

import com.ideahub.my_pay.Setting.Constant;
import com.ideahub.my_pay.Setting.GenericResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class InfoAuthenticationEntryPoint  implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        String message = GenericResponse.builder().status(Constant.HTTP_RESULT_FAILED).msg("Unauthorize")
                .statusCode(Constant.HTTP_UNAUTHORIZED_CODE).isSuccess(Constant.HTTP_RESULT_FAILED_BOOL)
                .error(authException.toString()).toString();
        response.setStatus(Constant.HTTP_UNAUTHORIZED_CODE);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(message);
    }
}