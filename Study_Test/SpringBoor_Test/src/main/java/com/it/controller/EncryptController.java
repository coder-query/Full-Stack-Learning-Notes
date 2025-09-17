package com.it.controller;

import org.example.tools.Impl.Md5Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/encrypt")
public class EncryptController {


	@Autowired
	private Md5Tool md5Tool;

	@RequestMapping("/test_01")
	public String getEncrypt() {
		return md5Tool.encrypt("帅宏-coding");
	}


//	@Autowired
//	private Sha256Tool sha256Tool;

//	@RequestMapping("/test_02")
//	public String getEncrypt_02() {
//		return sha256Tool.encrypt("帅宏-coding");
//	}
}
