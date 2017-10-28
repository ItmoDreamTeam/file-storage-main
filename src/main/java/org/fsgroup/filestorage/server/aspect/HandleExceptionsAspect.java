package org.fsgroup.filestorage.server.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.fsgroup.filestorage.server.exception.FileStorageException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class HandleExceptionsAspect {

    @Around("execution(org.springframework.http.ResponseEntity org.fsgroup.filestorage.server.controller.*.*(..))")
    public ResponseEntity handleException(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return (ResponseEntity) joinPoint.proceed();
        } catch (FileStorageException e) {
            return new ResponseEntity<>(e.getErrorMessage(), e.getErrorMessage().getError());
        }
    }
}
