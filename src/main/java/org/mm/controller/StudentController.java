package org.mm.controller;

import jakarta.validation.Valid;
import org.mm.dao.StudentRepository;
import org.mm.dto.StudentDTO;
import org.mm.entities.StudentEntity;
import org.mm.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable("id") Long id)
    {
        Optional<StudentDTO> studentDTO = studentService.getStudentById(id);
        return studentDTO.map(studentDTO1 -> ResponseEntity.ok(studentDTO1))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudent(@RequestParam(required = false) Integer age)
    {
        return ResponseEntity.ok(studentService.getAllStudent());
    }

    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@RequestBody @Valid StudentDTO studentDTO)
    {
        StudentDTO savedStudent = studentService.createStudent(studentDTO);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    @PutMapping(path = "/student/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable("id") Long studentid,@RequestBody StudentDTO studentDTO)
    {
        return ResponseEntity.ok(studentService.updateStudentById(studentid, studentDTO));
    }

    @DeleteMapping(path = "/student/{id}")
    public ResponseEntity<Boolean> deleteStudentById(@PathVariable("id") Long id)
    {
        return ResponseEntity.ok(studentService.studentDeleteById(id));
    }

    @PatchMapping(path = "/student/{id}")
    public ResponseEntity<StudentDTO> updatePartialStudent(@PathVariable("id") Long studentid,
                                           @RequestBody Map<String, Object> updates)
    {
        StudentDTO studentDTO = studentService.updatePartialStudentById(studentid, updates);
        if (studentDTO == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(studentDTO);
    }
}
