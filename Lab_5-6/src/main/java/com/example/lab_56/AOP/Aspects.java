package com.example.lab_56.AOP;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Aspects {
    private Long validatePage(Long val){
        if(val == null || val < 0)
            return 0L;
        return val;
    }

    private Long validateLimit(Long val){
        if(val == null || val < 5)
            return 20L;
        return val;
    }

    @Around("execution(* *(..)) && @within(org.springframework.web.bind.annotation.RestController)")
    public Object validationAdvice(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();

        String[] names = signature.getParameterNames();
        Object[] args = pjp.getArgs();

        for(int i = 0; i< names.length; i++){
            String name = names[i];

            if(name.equals("page"))
                args[i] = validatePage((Long) args[i]);

            if(name.equals("limit"))
                args[i] = validateLimit((Long) args[i]);
        }
        return pjp.proceed(args);
    }
}
