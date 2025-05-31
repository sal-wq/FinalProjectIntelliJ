import student.StudentInfo;
import student.University;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        University university = new University();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("""
                    1. Add a new Student
                    2. View all student
                    3. Update student details
                    4. Delete a student
                    5. Search students by grade 
                    6. List top-performing student
                    7. Calculate the average
                    8. Exit
                    Please choose option:  """);

            int n = scanner.nextInt();
            switch (n) {
                case 1 -> {
                    StudentInfo studentInfo = new StudentInfo();

                    System.out.print("Insert ID: ");
                    int ID = scanner.nextInt();
                    studentInfo.setID(ID);

                    scanner.nextLine();

                    System.out.print("Insert Name: ");
                    String Name = scanner.nextLine();
                    studentInfo.setName(Name);

                    System.out.print("Insert Age: ");
                    int Age = scanner.nextInt();
                    studentInfo.setAge(Age);

                    scanner.nextLine();

                    System.out.print("Insert Email: ");
                    String Email = scanner.nextLine();
                    studentInfo.setEmail(Email);

                    System.out.print("Insert Grade: ");
                    String Grade = scanner.nextLine();
                    studentInfo.setGrade(Grade);

                    System.out.print("Insert Score: ");
                    float Score = scanner.nextFloat();
                    studentInfo.setScore(Score);

                    university.add(studentInfo);
                }

                case 2 -> {
                    List<StudentInfo> list = university.viewAllUser();
                    if (list != null) {
                        list.forEach(System.out::println);
                    } else {
                        System.out.println("No data found.");
                    }
                }

                case 3 -> {
                    System.out.print("Insert ID of student to update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();

                    StudentInfo updatedInfo = new StudentInfo();
                    updatedInfo.setID(updateId);

                    System.out.print("New Name: ");
                    updatedInfo.setName(scanner.nextLine());

                    System.out.print("New Age: ");
                    updatedInfo.setAge(scanner.nextInt());
                    scanner.nextLine();

                    System.out.print("New Email: ");
                    updatedInfo.setEmail(scanner.nextLine());

                    System.out.print("New Grade: ");
                    updatedInfo.setGrade(scanner.nextLine());

                    System.out.print("New Score: ");
                    updatedInfo.setScore(scanner.nextFloat());

                    university.updateStudent(updatedInfo);
                }

                case 4 -> {
                    System.out.print("Insert ID of student to delete: ");
                    int deleteId = scanner.nextInt();
                    university.deleteStudent(deleteId);
                }

                case 5 -> {
                    System.out.print("Insert Grade to search: ");
                    scanner.nextLine();
                    String grade = scanner.nextLine();
                    List<StudentInfo> found = university.searchByGrade(grade);
                    if (found != null && !found.isEmpty()) {
                        found.forEach(System.out::println);
                    } else {
                        System.out.println("No students found with that grade.");
                    }
                }

                case 6 -> {
                    StudentInfo top = university.getTopStudent();
                    if (top != null) {
                        System.out.print("Top performing student:");
                        System.out.println(top);
                    } else {
                        System.out.println("No students available.");
                    }
                }

                case 7 -> {
                    float avg = university.calculateAverageScore();
                    System.out.print("Average score of all students: " + avg);
                }

                case 8 -> {
                    System.out.println("Exiting program.");
                    return;
                }

                default -> System.out.println("Invalid option! Try again.");
            }
        }
    }
}
