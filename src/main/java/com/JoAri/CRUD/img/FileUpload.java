package com.JoAri.CRUD.img;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.springframework.web.multipart.MultipartFile;

public class FileUpload {

    private static final String UPLOAD_PATH = "C:\\Work\\temporary\\File_Practice\\"; // ���ε��� ���� ���

    public void fileUpload(MultipartFile file, String savedFileName) {
        if (!file.isEmpty()) {
            try {
                Path uploadPath = new File(UPLOAD_PATH + savedFileName).toPath();
                Files.copy(file.getInputStream(), uploadPath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                // ���� ó��
            }
        }
    }

    public String getUploadPath() {
        return UPLOAD_PATH;
    }
}
