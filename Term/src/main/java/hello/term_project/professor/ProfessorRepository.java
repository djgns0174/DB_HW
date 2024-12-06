package hello.term_project.professor;

import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProfessorRepository {

    private static final String DB_URL = "jdbc:mysql://192.168.56.101:4567/db_term_project";
    private static final String DB_USER = "jooyehoon";
    private static final String DB_PASSWORD = "ckgusdk1234";

    public void insertprofessor(Professor professor) {

        String sql = "insert into professor(professor_id, name, major) values(?, ?, ?)";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setString(1, professor.getProfessor_id());
            pstmt.setString(2, professor.getName());
            pstmt.setString(3, professor.getMajor());

            pstmt.executeUpdate();

            con.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Professor> getAllprofessor(){
        String sql = "select * from professor";

        List<Professor> professors = new ArrayList<>();

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement pstmt = con.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                Professor professor = new Professor();
                professor.setProfessor_id(rs.getString("professor_id"));
                professor.setName(rs.getString("name"));
                professor.setMajor(rs.getString("major"));

                professors.add(professor);
            }

            con.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return professors;
    }

    public Optional<Professor> getprofessor(String professor_id) {
        String sql = "select * from professor where professor_id = ?";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setString(1, professor_id);

            ResultSet rs = pstmt.executeQuery();

            Professor professor = new Professor();
            while(rs.next()){
                professor.setProfessor_id(rs.getString("professor_id"));
                professor.setName(rs.getString("name"));
                professor.setMajor(rs.getString("major"));

            }

            con.close();

            return Optional.of(professor);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public void deleteProfessor(String professor_id){

        String sql = "delete from professor where professor_id = ?";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setString(1, professor_id);

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

    public void updateProfessor(Professor professor) {
        String sql = "update professor set name=?,major=? where professor_id=?";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setString(1, professor.getName());
            pstmt.setString(2, professor.getMajor());
            pstmt.setString(3, professor.getProfessor_id());

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