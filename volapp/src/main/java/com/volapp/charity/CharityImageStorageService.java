package com.volapp.charity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.FileNotFoundException;

@Service
public class CharityImageStorageService {
	
	@Autowired
	private static CharityImageRepository charityImageRepository;
	
	public CharityImage storeFile(MultipartFile file) throws Exception {
		
		// Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new Exception("Sorry! Filename contains invalid path sequence " + fileName);
            }

            CharityImage imageFile = new CharityImage(fileName, file.getContentType(), file.getBytes());

            return charityImageRepository.save(imageFile);
        } catch (IOException ex) {
            throw new Exception("Could not store file " + fileName + ". Please try again!", ex);
        }
	}
	
	public static CharityImage getFile(String fileId) throws Exception {
        return charityImageRepository.findById(fileId)
                .orElseThrow(() -> new FileNotFoundException("File not found with id " + fileId));
    }

}
