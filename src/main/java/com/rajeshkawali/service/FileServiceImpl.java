package com.rajeshkawali.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rajeshkawali.entity.FileDetails;
import com.rajeshkawali.entity.ImageDetails;
import com.rajeshkawali.repository.FileRepository;
import com.rajeshkawali.repository.ImageRepository;
import com.rajeshkawali.util.ImageUtils;

/**
 * @author Rajesh_Kawali
 *
 */
@Service
public class FileServiceImpl implements FileService {

	@Autowired
	private ImageRepository imageRepository;

	@Autowired
	private FileRepository fileRepository;

	private final String FOLDER_PATH = "C:\\Users\\Rajesh_Kawali\\Downloads/";

	public String uploadImage(MultipartFile file) throws IOException {
		ImageDetails imageData = imageRepository.save(ImageDetails.builder().name(file.getOriginalFilename())
				.type(file.getContentType()).imageData(ImageUtils.compressImage(file.getBytes())).build());
		if (imageData != null) {
			return "file uploaded successfully : " + file.getOriginalFilename();
		}
		return null;
	}

	public byte[] downloadImage(String fileName) {
		Optional<ImageDetails> dbImageData = imageRepository.findByName(fileName);
		byte[] images = ImageUtils.decompressImage(dbImageData.get().getImageData());
		return images;
	}

	public String uploadImageToFileSystem(MultipartFile file) throws IOException {
		String filePath = FOLDER_PATH + file.getOriginalFilename();
		FileDetails fileData = fileRepository.save(FileDetails.builder().name(file.getOriginalFilename())
				.type(file.getContentType()).filePath(filePath).build());
		file.transferTo(new File(filePath));
		if (fileData != null) {
			return "File uploaded successfully : " + filePath;
		}
		return null;
	}

	public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
		Optional<FileDetails> fileData = fileRepository.findByName(fileName);
		String filePath = fileData.get().getFilePath();
		byte[] images = Files.readAllBytes(new File(filePath).toPath());
		return images;
	}
}
