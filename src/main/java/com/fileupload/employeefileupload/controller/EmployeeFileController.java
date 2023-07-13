package com.fileupload.employeefileupload.controller;

import com.fileupload.employeefileupload.model.EmployeeFile;
import com.fileupload.employeefileupload.service.EmployeeFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/employee_file")
@RequiredArgsConstructor
public class EmployeeFileController {

    private final EmployeeFileService employeeFileService;

    @PostMapping("/upload")
public ResponseEntity<String> uploadFile(@RequestParam("fileName") String fileName, @RequestBody byte[] fileData) {
    try {
        employeeFileService.saveFile(fileName, fileData);
        return ResponseEntity.ok("File uploaded successfully");
    } catch (IOException e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file");
    }
}


    @GetMapping("/file/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
        EmployeeFile employeeFile = employeeFileService.getFile(id);
        if (employeeFile == null) {
//            return ResponseEntity.notFound().build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File Not Found".getBytes());
        }
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + employeeFile.getFileName())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(employeeFile.getFileData());
    }



    //    @PostMapping("/upload")
//    public ResponseEntity<String> uploadFile(@RequestParam("file") byte[] content, @RequestParam("filename") String filename) {
//        String message = "";
//        try {
//            employeeFileService.saveFile(filename, content);
//            message = "Uploaded the file successfully: " + filename;
//            return ResponseEntity.status(HttpStatus.OK).body(message);
//        } catch (Exception e) {
//            message = "Could not upload the file: " + filename + "!";
//            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
//        }
//    }


//    @GetMapping("/file_download/{id}")
//    public ResponseEntity<byte[]> getFile(@PathVariable Long id, @Value("${file.download.directory}") String downloadDirectory) {
//        EmployeeFile employeeFile = employeeFileService.getFile(id);
//        if (employeeFile == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//        try {
//            String filePath = downloadDirectory + employeeFile.getName();
//            FileOutputStream fos = new FileOutputStream(filePath);
//            fos.write(employeeFile.getContent());
//            fos.close();
//            File file = new File(filePath);
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//            headers.setContentDispositionFormData("attachment", employeeFile.getName());
//            headers.setContentLength(file.length());
//            return new ResponseEntity<>(employeeFile.getContent(), headers, HttpStatus.OK);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
}
