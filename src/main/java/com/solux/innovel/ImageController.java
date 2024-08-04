package com.solux.innovel;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/innovel")
public class ImageController {

    // 절대 경로로 변경
    private static final String UPLOAD_DIR = "C:/Users/keji1/OneDrive/문서/GitHub/backend/src/main/java/com/solux/innovel/uploads/";


    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is empty");
        }

        try {
            String fileName = file.getOriginalFilename();
            String filePath = UPLOAD_DIR + fileName;
            File dest = new File(filePath);
            dest.getParentFile().mkdirs(); // 부모 디렉토리가 없으면 생성
            file.transferTo(dest);

            // URL 생성 (도메인 이름과 파일 이름을 조합하여 URL을 생성)
            String imageUrl = "https://51e6-2406-5900-10e6-8026-ecdd-f031-868d-fc14.ngrok-free.app/innovel/images/" + fileName;

            return ResponseEntity.ok(imageUrl);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file");
        }
    }

    @GetMapping("/images/{filename}")
    public ResponseEntity<FileSystemResource> getImage(@PathVariable String filename) {
        FileSystemResource imgFile = new FileSystemResource(UPLOAD_DIR + filename);

        if (!imgFile.exists()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + imgFile.getFilename() + "\"")
                .body(imgFile);
    }
}
