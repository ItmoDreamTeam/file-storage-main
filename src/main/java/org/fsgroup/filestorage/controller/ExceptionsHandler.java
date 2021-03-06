package org.fsgroup.filestorage.controller;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.fsgroup.filestorage.exception.FileStorageException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ExceptionsHandler {

    @Around("execution(org.springframework.http.ResponseEntity org.fsgroup.filestorage.controller.*.*(..))")
    public ResponseEntity handleException(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return (ResponseEntity) joinPoint.proceed();
        } catch (FileStorageException e) {
            return new ResponseEntity<>(e.getErrorResponse(), e.getErrorResponse().httpStatus());
        }
    }
}
