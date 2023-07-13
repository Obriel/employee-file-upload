package com.fileupload.employeefileupload.repository;

import com.fileupload.employeefileupload.model.EmployeeFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeFileRepository extends JpaRepository<EmployeeFile, Long> {
}
