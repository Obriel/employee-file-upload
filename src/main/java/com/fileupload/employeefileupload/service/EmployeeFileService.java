package com.fileupload.employeefileupload.service;

import com.fileupload.employeefileupload.model.EmployeeFile;
import com.fileupload.employeefileupload.repository.EmployeeFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeFileService {

    private final EmployeeFileRepository employeeFileRepository;

    public void saveFile(String filename, byte[] content) {
        EmployeeFile employeeFile = new EmployeeFile();
        employeeFile.setFileName(filename);
        employeeFile.setFileData(content);
        employeeFileRepository.save(employeeFile);
    }

    public EmployeeFile getFile(Long id) {
        Optional<EmployeeFile> fileEntityOptional = employeeFileRepository.findById(id);
        if (fileEntityOptional.isPresent()) {
            return fileEntityOptional.get();
        } else {
            return null;
        }
    }
}
