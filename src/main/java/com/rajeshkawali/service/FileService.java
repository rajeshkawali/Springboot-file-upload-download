package com.rajeshkawali.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Rajesh_Kawali
 *
 */
public interface FileService {

	public String uploadImage(MultipartFile file) throws IOException;
	
	public byte[] downloadImage(String fileName);
	
	public String uploadImageToFileSystem(MultipartFile file) throws IOException;
	
	public byte[] downloadImageFromFileSystem(String fileName) throws IOException;
}
