package org.mm.controller;

import org.mm.dto.StudentDTO;
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

    @GetMapping(path = "/student/{id}")
    public StudentDTO getStudentById(@PathVariable("id") Long id)
    {
        return new StudentDTO(id, "Man Patel", "man@gmail.com",19, LocalDate.of(2024,1,2),true);
    }

    @GetMapping
    public String getAllStudent(@RequestParam(required = false) Integer age)
    {
        return "Hi age "+age;
    }

    @PostMapping
    public StudentDTO createStudent(@RequestBody StudentDTO studentDTO)
    {
        studentDTO.setId(101L);
        return studentDTO;
    }

    @PutMapping
    public String updateStudent()
    {
        return "Hello form Put (update meassgae)";
    }
}
