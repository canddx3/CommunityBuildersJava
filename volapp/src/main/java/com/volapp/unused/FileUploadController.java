package com.volapp.unused;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.volapp.charity.CharityImageStorageService;
import com.volapp.charity.Response;
import com.volapp.charity.User;

@RestController
public class FileUploadController {
	
	@Autowired
	private CharityImageStorageService charityImageStorageService;
	
	@PostMapping("/uploadFile")
    public Response uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
        User imageFile = charityImageStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(imageFile.getFileName())
                .toUriString();

        return new Response(imageFile.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }

	@PostMapping("/uploadMultipleFiles") 
	public List<Response> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) throws Exception { 
		 return	 Arrays.asList(files)
				 .stream()
				 .map(file -> {
					try {
						return uploadFile(file);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return null;
				})
				 .collect(Collectors.toList());
	}
	

    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) throws Exception {
        // Load file from database
        User imageFile = CharityImageStorageService.getFile(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(imageFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + imageFile.getFileName() + "\"")
                .body(new ByteArrayResource(imageFile.getData()));
    }

}
