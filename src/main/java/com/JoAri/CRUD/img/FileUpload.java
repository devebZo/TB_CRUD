package com.JoAri.CRUD.img;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import javax.servlet.ServletContext;

import org.springframework.web.multipart.MultipartFile;

public class FileUpload {

	private String UPLOAD_PATH = "/resources/images"; // 업로드할 파일 경로

	public void fileUpload(MultipartFile file, String savedFileName, ServletContext servletContext) {
		if (!file.isEmpty()) {
			try {
				String realPath = servletContext.getRealPath(UPLOAD_PATH);
				Path uploadPath = new File(realPath + File.separator + savedFileName).toPath();
				Files.copy(file.getInputStream(), uploadPath, StandardCopyOption.REPLACE_EXISTING);
				
				System.out.println("Real Path: " + realPath);

			} catch (IOException e) {
				e.printStackTrace(); // 예외 처리
			}
		}
	}

	public String getUploadPath() {
		return UPLOAD_PATH;
	}
}
