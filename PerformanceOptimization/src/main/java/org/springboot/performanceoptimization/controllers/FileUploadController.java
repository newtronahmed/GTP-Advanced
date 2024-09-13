package org.springboot.performanceoptimization.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@RestController
@RequestMapping("/api/files")
public class FileUploadController {

    private static final String UPLOAD_DIR = "uploads"; // Directory to save uploaded files
    private static final int NUM_THREADS = Runtime.getRuntime().availableProcessors();

    // Initialize the directory for file uploads
    static {
        try {
            Files.createDirectories(Paths.get(UPLOAD_DIR));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadAndProcessFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please select a file to upload.");
        }

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Path filePath = Paths.get(UPLOAD_DIR, fileName);

        try {
            Files.copy(file.getInputStream(), filePath);
            // Call the multithreaded processing method
            long keywordCount = processFileInParallel(filePath.toString(), "optimize");

            return ResponseEntity.ok("File uploaded and processed successfully. Keyword count: " + keywordCount);
        } catch (IOException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to upload or process file: " + fileName);
        }
    }

    private long processFileInParallel(String filePath, String keyword) throws IOException, InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
        try {
            long totalLines = Files.lines(Paths.get(filePath)).count();
            int actualThreads = (int) Math.min(NUM_THREADS, totalLines);
            long chunkSize = (long) Math.ceil((double) totalLines / actualThreads);

            List<Future<Long>> futures = new ArrayList<>();
            for (int i = 0; i < actualThreads; i++) {
                long startLine = i * chunkSize;
                long endLine = Math.min((i + 1) * chunkSize, totalLines);
                futures.add(executor.submit(() -> processFileChunk(filePath, keyword, startLine, endLine)));
            }

            return futures.stream().mapToLong(future -> {
                try {
                    return future.get();
                } catch (InterruptedException | ExecutionException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Error processing file chunk", e);
                }
            }).sum();
        } finally {
            executor.shutdown();
        }
    }

    private long processFileChunk(String filePath, String keyword, long startLine, long endLine) throws IOException {
        long count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            long currentLine = 0;
            while ((line = reader.readLine()) != null) {
                if (currentLine >= startLine && currentLine < endLine) {
                    if (line.contains(keyword)) {
                        count++;
                    }
                }
                currentLine++;
                if (currentLine >= endLine) {
                    break;
                }
            }
        }
        return count;
    }
}

