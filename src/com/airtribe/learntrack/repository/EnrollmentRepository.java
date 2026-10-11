package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Enrollment;
import java.util.ArrayList;

public class EnrollmentRepository {
    private final ArrayList<Enrollment> enrollments = new ArrayList<>();

    public void add(Enrollment enrollment) {
        enrollments.add(enrollment);
    }

    public Enrollment findById(int id) {
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getId() == id) {
                return enrollment;
            }
        }
        return null;
    }

    public ArrayList<Enrollment> getAll() {
        return new ArrayList<>(enrollments);
    }
}
