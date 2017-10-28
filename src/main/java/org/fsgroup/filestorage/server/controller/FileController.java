package org.fsgroup.filestorage.server.controller;

import org.fsgroup.filestorage.server.exception.file.FileDownloadException;
import org.fsgroup.filestorage.server.service.FileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user/{username}/file")
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

    @PutMapping("/{fileId}")
    public ResponseEntity edit(@PathVariable int fileId, @RequestParam String name) {
        fileService.edit(fileId, name);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{fileId}")
    public ResponseEntity delete(@PathVariable String username, @PathVariable int fileId) {
        fileService.delete(username, fileId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
