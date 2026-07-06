package com.pramod.firstapi.controller;

import java.io.File;
import java.io.IOException;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class FileUploadController {

    @PostMapping("/upload")
    public String uploadFile(
            @RequestParam("file") MultipartFile file)
            throws IOException {

        String uploadDir =
                System.getProperty("user.dir")
                + "/uploads/";

        File destination =
                new File(uploadDir
                        + file.getOriginalFilename());

        file.transferTo(destination);

        return "File Uploaded Successfully : "
                + file.getOriginalFilename();
    }
    @PostMapping("/uploadResume")
    public String uploadResume(
            @RequestParam("file")
            MultipartFile file)
            throws IOException {

        String uploadDir =
                System.getProperty("user.dir")
                + "/uploads/";

        File destination =
                new File(uploadDir
                        + file.getOriginalFilename());

        file.transferTo(destination);

        return "Resume Uploaded Successfully";
    }
    @PostMapping("/uploadImage")
    public String uploadImage(
            @RequestParam("file")
            MultipartFile file)
            throws IOException {

        String uploadDir =
                System.getProperty("user.dir")
                + "/uploads/";

        File destination =
                new File(uploadDir
                        + file.getOriginalFilename());

        file.transferTo(destination);

        return "Image Uploaded Successfully";
    }
    @GetMapping("/download")
    public ResponseEntity<Resource> downloadFile(
            @RequestParam String fileName)
            throws Exception {

        Path path = Paths.get(
                System.getProperty("user.dir"),
                "uploads",
                fileName);

        System.out.println(path.toAbsolutePath());
        Resource resource =
                new UrlResource(path.toUri());

        if (!resource.exists()) {
            throw new RuntimeException(
                    "File not found: " + fileName);
        }

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\""
                                + resource.getFilename()
                                + "\"")
                .contentLength(Files.size(path))
                .body(resource);
    }
    @GetMapping("/files")
    public List<String> getFiles() {

        File folder =
                new File(
                        System.getProperty("user.dir")
                        + "/uploads");

        String[] files =
                folder.list();

        return Arrays.asList(files);
    }
    @DeleteMapping("/deleteFile")
    public String deleteFile(
            @RequestParam String fileName) {

        File file =
                new File(
                        System.getProperty("user.dir")
                        + "/uploads/"
                        + fileName);

        if (file.delete()) {

            return "File Deleted Successfully";
        }

        return "File Not Found";
    }
}