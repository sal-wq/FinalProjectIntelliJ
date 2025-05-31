package student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class University {

    public List<StudentInfo> viewAllUser() {
        String sql = "SELECT * FROM \"StudentInfo\"";
        List<StudentInfo> studentList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DatabaseUtil.URL, DatabaseUtil.USER, DatabaseUtil.PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                StudentInfo student = new StudentInfo();
                student.setID(resultSet.getInt("ID"));
                student.setName(resultSet.getString("Name"));
                student.setAge(resultSet.getInt("Age"));
                student.setEmail(resultSet.getString("Email"));
                student.setGrade(resultSet.getString("Grade"));
                student.setScore(resultSet.getFloat("Score"));
                studentList.add(student);
            }
        } catch (Exception e) {
            System.out.println("Error getting all students: " + e.getMessage());
        }

        return studentList;
    }

    public int add(StudentInfo studentInfo) {
        String sql = "INSERT INTO public.\"StudentInfo\"(\"ID\", \"Name\", \"Age\", \"Email\", \"Grade\", \"Score\") VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(DatabaseUtil.URL, DatabaseUtil.USER, DatabaseUtil.PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, studentInfo.getID());
            statement.setString(2, studentInfo.getName());
            statement.setInt(3, studentInfo.getAge());
            statement.setString(4, studentInfo.getEmail());
            statement.setString(5, studentInfo.getGrade());
            statement.setFloat(6, studentInfo.getScore());

            int rows = statement.executeUpdate();
            if (rows > 0) {
                System.out.println("Student added successfully");
                return rows;
            } else {
                System.out.println("Add failed");
            }
        } catch (Exception e) {
            System.out.println("Error adding student: " + e.getMessage());
        }
        return 0;
    }

    public void updateStudent(StudentInfo updatedInfo) {
        String sql = "UPDATE public.\"StudentInfo\" SET \"Name\" = ?, \"Age\" = ?, \"Email\" = ?, \"Grade\" = ?, \"Score\" = ? WHERE \"ID\" = ?";
        try (Connection connection = DriverManager.getConnection(DatabaseUtil.URL, DatabaseUtil.USER, DatabaseUtil.PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, updatedInfo.getName());
            statement.setInt(2, updatedInfo.getAge());
            statement.setString(3, updatedInfo.getEmail());
            statement.setString(4, updatedInfo.getGrade());
            statement.setFloat(5, updatedInfo.getScore());
            statement.setInt(6, updatedInfo.getID());

            int rows = statement.executeUpdate();
            if (rows > 0) {
                System.out.println("Student updated successfully");
            } else {
                System.out.println("Update failed: student ID not found");
            }
        } catch (Exception e) {
            System.out.println("Error updating student: " + e.getMessage());
        }
    }

    public void deleteStudent(int deleteId) {
        String sql = "DELETE FROM public.\"StudentInfo\" WHERE \"ID\" = ?";
        try (Connection connection = DriverManager.getConnection(DatabaseUtil.URL, DatabaseUtil.USER, DatabaseUtil.PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, deleteId);

            int rows = statement.executeUpdate();
            if (rows > 0) {
                System.out.println("Student deleted successfully");
            } else {
                System.out.println("Delete failed: student ID not found");
            }
        } catch (Exception e) {
            System.out.println("Error deleting student: " + e.getMessage());
        }
    }

    public List<StudentInfo> searchByGrade(String grade) {
        String sql = "SELECT * FROM public.\"StudentInfo\" WHERE \"Grade\" = ?";
        List<StudentInfo> students = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DatabaseUtil.URL, DatabaseUtil.USER, DatabaseUtil.PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, grade);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                StudentInfo student = new StudentInfo();
                student.setID(resultSet.getInt("ID"));
                student.setName(resultSet.getString("Name"));
                student.setAge(resultSet.getInt("Age"));
                student.setEmail(resultSet.getString("Email"));
                student.setGrade(resultSet.getString("Grade"));
                student.setScore(resultSet.getFloat("Score"));
                students.add(student);
            }
        } catch (Exception e) {
            System.out.println("Error searching by grade: " + e.getMessage());
        }

        return students;
    }

    public StudentInfo getTopStudent() {
        String sql = "SELECT * FROM public.\"StudentInfo\" ORDER BY \"Score\" DESC LIMIT 1";
        try (Connection connection = DriverManager.getConnection(DatabaseUtil.URL, DatabaseUtil.USER, DatabaseUtil.PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                StudentInfo student = new StudentInfo();
                student.setID(resultSet.getInt("ID"));
                student.setName(resultSet.getString("Name"));
                student.setAge(resultSet.getInt("Age"));
                student.setEmail(resultSet.getString("Email"));
                student.setGrade(resultSet.getString("Grade"));
                student.setScore(resultSet.getFloat("Score"));
                return student;
            }
        } catch (Exception e) {
            System.out.println("Error getting top student: " + e.getMessage());
        }
        return null;
    }

    public float calculateAverageScore() {
        String sql = "SELECT AVG(\"Score\") AS average FROM public.\"StudentInfo\"";
        try (Connection connection = DriverManager.getConnection(DatabaseUtil.URL, DatabaseUtil.USER, DatabaseUtil.PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getFloat("average");
            }
        } catch (Exception e) {
            System.out.println("Error calculating average score: " + e.getMessage());
        }
        return 0;
    }
}
