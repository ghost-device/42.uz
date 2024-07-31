package uz.web.service;

import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.storage.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Service
public class CloudService {
    private final Storage storage;
    private final String bucketName;

    public CloudService() throws IOException {
        this.storage = StorageOptions.newBuilder()
                .setCredentials(ServiceAccountCredentials.fromStream(new FileInputStream("D:\\PDP\\Bootcamp\\Java\\JavaBackend(Jamshid aka)\\42.uz\\src\\main\\resources\\images-428112-d477a66b2fd3.json")))
                .build()
                .getService();
        this.bucketName = "42uzzz";
    }

    public String uploadFile(MultipartFile file) {
        String fileName = UserVerificationService.getUniqueCode();

        BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, fileName)
                .setContentType(file.getContentType()).build();

        try {
            storage.create(blobInfo, file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Video exception!");
        }

        return fileName;
    }

    public String getFileUrl(String fileName) {
        Bucket bucket = storage.get(bucketName);

        Blob blob = bucket.get(fileName);

        if (blob == null) {
            throw new RuntimeException("");
        }

        return blob.signUrl(30, TimeUnit.MINUTES).toString();
    }
}