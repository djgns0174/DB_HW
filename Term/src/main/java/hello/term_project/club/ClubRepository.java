package hello.term_project.club;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class ClubRepository {

    private static final String DB_URL = "jdbc:mysql://192.168.56.101:4567/db_term_project";
    private static final String DB_USER = "jooyehoon";
    private static final String DB_PASSWORD = "ckgusdk1234";

    public void insertClub(Club club) {

        String sql = "insert into club(name, description, create_date, " +
                "member_number, advisor_id, president_id) " +
                "values(?, ?, ?, ?, ?, ?)";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setString(1, club.getName());
            pstmt.setString(2, club.getDescription());
            pstmt.setString(3, club.getCreate_date());
            pstmt.setInt(4, club.getMember_number());
            pstmt.setInt(5, club.getAdvisor_id());
            pstmt.setString(6, club.getPresident_id());

            pstmt.executeUpdate();

            con.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Club> getAllClubs(){

        String sql = "select * from club";
        List<Club> clubs = new ArrayList<>();

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement pstmt = con.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){

                Club club = new Club();
                club.setName(rs.getString("name"));
                club.setDescription(rs.getString("description"));
                club.setCreate_date(rs.getString("create_date"));
                club.setMember_number(rs.getInt("member_number"));
                club.setAdvisor_id(rs.getInt("advisor_id"));
                club.setPresident_id(rs.getString("president_id"));

                clubs.add(club);
            }

            con.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return clubs;
    }

    public Optional<Club> getClub(String name) {
        String sql = "select * from club where name = ?";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setString(1, name);

            ResultSet rs = pstmt.executeQuery();

            Club club = new Club();
            while(rs.next()){
                club.setName(rs.getString("name"));
                club.setDescription(rs.getString("description"));
                club.setCreate_date(rs.getString("create_date"));
                club.setMember_number(rs.getInt("member_number"));
                club.setAdvisor_id(rs.getInt("advisor_id"));
                club.setPresident_id(rs.getString("president_id"));
            }

            con.close();

            return Optional.of(club);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public void deleteClub(String name){

        String sql = "delete from club where name = ?";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setString(1, name);

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

    public void updateClub(Club club, String oldName) {
        String sql = "update club set name=?, description=?, create_date=?, " +
                "member_number=?, advisor_id=?, president_id=? where name=?";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setString(1, club.getName());
            pstmt.setString(2, club.getDescription());
            pstmt.setString(3, club.getCreate_date());
            pstmt.setInt(4, club.getMember_number());
            pstmt.setInt(5, club.getAdvisor_id());
            pstmt.setString(6, club.getPresident_id());
            pstmt.setString(7, oldName);

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
