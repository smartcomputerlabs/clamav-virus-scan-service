package service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import xyz.capybara.clamav.ClamavClient;
import xyz.capybara.clamav.commands.scan.result.ScanResult;

import java.util.Collection;
import java.util.Map;

@Service
public class VirusScanService {
    private final ClamavClient clamAVClient;
    public VirusScanService(ClamavClient clamAVClient) {
        this.clamAVClient = clamAVClient;
    }
    public boolean isClean(MultipartFile file) throws Exception {
        byte[] fileBytes = file.getBytes();
        //return clamAVClient.scan(fileBytes).isClean();
        ScanResult scanRes = clamAVClient.scan(file.getInputStream());
        if (scanRes instanceof ScanResult.OK) {
            // OK
            return true;
        } else if (scanRes instanceof ScanResult.VirusFound) {
            Map<String, Collection<String>> viruses = ((ScanResult.VirusFound) scanRes).getFoundViruses();
            //logs virus found info
            //return viruses.containsKey(file.getOriginalFilename());
            return false;
        }
        return false;
    }
}