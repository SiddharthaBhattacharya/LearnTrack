package com.airtribe.learntrack;

import com.airtribe.learntrack.constants.AppConstants;
import com.airtribe.learntrack.constants.MenuOptions;
import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.repository.CourseRepository;
import com.airtribe.learntrack.repository.EnrollmentRepository;
import com.airtribe.learntrack.repository.StudentRepository;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.service.StudentService;
import com.airtribe.learntrack.util.InputValidator;
import java.util.ArrayList;
import java.util.Scanner;
// Final submission code.
public class Main {
    private final Scanner scanner = new Scanner(System.in);
    private final StudentService studentService;
    private final CourseService courseService;
    private final EnrollmentService enrollmentService;

    public Main() {
        StudentRepository studentRepository = new StudentRepository();
        CourseRepository courseRepository = new CourseRepository();
        EnrollmentRepository enrollmentRepository = new EnrollmentRepository();

        studentService = new StudentService(studentRepository);
        courseService = new CourseService(courseRepository);
        enrollmentService = new EnrollmentService(
                studentService, courseService, enrollmentRepository);
    }

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    private void start() {
        boolean running = true;
        System.out.println("========================================");
        System.out.println(AppConstants.APP_NAME);
        System.out.println("========================================");

        while (running) {
            showMainMenu();
            int choice = InputValidator.readInt(scanner, "Choose an option: ");

            switch (choice) {
                case MenuOptions.STUDENTS:
                    studentMenu();
                    break;
                case MenuOptions.COURSES:
                    courseMenu();
                    break;
                case MenuOptions.ENROLLMENTS:
                    enrollmentMenu();
                    break;
                case MenuOptions.EXIT:
                    running = false;
                    break;
                default:
                    System.out.println("That option is not available. Try again.");
            }
        }

        System.out.println(AppConstants.EXIT_MESSAGE);
    }

    private void showMainMenu() {
        System.out.println("\nMain Menu");
        System.out.println("1. Student management");
        System.out.println("2. Course management");
        System.out.println("3. Enrollment management");
        System.out.println("0. Exit");
    }

