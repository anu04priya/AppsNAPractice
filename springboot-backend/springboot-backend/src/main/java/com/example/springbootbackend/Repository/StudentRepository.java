package com.example.springbootbackend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springbootbackend.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

}
