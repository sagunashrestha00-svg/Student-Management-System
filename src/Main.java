import dao.StudentDAO;
import dao.CourseDAO;
import dao.EnrollmentDAO;
import dao.MarksDAO;
import exception.StudentNotFoundException;

import model.Student;
import model.Course;
import model.Enrollment;
import model.Marks;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        StudentDAO studentDAO = new StudentDAO();
        CourseDAO courseDAO = new CourseDAO();
        EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
        MarksDAO marksDAO = new MarksDAO();

        while (true) {

            System.out.println("\n========================================");
            System.out.println("       STUDENT MANAGEMENT SYSTEM");
            System.out.println("========================================");
            System.out.println("1. Student Management");
            System.out.println("2. Course Management");
            System.out.println("3. Enrollment Management");
            System.out.println("4. Marks Management");
            System.out.println("5. Exit");
            System.out.println("========================================");

            int mainChoice = readInt(
                    scanner,
                    "Enter your choice: "
            );

            switch (mainChoice) {

                // ==========================================
                // STUDENT MANAGEMENT
                // ==========================================
                case 1: {

                    while (true) {

                        System.out.println(
                                "\n---------- STUDENT MANAGEMENT ----------"
                        );

                        System.out.println("1. Add Student");
                        System.out.println("2. View All Students");
                        System.out.println("3. Search Student");
                        System.out.println("4. Update Student");
                        System.out.println("5. Delete Student");
                        System.out.println("6. Student Academic Report");
                        System.out.println("7. Back to Main Menu");

                        int studentMenuChoice =
                                readInt(
                                        scanner,
                                        "Enter your choice: "
                                );

                        if (studentMenuChoice == 7) {
                            break;
                        }

                        switch (studentMenuChoice) {

                            // ==================================
                            // ADD STUDENT
                            // ==================================
                            case 1: {

                                Student newStudent =
                                        new Student();

                                newStudent.setName(
                                        readText(
                                                scanner,
                                                "Enter name: "
                                        )
                                );

                                newStudent.setEmail(
                                        readText(
                                                scanner,
                                                "Enter email: "
                                        )
                                );

                                newStudent.setPhone(
                                        readText(
                                                scanner,
                                                "Enter phone: "
                                        )
                                );

                                newStudent.setAddress(
                                        readText(
                                                scanner,
                                                "Enter address: "
                                        )
                                );

                                studentDAO.addStudent(
                                        newStudent
                                );

                                break;
                            }


                            // ==================================
                            // VIEW ALL STUDENTS
                            // ==================================
                            case 2: {

                                List<Student> studentList =
                                        studentDAO.getAllStudents();

                                if (studentList.isEmpty()) {

                                    System.out.println(
                                            "No students found."
                                    );

                                } else {

                                    System.out.println(
                                            "\n----------- STUDENT LIST -----------"
                                    );

                                    for (Student currentStudent :
                                            studentList) {

                                        currentStudent.displayInfo();

                                        System.out.println(
                                                "------------------------------------"
                                        );
                                    }
                                }

                                break;
                            }


                            // ==================================
                            // SEARCH STUDENT
                            // ==================================
                            case 3: {

                                System.out.println(
                                        "\n---------- SEARCH STUDENT ----------"
                                );

                                System.out.println(
                                        "1. Search by ID"
                                );

                                System.out.println(
                                        "2. Search by Name"
                                );

                                int searchStudentChoice =
                                        readInt(
                                                scanner,
                                                "Enter your choice: "
                                        );

                                // SEARCH BY ID USING CUSTOM EXCEPTION
                                if (searchStudentChoice == 1) {

                                    int searchStudentId =
                                            readInt(
                                                    scanner,
                                                    "Enter student ID: "
                                            );

                                    try {

                                        Student searchedStudent =
                                                studentDAO.getStudentByIdOrThrow(
                                                        searchStudentId
                                                );

                                        System.out.println(
                                                "\nStudent found:"
                                        );

                                        searchedStudent.displayInfo();

                                    } catch (StudentNotFoundException e) {

                                        System.out.println(
                                                "Error: " + e.getMessage()
                                        );
                                    }

                                }

                                // SEARCH BY NAME
                                else if (searchStudentChoice == 2) {

                                    String searchStudentName =
                                            readText(
                                                    scanner,
                                                    "Enter student name: "
                                            );

                                    List<Student> matchingStudents =
                                            studentDAO.getStudentsByName(
                                                    searchStudentName
                                            );

                                    if (matchingStudents.isEmpty()) {

                                        System.out.println(
                                                "No student found with that name."
                                        );

                                    } else {

                                        System.out.println(
                                                "\nStudents found:"
                                        );

                                        for (Student currentStudent :
                                                matchingStudents) {

                                            currentStudent.displayInfo();

                                            System.out.println(
                                                    "------------------------------------"
                                            );
                                        }
                                    }

                                } else {

                                    System.out.println(
                                            "Invalid choice."
                                    );
                                }

                                break;
                            }


                            // ==================================
                            // UPDATE STUDENT
                            // ==================================
                            case 4: {

                                int updateStudentId =
                                        readInt(
                                                scanner,
                                                "Enter student ID to update: "
                                        );

                                Student studentForUpdate =
                                        studentDAO.getStudentById(
                                                updateStudentId
                                        );

                                if (studentForUpdate != null) {

                                    studentForUpdate.setName(
                                            readText(
                                                    scanner,
                                                    "Enter new name: "
                                            )
                                    );

                                    studentForUpdate.setEmail(
                                            readText(
                                                    scanner,
                                                    "Enter new email: "
                                            )
                                    );

                                    studentForUpdate.setPhone(
                                            readText(
                                                    scanner,
                                                    "Enter new phone: "
                                            )
                                    );

                                    studentForUpdate.setAddress(
                                            readText(
                                                    scanner,
                                                    "Enter new address: "
                                            )
                                    );

                                    studentDAO.updateStudent(
                                            studentForUpdate
                                    );

                                } else {

                                    System.out.println(
                                            "Student not found."
                                    );
                                }

                                break;
                            }


                            // ==================================
                            // DELETE STUDENT
                            // ==================================
                            case 5: {

                                int deleteStudentId =
                                        readInt(
                                                scanner,
                                                "Enter student ID to delete: "
                                        );

                                Student studentForDelete =
                                        studentDAO.getStudentById(
                                                deleteStudentId
                                        );

                                if (studentForDelete != null) {

                                    System.out.println(
                                            "\nStudent found:"
                                    );

                                    studentForDelete.displayInfo();

                                    if (confirmDelete(scanner)) {

                                        studentDAO.deleteStudent(
                                                deleteStudentId
                                        );
                                    }

                                } else {

                                    System.out.println(
                                            "Student not found."
                                    );
                                }

                                break;
                            }


                            // ==================================
                            // STUDENT ACADEMIC REPORT
                            // ==================================
                            case 6: {

                                int academicReportStudentId =
                                        readInt(
                                                scanner,
                                                "Enter student ID: "
                                        );

                                studentDAO
                                        .getStudentAcademicReport(
                                                academicReportStudentId
                                        );

                                break;
                            }


                            default:

                                System.out.println(
                                        "Invalid choice."
                                );
                        }
                    }

                    break;
                }


                // ==========================================
                // COURSE MANAGEMENT
                // ==========================================
                case 2: {

                    while (true) {

                        System.out.println(
                                "\n---------- COURSE MANAGEMENT ----------"
                        );

                        System.out.println("1. Add Course");
                        System.out.println("2. View All Courses");
                        System.out.println("3. Search Course");
                        System.out.println("4. Update Course");
                        System.out.println("5. Delete Course");
                        System.out.println("6. Back to Main Menu");

                        int courseMenuChoice =
                                readInt(
                                        scanner,
                                        "Enter your choice: "
                                );

                        if (courseMenuChoice == 6) {
                            break;
                        }

                        switch (courseMenuChoice) {

                            // ==================================
                            // ADD COURSE
                            // ==================================
                            case 1: {

                                Course newCourse =
                                        new Course();

                                newCourse.setCourseCode(
                                        readText(
                                                scanner,
                                                "Enter course code: "
                                        )
                                );

                                newCourse.setCourseName(
                                        readText(
                                                scanner,
                                                "Enter course name: "
                                        )
                                );

                                newCourse.setCreditHours(
                                        readInt(
                                                scanner,
                                                "Enter credit hours: "
                                        )
                                );

                                courseDAO.addCourse(
                                        newCourse
                                );

                                break;
                            }


                            // ==================================
                            // VIEW ALL COURSES
                            // ==================================
                            case 2: {

                                List<Course> courseList =
                                        courseDAO.getAllCourses();

                                if (courseList.isEmpty()) {

                                    System.out.println(
                                            "No courses found."
                                    );

                                } else {

                                    System.out.println(
                                            "\n----------- COURSE LIST -----------"
                                    );

                                    for (Course currentCourse :
                                            courseList) {

                                        System.out.println(
                                                "Course ID: "
                                                        + currentCourse.getId()
                                        );

                                        System.out.println(
                                                "Course Code: "
                                                        + currentCourse
                                                        .getCourseCode()
                                        );

                                        System.out.println(
                                                "Course Name: "
                                                        + currentCourse
                                                        .getCourseName()
                                        );

                                        System.out.println(
                                                "Credit Hours: "
                                                        + currentCourse
                                                        .getCreditHours()
                                        );

                                        System.out.println(
                                                "-----------------------------------"
                                        );
                                    }
                                }

                                break;
                            }


                            // ==================================
                            // SEARCH COURSE
                            // ==================================
                            case 3: {

                                int searchCourseId =
                                        readInt(
                                                scanner,
                                                "Enter course ID: "
                                        );

                                Course searchedCourse =
                                        courseDAO.getCourseById(
                                                searchCourseId
                                        );

                                if (searchedCourse != null) {

                                    System.out.println(
                                            "\nCourse found:"
                                    );

                                    System.out.println(
                                            "Course ID: "
                                                    + searchedCourse.getId()
                                    );

                                    System.out.println(
                                            "Course Code: "
                                                    + searchedCourse
                                                    .getCourseCode()
                                    );

                                    System.out.println(
                                            "Course Name: "
                                                    + searchedCourse
                                                    .getCourseName()
                                    );

                                    System.out.println(
                                            "Credit Hours: "
                                                    + searchedCourse
                                                    .getCreditHours()
                                    );

                                } else {

                                    System.out.println(
                                            "Course not found."
                                    );
                                }

                                break;
                            }


                            // ==================================
                            // UPDATE COURSE
                            // ==================================
                            case 4: {

                                int updateCourseId =
                                        readInt(
                                                scanner,
                                                "Enter course ID to update: "
                                        );

                                Course courseForUpdate =
                                        courseDAO.getCourseById(
                                                updateCourseId
                                        );

                                if (courseForUpdate != null) {

                                    courseForUpdate.setCourseCode(
                                            readText(
                                                    scanner,
                                                    "Enter new course code: "
                                            )
                                    );

                                    courseForUpdate.setCourseName(
                                            readText(
                                                    scanner,
                                                    "Enter new course name: "
                                            )
                                    );

                                    courseForUpdate.setCreditHours(
                                            readInt(
                                                    scanner,
                                                    "Enter new credit hours: "
                                            )
                                    );

                                    courseDAO.updateCourse(
                                            courseForUpdate
                                    );

                                } else {

                                    System.out.println(
                                            "Course not found."
                                    );
                                }

                                break;
                            }


                            // ==================================
                            // DELETE COURSE
                            // ==================================
                            case 5: {

                                int deleteCourseId =
                                        readInt(
                                                scanner,
                                                "Enter course ID to delete: "
                                        );

                                Course courseForDelete =
                                        courseDAO.getCourseById(
                                                deleteCourseId
                                        );

                                if (courseForDelete != null) {

                                    System.out.println(
                                            "\nCourse found:"
                                    );

                                    System.out.println(
                                            "Course ID: "
                                                    + courseForDelete.getId()
                                    );

                                    System.out.println(
                                            "Course Code: "
                                                    + courseForDelete
                                                    .getCourseCode()
                                    );

                                    System.out.println(
                                            "Course Name: "
                                                    + courseForDelete
                                                    .getCourseName()
                                    );

                                    System.out.println(
                                            "Credit Hours: "
                                                    + courseForDelete
                                                    .getCreditHours()
                                    );

                                    if (confirmDelete(scanner)) {

                                        courseDAO.deleteCourse(
                                                deleteCourseId
                                        );
                                    }

                                } else {

                                    System.out.println(
                                            "Course not found."
                                    );
                                }

                                break;
                            }


                            default:

                                System.out.println(
                                        "Invalid choice."
                                );
                        }
                    }

                    break;
                }


                // ==========================================
                // ENROLLMENT MANAGEMENT
                // ==========================================
                case 3: {

                    while (true) {

                        System.out.println(
                                "\n---------- ENROLLMENT MANAGEMENT ----------"
                        );

                        System.out.println("1. Add Enrollment");
                        System.out.println("2. View All Enrollments");
                        System.out.println("3. Search Enrollment");
                        System.out.println("4. Delete Enrollment");
                        System.out.println("5. List Students by Course");
                        System.out.println("6. Back to Main Menu");

                        int enrollmentMenuChoice =
                                readInt(
                                        scanner,
                                        "Enter your choice: "
                                );

                        if (enrollmentMenuChoice == 6) {
                            break;
                        }

                        switch (enrollmentMenuChoice) {

                            // ==================================
                            // ADD ENROLLMENT
                            // ==================================
                            case 1: {

                                Enrollment newEnrollment =
                                        new Enrollment();

                                newEnrollment.setStudentId(
                                        readInt(
                                                scanner,
                                                "Enter student ID: "
                                        )
                                );

                                newEnrollment.setCourseId(
                                        readInt(
                                                scanner,
                                                "Enter course ID: "
                                        )
                                );

                                newEnrollment.setEnrollmentDate(
                                        readDate(scanner)
                                );

                                enrollmentDAO.addEnrollment(
                                        newEnrollment
                                );

                                break;
                            }


                            // ==================================
                            // VIEW ALL ENROLLMENTS
                            // ==================================
                            case 2: {

                                List<Enrollment> enrollmentList =
                                        enrollmentDAO
                                                .getAllEnrollments();

                                if (enrollmentList.isEmpty()) {

                                    System.out.println(
                                            "No enrollments found."
                                    );

                                } else {

                                    System.out.println(
                                            "\n----------- ENROLLMENT LIST -----------"
                                    );

                                    for (Enrollment currentEnrollment :
                                            enrollmentList) {

                                        System.out.println(
                                                "Enrollment ID: "
                                                        + currentEnrollment
                                                        .getId()
                                        );

                                        System.out.println(
                                                "Student ID: "
                                                        + currentEnrollment
                                                        .getStudentId()
                                        );

                                        System.out.println(
                                                "Student Name: "
                                                        + currentEnrollment
                                                        .getStudentName()
                                        );

                                        System.out.println(
                                                "Course ID: "
                                                        + currentEnrollment
                                                        .getCourseId()
                                        );

                                        System.out.println(
                                                "Course Code: "
                                                        + currentEnrollment
                                                        .getCourseCode()
                                        );

                                        System.out.println(
                                                "Course Name: "
                                                        + currentEnrollment
                                                        .getCourseName()
                                        );

                                        System.out.println(
                                                "Enrollment Date: "
                                                        + currentEnrollment
                                                        .getEnrollmentDate()
                                        );

                                        System.out.println(
                                                "---------------------------------------"
                                        );
                                    }
                                }

                                break;
                            }


                            // ==================================
                            // SEARCH ENROLLMENT
                            // ==================================
                            case 3: {

                                int searchEnrollmentId =
                                        readInt(
                                                scanner,
                                                "Enter enrollment ID: "
                                        );

                                Enrollment searchedEnrollment =
                                        enrollmentDAO
                                                .getEnrollmentById(
                                                        searchEnrollmentId
                                                );

                                if (searchedEnrollment != null) {

                                    System.out.println(
                                            "\n=========================================="
                                    );

                                    System.out.println(
                                            "          ENROLLMENT DETAILS"
                                    );

                                    System.out.println(
                                            "=========================================="
                                    );

                                    System.out.println(
                                            "Enrollment ID: "
                                                    + searchedEnrollment
                                                    .getId()
                                    );

                                    System.out.println(
                                            "Student ID: "
                                                    + searchedEnrollment
                                                    .getStudentId()
                                    );

                                    System.out.println(
                                            "Student Name: "
                                                    + searchedEnrollment
                                                    .getStudentName()
                                    );

                                    System.out.println(
                                            "Course ID: "
                                                    + searchedEnrollment
                                                    .getCourseId()
                                    );

                                    System.out.println(
                                            "Course Code: "
                                                    + searchedEnrollment
                                                    .getCourseCode()
                                    );

                                    System.out.println(
                                            "Course Name: "
                                                    + searchedEnrollment
                                                    .getCourseName()
                                    );

                                    System.out.println(
                                            "Enrollment Date: "
                                                    + searchedEnrollment
                                                    .getEnrollmentDate()
                                    );

                                    System.out.println(
                                            "=========================================="
                                    );

                                } else {

                                    System.out.println(
                                            "Enrollment not found."
                                    );
                                }

                                break;
                            }


                            // ==================================
                            // LIST STUDENTS BY COURSE
                            // ==================================
                            case 5: {

                                int courseId =
                                        readInt(
                                                scanner,
                                                "Enter course ID: "
                                        );

                                List<Enrollment> studentsByCourse =
                                        enrollmentDAO
                                                .getStudentsByCourse(
                                                        courseId
                                                );

                                if (studentsByCourse.isEmpty()) {

                                    System.out.println(
                                            "No students are enrolled in this course."
                                    );

                                } else {

                                    Enrollment firstEnrollment =
                                            studentsByCourse.get(0);

                                    System.out.println(
                                            "\n=========================================="
                                    );

                                    System.out.println(
                                            "          STUDENTS BY COURSE"
                                    );

                                    System.out.println(
                                            "=========================================="
                                    );

                                    System.out.println(
                                            "Course ID: "
                                                    + firstEnrollment
                                                    .getCourseId()
                                    );

                                    System.out.println(
                                            "Course Code: "
                                                    + firstEnrollment
                                                    .getCourseCode()
                                    );

                                    System.out.println(
                                            "Course Name: "
                                                    + firstEnrollment
                                                    .getCourseName()
                                    );

                                    System.out.println(
                                            "------------------------------------------"
                                    );

                                    for (Enrollment currentEnrollment :
                                            studentsByCourse) {

                                        System.out.println(
                                                "Student ID: "
                                                        + currentEnrollment
                                                        .getStudentId()
                                        );

                                        System.out.println(
                                                "Student Name: "
                                                        + currentEnrollment
                                                        .getStudentName()
                                        );

                                        System.out.println(
                                                "Enrollment Date: "
                                                        + currentEnrollment
                                                        .getEnrollmentDate()
                                        );

                                        System.out.println(
                                                "------------------------------------------"
                                        );
                                    }
                                }

                                break;
                            }


                            // ==================================
                            // DELETE ENROLLMENT
                            // ==================================
                            case 4: {

                                int deleteEnrollmentId =
                                        readInt(
                                                scanner,
                                                "Enter enrollment ID to delete: "
                                        );

                                Enrollment enrollmentForDelete =
                                        enrollmentDAO
                                                .getEnrollmentById(
                                                        deleteEnrollmentId
                                                );

                                if (enrollmentForDelete != null) {

                                    System.out.println(
                                            "\nEnrollment found:"
                                    );

                                    System.out.println(
                                            "Enrollment ID: "
                                                    + enrollmentForDelete
                                                    .getId()
                                    );

                                    System.out.println(
                                            "Student ID: "
                                                    + enrollmentForDelete
                                                    .getStudentId()
                                    );

                                    System.out.println(
                                            "Student Name: "
                                                    + enrollmentForDelete
                                                    .getStudentName()
                                    );

                                    System.out.println(
                                            "Course ID: "
                                                    + enrollmentForDelete
                                                    .getCourseId()
                                    );

                                    System.out.println(
                                            "Course Code: "
                                                    + enrollmentForDelete
                                                    .getCourseCode()
                                    );

                                    System.out.println(
                                            "Course Name: "
                                                    + enrollmentForDelete
                                                    .getCourseName()
                                    );

                                    System.out.println(
                                            "Enrollment Date: "
                                                    + enrollmentForDelete
                                                    .getEnrollmentDate()
                                    );

                                    if (confirmDelete(scanner)) {

                                        enrollmentDAO.deleteEnrollment(
                                                deleteEnrollmentId
                                        );
                                    }

                                } else {

                                    System.out.println(
                                            "Enrollment not found."
                                    );
                                }

                                break;
                            }


                            default:

                                System.out.println(
                                        "Invalid choice."
                                );
                        }
                    }

                    break;
                }


                // ==========================================
                // MARKS MANAGEMENT
                // ==========================================
                case 4: {

                    while (true) {

                        System.out.println(
                                "\n---------- MARKS MANAGEMENT ----------"
                        );

                        System.out.println("1. Add Marks");
                        System.out.println("2. View All Marks");
                        System.out.println("3. Search Marks");
                        System.out.println("4. Update Marks");
                        System.out.println("5. Delete Marks");
                        System.out.println("6. Student Average");
                        System.out.println("7. Class Ranking by Average");
                        System.out.println("8. Back to Main Menu");

                        int marksMenuChoice =
                                readInt(
                                        scanner,
                                        "Enter your choice: "
                                );

                        if (marksMenuChoice == 8) {
                            break;
                        }

                        switch (marksMenuChoice) {

                            // ==================================
                            // ADD MARKS
                            // ==================================
                            case 1: {

                                Marks newMarks =
                                        new Marks();

                                newMarks.setStudentId(
                                        readInt(
                                                scanner,
                                                "Enter student ID: "
                                        )
                                );

                                newMarks.setCourseId(
                                        readInt(
                                                scanner,
                                                "Enter course ID: "
                                        )
                                );

                                double enteredMark =
                                        readDouble(
                                                scanner,
                                                "Enter marks: "
                                        );

                                if (enteredMark < 0 ||
                                        enteredMark > 100) {

                                    System.out.println(
                                            "Invalid marks! " +
                                                    "Marks must be between 0 and 100."
                                    );

                                    break;
                                }

                                newMarks.setMarks(
                                        enteredMark
                                );

                                String calculatedGrade =
                                        calculateGrade(
                                                enteredMark
                                        );

                                newMarks.setGrade(
                                        calculatedGrade
                                );

                                System.out.println(
                                        "Calculated Grade: "
                                                + calculatedGrade
                                );

                                marksDAO.addMarks(
                                        newMarks
                                );

                                break;
                            }


                            // ==================================
                            // VIEW ALL MARKS
                            // ==================================
                            case 2: {

                                List<Marks> marksList =
                                        marksDAO.getAllMarks();

                                if (marksList.isEmpty()) {

                                    System.out.println(
                                            "No marks found."
                                    );

                                } else {

                                    System.out.println(
                                            "\n----------- MARKS LIST -----------"
                                    );

                                    for (Marks currentMarks :
                                            marksList) {

                                        System.out.println(
                                                "Marks ID: "
                                                        + currentMarks.getId()
                                        );

                                        System.out.println(
                                                "Student ID: "
                                                        + currentMarks
                                                        .getStudentId()
                                        );

                                        System.out.println(
                                                "Course ID: "
                                                        + currentMarks
                                                        .getCourseId()
                                        );

                                        System.out.println(
                                                "Marks: "
                                                        + currentMarks.getMarks()
                                        );

                                        System.out.println(
                                                "Grade: "
                                                        + currentMarks.getGrade()
                                        );

                                        System.out.println(
                                                "----------------------------------"
                                        );
                                    }
                                }

                                break;
                            }


                            // ==================================
                            // SEARCH MARKS
                            // ==================================
                            case 3: {

                                int searchMarksId =
                                        readInt(
                                                scanner,
                                                "Enter marks ID: "
                                        );

                                Marks searchedMarks =
                                        marksDAO.getMarksById(
                                                searchMarksId
                                        );

                                if (searchedMarks != null) {

                                    System.out.println(
                                            "\nMarks found:"
                                    );

                                    System.out.println(
                                            "Marks ID: "
                                                    + searchedMarks.getId()
                                    );

                                    System.out.println(
                                            "Student ID: "
                                                    + searchedMarks
                                                    .getStudentId()
                                    );

                                    System.out.println(
                                            "Course ID: "
                                                    + searchedMarks
                                                    .getCourseId()
                                    );

                                    System.out.println(
                                            "Marks: "
                                                    + searchedMarks.getMarks()
                                    );

                                    System.out.println(
                                            "Grade: "
                                                    + searchedMarks.getGrade()
                                    );

                                } else {

                                    System.out.println(
                                            "Marks record not found."
                                    );
                                }

                                break;
                            }


                            // ==================================
                            // UPDATE MARKS
                            // ==================================
                            case 4: {

                                int marksUpdateStudentId =
                                        readInt(
                                                scanner,
                                                "Enter student ID: "
                                        );

                                int marksUpdateCourseId =
                                        readInt(
                                                scanner,
                                                "Enter course ID: "
                                        );

                                double updatedMarkValue =
                                        readDouble(
                                                scanner,
                                                "Enter new marks: "
                                        );

                                if (updatedMarkValue < 0 ||
                                        updatedMarkValue > 100) {

                                    System.out.println(
                                            "Invalid marks! " +
                                                    "Marks must be between 0 and 100."
                                    );

                                    break;
                                }

                                String updatedGrade =
                                        calculateGrade(
                                                updatedMarkValue
                                        );

                                Marks marksForUpdate =
                                        new Marks();

                                marksForUpdate.setStudentId(
                                        marksUpdateStudentId
                                );

                                marksForUpdate.setCourseId(
                                        marksUpdateCourseId
                                );

                                marksForUpdate.setMarks(
                                        updatedMarkValue
                                );

                                marksForUpdate.setGrade(
                                        updatedGrade
                                );

                                System.out.println(
                                        "Calculated Grade: "
                                                + updatedGrade
                                );

                                marksDAO.updateMarks(
                                        marksForUpdate
                                );

                                break;
                            }


                            // ==================================
                            // DELETE MARKS
                            // ==================================
                            case 5: {

                                int deleteMarksId =
                                        readInt(
                                                scanner,
                                                "Enter marks ID to delete: "
                                        );

                                Marks marksForDelete =
                                        marksDAO.getMarksById(
                                                deleteMarksId
                                        );

                                if (marksForDelete != null) {

                                    System.out.println(
                                            "\nMarks record found:"
                                    );

                                    System.out.println(
                                            "Marks ID: "
                                                    + marksForDelete.getId()
                                    );

                                    System.out.println(
                                            "Student ID: "
                                                    + marksForDelete
                                                    .getStudentId()
                                    );

                                    System.out.println(
                                            "Course ID: "
                                                    + marksForDelete
                                                    .getCourseId()
                                    );

                                    System.out.println(
                                            "Marks: "
                                                    + marksForDelete.getMarks()
                                    );

                                    System.out.println(
                                            "Grade: "
                                                    + marksForDelete.getGrade()
                                    );

                                    if (confirmDelete(scanner)) {

                                        marksDAO.deleteMarks(
                                                deleteMarksId
                                        );
                                    }

                                } else {

                                    System.out.println(
                                            "Marks record not found."
                                    );
                                }

                                break;
                            }


                            // ==================================
                            // STUDENT AVERAGE
                            // ==================================
                            case 6: {

                                int averageStudentId =
                                        readInt(
                                                scanner,
                                                "Enter student ID: "
                                        );

                                Student averageStudent =
                                        studentDAO.getStudentById(
                                                averageStudentId
                                        );

                                if (averageStudent == null) {

                                    System.out.println(
                                            "Student not found."
                                    );

                                    break;
                                }

                                List<Marks> allMarks =
                                        marksDAO.getAllMarks();

                                double totalMarks = 0;
                                int markCount = 0;

                                for (Marks currentMarks : allMarks) {

                                    if (currentMarks.getStudentId()
                                            == averageStudentId) {

                                        totalMarks +=
                                                currentMarks.getMarks();

                                        markCount++;
                                    }
                                }

                                if (markCount == 0) {

                                    System.out.println(
                                            "No marks found for this student."
                                    );

                                } else {

                                    double average =
                                            totalMarks / markCount;

                                    System.out.println(
                                            "\n=========================================="
                                    );

                                    System.out.println(
                                            "          STUDENT AVERAGE"
                                    );

                                    System.out.println(
                                            "=========================================="
                                    );

                                    System.out.println(
                                            "Student ID: "
                                                    + averageStudent.getId()
                                    );

                                    System.out.println(
                                            "Student Name: "
                                                    + averageStudent.getName()
                                    );

                                    System.out.printf(
                                            "Average Marks: %.2f%n",
                                            average
                                    );

                                    System.out.println(
                                            "Subjects with Marks: "
                                                    + markCount
                                    );
                                }

                                break;
                            }


                            // ==================================
                            // CLASS RANKING BY AVERAGE
                            // ==================================
                            case 7: {

                                List<Marks> allMarks =
                                        marksDAO.getAllMarks();

                                if (allMarks.isEmpty()) {

                                    System.out.println(
                                            "No marks found. Cannot generate ranking."
                                    );

                                    break;
                                }

                                Map<Integer, Double> totalMap =
                                        new HashMap<>();

                                Map<Integer, Integer> countMap =
                                        new HashMap<>();

                                for (Marks currentMarks : allMarks) {

                                    int studentId =
                                            currentMarks.getStudentId();

                                    double currentTotal =
                                            totalMap.getOrDefault(
                                                    studentId,
                                                    0.0
                                            );

                                    int currentCount =
                                            countMap.getOrDefault(
                                                    studentId,
                                                    0
                                            );

                                    totalMap.put(
                                            studentId,
                                            currentTotal
                                                    + currentMarks.getMarks()
                                    );

                                    countMap.put(
                                            studentId,
                                            currentCount + 1
                                    );
                                }

                                Map<Integer, Double> averageMap =
                                        new HashMap<>();

                                for (Integer studentId : totalMap.keySet()) {

                                    double average =
                                            totalMap.get(studentId)
                                                    / countMap.get(studentId);

                                    averageMap.put(
                                            studentId,
                                            average
                                    );
                                }

                                List<Map.Entry<Integer, Double>> ranking =
                                        new ArrayList<>(
                                                averageMap.entrySet()
                                        );

                                ranking.sort(
                                        (a, b) -> Double.compare(
                                                b.getValue(),
                                                a.getValue()
                                        )
                                );

                                Map<Integer, Student> studentMap =
                                        studentDAO.getStudentMap();

                                System.out.println(
                                        "\n================================================"
                                );

                                System.out.println(
                                        "          CLASS RANKING BY AVERAGE"
                                );

                                System.out.println(
                                        "================================================"
                                );

                                int rank = 1;

                                for (Map.Entry<Integer, Double> entry : ranking) {

                                    int studentId = entry.getKey();
                                    double average = entry.getValue();

                                    Student rankedStudent =
                                            studentMap.get(studentId);

                                    String studentName =
                                            rankedStudent != null
                                                    ? rankedStudent.getName()
                                                    : "Unknown Student";

                                    System.out.printf(
                                            "%d. ID: %d | Name: %s | Average: %.2f%n",
                                            rank,
                                            studentId,
                                            studentName,
                                            average
                                    );

                                    rank++;
                                }

                                break;
                            }

                            default:

                                System.out.println(
                                        "Invalid choice."
                                );
                        }
                    }

                    break;
                }


                // ==========================================
                // EXIT
                // ==========================================
                case 5: {

                    System.out.println(
                            "\nThank you for using " +
                                    "Student Management System!"
                    );

                    scanner.close();

                    return;
                }


                default:

                    System.out.println(
                            "Invalid choice. Please try again."
                    );
            }
        }
    }


    // ==========================================
    // SAFE INTEGER INPUT
    // ==========================================
    public static int readInt(
            Scanner scanner,
            String message) {

        while (true) {

            System.out.print(message);

            String input =
                    scanner.nextLine().trim();

            try {

                return Integer.parseInt(input);

            } catch (NumberFormatException e) {

                System.out.println(
                        "Invalid input. " +
                                "Please enter a whole number."
                );
            }
        }
    }


    // ==========================================
    // SAFE DOUBLE INPUT
    // ==========================================
    public static double readDouble(
            Scanner scanner,
            String message) {

        while (true) {

            System.out.print(message);

            String input =
                    scanner.nextLine().trim();

            try {

                return Double.parseDouble(input);

            } catch (NumberFormatException e) {

                System.out.println(
                        "Invalid input. " +
                                "Please enter a valid number."
                );
            }
        }
    }


    // ==========================================
    // NON-EMPTY TEXT INPUT
    // ==========================================
    public static String readText(
            Scanner scanner,
            String message) {

        while (true) {

            System.out.print(message);

            String input =
                    scanner.nextLine().trim();

            if (!input.isEmpty()) {

                return input;
            }

            System.out.println(
                    "Input cannot be empty. " +
                            "Please try again."
            );
        }
    }


    // ==========================================
    // DATE INPUT
    // ==========================================
    public static String readDate(
            Scanner scanner) {

        while (true) {

            System.out.print(
                    "Enter enrollment date (YYYY-MM-DD): "
            );

            String input =
                    scanner.nextLine().trim();

            try {

                LocalDate.parse(input);

                return input;

            } catch (DateTimeParseException e) {

                System.out.println(
                        "Invalid date format."
                );

                System.out.println(
                        "Please use YYYY-MM-DD."
                );
            }
        }
    }


    // ==========================================
    // AUTOMATIC GRADE CALCULATION
    // ==========================================
    public static String calculateGrade(
            double mark) {

        if (mark >= 90) {

            return "A+";

        } else if (mark >= 80) {

            return "A";

        } else if (mark >= 70) {

            return "B+";

        } else if (mark >= 60) {

            return "B";

        } else if (mark >= 50) {

            return "C+";

        } else if (mark >= 40) {

            return "C";

        } else {

            return "F";
        }
    }


    // ==========================================
    // DELETE CONFIRMATION
    // ==========================================
    public static boolean confirmDelete(
            Scanner scanner) {

        while (true) {

            System.out.print(
                    "Are you sure you want to delete? (yes/no): "
            );

            String answer =
                    scanner.nextLine()
                            .trim()
                            .toLowerCase();

            if (answer.equals("yes") ||
                    answer.equals("y")) {

                return true;

            } else if (answer.equals("no") ||
                    answer.equals("n")) {

                System.out.println(
                        "Delete operation cancelled."
                );

                return false;

            } else {

                System.out.println(
                        "Please enter yes or no."
                );
            }
        }
    }
}