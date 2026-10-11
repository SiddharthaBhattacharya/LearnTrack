package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.repository.StudentRepository;
import com.airtribe.learntrack.util.IdGenerator;
import java.util.ArrayList;

public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(String firstName, String lastName, String batch) {
        Student student = new Student(
                IdGenerator.getNextStudentId(), firstName, lastName, batch);
        studentRepository.add(student);
        return student;
    }

    // Overloaded method for students who provide an email address.
    public Student addStudent(String firstName, String lastName, String email, String batch) {
        Student student = new Student(
                IdGenerator.getNextStudentId(), firstName, lastName, email, batch);
        studentRepository.add(student);
        return student;
    }

    public ArrayList<Student> listStudents() {
        return studentRepository.getAll();
    }

    public Student findStudentById(int id) throws EntityNotFoundException {
        Student student = studentRepository.findById(id);
        if (student == null) {
            throw new EntityNotFoundException("Student with ID " + id + " was not found.");
        }
        return student;
    }

    public Student updateStudent(int id, String firstName, String lastName,
                                 String email, String batch) throws EntityNotFoundException {
        Student student = findStudentById(id);
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.setBatch(batch);
        return student;
    }

    public Student deactivateStudent(int id) throws EntityNotFoundException {
        Student student = findStudentById(id);
        student.setActive(false);
        return student;
    }

    // Kept as a separate service operation; the console menu uses deactivation instead.
    public void removeStudent(int id) throws EntityNotFoundException {
        findStudentById(id);
        studentRepository.removeById(id);
    }
}
