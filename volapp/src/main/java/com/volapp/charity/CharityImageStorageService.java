package com.volapp.charity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
//import java.io.FileNotFoundException;

@Service
public class CharityImageStorageService {
	
	@Autowired
	private static UserRepository userRepository;
	
	public User storeFile(MultipartFile file) throws Exception {
		
		// Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new Exception("Sorry! Filename contains invalid path sequence " + fileName);
            }

            User imageFile = new User(fileName, file.getContentType(), file.getBytes());

            return userRepository.save(imageFile);
        } catch (IOException ex) {
            throw new Exception("Could not store file " + fileName + ". Please try again!", ex);
        }
	}
	
	public static User getFile(String username) throws Exception {
		try {
        return userRepository.findByUsername(username);
		} catch (Exception ex) {
			throw new Exception("File not found for User " + username + ". Please consider uploading an image file.", ex);
		}
//                .orElseThrow(() -> new FileNotFoundException("File not found with id " + username));
    }

}
