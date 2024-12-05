package hello.term_project.student;

import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class StudentRepository {

    private static final String DB_URL = "jdbc:mysql://192.168.56.101:4567/db_term_project";
    private static final String DB_USER = "jooyehoon";
    private static final String DB_PASSWORD = "ckgusdk1234";

    public void insertStudent(Student student) {

        String sql = "insert into student(student_id, name, major, " +
                "year, club) " +
                "values(?, ?, ?, ?, ?)";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setString(1, student.getStudent_id());
            pstmt.setString(2, student.getName());
            pstmt.setString(3, student.getMajor());
            pstmt.setInt(4, student.getYear());
            pstmt.setString(5, student.getClub());    // 학생이 소속된 동아리 이름을 넣음

            pstmt.executeUpdate();

            con.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Student> getAllStudent(){
        String sql = "select * from student";

        List<Student> students = new ArrayList<>();

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement pstmt = con.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                Student student = new Student();
                student.setStudent_id(rs.getString("student_id"));
                student.setName(rs.getString("name"));
                student.setMajor(rs.getString("major"));
                student.setYear(rs.getInt("year"));
                student.setClub(rs.getString("club"));

                students.add(student);
            }

            con.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return students;
    }

    public Optional<Student> getStudent(String student_id) {
        String sql = "select * from student where student_id = ?";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setString(1, student_id);

            ResultSet rs = pstmt.executeQuery();

            Student student = new Student();
            while(rs.next()){
                student.setStudent_id(rs.getString("student_id"));
                student.setName(rs.getString("name"));
                student.setMajor(rs.getString("major"));
                student.setYear(rs.getInt("year"));
                student.setClub(rs.getString("club"));

            }

            con.close();

            return Optional.of(student);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public void deleteStudent(String student_id){

        String sql = "delete from student where student_id = ?";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setString(1, student_id);

            int rowAffected = pstmt.executeUpdate();

            if (rowAffected > 0) {
                System.out.println("정상적으로 삭제되었습니다.");
            } else{
                System.out.println("삭제된 데이터가 없습니다.");
            }

            con.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStudent(Student student) {
        String sql = "update student set name=?,major=?,year=?,club=? where student_id=?";    // 학생의 동아리를 바꾸는 sql

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getMajor());
            pstmt.setInt(3, student.getYear());
            pstmt.setString(4, student.getClub());
            pstmt.setString(5, student.getStudent_id());

            int rowAffected = pstmt.executeUpdate();

            if (rowAffected > 0) {
                System.out.println("정상적으로 수정되었습니다.");
            } else{
                System.out.println("수정된 데이터가 없습니다.");
            }

            con.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
