package org.javacommunity.scan.service;

import io.sensesecure.clamav4j.ClamAV;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.InetSocketAddress;

@Service
public class VirusScanService {
    public boolean isClean(MultipartFile file) throws Exception {
        byte[] fileBytes = file.getBytes();
        InetSocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 8080);
        ClamAV clamAV = new ClamAV(socketAddress,15000);
        String scanRes = clamAV.scan(file.getInputStream());
        if (scanRes.equals("OK")) {
            return true;
        }else{
            return false;
        }
    }
}