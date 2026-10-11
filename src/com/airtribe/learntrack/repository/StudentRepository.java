package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Student;
import java.util.ArrayList;

public class StudentRepository {
    private final ArrayList<Student> students = new ArrayList<>();

    public void add(Student student) {
        students.add(student);
    }

    public Student findById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    public ArrayList<Student> getAll() {
        return new ArrayList<>(students);
    }

    public boolean removeById(int id) {
        Student student = findById(id);
        if (student == null) {
            return false;
        }
        students.remove(student);
        return true;
    }
}
