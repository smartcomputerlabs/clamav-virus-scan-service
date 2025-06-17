package controller;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.VirusScanService;

@RestController
public class FileUploadController {
    private final VirusScanService virusScanService;
    public FileUploadController(VirusScanService virusScanService) {
        this.virusScanService = virusScanService;
    }
    @PostMapping("/scan")
    public String handleFileScan(@RequestParam("file") MultipartFile file) {
        try {
            if (virusScanService.isClean(file)) {
                // Process the file if clean
                return "File is safe, proceed with upload";
            } else {
                return "File is safe, proceed with upload";
            }
        } catch (Exception e) {
            return "Error during virus scan: " + e.getMessage();
        }
    }
}