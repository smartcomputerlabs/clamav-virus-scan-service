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
        //By default, the client will try to connect to the port 3310 which is the default ClamAV daemon port.
//      If your ClamAV daemon listens to another port, you can indicate it with:
//        ClamavClient client = new ClamavClient("localhost", 3311);
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