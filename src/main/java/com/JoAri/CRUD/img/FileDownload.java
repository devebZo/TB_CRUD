package com.JoAri.CRUD.img;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

@Component("ImgDownView")
public class FileDownload extends AbstractView {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		/*
		 * ModelAndView ��ü�� addObject( ) �ؼ� ���� ������ Map ��ü���� ���� �ִ�.
		 */
		@SuppressWarnings("unchecked")
		Map<String, Object> imgMap = (Map<String, Object>) model.get("dto");
		// ������ �ٿ�ε� �ϴ� �۾��� ���ش�.
//	    long fileSize=dto.getFileSize();
		String orgFileName = imgMap.get("realName").toString();
		String saveFileName = imgMap.get("saveName").toString();

		// �ٿ�ε� ������ ������ ���� ��� �����ϱ�
		// File.separator �� window ������ \ , linux ������ / �� ������ �ȴ�.
		String path = request.getRealPath("/resources/images/") + File.separator + saveFileName;
//		String path = "/resources/images/"+saveFileName;
		// �ٿ�ε��� ���Ͽ��� �о���� ��Ʈ�� ��ü �����ϱ�
		FileInputStream fis = new FileInputStream(path);
		// �ٿ�ε� �����ִ� �۾��� �Ѵ�. (���� ���� �����Ϳ� �������ϸ��� ��������Ѵ�.)
		// �ٿ�ε� �����ִ� �۾��� �Ѵ�.
		String encodedName = null;
		// �ѱ� ���ϸ� ����ó��
		if (request.getHeader("User-Agent").contains("whale")) {
			// �����簡 ���̾� �����ΰ��
			encodedName = new String(orgFileName.getBytes("utf-8"), "ISO-8859-1");
		} else { // �׿� �ٸ� ������
			encodedName = URLEncoder.encode(orgFileName, "utf-8");
			// ���ϸ� �������ִ� ��� ó��
			encodedName = encodedName.replaceAll("\\+", " ");
		}

		// ���� ��� ���� ����
		response.setHeader("Content-Disposition", "attachment;filename=" + encodedName);
		response.setHeader("Content-Transfer-Encoding", "binary");

		// �ٿ�ε��� ������ ũ�� �о�ͼ� �ٿ�ε��� ������ ũ�� ����
//		response.setContentLengthLong(fileSize);

		// Ŭ���̾�Ʈ���� ����Ҽ� �ִ� ��Ʈ�� ��ü ������
		// response.getOutputStream() �޼ҵ�� Ŭ���̾�Ʈ���� ����Ҽ� �ִ� OutputStream ��ü�� ��ȯ�Ѵ�.
		BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
		// �ѹ��� �ִ� 1M byte �� �о�ü� �ִ� ����
		byte[] buffer = new byte[1024 * 1024];
		int readedByte = 0;
		// �ݺ��� ���鼭 ������ֱ�
		while (true) {
			// byte[] ��ü�� �̿��ؼ� ���Ͽ��� byte �˰��� �о����
			readedByte = fis.read(buffer);
			if (readedByte == -1)
				break; // ���̻� ���� �����Ͱ� ���ٸ� �ݺ��� ���� ������
			// ���� ��ŭ ����ϱ�
			bos.write(buffer, 0, readedByte);
			bos.flush(); // ���
		}
		// FileInputStream �ݾ��ֱ�
		fis.close();
	}

}