    private void studentMenu() {
        boolean goBack = false;
        while (!goBack) {
            System.out.println("\nStudent Management");
            System.out.println("1. Add student");
            System.out.println("2. View all students");
            System.out.println("3. Search student by ID");
            System.out.println("4. Update student");
            System.out.println("5. Deactivate student");
            System.out.println("0. Back to main menu");
            int choice = InputValidator.readInt(scanner, "Choose an option: ");

            try {
                switch (choice) {
                    case MenuOptions.STUDENT_ADD:
                        addStudent();
                        break;
                    case MenuOptions.STUDENT_LIST:
                        showStudents(studentService.listStudents());
                        break;
                    case MenuOptions.STUDENT_SEARCH:
                        int studentId = InputValidator.readInt(scanner, "Student ID: ");
                        System.out.println(studentService.findStudentById(studentId));
                        break;
                    case MenuOptions.STUDENT_UPDATE:
                        updateStudent();
                        break;
                    case MenuOptions.STUDENT_DEACTIVATE:
                        int deactivateId = InputValidator.readInt(scanner, "Student ID to deactivate: ");
                        Student inactiveStudent = studentService.deactivateStudent(deactivateId);
                        System.out.println("Student deactivated: " + inactiveStudent.getDisplayName());
                        break;
                    case MenuOptions.BACK:
                        goBack = true;
                        break;
                    default:
                        System.out.println("That option is not available. Try again.");
                }
            } catch (EntityNotFoundException | IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void addStudent() {
        String firstName = InputValidator.readRequiredText(scanner, "First name: ");
        String lastName = InputValidator.readRequiredText(scanner, "Last name: ");
        String email = InputValidator.readEmail(scanner, "Email (optional): ");
        String batch = InputValidator.readRequiredText(scanner, "Batch: ");

        Student student;
        if (email.isEmpty()) {
            student = studentService.addStudent(firstName, lastName, batch);
        } else {
            student = studentService.addStudent(firstName, lastName, email, batch);
        }
        System.out.println("Student added successfully. ID: " + student.getId());
    }

    private void updateStudent() throws EntityNotFoundException {
        int id = InputValidator.readInt(scanner, "Student ID to update: ");
        Student existingStudent = studentService.findStudentById(id);
        System.out.println("Current details: " + existingStudent);

        String firstName = InputValidator.readRequiredText(scanner, "New first name: ");
        String lastName = InputValidator.readRequiredText(scanner, "New last name: ");
        String email = InputValidator.readEmail(scanner, "New email (optional): ");
        String batch = InputValidator.readRequiredText(scanner, "New batch: ");

        Student updatedStudent = studentService.updateStudent(
                id, firstName, lastName, email, batch);
        System.out.println("Student updated: " + updatedStudent);
    }

    private void showStudents(ArrayList<Student> students) {
        if (students.isEmpty()) {
            System.out.println("No students have been added yet.");
            return;
        }
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private void courseMenu() {
        boolean goBack = false;
        while (!goBack) {
            System.out.println("\nCourse Management");
            System.out.println("1. Add course");
            System.out.println("2. View all courses");
            System.out.println("3. Search course by ID");
            System.out.println("4. Deactivate course");
            System.out.println("5. Activate course");
            System.out.println("0. Back to main menu");
            int choice = InputValidator.readInt(scanner, "Choose an option: ");

            try {
                switch (choice) {
                    case MenuOptions.COURSE_ADD:
                        addCourse();
                        break;
                    case MenuOptions.COURSE_LIST:
                        showCourses(courseService.listCourses());
                        break;
                    case MenuOptions.COURSE_SEARCH:
                        int courseId = InputValidator.readInt(scanner, "Course ID: ");
                        System.out.println(courseService.findCourseById(courseId));
                        break;
                    case MenuOptions.COURSE_DEACTIVATE:
                        int deactivateId = InputValidator.readInt(scanner, "Course ID to deactivate: ");
                        System.out.println("Course deactivated: "
                                + courseService.deactivateCourse(deactivateId).getCourseName());
                        break;
                    case MenuOptions.COURSE_ACTIVATE:
                        int activateId = InputValidator.readInt(scanner, "Course ID to activate: ");
                        System.out.println("Course activated: "
                                + courseService.activateCourse(activateId).getCourseName());
                        break;
                    case MenuOptions.BACK:
                        goBack = true;
                        break;
                    default:
                        System.out.println("That option is not available. Try again.");
                }
            } catch (EntityNotFoundException | IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void addCourse() {
        String name = InputValidator.readRequiredText(scanner, "Course name: ");
        String description = InputValidator.readRequiredText(scanner, "Description: ");
        int duration = InputValidator.readPositiveInt(scanner, "Duration in weeks: ");
        Course course = courseService.addCourse(name, description, duration);
        System.out.println("Course added successfully. ID: " + course.getId());
    }

    private void showCourses(ArrayList<Course> courses) {
        if (courses.isEmpty()) {
            System.out.println("No courses have been added yet.");
            return;
        }
        for (Course course : courses) {
            System.out.println(course);
        }
    }

    private void enrollmentMenu() {
        boolean goBack = false;
        while (!goBack) {
            System.out.println("\nEnrollment Management");
            System.out.println("1. Enroll a student in a course");
            System.out.println("2. View enrollments for a student");
            System.out.println("3. View all enrollments");
            System.out.println("4. Mark enrollment as completed");
            System.out.println("5. Cancel enrollment");
            System.out.println("0. Back to main menu");
            int choice = InputValidator.readInt(scanner, "Choose an option: ");

            try {
                switch (choice) {
                    case MenuOptions.ENROLLMENT_ADD:
                        int studentId = InputValidator.readInt(scanner, "Student ID: ");
                        int courseId = InputValidator.readInt(scanner, "Course ID: ");
                        Enrollment enrollment = enrollmentService.enrollStudent(studentId, courseId);
                        System.out.println("Enrollment created: " + enrollment);
                        break;
                    case MenuOptions.ENROLLMENT_BY_STUDENT:
                        int searchStudentId = InputValidator.readInt(scanner, "Student ID: ");
                        showEnrollments(enrollmentService.listEnrollmentsForStudent(searchStudentId));
                        break;
                    case MenuOptions.ENROLLMENT_LIST:
                        showEnrollments(enrollmentService.listAllEnrollments());
                        break;
                    case MenuOptions.ENROLLMENT_COMPLETE:
                        changeEnrollmentStatus(EnrollmentStatus.COMPLETED);
                        break;
                    case MenuOptions.ENROLLMENT_CANCEL:
                        changeEnrollmentStatus(EnrollmentStatus.CANCELLED);
                        break;
                    case MenuOptions.BACK:
                        goBack = true;
                        break;
                    default:
                        System.out.println("That option is not available. Try again.");
                }
            } catch (EntityNotFoundException | IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void changeEnrollmentStatus(EnrollmentStatus status) throws EntityNotFoundException {
        int enrollmentId = InputValidator.readInt(scanner, "Enrollment ID: ");
        Enrollment enrollment = enrollmentService.changeStatus(enrollmentId, status);
        System.out.println("Enrollment updated: " + enrollment);
    }

    private void showEnrollments(ArrayList<Enrollment> enrollments) {
        if (enrollments.isEmpty()) {
            System.out.println("No enrollments to display.");
            return;
        }
        for (Enrollment enrollment : enrollments) {
            System.out.println(enrollment);
        }
    }
}
