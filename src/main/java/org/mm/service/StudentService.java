package org.mm.service;

import org.mm.dao.StudentRepository;
import org.mm.dto.StudentDTO;
import org.mm.entities.StudentEntity;
import org.mm.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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

    public Optional<StudentDTO> getStudentById(Long id)
    {
//        Optional<StudentEntity> studentEntity = studentRepository.findById(id);
//        return studentEntity.map(studentEntity1 -> convertEntityToStudentDTO(studentEntity1));

        return studentRepository.findById(id).map(studentEntity -> convertEntityToStudentDTO(studentEntity));
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

    public StudentDTO updateStudentById(Long studentid, StudentDTO studentDTO)
    {
        isExistStudent(studentid);
        StudentEntity studentEntity = convertStudentDTOToEntity(studentDTO);
        studentEntity.setId(studentid);
        StudentEntity studentEntity1 = studentRepository.save(studentEntity);
        return convertEntityToStudentDTO(studentEntity1);
    }

    public void isExistStudent(Long studentid)
    {
        boolean exists = studentRepository.existsById(studentid);
        if(!exists) throw new ResourceNotFoundException("Student not found with id " + studentid);
    }

    public boolean studentDeleteById(Long id)
    {
        isExistStudent(id);
        studentRepository.deleteById(id);
        return true;
    }

    public StudentDTO updatePartialStudentById(Long studentid, Map<String, Object> updates) {
        isExistStudent(studentid);
        StudentEntity studentEntity = studentRepository.findById(studentid).get();
        updates.forEach((field, value) -> {
            Field firldToBeUpdated = ReflectionUtils.findRequiredField(StudentEntity.class, field);
            firldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(firldToBeUpdated, studentEntity, value);
        });
        return convertEntityToStudentDTO(studentRepository.save(studentEntity));
    }
}
