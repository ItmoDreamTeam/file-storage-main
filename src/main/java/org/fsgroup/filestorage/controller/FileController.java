package org.fsgroup.filestorage.controller;

import org.fsgroup.filestorage.exception.file.FileDownloadException;
import org.fsgroup.filestorage.service.FileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/user/{username}/file")
@CrossOrigin
public class FileController {

    @Resource
    private FileService fileService;

    @PostMapping
    public ResponseEntity upload(@PathVariable String username, @RequestParam MultipartFile file) {
        fileService.upload(username, file);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/{fileId}")
    public ResponseEntity download(HttpServletResponse response, @PathVariable int fileId) {
        try {
            fileService.download(fileId, response.getOutputStream());
            response.flushBuffer();
        } catch (Exception e) {
            throw new FileDownloadException();
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{fileId}")
    public ResponseEntity delete(@PathVariable String username, @PathVariable int fileId) {
        fileService.delete(username, fileId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
