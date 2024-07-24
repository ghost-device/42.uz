package uz.web.service.fileUpload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileService {
    CloudService cloudService;

    public String uploadFile(MultipartFile file) throws IOException {
         String contentType = file.getContentType();
         if(contentType != null){
             return cloudService.uploadFile(file);
         }
         throw new IllegalArgumentException("Unsupported content type");
    }

    public String getFileUrl(String fileType, String fileName ) {
         return cloudService.getFileUrl(fileName);
    }
}