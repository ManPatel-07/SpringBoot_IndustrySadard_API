package org.mm.controller;

import org.mm.dao.StudentRepository;
import org.mm.dto.StudentDTO;
import org.mm.entities.StudentEntity;
import org.mm.service.StudentService;
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

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(path = "/student/{id}")
    public StudentDTO getStudentById(@PathVariable("id") Long id)
    {
        return studentService.getStudentById(id);
    }

    @GetMapping
    public List<StudentDTO> getAllStudent(@RequestParam(required = false) Integer age)
    {
        return studentService.getAllStudent();
    }

    @PostMapping
    public StudentDTO createStudent(@RequestBody StudentDTO studentDTO)
    {
        return studentService.createStudent(studentDTO);
    }

    @PutMapping
    public String updateStudent()
    {
        return "Hello form Put (update meassgae)";
    }
}
