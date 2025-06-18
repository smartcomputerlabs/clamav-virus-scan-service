package org.javacommunity.scan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.javacommunity.scan.service.VirusScanService;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;

@RestController
public class VirusScanController {

    @Autowired
    VirusScanService virusScanService;
    //public VirusScanController(VirusScanService virusScanService) {
    //    this.virusScanService = virusScanService;    }


    @PostMapping(path = "/scan", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    @ResponseBody
    public String handleFileScan(@RequestPart List<MultipartFile> attachment) {
        if (attachment.size() > 1) {
            System.out.println(" number of files attached -- " + attachment.size());
            return "{\"FILE_STATUS\":\"INFECTED\",\"MESSAGE\":\"Only One File Allowed\"}";
        } else {
            MultipartFile file = attachment.get(0);
            System.out.println(" file type -- " + file.getContentType());
            System.out.println(" file type -- " + file.getSize());
            if (file.isEmpty()) {
                return "{\"FILE_STATUS\":\"INFECTED\",\"MESSAGE\":\"No File Attached Or Invalid File\"}";
            } else if (!file.getContentType().equals("application/pdf")) {
                //Files.probeContentType();
                return "{\"FILE_STATUS\":\"INFECTED\",\"MESSAGE\":\"Only PDF file is Allowed\"}";
            } else if (file.getSize() > 10000000l) {//in bytes
                //Files.probeContentType();
                return "{\"FILE_STATUS\":\"INFECTED\",\"MESSAGE\":\"File Size Exceeds Maximum Allowed Size\"}";
            }
            try {
                InputStream inputStream = file.getInputStream();
                System.out.println("Start scan for - " + file.getOriginalFilename());
                return virusScanService.isClean(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
                return "{\"FILE_STATUS\":\"INFECTED\",\"MESSAGE\":\"Malformed File\"}";
            }
        }
    }

}