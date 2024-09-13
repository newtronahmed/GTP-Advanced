//package org.springboot.performanceoptimization.controllers;
//
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.http.ResponseEntity;
//import org.springframework.util.StringUtils;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
//@RestController
//@RequestMapping("/api/files")
//public class FileUploadInitController {
//
//    private static final String UPLOAD_DIR = "uploads"; // Directory to save uploaded files
//
//    // Initialize the directory for file uploads
//    static {
//        try {
//            Files.createDirectories(Paths.get(UPLOAD_DIR));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @PostMapping("/upload")
//    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
//        // Check if the file is empty
//        if (file.isEmpty()) {
//            return ResponseEntity.badRequest().body("Please select a file to upload.");
//        }
//
//        // Get the original filename and sanitize it
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//
//        try {
//            // Save the file to the upload directory
//            Path filePath = Paths.get(UPLOAD_DIR, fileName);
//            Files.copy(file.getInputStream(), filePath);
//
//            // Return success message
//            return ResponseEntity.ok("File uploaded successfully: " + fileName);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return ResponseEntity.status(500).body("Failed to upload file: " + fileName);
//        }
//    }
//}
//
