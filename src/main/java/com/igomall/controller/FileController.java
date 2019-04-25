
package com.igomall.controller;

import java.util.HashMap;
import java.util.Map;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.igomall.common.FileType;
import com.igomall.common.Message;
import com.igomall.service.FileService;

/**
 * Controller - 文件
 * 
 * @author IGOMALL  Team
 * @version 1.0
 */
@Controller("apiFileController")
@RequestMapping("/api/file")
@CrossOrigin
public class FileController extends BaseController {

	@Autowired
	private FileService fileService;

	/**
	 * 上传
	 */
	@PostMapping("/upload")
	public @ResponseBody Map<String, Object> upload(FileType fileType, MultipartFile file) {
		Map<String, Object> data = new HashMap<>();
		if (fileType == null || file == null || file.isEmpty()) {
			data.put("message", ERROR_MESSAGE);
			data.put("state", ERROR_MESSAGE);
			return data;
		}
		if (!fileService.isValid(fileType, file)) {
			data.put("message", Message.warn("文件上传异常"));
			data.put("state", "error");
			return data;
		}
		String url = fileService.upload(fileType, file, false);
		if (StringUtils.isEmpty(url)) {
			data.put("message", Message.warn("文件上传失败"));
			data.put("state", "error");
			return data;
		}
		data.put("message", SUCCESS_MESSAGE);
		data.put("state", "success");
		data.put("url", url);
		return data;
	}

}