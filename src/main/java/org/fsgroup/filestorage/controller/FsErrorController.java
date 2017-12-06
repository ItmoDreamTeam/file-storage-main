package org.fsgroup.filestorage.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FsErrorController implements ErrorController {

    private static final String ERROR_PATH = "/error";

    @RequestMapping(ERROR_PATH)
    public ResponseEntity error() {
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
