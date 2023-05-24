package com.example.lab_56.AOP;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.checkerframework.checker.lock.qual.EnsuresLockHeld;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Component
@Aspect
public class Aspects {
    private boolean validatePage(Long val){
        return !(val == null || val < 0);
    }

    private boolean validateLimit(Long val){
        return !(val == null || val < 5);
    }

    @Around("execution(* *(..)) && @within(org.springframework.web.bind.annotation.RestController)")
    public Object validationAdvice(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();

        String[] names = signature.getParameterNames();
        Object[] args = pjp.getArgs();

        for(int i = 0; i< names.length; i++){
            String name = names[i];

            if(name.equals("page") && !validatePage((Long) args[i]))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Page param invalid!");

            if(name.equals("limit") && !validateLimit((Long) args[i]))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Limit param invalid!");
        }
        Object result = pjp.proceed();

        if(result == null || List.class.isAssignableFrom(result.getClass()) && ((List<?>) result).size() == 0){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }

        return result;
    }
}
