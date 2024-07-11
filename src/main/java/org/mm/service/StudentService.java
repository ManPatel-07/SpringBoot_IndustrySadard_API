package org.mm.service;

import org.mm.dao.StudentRepository;
import org.mm.dto.StudentDTO;
import org.mm.entities.StudentEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService
{
    private final StudentRepository studentRepository;

    @Autowired
    private ModelMapper modelMapper;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public StudentDTO getStudentById(Long id)
    {
        StudentEntity studentEntity = studentRepository.findById(id).orElse(null);
        return convertEntityToStudentDTO(studentEntity);
    }

    public List<StudentDTO> getAllStudent()
    {
        List<StudentEntity> studentEntities = studentRepository.findAll();
        return studentEntities
                .stream()
                .map(studentEntity -> convertEntityToStudentDTO(studentEntity))
                .collect(Collectors.toList());
    }

    public StudentDTO createStudent(StudentDTO studentDTO) {
        StudentEntity studentEntity = studentRepository.save(convertStudentDTOToEntity(studentDTO));
        return convertEntityToStudentDTO(studentEntity);
    }

    private StudentDTO convertEntityToStudentDTO(StudentEntity studentEntity) {
        return modelMapper.map(studentEntity, StudentDTO.class);
    }

    private StudentEntity convertStudentDTOToEntity(StudentDTO studentDTO) {
        return modelMapper.map(studentDTO, StudentEntity.class);
    }

}
