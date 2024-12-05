package hello.term_project.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Insert {

    public static void insert(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://192.168.56.101:4567/db_term_project",
                    "jooyehoon", "ckgusdk1234");
            Statement stmt = con.createStatement();
            stmt.executeUpdate("insert into club " +
                    "(name, description, create_date, member_number, advisor_id, president_id) " +
                    "values ('sammaru', '...', '2001-1-1', 40, 123, '2020039051')");

            con.close();
        }catch(Exception e){ System.out.println(e);}
    }
}
