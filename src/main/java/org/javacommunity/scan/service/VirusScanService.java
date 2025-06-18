package org.javacommunity.scan.service;

import io.sensesecure.clamav4j.ClamAV;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.InetSocketAddress;

@Service
public class VirusScanService {
    public String isClean(InputStream inputStream) {
        ClamAV clamAV = null;
        InetSocketAddress socketAddress = null;
        try {
            socketAddress = new InetSocketAddress("127.0.0.1", 3310);
            clamAV = new ClamAV(socketAddress,15000);
            String scanRes = clamAV.scan(inputStream);
            if (scanRes.equals("OK")) {
                System.out.println("Virus scan complete, File is safe.");
                return "{\"FILE_STATUS\":\"SAFE\",\"MESSAGE\":\"No Malware Found by ClamAV\"}";
            } else {
                System.out.println("Malware is Found by ClamAV");
                return "{\"FILE_STATUS\":\"UN_SAFE\",\"MESSAGE\":\"Malware is Found by ClamAV\"}";
            }
        } catch (Exception e) {
            e.printStackTrace();
            if(null != socketAddress && socketAddress.isUnresolved()){
                System.out.println("Error In Establishing Socket Connection");
                return "{\"FILE_STATUS\":\"UN_SAFE\",\"MESSAGE\":\"Error In Establishing Socket Connection\"}";
            }
            if(clamAV != null && !clamAV.ping()){
                System.out.println("ClamAV is Down");
                return "{\"FILE_STATUS\":\"UN_SAFE\",\"MESSAGE\":\"ClamAV is Down\"}";
            }else{
                System.out.println("Unable To Contact ClamAV");
                return "{\"FILE_STATUS\":\"UN_SAFE\",\"MESSAGE\":\"Unable To Contact ClamAV\"}";
            }
        }
    }
}