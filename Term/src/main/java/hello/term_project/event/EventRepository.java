package hello.term_project.event;

import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EventRepository {

    private static final String DB_URL = "jdbc:mysql://192.168.56.101:4567/db_term_project";
    private static final String DB_USER = "jooyehoon";
    private static final String DB_PASSWORD = "ckgusdk1234";

    public void insertEvent(Event event) {

        String sql = "insert into event(description, event_date, " +
                "name, participant_number, location, host_club_name) " +
                "values(?, ?, ?, ?, ?, ?)";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setString(1, event.getDescription());
            pstmt.setString(2, event.getEvent_date());
            pstmt.setString(3, event.getName());
            pstmt.setInt(4, event.getParticipant_number());
            pstmt.setString(5, event.getLocation());
            pstmt.setString(6, event.getHost_club_name());

            pstmt.executeUpdate();

            con.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Event> getAllEvent(){
        String sql = "select * from event";
        List<Event> events = new ArrayList<>();

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement pstmt = con.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                Event event = new Event();
                event.setId(rs.getInt("id"));
                event.setDescription(rs.getString("description"));
                event.setEvent_date(rs.getString("event_date"));
                event.setName(rs.getString("name"));
                event.setParticipant_number(rs.getInt("participant_number"));
                event.setLocation(rs.getString("location"));
                event.setHost_club_name(rs.getString("host_club_name"));
                events.add(event);
            }

            con.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return events;
    }

    public Optional<Event> getEvent(Integer id) {
        String sql = "select * from event where id = ?";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();

            Event event = new Event();
            while(rs.next()){
                event.setId(rs.getInt("id"));
                event.setDescription(rs.getString("description"));
                event.setEvent_date(rs.getString("event_date"));
                event.setName(rs.getString("name"));
                event.setParticipant_number(rs.getInt("participant_number"));
                event.setLocation(rs.getString("location"));
                event.setHost_club_name(rs.getString("host_club_name"));
            }

            con.close();
            return Optional.of(event);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public void deleteEvent(Integer id){

        String sql = "delete from event where id = ?";

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

    public void updateEvent(Event event) {
        String sql = "update event set description=?, event_date=?, " +
                "name=?, participant_number=?, location=?, host_club_name=? where id=?";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setString(1, event.getDescription());
            pstmt.setString(2, event.getEvent_date());
            pstmt.setString(3, event.getName());
            pstmt.setInt(4, event.getParticipant_number());
            pstmt.setString(5, event.getLocation());
            pstmt.setString(6, event.getHost_club_name());
            pstmt.setInt(7, event.getId());

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
