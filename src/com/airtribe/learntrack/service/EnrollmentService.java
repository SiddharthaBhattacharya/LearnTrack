package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.repository.EnrollmentRepository;
import com.airtribe.learntrack.util.IdGenerator;
import java.time.LocalDate;
import java.util.ArrayList;

public class EnrollmentService {
    private final StudentService studentService;
    private final CourseService courseService;
    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentService(StudentService studentService, CourseService courseService,
                             EnrollmentRepository enrollmentRepository) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.enrollmentRepository = enrollmentRepository;
    }

    public Enrollment enrollStudent(int studentId, int courseId)
            throws EntityNotFoundException {
        Student student = studentService.findStudentById(studentId);
        Course course = courseService.findCourseById(courseId);

        if (!student.isActive()) {
            throw new IllegalArgumentException("This student is inactive and cannot enroll.");
        }
        if (!course.isActive()) {
            throw new IllegalArgumentException("This course is inactive and cannot accept enrollments.");
        }

        for (Enrollment existing : enrollmentRepository.getAll()) {
            boolean sameStudent = existing.getStudentId() == studentId;
            boolean sameCourse = existing.getCourseId() == courseId;
            boolean activeEnrollment = existing.getStatus() == EnrollmentStatus.ACTIVE;
            if (sameStudent && sameCourse && activeEnrollment) {
                throw new IllegalArgumentException(
                        "This student already has an active enrollment in that course.");
            }
        }

        Enrollment enrollment = new Enrollment(IdGenerator.getNextEnrollmentId(),
                studentId, courseId, LocalDate.now(), EnrollmentStatus.ACTIVE);
        enrollmentRepository.add(enrollment);
        return enrollment;
    }

    public ArrayList<Enrollment> listEnrollmentsForStudent(int studentId)
            throws EntityNotFoundException {
        studentService.findStudentById(studentId);
        ArrayList<Enrollment> result = new ArrayList<>();
        for (Enrollment enrollment : enrollmentRepository.getAll()) {
            if (enrollment.getStudentId() == studentId) {
                result.add(enrollment);
            }
        }
        return result;
    }

    public ArrayList<Enrollment> listAllEnrollments() {
        return enrollmentRepository.getAll();
    }

    public Enrollment changeStatus(int enrollmentId, EnrollmentStatus status)
            throws EntityNotFoundException {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId);
        if (enrollment == null) {
            throw new EntityNotFoundException(
                    "Enrollment with ID " + enrollmentId + " was not found.");
        }
        enrollment.setStatus(status);
        return enrollment;
    }
}
