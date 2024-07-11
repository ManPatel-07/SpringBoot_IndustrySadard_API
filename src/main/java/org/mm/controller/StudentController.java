package org.mm.controller;

import org.mm.dao.StudentRepository;
import org.mm.dto.StudentDTO;
import org.mm.entities.StudentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/students")
public class StudentController
{
//    @GetMapping(path = "/")
//    public String getMyMessage()
//    {
//        return "This is my message";
//    }

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping(path = "/student/{id}")
    public StudentEntity getStudentById(@PathVariable("id") Long id)
    {
        return studentRepository.findById(id).orElse(null);
    }

    @GetMapping
    public List<StudentEntity> getAllStudent(@RequestParam(required = false) Integer age)
    {
        return studentRepository.findAll();
    }

    @PostMapping
    public StudentEntity createStudent(@RequestBody StudentEntity student)
    {
        return studentRepository.save(student);
    }

    @PutMapping
    public String updateStudent()
    {
        return "Hello form Put (update meassgae)";
    }
}
