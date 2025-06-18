package com.example.scanfile;

import io.sensesecure.clamav4j.ClamAV;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.nio.file.Path;

@SpringBootTest
class ScanfileApplicationTests {

    /*@Test
    void contextLoads() {
    }*/
    public static void main(String[] args) {

        try {
            ClamAV clamAV = null;
            InetSocketAddress socketAddress = null;
            InputStream inputStream = new FileInputStream("H:\\Documentation_Content_Ideas.docx");
            socketAddress = new InetSocketAddress("127.0.0.1", 8080);
            clamAV = new ClamAV(socketAddress,15000);
            String scanRes = clamAV.scan(inputStream);
            if (scanRes.equals("OK")) {
                System.out.println("Virus scan complete, File is safe.");
                //return "{\"FILE_STATUS\":\"SAFE\",\"MESSAGE\":\"No Malware Found by ClamAV\"}";
            } else {
                System.out.println("Malware is Found by ClamAV");
                //return "{\"FILE_STATUS\":\"UN_SAFE\",\"MESSAGE\":\"Malware is Found by ClamAV\"}";
            }
        } catch (Exception e) {
            e.printStackTrace();
            /*if(null != socketAddress && socketAddress.isUnresolved()){
                System.out.println("Error In Establishing Socket Connection");
                return "{\"FILE_STATUS\":\"UN_SAFE\",\"MESSAGE\":\"Error In Establishing Socket Connection\"}";
            }
            if(clamAV != null && !clamAV.ping()){
                System.out.println("ClamAV is Down");
                return "{\"FILE_STATUS\":\"UN_SAFE\",\"MESSAGE\":\"ClamAV is Down\"}";
            }else{
                System.out.println("Unable To Contact ClamAV");
                return "{\"FILE_STATUS\":\"UN_SAFE\",\"MESSAGE\":\"Unable To Contact ClamAV\"}";
            }*/
        }
    }
}
