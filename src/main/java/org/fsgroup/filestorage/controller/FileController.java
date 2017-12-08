package org.fsgroup.filestorage.controller;

import org.fsgroup.filestorage.exception.FileStorageException;
import org.fsgroup.filestorage.service.FileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/files")
public class FileController {

    @Resource
    private FileService service;

    @PostMapping
    public ResponseEntity upload(Authentication auth, @RequestParam MultipartFile file) {
        service.upload(auth.getName(), file);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity download(HttpServletResponse response, Authentication auth, @PathVariable int id) {
        try {
            service.download(auth.getName(), id, response.getOutputStream());
            response.flushBuffer();
        } catch (Exception e) {
            throw new FileStorageException("Error while downloading file");
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(Authentication auth, @PathVariable int id) {
        service.delete(auth.getName(), id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
