package com.RPL.SiapSidang;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import jakarta.servlet.http.HttpSession;
import java.util.Arrays;
import org.aspectj.lang.JoinPoint;

@Aspect
@Component
public class AuthorizationAspect {
    private HttpSession session;

    public AuthorizationAspect(HttpSession session){
        this.session = session;
    }

    @Before("@annotation(requiredRole)")
    public void checkAuthorization(JoinPoint joinPoint, RequiredRole requiredRole) throws Throwable{
        String nama = (String)session.getAttribute("nama");
        if(nama == null || nama.length() == 0){
            throw new Exception("Login Required!");
        }

        String peran = (String) session.getAttribute("peran");

        String[] roles = requiredRole.value();
        
        // if(Arrays.asList(roles).contains("*")){
        //     return;
        // }

        if(Arrays.asList(roles).contains(peran)){
            return;
        }
        else{
            throw new Exception("Wrong role!");
        }
    }    
}
