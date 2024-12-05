package hello.term_project.announcement;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class AnnouncementRepository {

    private static final String DB_URL = "jdbc:mysql://192.168.56.101:4567/db_term_project";
    private static final String DB_USER = "jooyehoon";
    private static final String DB_PASSWORD = "ckgusdk1234";

    public void insertAnnouncement(Announcement announcement) {

        String sql = "insert into announcement(title, content, " +
                "announce_date, writer) " +
                "values(?, ?, ?, ?)";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setString(1, announcement.getTitle());
            pstmt.setString(2, announcement.getContent());
            pstmt.setString(3, announcement.getAnnounce_date());
            pstmt.setString(4, announcement.getWriter());

            pstmt.executeUpdate();

            con.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<Announcement> getAnnouncement(Integer id) {
        String sql = "select * from announcement where id = ?";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();

            Announcement announcement = new Announcement();
            while(rs.next()){
                announcement.setId(rs.getInt("id"));
                announcement.setTitle(rs.getString("title"));
                announcement.setContent(rs.getString("content"));
                announcement.setAnnounce_date(rs.getString("announce_date"));
                announcement.setWriter(rs.getString("writer"));
            }

            con.close();
            return Optional.of(announcement);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public List<Announcement> getAllAnnouncements() {
        String sql = "select * from announcement";
        List<Announcement> announcements  = new ArrayList<>();

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement pstmt = con.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Announcement announcement = new Announcement();
                announcement.setId(rs.getInt("id"));
                announcement.setTitle(rs.getString("title"));
                announcement.setContent(rs.getString("content"));
                announcement.setAnnounce_date(rs.getString("announce_date"));
                announcement.setWriter(rs.getString("writer"));
                announcements.add(announcement);
            }

            con.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return announcements;
    }

    public void deleteAnnouncement(Integer id){

        String sql = "delete from announcement where id = ?";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, id);

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

    public void updateAnnouncement(Announcement announcement) {
        String sql = "update announcement set title=?, content=?, announce_date=?, writer=? where id=?";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setString(1, announcement.getTitle());
            pstmt.setString(2, announcement.getContent());
            pstmt.setString(3, announcement.getAnnounce_date());
            pstmt.setString(4, announcement.getWriter());
            pstmt.setInt(5, announcement.getId());

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
